package pl.edu.uwr.i331319.po.propcalc.resolution;

import java.util.HashSet;
import java.util.LinkedList;

import pl.edu.uwr.i331319.po.propcalc.formula.*;

/** 
 * Implementuje prosty algorytm rezolucji.
 * */
public class Resolution {
	
	/** 
	 * Sprawdza, czy klauzula zwrócona przez <code>checkTautology(phi)</code> jest pusta.
	 * @see pl.edu.uwr.i331319.po.propcalc.resolution.Resolution#checkTautology checkTautology
	 * */
	public static boolean isTautology(Formula phi) {
		return checkTautology(phi).isEmpty();
	}
	
	/** 
	 * Sprawdza, czy klauzula zwrócona przez <code>checkContradiction(phi)</code> jest pusta.
	 * @see pl.edu.uwr.i331319.po.propcalc.resolution.Resolution#checkContradiction checkContradiction
	 * */
	public static boolean isContradiction(Formula phi) {
		return checkContradiction(phi).isEmpty();
	}
	
	/** 
	 * Zwraca klauzulę pustą, jeśli formuła <code>phi</code> jest tautologią.
	 * W przeciwnym razie zwraca dowolną niepustą klauzulę.
	 * */
	public static Clause checkTautology(Formula phi) {
		return applyResolution(phi.toNegNNF());
	}
	
	/** 
	 * Zwraca klauzulę pustą, jeśli formuła <code>phi</code> jest sprzeczna.
	 * W przeciwnym razie zwraca dowolną niepustą klauzulę.
	 * */
	public static Clause checkContradiction(Formula phi) {
		return applyResolution(phi.toNNF());
	}
	
	private static Clause applyResolution(Formula phi) {
		LinkedList<Clause> usableClauses = new LinkedList<Clause>(phi.toCNF().toClausalForm());
		LinkedList<Clause> usedClauses = new LinkedList<Clause>();

		while(!usableClauses.isEmpty()) {
			Clause c = usableClauses.poll();
			
			boolean subsumed = false;
			for (Clause usedClause : usedClauses) {
				if (c.literals.containsAll(usedClause.literals)) {
					subsumed = true;
					break;
				}
			} 
			if (subsumed) continue;
			
			for (Clause usedClause : usedClauses) {
				for (Literal l1 : c.literals) {
					for (Literal l2 : usedClause.literals) {
						if (l1.isComplementaryTo(l2)) {
							Clause resolvent = resolve(c, usedClause, l1, l2);
							if (resolvent.isEmpty()) return resolvent;
							if (!resolvent.isTautology())
								usableClauses.add(resolvent);
						}
					}
				}
			}
			usedClauses.add(c);
		}
		
		return usedClauses.getFirst();
	}
	
	private static Clause resolve(Clause c1, Clause c2, Literal l1, Literal l2) {
		HashSet<Literal> c1temp = new HashSet<Literal>(c1.literals);
		c1temp.remove(l1);
		HashSet<Literal> c2temp = new HashSet<Literal>(c2.literals);
		c2temp.remove(l2);
		HashSet<Literal> resSet = new HashSet<Literal>();
		resSet.addAll(c1temp);
		resSet.addAll(c2temp);
		return new Clause(resSet, c1, c2);
	}
}
