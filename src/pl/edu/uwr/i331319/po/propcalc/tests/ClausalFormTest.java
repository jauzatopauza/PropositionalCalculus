package pl.edu.uwr.i331319.po.propcalc.tests;

import pl.edu.uwr.i331319.po.propcalc.formula.*;
import java.util.HashSet;

public class ClausalFormTest {

	public static void main(String[] args) {
		/* (p and q and r) or (r and s) */
		Formula phi = new Disjunction(
				new Conjunction(
						new Variable("p"),
						new Conjunction(
								new Variable("q"),
								new Variable("r"))),
				new Conjunction(
						new Variable("r"),
						new Variable("s")));
		HashSet<Clause> phiClauses = phi.toCNF().toClausalForm();
		
		/* CNF: (p or r) and (p or s) and (q or r) and (q or s) and (r or r) and (r or s) */
		/* Postać klauzalna: [[r], [p, r], [p, s], [q, r], [q, s], [r, s]] */
		System.out.println(phiClauses);
		
		/* not (p or q) or r */
		Formula psi = new Disjunction(
							new Negation(new Disjunction(
									new Variable("p"),
									new Variable("q"))),
							new Variable("r"));
		HashSet<Clause> psiClauses = psi.toCNF().toClausalForm();
		
		/* CNF: (not p or r) and (not q or r) */
		/* Postać klauzalna: [[r, not p], [r, not q]] */
		System.out.println(psiClauses);
		System.out.println(psi.toClausalForm());
	}

}
