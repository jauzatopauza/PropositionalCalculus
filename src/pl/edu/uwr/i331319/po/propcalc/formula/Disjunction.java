package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

/** 
 * Reprezentuje dysjunkcję formuł.
 * */
public class Disjunction extends Formula {
	private Formula left;
	private Formula right;
	private static final String name = "or";
	
	/** 
	 * Tworzy nową dysjunkcję.
	 * @param left Lewa podformuła
	 * @param right Prawa podformuła
	 * */
	public Disjunction(Formula left, Formula right) {
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
		return new Disjunction(left.toNNF(), right.toNNF());
	}
	
	@Override
	public Formula toNegNNF() {
		return new Conjunction(left.toNegNNF(), right.toNegNNF());
	}

	@Override
	public Formula toCNF() {
		Formula L = left.toCNF();
		Formula R = right.toCNF();
		
		if (L instanceof Conjunction)
			return new Conjunction(
					new Disjunction(((Conjunction) L).getLeft(), R).toCNF(),
					new Disjunction(((Conjunction) L).getRight(), R).toCNF());
		else if (R instanceof Conjunction)
			return new Conjunction(
					new Disjunction(L, ((Conjunction) R).getLeft()).toCNF(),
					new Disjunction(L, ((Conjunction) R).getRight()).toCNF());
		else 
			return new Disjunction(L, R);
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
		HashSet<Clause> L = left.toClausalForm();
		HashSet<Clause> R = right.toClausalForm();
		if (L.size() > 1 || R.size() > 1) 
			return toCNF().toClausalForm(); 
		
		Clause cl = L.iterator().next();
		Clause cr = R.iterator().next();
		HashSet<Clause> res = new HashSet<Clause>();
		HashSet<Literal> aux = new HashSet<Literal>();
		aux.addAll(cl.literals);
		aux.addAll(cr.literals);
		res.add(new Clause(aux));
		return res;
	}

}
