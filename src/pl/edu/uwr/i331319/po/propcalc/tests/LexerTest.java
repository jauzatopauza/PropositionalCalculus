package pl.edu.uwr.i331319.po.propcalc.tests;

import pl.edu.uwr.i331319.po.propcalc.io.Parser;

public class LexerTest {

	public static void main(String[] args) {
		String[] strings = {"not (not p or q) or (not not q or not p)", "not (not p or q) or (not q or p)", "twoja((stara) zapierdala;; ;;. !"};
		for (String s : strings) 
			System.out.println(Parser.tokenize(s));
	}

}
