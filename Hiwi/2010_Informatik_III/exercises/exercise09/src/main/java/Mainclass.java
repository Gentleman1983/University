import java.util.*;

public class Mainclass {

	public static void main(String[] args) {
		UnionFind U = new UnionFind(15);
		for (int i = 1; i <= 15; i++)
			U.MakeSet(i);
		System.out.println("Es wurden die Elemente von 1 bis 15 angelegt.");
		int x = 0, y = 0;
		do {
			System.out.println("Bitte zwei zu verbindende Elemente angeben, oder zwei mal -1 zum Beenden der Eingabe");
			x = Keyboard.readInteger();
			y = Keyboard.readInteger();
			if (x>0 && y>0) {
				U.Union(U.Find(x), U.Find(y));  // Damit man nicht nur Representanten angeben kann noch ein kleines Find
			}
		} while (x>0 && y>0);
		
		List[] L = U.GetList();
		for (int i = 1; i < L.length; i++) {
			if (L[i].size() > 0) {
				System.out.println("Partition mit Index " + i + ":");
				for (int j = 0; j < L[i].size(); j++) {
					System.out.print(((Integer)L[i].get(j)) + ", ");
				}
				System.out.println();
			}
		}
	}

}
