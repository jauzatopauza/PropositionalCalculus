package pl.edu.uwr.i331319.po.propcalc;

import java.io.IOException;

import pl.edu.uwr.i331319.po.propcalc.formula.Clause;
import pl.edu.uwr.i331319.po.propcalc.formula.Formula;
import pl.edu.uwr.i331319.po.propcalc.io.CommandLineIO;
import pl.edu.uwr.i331319.po.propcalc.io.Parser;
import pl.edu.uwr.i331319.po.propcalc.resolution.Resolution;

public class PropositionalCalculus {

	public static void main(String[] args) throws IOException {
		CommandLineIO clio = new CommandLineIO();
		CommandLineIO.printWelcomeMessage();
		Formula phi = null;
		String s;
		boolean running = true;
		
		while (running) {
			if (phi != null) { 
				s = clio.promptRead();
				if (s.equals("q"))
					running = false;
				else if (s.equals("help"))
					clio.printHelpMessage();
				else if (s.equals("taut?")) {
					Clause res = Resolution.checkTautology(phi);
					System.out.println(res.isEmpty());
					if (res.isEmpty()) {
						System.out.println("Proof: ");
						CommandLineIO.printClauseTree(res);
					}
				} else if (s.equals("contr?")) {
					Clause res = Resolution.checkContradiction(phi);
					System.out.println(res.isEmpty());
					if (res.isEmpty()) {
						System.out.println("Proof: ");
						CommandLineIO.printClauseTree(res);
					}
				} else if (s.equals("cnf")) 
					System.out.println(phi.toNNF().toCNF());
				else if (s.equals("nnf"))
					System.out.println(phi.toNNF());
				else if (s.equals("clauses"))
					System.out.println(phi.toNNF().toCNF().toClausalForm());
				else if (s.equals("negclauses"))
					System.out.println(phi.toNegNNF().toCNF().toClausalForm());
				else if (s.equals("new"))
					phi = null;
			} else {
				s = clio.promptReadFormula();
				if (s.equals("q"))
					running = false;
				else if (s.equals("help"))
					clio.printHelpMessage();
				else {
					phi = Parser.parse(Parser.tokenize(s));
					if (phi == null) CommandLineIO.printErrorMessage();
				}
			}
		}
	}

}
