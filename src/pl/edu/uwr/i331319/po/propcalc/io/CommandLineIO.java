package pl.edu.uwr.i331319.po.propcalc.io;

import java.io.*;
import java.util.LinkedList;

import pl.edu.uwr.i331319.po.propcalc.formula.Clause;

public class CommandLineIO {
	private String helpMessage;
	private BufferedReader stdin;
	
	public CommandLineIO() {
		helpMessage = readHelpMessage();
		stdin = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private String readHelpMessage() {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("res/helpmsg.txt")));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return  sb.toString();
	}
	
	public void printHelpMessage() {
		System.out.print(helpMessage);
	}
	
	public static void printClauseTree(Clause c) {
		LinkedList<String> message = new LinkedList<String>();
		LinkedList<Clause> queue = new LinkedList<Clause>();
		queue.offer(c);
		
		while (!queue.isEmpty()) {
			Clause current = queue.poll();
			if (!current.seen) {
				message.addFirst(current.toString());
				current.seen = true;
			}
			
			if (current.left != null) queue.offer(current.left);
			if (current.right != null) queue.offer(current.right);
		}
		
		for (String s : message) 
			System.out.println(s);
	}
	
	public static void printWelcomeMessage() {
		System.out.println("Propositional Calculus. Use 'help' for help, 'q' to quit.");
	}
	
	public String promptReadFormula() throws IOException {
		System.out.println("Enter propositional formula.");
		return promptRead();
	}
	
	public String promptRead() throws IOException {
		System.out.print("propcalc> ");
		return stdin.readLine();
	}
	
	public static void printErrorMessage() {
		System.out.println("Syntax error.");
	}
	
}
