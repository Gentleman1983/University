/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 5 - Christian Otto - NormRational
 */

/**
 * This class represents my normalized rational values.
 * It's very simple because all the things we have to
 * implement were used in the super class Rational.
 * @author cotto
 */
public class NormRational  extends Rational
{
    /**
     * The standard constructor creates a zero-instance.
     */
    
    NormRational()
    {
        super();
    }
    
    /**
     * This constructer creates an initialized instance.
     * @param count Count of the normalized rational.
     * @param divisor Divisor of the normalized rational.
     * @throws java.lang.NumberFormatException
     */
    
    NormRational(long count, long divisor) throws NumberFormatException
    {
        super(count, divisor);
    }
}
