package pl.edu.uwr.i331319.po.propcalc.tests;

import pl.edu.uwr.i331319.po.propcalc.formula.Formula;
import pl.edu.uwr.i331319.po.propcalc.io.Parser;

public class ParserTest {

	public static void main(String[] args) {
		
		String[] strings = {"not (not p or q) or (not not q or not p)", "not (not p or q) or (not q or p)", "((p or", "p q r", "p q not and r or"};
		for (String s : strings) {
			Formula phi = Parser.parse(Parser.tokenize(s));
			if (phi == null)
				System.out.println("Syntax error.");
			else
				System.out.println(phi.toString());
		}
		
	}

}
