/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 3 - Christian Otto - Huffman
 */

import java.io.*;
import java.util.*;

/**
 * This class implements all functionalities to
 * encrypt and decrypt huffman codes.
 * @author cotto
 */

public class Huffman
{
    
  /** 
   * Create a list of trees for our letters.
   * @param count How often does witch letter exist?
   * @return List of nodes.
   */
  
    private static LinkedList<CodeTree> buildTrees(long[] count, boolean debug)
    {
        LinkedList<CodeTree> tree_list = new LinkedList<CodeTree>(); // Build list for nodes
        
        for(int i = 0, j = 0; i <=128; i++)
        {
            if(count[i] == 0)
            {
                continue;
            }
            else
            {
                CodeTreeNode node = new CodeTreeNode(i, count[i]);
                CodeTree tree = new CodeTree(node);
                if(debug)
                {
                    System.out.println(node.toString());
                }
                
                tree_list.add(tree);
            }
        }
        
        return tree_list;
    }
    
 /** 
   * Create a huffman code for the text 
   * submitted on command line
   * @param EventArgs the command line arguments
   */
    
    public static void main(String[] EventArgs)
    {
        boolean debug = false; // Set debug mode
        
        if ( EventArgs.length != 1 )  //Syntax check
        {
	    System.out.println("ERROR:\n\nPlease use syntax:\njava Huffman <Filename>");
	    return;
	}

	BufferedReader input; // File import
	try 
        {
	    input = new BufferedReader(new FileReader(EventArgs[0])); 
	}
	catch(FileNotFoundException e) 
        {
	    System.out.println("ERROR: file \"" + EventArgs[0] + "\" not found");
	    return; 
	}
	
	String input_line ;
	try 
        {
            long[] count = new long[129];
            
            while ((input_line = input.readLine()) != null)  // Letter count
            {
                byte[] byte_line = input_line.getBytes();
		
                for (int i = 0; i < byte_line.length; i++)
                {
                    if(debug)
                    {
                        System.out.println(byte_line[i] + " " + (char) byte_line[i]);
                    }
                    count[byte_line[i]]++;
                }
            } 
            count[128]++; // Add end signal \0 once
            if(debug)
            {
                for(int i=0; i <= 128; i++){System.out.println("count[" + i + "] = " + count[i]);}
            }
                    
            LinkedList<CodeTree> tree_list = buildTrees(count, debug);
            tree_list = CodeTree.fusionTrees(tree_list, debug);
            tree_list.getFirst().getCodeTable();
	}
        catch(IOException e) 
        {
	    System.out.println("ERROR: input/output error");
	    return; 
	}
        
    }
}
