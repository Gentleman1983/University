/*
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 2 - Christian Otto - Complex
 */

/**
 * This class is to test the functionalities of
 * the class Complex.java
 * @author Christian Otto
 */

public class ComplexTest 
{
    /** Test the functionalities of Complex.java
     * @param EventArgs the command line arguments
     */
    public static void test_equalities(Complex comp)
    {
        System.out.print("Objektmethoden:\t");
        if(comp.isZero())
        {
            System.out.print("ist 0,\t");
        }
        else
        {
            System.out.print("ist nicht 0,\t");
        }
        if(comp.isOne())
        {
            System.out.print("ist 1,\t");
        }
        else
        {
            System.out.print("ist nicht 1,\t");
        }
        if(comp.isI())
        {
            System.out.println("ist i.");
        }
        else
        {
            System.out.println("ist nicht i.");
        }
        System.out.print("Statische Methoden:\t");
        if(Complex.isZero(comp))
        {
            System.out.print("ist 0,\t");
        }
        else
        {
            System.out.print("ist nicht 0,\t");
        }
        if(Complex.isOne(comp))
        {
            System.out.print("ist 1,\t");
        }
        else
        {
            System.out.print("ist nicht 1,\t");
        }
        if(Complex.isI(comp))
        {
            System.out.println("ist i.\n");
        }
        else
        {
            System.out.println("ist nicht i.\n");
        }
    }
    
    /** Test the functionalities of Complex.java
     * @param EventArgs the command line arguments
     */
    
    public static void main(String[] EventArgs) 
    {
        double[] input_values = new double[6]; //parse EventArgs to double
        for (int i = 0; i < 6; i++)
        {
            input_values[i] = Double.parseDouble(EventArgs[i]);
        }
        // Create Complex Instances
        Complex a = new Complex(input_values[0], input_values[1]);
        Complex b = new Complex(input_values[2], input_values[3]);
        Complex c = new Complex(input_values[4], input_values[5]);
        Complex d = new Complex();
        
        System.out.println("Prüfe auf Gleichheit mit Standardwerten (0, 1, i):");
        System.out.println("Prüfe Instanz a:");
        test_equalities(a);
        System.out.println("Prüfe Instanz b:");
        test_equalities(b);
        System.out.println("Prüfe Instanz c:");
        test_equalities(c);
        System.out.println("Prüfe Instanz d:");
        test_equalities(d);
        
        System.out.println("Prüfe auf Gleichheit der komplexen Zahlen:");
        System.out.println("a = b?\t" + a.equals(b));
        System.out.println("a = c?\t" + a.equals(c));
        System.out.println("a = d?\t" + a.equals(d));
        System.out.println("b = c?\t" + b.equals(c));
        System.out.println("b = d?\t" + b.equals(d));
        System.out.println("c = d?\t" + c.equals(d) + "\n");
        
        System.out.println("Klone Zahl a, b, c, d:");
        Complex e = a.clone();
        e.toString();
        Complex f = b.clone();
        f.toString();
        Complex g = c.clone();
        g.toString();
        Complex h = d.clone();
        h.toString();
        System.out.print("\n");
        
        System.out.println("Ändere Wert von h von 0 nach 1=>i=>1 + i:");
        h.toString();
        h.setValue(1, 0);
        h.toString();
        h.setValue(0, 1);
        h.toString();
        h.setValue(1, 1);
        h.toString();
        System.out.print("\n");
        
        System.out.println("Berechne e = e + f");
        e.add(f);
        e.toString();
        System.out.println("Berechne e = e + g");
        e.add(g);
        e.toString();
        System.out.println("Berechne e = e + h");
        e.add(h);
        e.toString();
        System.out.print("\n");
        
        System.out.println("Berechne f = f * e");
        f.mult(e);
        f.toString();
        System.out.println("Berechne f = f * g");
        f.mult(g);
        f.toString();
        System.out.println("Berechne f = f * d (=0)");
        f.mult(d);
        f.toString();
        System.out.print("\n");
        
        System.out.println("Berechne Beträge:");
        System.out.println("|a| = " + a.abs());
        System.out.println("|b| = " + b.abs());
        System.out.println("|c| = " + c.abs());
        System.out.println("|d| = " + d.abs());
        System.out.print("\n");
        
        System.out.println("Berechne mit statischen Methoden:");
        System.out.println("e = a + b");
        e = Complex.add(a, b);
        e.toString();
        System.out.println("f = b + c");
        f = Complex.add(b, c);
        f.toString();
        System.out.println("g = a + c");
        f = Complex.add(a, c);
        f.toString();
        System.out.println("e = a * b");
        e = Complex.mult(a, b);
        e.toString();
        System.out.println("f = b * c");
        f = Complex.mult(b, c);
        f.toString();
        System.out.println("g = a * c");
        f = Complex.mult(a, c);
        f.toString();
        System.out.print("\n");
        
        System.out.println("Berechne Beträge über statische Methoden:");
        System.out.println("|a| = |" + a.getReal() + " + " + a.getImagine() + "i| = "+ Complex.abs(a));
        System.out.println("|b| = |" + b.getReal() + " + " + b.getImagine() + "i| = "+ Complex.abs(b));
        System.out.println("|c| = |" + c.getReal() + " + " + c.getImagine() + "i| = "+ Complex.abs(c));
        System.out.println("|d| = |" + d.getReal() + " + " + d.getImagine() + "i| = "+ Complex.abs(d));
        System.out.println("|e| = |" + e.getReal() + " + " + e.getImagine() + "i| = "+ Complex.abs(e));
        System.out.println("|f| = |" + f.getReal() + " + " + f.getImagine() + "i| = "+ Complex.abs(f));
        System.out.println("|g| = |" + g.getReal() + " + " + g.getImagine() + "i| = "+ Complex.abs(g));
        System.out.println("|h| = |" + h.getReal() + " + " + h.getImagine() + "i| = "+ Complex.abs(h));
        System.out.print("\n");
        
        System.out.println("Vielen Dank für die Nutzung von ComplexTest V. 1.00.\n Bitte nutzen Sie bald wieder Produkte aus unserem Hause...");
    }

}
