package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

/** Reprezentuje zmienną zdaniową. */
public class Variable extends Formula {
	private final String name;
	
	/** 
	 * Tworzy nową zmienną zdaniową o podanej nazwie.
	 * @param name Nazwa zmiennej
	 * */
	public Variable(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Formula toNNF() {
		return new Variable(name);
	}
	
	@Override
	public Formula toNegNNF() {
		return new Negation(new Variable(name));
	}

	@Override
	public Formula toCNF() {
		return new Variable(name);
	}
	
	public String toString() {
		return name;
	}
	
	@Override
	public HashSet<Clause> toClausalForm() {
		HashSet<Literal> aux = new HashSet<Literal>();
		aux.add(new Literal(name, true));
		HashSet<Clause> res = new HashSet<Clause>();
		res.add(new Clause(aux));
		return res;
	}

}
