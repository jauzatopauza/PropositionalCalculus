package pl.edu.uwr.i331319.po.propcalc.resolution;

import java.util.HashSet;
import java.util.LinkedList;

import pl.edu.uwr.i331319.po.propcalc.formula.*;

public class Resolution {
	
	public static boolean isTautology(Formula phi) {
		return checkTautology(phi).isEmpty();
	}
	
	public static boolean isContradiction(Formula phi) {
		return checkContradiction(phi).isEmpty();
	}
	
	public static Clause checkTautology(Formula phi) {
		return applyResolution(phi.toNegNNF());
	}
	
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
			} // kuchnia włoska, może da się to ładniej przerobić
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
		HashSet<Literal> resSet = new HashSet<Literal>();
		resSet.addAll(c1.literals);
		resSet.addAll(c2.literals);
		resSet.remove(l1);
		resSet.remove(l2);
		return new Clause(resSet, c1, c2);
	}
}
