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
				g = new Graph_start (100);
				while (!back) {
					
					printInfo1();
					int choice2 = in.nextInt();
					switch (choice2) {
					case 1:
						end = false;
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
						end = false;
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
						int choice3 = in.nextInt();
						g.deleteEdge(choice3);
						System.out.println("Es bestehen nun folgende Verbindungen: ");
						g.print();
						break;
					case 5: 
						g.print();
						break;
					case 6:
						if (g.knzahl < 2)
							System.out.println("Es existiert kein Graph!");
						else {
							g.knotenausgabe();
							System.out.println("Startknoten: ");
							int index1 = in.nextInt()-1;
							System.out.println("Zielknoten: ");
							int index2 = in.nextInt()-1;
							dijkstra(index1, index2);
						}
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
				randomGraph();
				while (!back) {
					random.print();
					printInfo2();
					choice = in.nextInt();
					switch (choice) {
					case 1:
						if (random.knzahl < 2)
							System.out.println("Es existiert kein Graph!");
						else {
							random.knotenausgabe();
							System.out.println("Startknoten: ");
							int index1 = in.nextInt()-1;
							System.out.println("Zielknoten: ");
							int index2 = in.nextInt()-1;
							dijkstra(index1, index2);
						}
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
		System.out.println("(6) Dejkstra-Algorithmus");
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
		int amount = in.nextInt();
		random = new Graph_start (amount);
		String n = "";
		for (int i = 0; i < amount; i++) {
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
		for (int i = 1; i < amount; i++) {
			kantenzahl = kantenzahl + i;
		}
		System.out.println("Wie viele Kanten sollen erstellt werden?");
		amount = in.nextInt();
		if (amount >= kantenzahl)
			amount = kantenzahl;
		for (int i = 0; i < amount; i++) {
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
	
	//Fehler im Algorithmus: Startknoten muss immer der Index 0 sein in Adjazenzmatrix
	public void dijkstra(int index1, int index2) {
		int knzahl = 0;
		String start = "", end = "";
		if (g == null) {
			knzahl = random.knzahl;
			start = random.askNode(index1);
		}
		else if (random == null) {
			knzahl = g.knzahl;
			start = g.askNode(index1);
		}
		
		int[][] matrix = new int [knzahl][knzahl]; //Entfernungen
		int[] distance = new int[knzahl]; //speichert Zwischenentfernungen
		int[] visited = new int[knzahl]; //Knoten, die schon besucht wurden
		int[] preD = new int[knzahl]; //aktueller Pfad
		int min;
		int nextNode = 0;
		
		for (int i = 0; i < knzahl-index1; i++) {
			visited[i] = 0;
			preD[i] = 0;
			for (int j = 0; j < knzahl; j++) { //VERÄNDERT
				if (random == null) {
					matrix[i][j] = g.getKante()[i+index1][j]; //VERÄNDERT
				}
				else if (g == null)
					matrix[i][j] = random.getKante()[i][j];
				if (matrix[i][j] == 0)
					matrix[i][j] = Integer.MAX_VALUE; //weil es keine Verbindung hat
			}
		}
		
		for (int i = knzahl-index1; i < knzahl; i++) {
			for (int j = 0; j < knzahl; j++) {
				if (random == null) {
					matrix[i][j] = g.getKante()[i-(knzahl-index1)][j]; //VERÄNDERT
				}
				else if (g == null)
					matrix[i][j] = random.getKante()[i-(knzahl-index1)][j];
				if (matrix[i][j] == 0)
					matrix[i][j] = Integer.MAX_VALUE; //weil es keine Verbindung hat
			}
		}
		
		for (int i = 0; i < knzahl; i++) {
			for (int j = 0; j < knzahl; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		
		distance = matrix[0];
		distance[0] = 0;
		visited[0] = 1;
		
		//start
		
		for(int i = 0; i < knzahl; i++) {
			min = Integer.MAX_VALUE;
			for(int j = 0; j < knzahl; j++) {
				if(min >= distance[j] && visited[j] != 1) {
					min = distance[j];
					nextNode = j;
				}
			}
			
			visited[nextNode] = 1;
			
			//actual start of algorithm
			
			for (int c = 0; c < knzahl; c++) {
				if (visited[c] != 1) {
					if (min+matrix[nextNode][c] < distance[c]) {
						distance[c] = min+matrix[nextNode][c];
						preD[c] = nextNode;
					}
				}
			}
		}
		
		if (g == null) {
			end = random.askNode(index2);
		}
		else if (random == null) {
			end = g.askNode(index2);
		}
		
		if (distance[index2] == Integer.MAX_VALUE)
			System.out.println("Es besteht keine Verbindung zum Zielknoten!");
		else {
			System.out.println("Die Entfernung beträgt: "+distance[index2]);
			System.out.print("\nWeg von "+start+" nach "+end+" = "+end);
			int j = 0;
			//System.out.println("Index1: "+index1);
//			String r = g.askNode(index1);
//			System.out.println(r);
//			r = g.askNode(0);
//			System.out.println(r);
			do {
				System.out.println("ok");
				j = preD[j];
				String node = "";
				if (g == null) {
					node = random.askNode(j);
				}
				else if (random == null) {
					node = g.askNode(j);
				}
				System.out.print(" <- "+node);
				//System.out.print(" <- "+(j+1));
			}
			while(j != 0);
		}
		System.out.println("\n\n");
	}

}
