package pl.edu.uwr.i331319.po.propcalc.tests;

import pl.edu.uwr.i331319.po.propcalc.formula.*;

public class NNFTest {

	public static void main(String[] args) {
		
		Formula phi = new Disjunction(new Negation(new Negation(new Variable("p"))), new Negation(new Variable("p")));
		/* not not p or not p */
		System.out.println(phi.toString());
		/* p or not p */
		System.out.println(phi.toNNF().toString());
		/* not p and p */
		System.out.println(phi.toNegNNF().toString());
		
		System.out.println();
		
		Formula psi = new Negation(new Conjunction(new Variable("p"), new Disjunction(new Variable("q"), new Variable("p"))));
		/* not (p and (q or p)) */
		System.out.println(psi.toString());
		/* not p or not q and not p */
		System.out.println(psi.toNNF().toString());
		/* p and (q or p) */
		System.out.println(psi.toNegNNF().toString());
	}

}
