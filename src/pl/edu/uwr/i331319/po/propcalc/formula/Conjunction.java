package pl.edu.uwr.i331319.po.propcalc.formula;

public class Conjunction extends Formula {
	private Formula left;
	private Formula right;
	private static final String name = "and";
	
	public Conjunction(Formula left, Formula right) {
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

}
