package pl.edu.uwr.i331319.po.propcalc.formula;

public class Variable extends Formula {
	private final String name;
	
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

}
