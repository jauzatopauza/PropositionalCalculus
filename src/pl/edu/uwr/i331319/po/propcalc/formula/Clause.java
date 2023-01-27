package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

/** Reprezentuje klauzulę, być może będącą rezolwentą innych klauzul, tworząc z nimi drzewo. */
public class Clause {
	/** Lewy rodzic */
	public Clause left;
	/** Prawy rodzic */
	public Clause right;
	/** Zbiór literałów	*/
	public HashSet<Literal> literals;
	/** Informacja, czy klauzula została już wypisana w dowodzie */
	public boolean seen;
	
	/** Tworzy nową klauzulę złożoną z literałów <code>literals</code>,
	 *  będącą rezolwentą klauzul <code>leftParent</code> i <code>rightParent</code>. */
	public Clause(HashSet<Literal> literals, Clause leftParent, Clause rightParent) {
		this.literals = literals;
		left = leftParent;
		right = rightParent;
		seen = false;
	}
	
	/** 
	 * Tworzy nową klauzulę złożoną z literałów <code>literals</code>,
	 * niebędącą rezolwentą żadnych innych klauzul.
	 * */
	public Clause(HashSet<Literal> literals) {
		this.literals = literals;
		seen = false;
	}
	
	/** Sprawdza, czy klauzula jest pusta. */
	public boolean isEmpty() {
		return literals.isEmpty();
	}
	
	/** Sprawdza, czy klauzula jest tautologiczna, czyli czy zawiera komplementarne literały. */
	public boolean isTautology() {
		boolean res = false;
		for (Literal l : literals) 
			if (literals.contains(new Literal(l.getName(), !l.getPolarity())))
				res = true;
		
		return res;
	}
	
	/** Zwraca kod mieszający, wykorzystywany przy sprowadzaniu formuły do postaci klauzalnej. */
	@Override
	public int hashCode() {
		int res = 17;
		res = 37 * res + literals.hashCode();
		return res;
	}
	
	/** 
	 * Sprawdza, czy klauzula jest równa obiektowi <code>o</code>.
	 * Dzieje się tak wtedy i tyko wtedy, gdy <code>o</code> jest klauzulą
	 * o takim samym (według <code>equals()</code>) zbiorze literałów.
	 * */
	@Override
	public boolean equals(Object o) {
		return o instanceof Clause
				&& ((Clause) o).literals.equals(literals);
	}
	/** 
	 * Zwraca reprezentację klauzuli jako łańcucha znaków.
	 * Jeśli nie jest ona rezolwentą żadnych klauzul, jest to po prostu <code>literals.toString()</code>.
	 * W przeciwnym przypadku dołączana jest informacja o tym, jakie literały zawierali rodzice.
	 * */
	@Override
	public String toString() {
		if (left != null && right != null) 
			return literals.toString() + "\t res " + left.literals.toString() + ", " + right.literals.toString();
		return literals.toString();
	}
}
