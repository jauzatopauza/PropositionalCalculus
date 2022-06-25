package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;
import java.util.HashMap;

public abstract class Formula {
	public static final HashMap<String, Integer> priorities = new HashMap<String, Integer>();
	
	static {
		priorities.put("or", 1);
		priorities.put("and", 2);
		priorities.put("not", 3);
	}
	
	/* Sprowadzenie do negacyjnej postaci normalnej. */
	public abstract Formula toNNF();	
	
	/* Sprowadzenie do zaprzeczonej negacyjnej postaci normalnej. 
	 * Wzajemna rekursja z toNNF(). */
	public abstract Formula toNegNNF();	
	
	/* Sprowadzenie do koniunkcyjnej postaci normalnej. */
	public abstract Formula toCNF();
	
	public abstract String getName();
	
	public abstract String toString();
	
	/* Sprowadzenie do postaci klauzalnej, czyli zbioru klauzul,
	 * które to z kolei reprezentują zbiory literałów. */
	public abstract HashSet<Clause> toClausalForm();
	
	public boolean isStrongerThan(Formula phi) {
		if (priorities.containsKey(phi.getName()))
			return priorities.get(this.getName()) > priorities.get(phi.getName());
		return false;
	}
	
	public static boolean isStrongerThan(String op1, String op2) {
		if (priorities.containsKey(op1) && priorities.containsKey(op2))
			return priorities.get(op1) > priorities.get(op2);
		return false;
	}
}
