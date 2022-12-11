/*
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 2 - Christian Otto - Complex
 */

/**
 * This class implements all functionalities to
 * calculate using complex values.
 * @author cotto
 */
public class Complex {
    // Real value
    private double real = 0.0;
    // Imaginary value
    private double imagine = 0.0;
    // Standard complex instances
    private static Complex zero = new Complex(); // Standard constructor created zero-instances!
    private static Complex one = new Complex(1, 0);
    private static Complex img = new Complex(0, 1);
    
    // Constructors
    
  /**
   * Creates instance using standard values (Re, Im) = (0, 0).
   */
    
    Complex()
    {
        System.out.println("Instanz mit Standardwerten erstellt.");
    }
    
  /**
   * Creates instance using user defined values (Re, Im) = (re, im).
   * @param re Real value.
   * @param im Imaginary value.
   */
    
    Complex(double re, double im)
    {
        setValue(re, im);
        System.out.println("Instanz mit benutzerdefinierten Werten erstellt.");
    }
    
    // Methods
    
  /**
   * Adds complex values.
   * @param comp Instance of Complex.
   */
    
    public void add(Complex comp)
    {
        add(comp.getReal(), comp.getImagine());
    }
    
  /**
   * Adds complex values and store as this.
   * @param r Real value.
   * @param i Imaginary value.
   */
    
    public void add(double r, double i)
    {
        r += getReal();
        i += getImagine();
        setValue(r, i);
    }
    
  /**
   * Adds complex values.
   * @param comp1 Instance of Complex.
   * @param comp2 Instance of Complex.
   * @return Sum of comp1 and comp2 as instance of Complex.
   */
    
    public static Complex add(Complex comp1, Complex comp2)
    {
        Complex comp_ret = new Complex();
        comp_ret.add(comp1);
        comp_ret.add(comp2);
        return comp_ret;
    }
    /**
   * Multiplies complex values (this * comp).
   * @param comp Instance of Complex.
   */
    
    public void mult(Complex comp)
    {
        mult(comp.getReal(), comp.getImagine());
    }
    
  /**
   * Multiplies complex values (this * comp) and stores as this.
   * @param re Real value.
   * @param im Imaginary value.
   */
    
    public void mult(double re, double im)
    {
        double r = 0.0, i = 0.0;
        r = getReal() * re - getImagine() * im;
        i = getReal() * im + getImagine() * re;
        setValue(r, i);
    }
    
  /**
   * Multipliess complex values (comp1 * comp2).
   * @param comp1 Instance of Complex.
   * @param comp2 Instance of Complex.
   * @return Product of comp1 and comp2 as instance of Complex.
   */
    
    public static Complex mult(Complex comp1, Complex comp2)
    {
        Complex comp_ret = new Complex();
        comp_ret.add(comp1);
        comp_ret.mult(comp2);
        return comp_ret;
    }
    
    /**
   * Calculates absolute value.
   * @param comp Instance of Complex.
   * @return Absolute value.
   */
    
    public double abs()
    {
        return abs(getReal(), getImagine());
    }
    
  /**
   * Calculates absolute value.
   * @param r Real value.
   * @param i Imaginary value.
   * @return Absolute value.
   */
    
    public double abs(double r, double i)
    {
        double ret_value = 0.0;
        ret_value = Math.sqrt(r * r + i * i);
        return ret_value;
    }
    
  /**
   * Calculates absolute value.
   * @param comp Instance of Complex.
   * @return Absolute value.
   */
    
    public static double abs(Complex comp)
    {
        return comp.abs();
    }
    
  /**
   * Sets real value.
   * @param re Real value.
   */
    
    public void setReal (double re)
    {
        real = re;
    }
    
  /**
   * Sets imaginary value.
   * @param im Imaginary value.
   */
    
    public void setImagine(double im)
    {
        imagine = im;
    }
    
  /**
   * Sets value.
   * @param re Real value.
   * @param im Imaginary value.
   */
    
    public void setValue(double re, double im)
    {
        setReal(re);
        setImagine(im);
    }
    
  /**
   * Returns real value.
   * @return Real value.
   */
    
    public double getReal()
    {
        return real;
    }
    
  /**
   * Returns imaginary value.
   * @return Imaginary value.
   */
    
    public double getImagine()
    {
        return imagine;
    }
    
    
  /**
   * Compares two instances.
   * @param comp An instance of Complex being compared to this instance.
   * @return true, if real and imaginary values are equal.
   */
    
    public boolean equals(Complex comp)
    {
        return ((getReal() == comp.getReal()) &&
                (getImagine() == comp.getImagine()));
    }
    
  /**
   * Compares to Zero.
   * @return true, if instance is zero.
   */
    
    public boolean isZero()
    {
        return this.equals(zero);
    }
    
  /**
   * Compares to Zero.
   * @param comp An instance of Complex being compared to this instance.
   * @return true, if real and imaginary values are equal.
   */
    
    public static boolean isZero(Complex comp)
    {
        return comp.isZero();
    }
    
  /**
   * Compares to One.
   * @return true, if instance is one.
   */
    
    public boolean isOne()
    {
        return this.equals(one);
    }
    
  /**
   * Compares to One.
   * @param comp An instance of Complex being compared to one.
   * @return true, if instance is one.
   */
    
    public static boolean isOne(Complex comp)
    {
        return comp.isOne();
    }
    
  /**
   * Compares to i.
   * @return true, if instance is i.
   */
    
    public boolean isI()
    {
        return this.equals(img);
    }
    
  /**
   * Compares to i.
   * @param comp An instance of Complex being compared to i.
   * @return true, if instance is i.
   */
    
    public static boolean isI(Complex comp)
    {
        return comp.isI();
    }
    
  /**
   * Clones instance and returns cloned instance.
   * @return cloned instance.
   */
    
    public Complex clone()
    {
        Complex ret_value = new Complex(getReal(), getImagine());
        return ret_value;
    }
    
  /**
   * Returns value as string.
   * @return Value as string.
   */
    
    public String toString()
    {
        String ret_string = "Der Wert beträgt " + getReal() + " + " + getImagine() +"i.";
        System.out.println(ret_string);
        return ret_string;
    }

}
