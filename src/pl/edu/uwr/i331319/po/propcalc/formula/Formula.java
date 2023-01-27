package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;
import java.util.HashMap;

/** 
 * Reprezentuje formułę logiczną.
 * */
public abstract class Formula {
	/** 
	 * Odwzorowanie mieszające zawierające informacje o priorytetach spójników
	 * */
	public static final HashMap<String, Integer> priorities = new HashMap<String, Integer>();
	
	static {
		priorities.put("or", 1);
		priorities.put("and", 2);
		priorities.put("not", 3);
	}
	
	/** Zwraca równoważną formułę w negacyjnej postaci normalnej. */
	public abstract Formula toNNF();	
	
	/** Zwraca formułę w negacyjnej postaci normalnej równoważną zaprzeczeniu danej.  */
	public abstract Formula toNegNNF();	
	
	/** Zwraca równoważną formułę w koniunkcyjnej postaci normalnej. */
	public abstract Formula toCNF();
	
	/** Zwraca łańcuch znaków z nazwa formuły. Spójniki mają ustalone nazwy, 
	 * nazwy zmiennych odczytuje parser. */
	public abstract String getName();
	
	/** 
	 * Zwraca łańcuch znaków reprezentujący formułę w składni konkretnej.
	 * */
	public abstract String toString();
	
	/** Sprowadza formułę do postaci klauzalnej, czyli zbioru klauzul,
	 * które z kolei reprezentują zbiory literałów. */
	public abstract HashSet<Clause> toClausalForm();
	
	/** 
	 * Sprawdza, czy formuła jest silniejsza od <code>phi</code>, tj. czy ma w węźle
	 * silniej wiążący spójnik.
	 * */
	public boolean isStrongerThan(Formula phi) {
		if (priorities.containsKey(phi.getName()))
			return priorities.get(this.getName()) > priorities.get(phi.getName());
		return false;
	}
	
	/** 
	 * Sprawdza, czy <code>op1</code> jest nazwą spójnika wiążącego silniej niż spójnik o nazwie <code>o2</code>.
	 * */
	public static boolean isStrongerThan(String op1, String op2) {
		if (priorities.containsKey(op1) && priorities.containsKey(op2))
			return priorities.get(op1) > priorities.get(op2);
		return false;
	}
}
