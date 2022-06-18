package pl.edu.uwr.i331319.po.propcalc.formula;

public class Literal {
	private String name;
	private boolean polarity;
	
	public Literal(String name, boolean polarity) {
		this.name = name;
		this.polarity = polarity;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getPolarity() {
		return polarity;
	}
	
	public boolean isComplementaryTo(Literal l) {
		return l.name.equals(name) && (l.polarity ^ polarity);
	}
	
	@Override
	public int hashCode() {
		int res = 17;
		res = 37 * res + (polarity ? 0 : 1);
		res = 37 * res + name.hashCode();
		return res;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Literal
				&& ((Literal) o).getName().equals(name) 
				&& ((Literal) o).getPolarity() == polarity;
	}
	
	@Override
	public String toString() {
		if (polarity) return name;
		else return "not " + name;
	}
}
