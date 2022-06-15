package pl.edu.uwr.i331319.po.propcalc.formula;

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

	/* Wygl¹da w chuj radziecko, pewnie siê zapêtla na amen. */
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

}
