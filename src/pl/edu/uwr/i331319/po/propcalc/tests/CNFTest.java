package pl.edu.uwr.i331319.po.propcalc.tests;

import pl.edu.uwr.i331319.po.propcalc.formula.*;

public class CNFTest {

	public static void main(String[] args) {
		/* not (p or q) or r */
		Formula phi = new Disjunction(
							new Negation(new Disjunction(
									new Variable("p"),
									new Variable("q"))),
							new Variable("r"));
		
		/* (not p or r) and (not q or r) */
		System.out.println(phi.toCNF().toString());
		
		/* (p and q and r) or (r and s) */
		Formula psi = new Disjunction(
							new Conjunction(
									new Variable("p"),
									new Conjunction(
											new Variable("q"),
											new Variable("r"))),
							new Conjunction(
									new Variable("r"),
									new Variable("s")));
		/* (p or r) and (p or s) and (q or r) and (q or s) and (r or r) and (r or s) */
		System.out.println(psi.toCNF().toString());
		
		/* (p or q or r and s) and q */
		Formula rho = new Conjunction(
							new Disjunction(
									new Variable("p"),
									new Disjunction(
											new Variable("q"),
											new Conjunction(
													new Variable("r"),
													new Variable("s")))),
							new Variable("q"));
		/* (p or q or r) and (p or q or s) and q */
		System.out.println(rho.toCNF().toString());
	}

}
