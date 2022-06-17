package pl.edu.uwr.i331319.po.propcalc.formula;

import java.util.HashSet;

public class Clause {
	public HashSet<Literal> literals;
	
	/* Wkrótce rozszerzymy klauzulę o pamiętanie swoich rodziców (ale może ich nie być!). */
	public Clause(HashSet<Literal> literals) {
		this.literals = literals;
	}
	
	public boolean isEmpty() {
		return literals.isEmpty();
	}
	
	public boolean isTautology() {
		//TODO: Dla każdego literału sprawdzić, czy zbiór zawiera literał komplementarny.
		return false;
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
		return literals.toString();
	}
}
