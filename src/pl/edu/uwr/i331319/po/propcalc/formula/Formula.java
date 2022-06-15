package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.Hashtable;

public abstract class Formula {
	public static final Hashtable<String, Integer> priorities = new Hashtable<String, Integer>();
	
	static {
		priorities.put("or", 1);
		priorities.put("and", 2);
		priorities.put("not", 3);
	}
	
	/* Sprowadzenie do negacyjnej postaci normalnej. */
	public abstract Formula toNNF();	
	
	/* Teoretycznie funkcja pomocnicza dla toNNF(),
	 * sprowadzaj¹ca do zaprzeczonego NNF-u.
	 * Mo¿e siê okazaæ bardziej pomocna ni¿ toNNF()! */
	public abstract Formula toNegNNF();	
	
	/* Sprowadzenie do koniunkcyjnej postaci normalnej. */
	public abstract Formula toCNF();
	
	public abstract String getName();
	
	public abstract String toString();
	
	public boolean isStrongerThan(Formula phi) {
		if (priorities.containsKey(phi.getName()))
			return priorities.get(this.getName()) > priorities.get(phi.getName());
		return false;
	}
}
