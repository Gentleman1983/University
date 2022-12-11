/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 4 - Christian Otto - Algebra
 */

/**
 * This class represents my rational values.
 * @author cotto
 */
public class rational
{
    // Class values
    private long count = 0;
    private long divisor = 1;
    private static rational zero = new rational();
    
    // Constructors
    
    /**
     * Creates zero-instance.
     */
    
    rational()
    {
    }
    
    /**
     * This creates a rational value. If divisor is zero 
     * a NumberFormatException is being thrown.
     * @param count Count.
     * @param divisor divisor.
     * @throws java.lang.NumberFormatException
     */
    
    rational(long count, long divisor) throws NumberFormatException
    {
        if(divisor != 0)
        {
            this.set(count, divisor);
        }
        else
        {
            throw new NumberFormatException("rational: Illegal value - divisor zero not supported.");
        }
    }
    
    // Class methods
    
    /**
     * Clones this.
     * @return Clone of this.
     */
    
    public rational clone()
    {
        return new rational(this.count, this.divisor);
    }
    
    /**
     * Clones cloned.
     * @param cloned Instance being cloned.
     * @return Clone of cloned.
     */
    
    public static rational clone(rational cloned)
    {
        return new rational(cloned.count, cloned.divisor);
    }
    
    /**
     * Adds two rational values.
     * @param item Item added to this.
     */
    
    public void add(rational item)
    {
        this.set(item.count * this.divisor + this.count * item.divisor, item.divisor * this.divisor);
    }
    
    /**
     * Adds two rational values.
     * @param item1 Item to which item2 is added.
     * @param item2 Item added to item1.
     */
    
    public static void add(rational item1, rational item2)
    {
        item1.add(item2);
    }
    
    /**
     * Substracts(?) rational value item from this.
     * @param item Item substracted(?) from this.
     */
    
    public void subs(rational item)
    {
        if(!item.isNull())
        {
            this.set(this.count * item.divisor - item.count * this.divisor, item.divisor * this.divisor);
        }
    }
    
    /**
     * Substracts(?) rational value item2 from item1.
     * @param item1 Item which was substracted(?) from.
     * @param item2 Item substracted(?) from item1.
     */
    
    public static void subs(rational item1, rational item2)
    {
        item1.subs(item2);
    }
    
    /**
     * Multiplies two rational values.
     * @param item Multiplied to this.
     */
    
    public void mult(rational item)
    {
        this.set(this.count * item.count, this.divisor * item.divisor);
    }
    
    /**
     * Multiplies item1 and item2.
     * @param item1 Item to which item2 is multiplied to.
     * @param item2 Item which is multiplied to item1.
     */
    
    public static void mult(rational item1, rational item2)
    {
        item1.mult(item2);
    }
    
    /**
     * Multiply rational value this by long value.
     * @param value Value multiplied.
     */
    
    public void mult(long value)
    {
        this.set(this.count * value, this.divisor);
    }
    
    /**
     * Multiply rational value item by long value.
     * @param item Instance multiplied by value.
     * @param value Value multiplied.
     */
    
    public static void mult(rational item, long value)
    {
        item.mult(value);
    }
    
    /**
     * Divides this rational value by rational value
     * item.
     * @param item Instance devided by.
     * @throws java.lang.ArithmeticException
     */
    
    public void div(rational item) throws ArithmeticException
    {
        if(item.isNull())
        {
            throw new ArithmeticException("div: Division by zero.");
        }
        else
        {
            this.set(this.count * item.divisor, this.divisor * item.count);
        }            
    }
    
    /**
     * Divides rational value item1 by rational value
     * item2.
     * @param item1 Instance devided by item 2.
     * @param item2 Instance deviding item1.
     * @throws java.lang.ArithmeticException
     */
    
    public static void div(rational item1, rational item2) throws ArithmeticException
    {
       if(item2.isNull())
       {
           throw new ArithmeticException("div: Division by zero.");
       }
       else
       {
           item1.div(item2);
       }
    }
    
    /**
     * Divides rational value this by long value.
     * @param value Long value divided by.
     * @throws java.lang.ArithmeticException
     */
    
    public void div(long value) throws ArithmeticException
    {
        if(value == 0)
        {
            throw new ArithmeticException("div: Division by zero.");
        }
        else
        {
            this.set(this.count, this.divisor * value);
        }
    }
    
    /**
     * Divides rational value item by long value.
     * @param item Instance divided by value.
     * @param value Value divided by.
     * @throws java.lang.ArithmeticException
     */
    
    public static void div(rational item, long value) throws ArithmeticException
    {
        if(value == 0)
        {
            throw new ArithmeticException("div: Division by zero.");
        }
        else
        {
            item.div(value);
        }
    }
    
    /**
     * Optimizes a rational value.
     */
    
    public void optimize()
    {
        boolean is_neg = false; // Is this negative?
        if(this.count < 0)           // Erase sign for optimization.
        {
            this.count *= -1;
            is_neg = true;
            if(this.divisor < 0)
            {
                this.divisor *= -1;
                is_neg = false;
            }
        }
        else if(this.divisor < 0)
        {
            this.divisor *= -1;
            is_neg = true;
        }
        
        if(this.isNull()) // Simple...
        {
            this.count = 0;
            this.divisor = 1;
        }
        else if(this.count == this.divisor) // Simple, too...
        {
            this.count = 1;
            this.divisor = 1;
        }
        else // Now it's getting interesting... ;)
        {
            long min; // Value of the smaller value.
            long max; // Value of the greater value.
            boolean div_min; // Is divisor minimum?
            
            if(this.count < this.divisor) // Which value is the greater one?
            {
                min = this.count;
                max = this.divisor;
                div_min = false;
            }
            else
            {
                min = this.divisor;
                max = this.count;
                div_min = true;
            }
            
            if(max % min == 0) // Is min divisor of max?
            {
                max /= min;
                min = 1;
            }
            else // Or do we need the brute force method?
            {
                boolean finished = false; // Have we finisched?
                while(!finished)
                {
                    for(int i = 2; i <= min; i++)
                    {
                        if(i == min)
                        {
                            finished = true;
                        }
                       if((min % i == 0) && (max % i == 0))  // Found divisor!
                        {
                            max /= i;
                            min /= i;
                            i = 1; // Reset i
                        }
                    }
                }
            }
            
            if(!div_min)  // Set values!
            {
                this.count = min;
                this.divisor = max;
            }
            else
            {
                this.count = max;
                this.divisor = min;
            }
            
            if(is_neg) // Fix wrong signs.
            {
                this.count *= -1;
            }
        }
    }
    
    /**
     * Returns value of this instance as double value.
     * @return Value of this.
     */
    
    private double getValue()
    {
        double count = (double) this.count;
        double divisor = (double) this.divisor;
        return count / divisor;
    }
    
    /**
     * Checks if this instance equals instance item.
     * @param item Instance of rational.
     * @return true, if this equals item.
     */
    
    public boolean equals(rational item)
    {
       if(this.getValue() == item.getValue())
       {
           return true;
       }
       else
       {
           return false;
       }
    }
    
    /**
     * Checks if item1 equals item2.
     * @param item1 Instance of rational.
     * @param item2 Instance of rational.
     * @return true, if item1 equals item2.
     */
    
    public static boolean equals(rational item1, rational item2)
    {
        return item1.equals(item2);
    }
    
    /**
     * Checks if this instance equals zero.
     * @return true, if this equals zero.
     */
    
    public boolean isNull()
    {
        return equals(zero);
    }
    
    /**
     * Checks if item equals zero.
     * @param item Instance of rational.
     * @return true, if item equals zero.
     */
    
    public static boolean isNull(rational item)
    {
        return item.isNull();
    }
    
    /**
     * Returns rational value as fraction.
     * @return value.
     */
    
    public String toString()
    {
       String ret_string;
       if(this.isNull())
       {
           ret_string = "0";
       }
       else if(this.divisor == 1)
       {
           ret_string = "" + count;
       }
       else        
       {
           ret_string = count + " / " + divisor;
       }
       return ret_string;
    }
    
    /**
     * Set count and divisor of rational value this.
     * @param count Count.
     * @param divisor Divisor.
     * @throws java.lang.NumberFormatException
     */
    
    public void set(long count, long divisor) throws NumberFormatException
    {
        if(divisor == 0)
        {
            throw new NumberFormatException("set: Illegal value - divisor zero not supported.");
        }
        else
        {
            this.count = count;
            this.divisor = divisor;
            this.optimize();
        }
    }
    
    /**
     * Sets count and divisor for rational value item.
     * @param item Instance getting new value.
     * @param count Count.
     * @param divisor Divisor.
     * @throws java.lang.NumberFormatException
     */
    
    public static void set(rational item, long count, long divisor) throws NumberFormatException
    {
        if(divisor == 0)
        {
            throw new NumberFormatException("rational: Illegal value - divisor zero not supported.");
        }
        else
        {
            item.set(count, divisor);
        }
    }
}
