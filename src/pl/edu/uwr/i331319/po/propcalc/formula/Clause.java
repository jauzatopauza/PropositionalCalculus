package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

public class Clause {
	public Clause left;
	public Clause right;
	public HashSet<Literal> literals;
	public boolean seen;
	
	public Clause(HashSet<Literal> literals, Clause leftParent, Clause rightParent) {
		this.literals = literals;
		left = leftParent;
		right = rightParent;
		seen = false;
	}
	
	public Clause(HashSet<Literal> literals) {
		this.literals = literals;
	}
	
	public boolean isEmpty() {
		return literals.isEmpty();
	}
	
	public boolean isTautology() {
		boolean res = false;
		for (Literal l : literals) 
			if (literals.contains(new Literal(l.getName(), !l.getPolarity())))
				res = true;
		
		return res;
	}
	
	@Override
	public int hashCode() {
		int res = 17;
		res = 37 * res + literals.hashCode();
		return res;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Clause
				&& ((Clause) o).literals.equals(literals);
	}
	
	@Override
	public String toString() {
		if (left != null && right != null) 
			return literals.toString() + "\t res " + left.literals.toString() + ", " + right.literals.toString();
		return literals.toString();
	}
}
