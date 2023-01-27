package pl.edu.uwr.i331319.po.propcalc.io;

import java.util.LinkedList;

import pl.edu.uwr.i331319.po.propcalc.formula.*;

/** 
 * Odpowiada za parsowanie łańcuchów znaków reprezentujących formuły logiczne.
 * */
public class Parser {
	/** 
	 * Parsuje listę tokenów, wykorzystując algorytm stacji rozrządowej.
	 * Zwrócenie wartości <code>null</code> oznacza błąd składniowy.
	 * Niektóre błędne formuły parsowane są bez zgłoszenia błędu,
	 * wynik może być zaskakujący.
	 * @param toks Lista tokenów
	 * @return Abstrakcyjne drzewo rozbioru
	 * */
	public static Formula parse(LinkedList<String> toks) {
		LinkedList<Formula> operands = new LinkedList<Formula>();
		LinkedList<String> operators = new LinkedList<String>();
		
		for (String token : toks) {
			if (token.equals("("))
				operators.push(token);
			else if (token.equals(")")) {
				String top;
				boolean found = false;
				while (!operators.isEmpty() && !found) {
					top = operators.pop();
					if (top.equals("("))
						found = true;
					else if (!pushNode(operands, top)) // znalazł jeden prosty trik, by zmodyfikowa� stan programu!
						return null;
				}
				if (!found) return null; // niedopasowany nawias zamykający
			} else if (Formula.priorities.containsKey(token)) {
				while (!operators.isEmpty() 
						&& !operators.peek().equals("(") 
						&& Formula.isStrongerThan(operators.peek(), token)) 
					if (!pushNode(operands, operators.pop()))
						return null;
				operators.push(token);
			} else
				operands.push(new Variable(token));
		}
		
		while (!operators.isEmpty()) {
			String op = operators.pop();
			if (op.equals("(")) return null; // niedopasowany nawias otwierający
			else if (!pushNode(operands, op))
				return null;
		}
		if (operands.size() == 1)
			return operands.pop();
		else return null; // nie ma operatorów
	}
	
	
	private static boolean pushNode(LinkedList<Formula> stack, String op) {
		Formula left = stack.pop();
		if (op.equals("not")) {
			stack.push(new Negation(left)); // operator unarny
			return true;
		} else {
			if (stack.isEmpty()) return false; // arity mismatch
			Formula right = stack.pop(); // operator binarny
			if (op.equals("and")) 
				stack.push(new Conjunction(right, left));
			else if (op.equals("or"))
				stack.push(new Disjunction(right, left));
			return true;
		}
	}
	
	/** 
	 * Wydziela tokeny z łańcucha znaków.
	 * @param s Łańcuch znaków
	 * @return Lista tokenów
	 * */
	public static LinkedList<String> tokenize(String s) {
		LinkedList<String> res = new LinkedList<String>();
		int wordBeginning = 0;
		boolean word = false;
		for (int i = 0, n = s.length(); i < n; i++) {
			if (Character.isWhitespace(s.charAt(i))) {
				if (word) 
					res.add(s.substring(wordBeginning, i));
				wordBeginning = i + 1;
				word = false;
			} else if (s.charAt(i) == '(' || s.charAt(i) == ')') {
				if (word) 
					res.add(s.substring(wordBeginning, i));
				wordBeginning = i + 1;
				word = false;
				res.add(s.substring(i, i + 1));
			} else 
				word = true;
		}
		if (word)
			res.add(s.substring(wordBeginning));
		return res;
	}
}
