/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cotto
 */
public class SearchTreeNode extends CompRational
{
    // Class values
    private Rational node; 
    
    // Constructors
    
    /**
     * Standard constructor
     */
    
    SearchTreeNode()
    {
    }
    
    /**
     * Create instance using node value node.
     * @param node Value of the node.
     */
    
    SearchTreeNode(Rational node)
    {
        this.node = node;
    }
    
    /**
     * Set node value.
     * @param node Rational value.
     */
    
    public void setTreeNode(Rational node)
    {
        this.node = node;
    }
    
    /**
     * Get node value.
     * @return Rational node value.
     */
    
    public Rational getTreeNode()
    {
        return node;
    }
    
    /**
     * Compares two CompRational values.
     * @param cr Instance of SearchTreeNode compared with this.
     * @return 1, if this > cr, 0 if this = cr, -1 if this < cr.
    */
    
    public int compareTo(Object cr)
    {
        if(this.node.equals(this.node))
        {
            return 0; // this = cr.
        }
        else
        {
            Rational temp = this.node.clone();
            temp.subs(cr.getTreeNode());
            if(temp.isPositive())
            {
                return 1; // this > cr.
            }
            else
            {
                return -1; // cr > this.
            }
        }
    }
}
