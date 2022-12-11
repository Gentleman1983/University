/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 5 - Christian Otto - Binary SearchTree
 */

/**
 * Test class to read rational values and test our
 * binary searchtree.
 * @author cotto
 */
public class BinarySearchTreeTest
{
    /**
     * @param EventArgs the command line arguments
     */
    public static void main(String[] EventArgs) 
    {
        Rational r1 = new Rational(1, 1);
        Rational r2 = new Rational(2, 1);
        SearchTreeNode tn1 = new SearchTreeNode(r1);
        SearchTreeNode tn2 = new SearchTreeNode(r2);
        System.out.println(tn1.compareTo(tn2) + ", "+ tn1.compareTo(tn2));
    }

}
