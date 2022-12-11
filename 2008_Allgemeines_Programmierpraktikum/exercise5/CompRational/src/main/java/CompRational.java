/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 5 - Christian Otto - Binary SearchTree
 */

/**
 * This class represents my comparable rational 
 * values.
 * @author cotto
 */
abstract public class CompRational extends Rational implements Comparable
{
    // Constructors:
    
    /**
     * Standard constructor.
     */
    
    CompRational()
    {
        super();
    }
    
    /**
     * Constructer setting user defined values.
     * @param count Count.
     * @param divisor Divisor.
     */
    
    CompRational(long count, long divisor)
    {
        super(count, divisor);
    }
    
    /**
     * Compares two CompRational values.
     * @param cr Instance of SearchTreenode compared with this.
     * @return 1, if this > cr, 0 if this = cr, -1 if this < cr.
    */
    
    abstract public int compareTo(Object cr);
    
}
