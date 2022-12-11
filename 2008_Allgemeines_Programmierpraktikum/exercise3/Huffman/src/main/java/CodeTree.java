/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 3 - Christian Otto - Huffman
 */

import java.util.*;

/**
 * This class represents my code trees
 * @author cotto
 */
public class CodeTree
{
    // Class values
    private CodeTreeNode node;
    private CodeTree left_child;
    private CodeTree right_child;
    
    // Comstructors
    
 /**
   * Creates the leave of a code tree
   * @param node Which node is represents the leave?
   */    
    
    CodeTree(CodeTreeNode node)
    {
        this.node = node;
    }
    
  /**
    * Creates a new code tree to merge two others.
    * @param left The left child tree.
    * @param right The right child tree.
    */    
    
    CodeTree(CodeTree left, CodeTree right)
    {
        left_child = left;
        right_child = right;
        node = new CodeTreeNode(left.node.getCount() + right.node.getCount());
    }
    
    // Class methods
   
  /**
    * Starts search for all huffman codes.
    */    
 
    public void getCodeTable()
    {
        if(isLeave())
        {
            System.out.println(node.toString() + "0" + "\t(" + node.getCount() + ")");
        }
        else
        {
            if(left_child != null)
            {
                left_child.getCodeTable("0");
            }
            if(right_child != null)
            {
                right_child.getCodeTable("1");
            }
        }
    }
 
 /**
   * Increases depths of search in code tree for huffman codes...
   * @param code The huffman code to get to this node.
   */    
    
    private void getCodeTable(String code)
    {
        if(isLeave())
        {
            System.out.println(node.toString() + code + "\t(" + node.getCount() + ")");
        }
        else
        {
            if(left_child != null)
            {
                left_child.getCodeTable(code + "0");
            }
            if(right_child != null)
            {
                right_child.getCodeTable(code + "1");
            }
        }
    }
 
 /**
   * How many letters are merged in this tree?
   * @return Value of this tree
   */    
    
 public long getCount()
    {
        return node.getCount();
    }
 
  /**
   * Is this tree a leave?
   * @return true if this "tree" is a leave
   */    
    
 private boolean isLeave()
    {
        if(left_child == null && right_child == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

 /** 
   * Moves the hugest trees to the end of the priority list.
   * @param list List of trees.
   * @param debug Debug mode.
   * @return List of trees. 
   */
    
    private static LinkedList<CodeTree> getPriorities(LinkedList<CodeTree> list, boolean debug)
    {
        int lng = list.size();
        if(debug)
        {
            System.out.println("Listlength: " + lng);
        }
        
        for(int i = lng - 2; i >=0; i--)
        {
            long a = list.get(i).getCount(), b = list.get(lng - 1).getCount();
            if(a < b)
            {
                if(debug)
                {
                    System.out.println(a + " < " + b);
                }
                list.addLast(list.remove(i)); // Move tree to end of the list.
            }
        }
        for(int i = lng - 3; i >=0; i--)
        {
            long a = list.get(i).getCount(), b = list.get(lng - 2).getCount();
            if(a < b)
            {
                if(debug)
                {
                    System.out.println(a + " < " + b);
                }
                list.addLast(list.remove(i)); // Move tree to end of the list.
            }
        }
        return list;
    }
   
 /** 
   * Combines all trees recursively.
   * @param list List of trees.
   * @param debug Debug mode.
   * @return List of the whole tree. 
   */
    
    public static LinkedList<CodeTree> fusionTrees(LinkedList<CodeTree> list, boolean debug)
    {
        if(list.size() > 1)
        {
            list = getPriorities(list, debug);
            CodeTree temp_tree = new CodeTree(list.removeLast(), list.removeLast());
            if(debug)
            {
                System.out.println("New tree length: " + temp_tree.getCount());
            }
            list.addLast(temp_tree);
            list = fusionTrees(list, debug);
        }
        return list;
    }
}
