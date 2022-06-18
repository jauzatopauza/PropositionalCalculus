package pl.edu.uwr.i331319.po.propcalc.tests;

import pl.edu.uwr.i331319.po.propcalc.formula.*;
import pl.edu.uwr.i331319.po.propcalc.io.CommandLineIO;
import pl.edu.uwr.i331319.po.propcalc.resolution.Resolution;

public class ResolutionTest {

	public static void main(String[] args) {
		Formula[] formulae = new Formula[5];
		
		/* Prawo kontrapozycji: not (not p or q) or (not not q or not p) */
		formulae[0] = new Disjunction(new Negation(new Disjunction(new Negation(new Variable("p")),
																			new Variable("q"))),
												new Disjunction(new Negation(new Negation(new Variable("q"))),
																new Negation(new Variable("p"))));
		/* Nietautologia: not (not p or q) or (not q or p) */
		formulae[1] = new Disjunction(new Negation(new Disjunction(new Negation(new Variable("p")),
																			new Variable("q"))),
												new Disjunction(new Negation(new Variable("q")),
																new Variable("p")));
		
		/* Prawo wyłączonego środka: p or not p */
		formulae[2] = new Disjunction(new Variable("p"), new Negation(new Variable("p")));
		
		/* Prawo niesprzeczności: not (p and not p) */
		formulae[3] = new Negation(new Conjunction(new Variable("p"), new Negation(new Variable("p"))));
		
		/* Nietautologia: p and q and r or not p and not q and not r */
		formulae[4] = new Disjunction(new Conjunction(new Variable("p"), 
																new Conjunction(new Variable("q"), 
																				new Variable("r"))),
												new Conjunction(new Negation(new Variable("p")),
														new Conjunction(new Negation(new Variable("q")),
																new Negation(new Variable("r")))));
		for (Formula phi : formulae) {
			System.out.println(phi.toString());
			Clause r = Resolution.checkTautology(phi);
			boolean ans = r.isEmpty();
			System.out.println("tautology? " + ans);
			if (ans) {
				System.out.println("Proof: ");
				CommandLineIO.printClauseTree(r);
			}
			System.out.println();
		}
	}

}
