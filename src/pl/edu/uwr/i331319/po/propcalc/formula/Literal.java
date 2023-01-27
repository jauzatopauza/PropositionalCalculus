package pl.edu.uwr.i331319.po.propcalc.formula;

/** Reprezentuje literał mogcy być częścią klauzuli. */
public class Literal {
	private String name;
	private boolean polarity;
	
	/** 
	 * Tworzy nowy literał o podanej nazwie i polarności.
	 * @param name Nazwa literału
	 * @param polarity Polarność literału: <code>true</code> - zmienna niezanegowana, <code>false</code> - zmienna zanegowana.
	 * */
	public Literal(String name, boolean polarity) {
		this.name = name;
		this.polarity = polarity;
	}
	
	/** Zwraca nazwę literału. */
	public String getName() {
		return name;
	}
	
	/** Zwraca polarność literału. */
	public boolean getPolarity() {
		return polarity;
	}
	
	/** Sprawdza, czy literał jest komplementarny do literału <code>l</code>, tzn. czy różni się tylko polarnością. */
	public boolean isComplementaryTo(Literal l) {
		return l.name.equals(name) && (l.polarity ^ polarity);
	}
	
	/** Zwraca kod mieszający wykorzystywany przez {@link pl.edu.uwr.i331319.po.propcalc.formula.Clause Clause}. */
	@Override
	public int hashCode() {
		int res = 17;
		res = 37 * res + (polarity ? 0 : 1);
		res = 37 * res + name.hashCode();
		return res;
	}
	/** 
	 * Sprawdza, czy literał jest równy obiektowi <code>o</code>.
	 * Dzieje się tak wtedy i tylko wtedy, gdy <code>o</code> jest literałem,
	 * ma tę samą nazwę i tę samą polarność.
	 * */
	@Override
	public boolean equals(Object o) {
		return o instanceof Literal
				&& ((Literal) o).getName().equals(name) 
				&& ((Literal) o).getPolarity() == polarity;
	}
	
	/** 
	 * Zwraca reprezentację literału jako łańcucha znaków.
	 * W przypadku niezanegowanej zmiennej jest to po prostu jej nazwa,
	 * w przypadku zmiennej zanegowanej będzie stało przed jej nazwą słowo <code>not</code>.
	 * */
	@Override
	public String toString() {
		if (polarity) return name;
		else return "not " + name;
	}
}
