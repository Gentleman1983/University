/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 3 - Christian Otto - Huffman
 */

/**
 * This class represents nodes of my code trees
 * @author cotto
 */
public class CodeTreeNode 
{
    // Class values
    private int letter_id = -1; // ID of the letter represented by this node, -1 == no letter
    private long count = 0; // How often this letter was found in the coded file
    private String code; // Code of this node
    
    // Constructors
    
 /**
   * Creates a node of the code tree.
   */
    
    CodeTreeNode()
    {        
    }
    
 /**
   * Creates a node of the code tree.
   * @param count How often does this letter exist?
   */    
    
    CodeTreeNode(long count)
    {
        this.count = count;
    }
   
  /**
   * Creates a node of the code tree.
   * @param letter_id The ID of the letter.
   * @param count How often does this letter exist?
   */   
    
    CodeTreeNode(int letter_id, long count)
    {
        this.count = count;
        this.letter_id = letter_id;
    }
    
    // Class methods
    
 /**
   * Returns the letter ID.
   * @return The letter ID.
   */
    
    public int getLetter()
    {
        return letter_id;
    }
    
 /**
   * Returns the letter count.
   * @return How oftend does this letter exist?
   */
    
    public long getCount()
    {
        return count;
    }
    
 /**
   * Returns whether the node is a leave or not.
   * @return true if node is a leave.
   */
    
    public boolean isLeave()
    {
        if(letter_id == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
 /**
   * Returns the letter.
   * @return The letter.
   */    
    
    public String toString()
    {
        String ret_string;
        if(letter_id == 128) // Is the end symbol
        {
            ret_string = "\\0:\t";
        }
        else if (Character.isWhitespace((char)  letter_id)) // Is a white space
        {
            ret_string = letter_id + ":\t";
        }
        else
        {
            ret_string = (char)  letter_id + ":\t"; // Is anything else
        }
        return ret_string;
    }
}
