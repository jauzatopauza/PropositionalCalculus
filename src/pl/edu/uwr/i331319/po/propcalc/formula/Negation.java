package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

public class Negation extends Formula {
	private Formula child;
	private static final String name = "not";
	
	public Negation(Formula arg) {
		this.child = arg;
	}
	
	public String getName() {
		return name;
	}
	
	public Formula getChild() {
		return child;
	}
	
	
	@Override
	public Formula toNNF() {
		return child.toNegNNF();
	}
	
	@Override
	public Formula toNegNNF() {
		return child.toNNF();
	}

	@Override
	public Formula toCNF() {
		if (child instanceof Variable) return new Negation(child);
		else return child.toNegNNF().toCNF();
	}
	
	public String toString() {
		String s;
		if(isStrongerThan(child)) s = "(" + child.toString() + ")";
		else s = child.toString();
		return name + " " + s;
	}
	
	@Override
	public HashSet<Clause> toClausalForm() {
		if (!(child instanceof Variable))
			return toCNF().toClausalForm();
		
		HashSet<Literal> aux = new HashSet<Literal>();
		aux.add(new Literal(child.getName(), false));
		HashSet<Clause> res = new HashSet<Clause>();
		res.add(new Clause(aux));
		return res;
	}

}
