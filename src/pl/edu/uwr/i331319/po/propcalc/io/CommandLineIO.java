package pl.edu.uwr.i331319.po.propcalc.io;

import java.util.LinkedList;

import pl.edu.uwr.i331319.po.propcalc.formula.Clause;

public class CommandLineIO {
	public static void printClauseTree(Clause c) {
		LinkedList<String> message = new LinkedList<String>();
		LinkedList<Clause> queue = new LinkedList<Clause>();
		queue.offer(c);
		
		while (!queue.isEmpty()) {
			Clause current = queue.poll();
			message.addFirst(current.toString());
			
			if (current.left != null) queue.offer(current.left);
			if (current.right != null) queue.offer(current.right);
		}
		
		for (String s : message) 
			System.out.println(s);
	}
}
