package pl.edu.uwr.i331319.po.propcalc.io;

import java.io.*;
import java.util.LinkedList;

import pl.edu.uwr.i331319.po.propcalc.formula.Clause;

/** 
 * Interfejs do komunikacji z użytkownikiem w terminalu.
 * */
public class CommandLineIO {
	private String helpMessage;
	private BufferedReader stdin;
	
	/** 
	 * Tworzy nowy obiekt CommandLineIO, wczytując wiadomość helpmsg.txt z pliku
	 * oraz przygotowując strumień do odczytu wejścia.
	 * */
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
	
	/** 
	 * Wypisuje wiadomość z pomocą.
	 * */
	public void printHelpMessage() {
		System.out.print(helpMessage);
	}
	
	/** 
	 * Wypisuje drzewo opisujące wyprowadzenie klauzuli.
	 * Każdy węzeł drzewa wypisywany jest tylko raz. Służy za notację do 
	 * przedstawiania dowodów rezolucyjnych.
	 * */
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
	
	/** 
	 * Wypisuje wiadomość powitalną. 
	 * */
	public static void printWelcomeMessage() {
		System.out.println("Propositional Calculus. Use 'help' for help, 'q' to quit.");
	}
	
	/** 
	 * Wypisuje wiadomość z prośbą o wprowadzenie formuły, a następnie wywołuje {@link pl.edu.uwr.i331319.po.propcalc.io.CommandLineIO#promptRead() promptRead()}.
	 * @return Wczytany łańcuch
	 * */
	public String promptReadFormula() throws IOException {
		System.out.println("Enter propositional formula.");
		return promptRead();
	}
	
	/** 
	 * Wypisuje zachętę <code>propcalc&gt;</code> i wczytuje łańcuch znaków.
	 * @return Wczytany łańcuch
	 * */
	public String promptRead() throws IOException {
		System.out.print("propcalc> ");
		return stdin.readLine();
	}
	
	/** 
	 * Wypisuje wiadomość o błędzie składniowym.
	 * */
	public static void printErrorMessage() {
		System.out.println("Syntax error.");
	}
	
}
