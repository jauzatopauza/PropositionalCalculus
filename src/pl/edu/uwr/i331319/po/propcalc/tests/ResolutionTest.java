package pl.edu.uwr.i331319.po.propcalc.tests;

import pl.edu.uwr.i331319.po.propcalc.formula.*;
import pl.edu.uwr.i331319.po.propcalc.resolution.Resolution;

public class ResolutionTest {

	public static void main(String[] args) {
		/* Prawo kontrapozycji: not (not p or q) or (not not q or not p) */
		Formula contraposition = new Disjunction(new Negation(new Disjunction(new Negation(new Variable("p")),
																			new Variable("q"))),
												new Disjunction(new Negation(new Negation(new Variable("q"))),
																new Negation(new Variable("p"))));
		System.out.println(Resolution.checkTautology(contraposition));
		
		/* Nietautologia: not (not p or q) or (not q or p) */
		Formula satisfiable = new Disjunction(new Negation(new Disjunction(new Negation(new Variable("p")),
																			new Variable("q"))),
												new Disjunction(new Negation(new Variable("q")),
																new Variable("p")));
		System.out.println(Resolution.checkTautology(satisfiable));
		
		/* Prawo wyłączonego środka: p or not p */
		Formula tertiumNonDatur = new Disjunction(new Variable("p"), new Negation(new Variable("p")));
		System.out.println(Resolution.checkTautology(tertiumNonDatur));
		
		/* Prawo niesprzeczności: not (p and not p) */
		Formula noncontradiction = new Negation(new Conjunction(new Variable("p"), new Negation(new Variable("p"))));
		System.out.println(Resolution.checkTautology(noncontradiction));
		
		/* Nietautologia: p and q and r or not p and not q and not r */
		Formula satisfiable2 = new Disjunction(new Conjunction(new Variable("p"), 
																new Conjunction(new Variable("q"), 
																				new Variable("r"))),
												new Conjunction(new Negation(new Variable("p")),
														new Conjunction(new Negation(new Variable("q")),
																new Negation(new Variable("r")))));
		System.out.println(Resolution.checkTautology(satisfiable2));
	}

}
