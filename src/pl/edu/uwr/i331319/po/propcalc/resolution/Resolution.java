package pl.edu.uwr.i331319.po.propcalc.resolution;

import java.util.HashSet;
import java.util.LinkedList;

import pl.edu.uwr.i331319.po.propcalc.formula.*;

public class Resolution {
	/* Docelowo ma zwracać uzyskaną klauzulę pustą w razie odpowiedzi
	 * pozytywnej lub dowolną klauzulę niepustą w razie odpowiedzi
	 * negatywnej. */
	public static boolean checkTautology(Formula phi) {
		LinkedList<Clause> usableClauses = new LinkedList<Clause>(phi.toNegNNF().toCNF().toClausalForm());
		LinkedList<Clause> usedClauses = new LinkedList<Clause>();

		while(!usableClauses.isEmpty()) {
			Clause c = usableClauses.poll();
			
			boolean subsumed = false;
			for (Clause usedClause : usedClauses) {
				if (c.literals.containsAll(usedClause.literals)) {
					subsumed = true;
					break;
				}
			} // kuchnia włoska, może da się to ładniej przerobić
			if (subsumed) continue;
			
			for (Clause usedClause : usedClauses) {
				for (Literal l1 : c.literals) {
					for (Literal l2 : usedClause.literals) {
						if (l1.isComplementaryTo(l2)) {
							Clause resolvent = resolve(c, usedClause, l1, l2);
							if (resolvent.isEmpty()) return true;
							if (!resolvent.isTautology())
								usableClauses.add(resolvent);
						}
					}
				}
			}
			usedClauses.add(c);
		}
		
		return false;
	}
	
	private static Clause resolve(Clause c1, Clause c2, Literal l1, Literal l2) {
		HashSet<Literal> resSet = new HashSet<Literal>();
		resSet.addAll(c1.literals);
		resSet.addAll(c2.literals);
		resSet.remove(l1);
		resSet.remove(l2);
		return new Clause(resSet);
	}
}
