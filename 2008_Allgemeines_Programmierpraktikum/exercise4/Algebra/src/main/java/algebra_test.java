/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 4 - Christian Otto - Algebra
 */

import java.io.*;
import java.util.*;

/**
 * This class implements all functionalities to
 * calculate with matrices.
 * @author cotto
 */
public class algebra_test
{

    /**
     * This methos will be to test the functionality of
     * our algebra library.
     * @param EventArgs The command line arguments.
     */
    public static void main(String[] EventArgs)
    {
        LinkedList<rational[]> vector_list = new LinkedList<rational[]>();
        LinkedList<rational[]> matrix_list = new LinkedList<rational[]>();
        LinkedList<char[]> input_list = new LinkedList<char[]>();
        LinkedList<rational> rat_list = new LinkedList<rational>();
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
            while ((input_line = input.readLine()) != null)  // Read complete file
            {
                char[] in_line = input_line.toCharArray();
		input_list.add(in_line);
            } 
            
            boolean vector_ready = false;
            while(!input_list.isEmpty())
            {
                System.out.println(input_list.size());
                char[] row = input_list.removeFirst();
                int lng = row.length;
                for(int i=0;(!vector_ready) && (i < lng); i++)
                {
                    rational[] rat = new rational[1];
                    if(Character.isWhitespace(row[i]))
                    {
                        continue; // Who cares about whitespace?
                    }
                    else if(row[i] == '$')
                    {
                        vector_ready = true;
                    }
                    else if(Character.isDigit(row[i]))
                    {
                        int value = Character.getNumericValue(row[i]);
                        while((i < lng - 1) && (Character.isDigit(row[i + 1])))
                        {
                            i++;
                            value *= 10;
                            value += Character.getNumericValue(row[i]);
                        }
                        rat[0] = new rational(value, 1);
                        vector_list.add(rat);
                    }
                }
                if((vector_ready) && (!input_list.isEmpty()))
                {
                    for(int i=0;i < lng; i++)
                    {
                        if(Character.isWhitespace(row[i]))
                        {
                            continue; // Who cares about whitespace?
                        }
                        else if(Character.isDigit(row[i]))
                        {
                            int value = Character.getNumericValue(row[i]);
                            while((i < lng - 1) && (Character.isDigit(row[i + 1])))
                            {
                                i++;
                                value *= 10;
                                value += Character.getNumericValue(row[i]);
                            }
                            rat_list.add(new rational(value, 1));
                        }
                    }
                }
            }
           rational[] rat = new rational[1];
            for(int i = 0; i < vector_list.size() * vector_list.size(); i++)
            {
                if(i % vector_list.size() == 0)
                {
                    if(i != 0)
                    {
                        matrix_list.add(rat);
                    }
                    rat = new rational[vector_list.size()];
                }
                rat[i % vector_list.size()] =rat_list.removeFirst();
            }
            matrix_list.add(rat);
            
            matrix mat = new matrix(matrix_list.size(), matrix_list.size(), matrix_list);
            matrix vec = new matrix(1, vector_list.size(), vector_list);
            
            System.out.println(mat + "\n" + vec);
            matrix erg = algebra.algebra(mat, vec);
            System.out.println("Result:\n" + erg);
            
            System.out.println("Check:\n");
            
           matrix result = matrix.mult(mat, erg);
           System.out.println(result);
            
            
	}
        catch(IOException e) 
        {
	    System.out.println("ERROR: input/output error");
	    return; 
	}
    }

}
