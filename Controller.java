import java.util.Scanner;
import java.util.Random;


public class Controller {
	
	Scanner in = new Scanner (System.in);
	Graph_start g;
	Graph_start random;
	Random randomgenerator = new Random();
	
	public void run() {
		while (true) {
			printMainInfo();
			boolean end = false, ok, equal, back = false;
			int choice = in.nextInt();
			switch (choice) {
			case 1:
				while (!back) {
					g = new Graph_start (100);
					printInfo1();
					choice = in.nextInt();
					switch (choice) {
					case 1:
						while (!end) {
							String s = nameNode();
							ok = g.controllNode(s);
							if (ok == true) {
								g.newNode(s);
								end = true;
							}
							else 
								System.out.println("Der Knoten existiert bereits. Bitte wählen Sie einen anderen Namen!");
						}
						break;
					case 2:
						String r = "", t = "";
						while (!end) {
							System.out.println("Startknoten: ");
							r = chooseNode();
							System.out.println("Zielknoten: ");
							t = chooseNode();
							ok = g.controllEdge(r, t);
							equal = r.equals(t);
							if (equal == true || ok == false){
								System.out.println("Wählen Sie einen anderen Zielknoten aus!");
								r = null;
								t = null;
							}
							else if (equal != true || ok == true) {
								end = true;
								int zahl = gewicht();			
								g.newEdge(r, t, zahl);
							}		
						}	
						break;
					case 3:
						System.out.println("Welchen Knoten wollen Sie löschen?");
						g.knotenausgabe();
						int wahl = in.nextInt() - 1;
						g.deleteNode(wahl);
						break;
					case 4:
						System.out.println("Welche Kante wollen Sie löschen?");
						g.print();
						int choice2 = in.nextInt();
						g.deleteEdge(choice2);
						System.out.println("Es bestehen nun folgende Verbindungen: ");
						g.print();
						break;
					case 5: 
						g.print();
						break;
					case 6:
						break;
					case 7:
						break;
					case 8:
						break;
					case 9:
						back = true;
						break;
					case 0: 
						System.exit(0);;
					}
				}
				break;
			case 2:
				back = false;
				while (!back) {
					randomGraph();
					random.print();
					printInfo2();
					choice = in.nextInt();
					switch (choice) {
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						back = true;
						break;
					case 0:
						System.exit(0);
					}
				}	
				break;
			case 0: 
				System.exit(0);
			}
		}
		
	}
	
	public void printMainInfo() {
		System.out.println("Mit welchem Graphen wollen Sie arbeiten?");
		System.out.println("(1) Eigenen Graphen erstellen");
		System.out.println("(2) Zufälliger Graph");
		System.out.println("(0) Ende des Programms");
	}
	
	public void printInfo1() {
		System.out.println("Was möchten Sie tun?\n\n");
		System.out.println("(1) Knoten hinzufügen");
		System.out.println("(2) Kante hinzufügen");
		System.out.println("(3) Knoten löschen");
		System.out.println("(4) Kante löschen");
		System.out.println("(5) Graph anzeigen");
		System.out.println("(6)  Dejkstra-Algorithmus");
		System.out.println("(7) Graph in externe Datei speichern");
		System.out.println("(8) Graph aus externer Datei lesen");
		System.out.println("(9) Zurück ins Hauptmenü");
		System.out.println("(0) Ende des Programms");
		//System.out.println("");
	}
	
	public void printInfo2() {
		System.out.println("(1) Dejkstra-Algorithmus");
		System.out.println("(2) Graph in externe Datei speichern");
		System.out.println("(3) Graph aus externer Datei lesen");
		System.out.println("(4) Zurück ins Hauptmenü");
		System.out.println("(0) Ende des Programms");
	}
	
	public String nameNode() {
		String s;
		System.out.println("Wie wollen Sie den Knoten nennen?");
		s = in.next();
		return s;
	}
	
	public String chooseNode() {
		String s = "";
		g.knotenausgabe();
		int index = in.nextInt();
		s = g.askNode(index-1);
		return s;
	}
	
	public int gewicht() {
		int zahl = 0;
		System.out.println("Geben Sie den Wert der Kante ein: ");
		zahl = in.nextInt();
		return zahl;
	}
	
	public void randomGraph() {
		boolean ok, end, equal;
		System.out.println("Wie viele Knoten sollen erstellt werden?");
		int size = in.nextInt();
		random = new Graph_start (size);
		String n = "";
		for (int i = 0; i < size; i++) {
			ok = false;
			while (!ok) {
				n = randomName();
				ok = random.controllNode(n);
				if (ok == true)
					random.newNode(n);
			}
			
		}
		//kantenzahl = !knotenzahl als addition
		int kantenzahl = 0;
		for (int i = 1; i < size; i++) {
			kantenzahl = kantenzahl +i;
		}
		for (int i = 0; i < kantenzahl; i++) {
			end = false;
			String p = "", q = "";
			while (!end) {
				int index1 = randomgenerator.nextInt(random.knzahl); //-1
				p = random.askNode(index1);
				int index2 = randomgenerator.nextInt(random.knzahl);
				q = random.askNode(index2);
				ok = random.controllEdge(p, q);
				equal = p.equals(q);
				if (equal == true || ok == false){
					p = null;
					q = null;
					System.out.println("Fehler! Entweder handelt es sich um denselben Knoten oder es besteht bereits eine Verbindung!");
				}
				else if (equal == false && ok == true) {
					end = true;
					int wert = randomgenerator.nextInt(100);			
					random.newEdge(p, q, wert);
					System.out.println("Durchlauf: "+(i+1)+"\tStrings: "+p+" "+q+"\tWert: "+wert);
				}	
				
			}
			
		}
	}
	
	public String randomName() {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
		    char c = chars[randomgenerator.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		System.out.println(output);
		return output;
	}
	
	public void dijstra() {
		
	}

}
