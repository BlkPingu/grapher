import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Graph_start {
	BufferedWriter f;
	public int knzahl = 0;
	private int[][] kante; // Kantenmatrix
	private String[] knoten; // Knotenvektor

	public int[][] getKante() {
		return kante;
	}

	public void setKante(int[][] kante) {
		this.kante = kante;
	}

	public String[] getKnoten() {
		return knoten;
	}

	public void setKnoten(String[] knoten) {
		this.knoten = knoten;
	}

	Graph_start(int kn) { // kn maximale Knotenzahl
		kante = new int[kn][kn];
		knoten = new String[kn];
		for (int i = 0; i < knzahl; i++) // Initialisieren: alle Kanten-
			for (int j = 0; j < knzahl; j++)
				kante[i][j] = 0;
	}

	public void newNode(String Kn) {
		// Pruefen, ob Knoten schon existiert fehlt noch!
		knoten[knzahl] = Kn;
		knzahl++;
	}

	public int nodeNumber(String kn) {
		int i;
		for (i = 0; i < knzahl; i++)
			if (knoten[i].equals(kn))
				return i;
		return -1;
	}

	public void newEdge(String K1, String K2, int wert) {
		int n1, n2;
		// Pruefen, ob beide Knoten schon existieren !
		n1 = nodeNumber(K1);
		n2 = nodeNumber(K2);
		if (n1 != -1 && n2 != -1 && kante[n2][n1] == 0 && kante[n1][n2] == 0)
			kante[n1][n2] = wert;
		else
			System.out.println("Keine Speicherung möglich, da bereits eine Verbindung der Knoten existiert.");
	}

	public void print() {
		int counter = 0;
		System.out.println("\n Knotenliste\n");
		for (int i = 0; i < knzahl; i++)
			System.out.print("  " + knoten[i]);

		System.out.println("\n\n Kantenliste\n");
		for (int i = 0; i < knzahl; i++) { // Initialisieren: alle Kanten
			for (int j = 0; j < knzahl; j++) {
				if (kante[i][j] != 0) {
					++counter;
					System.out.print((counter)+". Kante " + knoten[i] + " -> " + knoten[j] + " (" + kante[i][j] + ")   ");
				}
					// System.out.print(" Kante "+knoten[i]+" -> "+knoten[j]);
					
			}
			System.out.println();
		}
	}

	// weiter Methoden noch zu realisieren
	public void deleteNode(int Kn) {		
		knoten[Kn] = null;
		
		for (int i = 0; i < knzahl; i++) {
			if (knoten[i] == null) {
				//Aufrücken der Knoten, damit keine Lücke im Array bleibt
				for (int j = i; j < knzahl; j++) {
					knoten [j] = knoten [j+1];
				}
				//Aufrücken der Kantenmatrix, damit keine Lücke im Array bleibt
				for (int m = 0; m < knzahl; m++) {
					for (int j = i; j < knzahl; j++) {
						kante[m][j] = kante[m][j+1];
						}
				}
				for (int m = 0; m < knzahl; m++) {
					for (int j = i; j < knzahl; j++) {
						kante [j][m] = kante [j+1][m];
					}
				}
			}
		}
		
		--knzahl;
		
		for (int i = 0; i < knzahl; i++) {
			for (int j = 0; j < knzahl; j++) {
				System.out.print(kante[i][j]+"\t");
			}
			System.out.println();
		}
	}

	public void deleteEdge(int wahl) {
		int counter = 0;
		for (int i = 0; i < knzahl; i++) {
			for (int j = 0; j < knzahl; j++) {
				if (kante[i][j] != 0) {
					++counter;
					if (counter == wahl) {
						kante[i][j] = 0;
					}
				}
			}
		}
	}

	public void knotenausgabe() {
		for (int i = 0; i < knzahl; i++)
			if (knoten[i] != null)
				System.out.println((i + 1) + ": " + knoten[i] + "	");
	}

	public String askNode(int zahl) {
		//zahl = zahl - 1;
		String s = knoten[zahl];
		return s;
	}

	public boolean controllNode(String s) {
		for (int i = 0; i < knzahl; i++) {
			if (knoten[i].equals(s))
				return false;
		}
		return true;
	}
	
	public boolean controllEdge(String s, String t) {
		int zahl1 = nodeNumber(s); 
		int zahl2 = nodeNumber(t);
		if (kante[zahl1][zahl2] == 0 && kante[zahl2][zahl1] == 0)
			return true;
		else
			return false;
	}

	public void dateischreiben() {
		// try {
		// f = new BufferedWriter(
		// new FileWriter("file1.txt"));
		// System.out.println("Wie wollen Sie den Knoten nennen: ");
		// f.write("###");
		// f.newLine();
		// f.write("6");
		// f.newLine();
		// System.out.println(6);
		// for (int i = 0; i < 6; ++i) {
		// f.write((i+1)*(i+1)+"");
		// f.newLine();
		// System.out.println((i+1)*(i+1));
		// }
		// f.close();
		// }
		// catch (IOException e) {
		// System.out.println("Fehler beim Erstellen der Datei");
		// }
	}

	public void dateilesen() {
	}

	// fakultativ Breitensuche, Tiefensuche oder Wegsuche
}
