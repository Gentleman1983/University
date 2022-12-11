class sieb
{
	public static void main(String[] args)
	{
		int i, j; // Schleifenzahlvariable
		int n = java.lang.Integer.decode(args[0]);
		int m = (n / 2) + (n % 2), q; // Ungerade Zahlen wegwerfen

		System.out.println("m= " + m);
		if(m % 8 == 0)
		{
			q = m / 8;
		}
		else
		{
			q = m / 8 + 1;
		}
		System.out.println(" DEBUGAUSGABE: Es werden " + m + " Bits, also " + q + " Bytes, auf dem Stack benoetigt!");

		int[] feld = new int[n];
		for(i = 0; i < n; i++)
		{
			feld[i] = 0; // Alle Zahlen als potentielle Primzahlen markieren
		}
		feld[0] = 1; // 1 von der Primzahlliste streichen

		System.out.print("Primzahlliste: 2");

		for(j = 1; j < m; j++)
		{
			if(feld[j] == 1)
			{
				continue;
			}
			else
			{
				for(i = j + 1; i < m; i++) // Hier irgendwo ist der Wurm drin...
				{
					if(((2 * i + 1) % (2 * j + 1)) == 0)
					{
						feld[i] = 1;
					}
				}
				if(feld[j] == 0)
				{
					System.out.print(", " + (2 * j + 1));
				}
			}
		}
		System.out.print("\n");
	}
}
