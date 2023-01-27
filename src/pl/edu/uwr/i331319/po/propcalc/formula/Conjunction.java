package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

/** 
 * Reprezentuje koniunkcję formuł.
 * */
public class Conjunction extends Formula {
	private Formula left;
	private Formula right;
	private static final String name = "and";
	
	/** 
	 * Tworzy nową koniunkcję.
	 * @param left Lewa podformuła
	 * @param right Prawa podformuła
	 * */
	public Conjunction(Formula left, Formula right) {
		this.left = left;
		this.right = right;
	}
	
	public String getName() {
		return name;
	}
	
	/** 
	 * Zwraca lewą podformułę.
	 * */
	public Formula getLeft() {
		return left;
	}
	
	/** 
	 * Zwraca prawą podformułę.
	 * */
	public Formula getRight() {
		return right;
	}
	
	@Override
	public Formula toNNF() {
		return new Conjunction(left.toNNF(), right.toNNF());
	}
	
	@Override
	public Formula toNegNNF() {
		return new Disjunction(left.toNegNNF(), right.toNegNNF());
	}

	@Override
	public Formula toCNF() {
		return new Conjunction(left.toCNF(), right.toCNF());
	}
	
	public String toString() {
		String l, r;
		if (isStrongerThan(left)) l = "(" + left.toString() + ")";
		else l = left.toString();
		if (isStrongerThan(right)) r = "(" + right.toString() + ")";
		else r = right.toString();
		return l + " " + name + " " + r;
	}

	@Override
	public HashSet<Clause> toClausalForm() {
		HashSet<Clause> res = new HashSet<Clause>();
		HashSet<Clause> L = left.toClausalForm();
		HashSet<Clause> R = right.toClausalForm();
		res.addAll(L);
		res.addAll(R);
		return res;
	}

}
