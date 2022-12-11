public class binaerziffernvorwaerts {
    static void binaerziffern(int n) {
	if (n == 0)
	    System.out.print('0');
	if (n == 1)
	    System.out.print('1');
	if (n > 1) {
	    intStack tmp = new intStack();
	    
	    while (n > 0)
	    {
	    	tmp.push(n%2);
		n = (n-n%2)/2;
	    }
	    
	    while (!tmp.is_empty())
	    {
	    	System.out.print(tmp.top());
		tmp.pop();
	    }
	}
    }
    public static void main( String[] args) {
	if (args.length != 1) {
	    System.out.println("Aufruf binaerziffernvorwaerts " + 
			       "<natuerliche zahl>");
	    return;
	}
        int n = Integer.parseInt(args[0]);

	if ( n < 0)
	    System.out.println("Nur natuerliche Zahlen erlaubt!");
	else { 
	    binaerziffern(n);
	    System.out.println();
	}
    }
}


