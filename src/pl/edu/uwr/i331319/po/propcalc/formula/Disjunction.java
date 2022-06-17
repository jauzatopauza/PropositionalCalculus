package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

public class Disjunction extends Formula {
	private Formula left;
	private Formula right;
	private static final String name = "or";
	
	public Disjunction(Formula left, Formula right) {
		this.left = left;
		this.right = right;
	}
	public String getName() {
		return name;
	}
	
	public Formula getLeft() {
		return left;
	}
	
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

	/* Wygląda w chuj radziecko, pewnie się zapętla na amen. */
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
		if (L.size() > 1 || R.size() > 1) //   Jeżeli postać klauzalna któregoś dysjunktu jest sussy,
			return toCNF().toClausalForm(); // to znaczy, że nie jesteśmy jeszcze w CNF-ie!
		
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
