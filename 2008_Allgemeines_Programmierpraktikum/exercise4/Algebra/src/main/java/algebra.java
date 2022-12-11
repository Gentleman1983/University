/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 4 - Christian Otto - Algebra
 */

/**
 * This class represents my gauss-jordan-algorithm.
 * @author cotto
 */
class algebra 
{
    /**
     * Checks parameters used for the gauss-jordan-algorithm.
     * @param mat A quadratic matrix.
     * @param vec A row vector.
     * @return Solution.
     * @throws java.lang.IllegalArgumentException
     */
    
    public static matrix algebra(matrix mat, matrix vec) throws IllegalArgumentException
    {
        if(!vec.isVector())
        {
            throw new IllegalArgumentException("algebra: vec is not a vector.");
        }
        else if(vec.getCols() > 1)
        {
            throw new IllegalArgumentException("algebra: wrong vector direction.");
        }
        else if(!mat.isQuadratic())
        {
            throw new IllegalArgumentException("algebra: mat is not quadratic.");
        }
        else if(vec.getRows() != mat.getCols())
        {
            throw new IllegalArgumentException("algebra: mat and vec don't fit to each other.");
        }
        else
        {
            return run_gauss_jordan(mat, vec);
        }
    }
    
    /**
     * This method solves a linear equation system by 
     * using the gauss-jordan-algorithm. Neither fast
     * nor beautiful, but it works. And while doing this 
     * late at night this is okay.
     * @param mat A quadratic matrix.
     * @param vec A vector.
     * @return Solution to the equation system.
     */
    
    private static matrix run_gauss_jordan(matrix mat, matrix vec)
    {
        matrix solve = mat.clone();    // Clone matrix and vector to get work copy to
        matrix var_vec = vec.clone(); // solve the gauss-jordan-algorithm.
        
        for(int j = 0; j < solve.getRows(); j++)
        {
           boolean done=false; 
           for(int i = j; (!done) && (i < solve.getRows()); i++)  
            {                                                                               
               if(!solve.getMatrixValue(j, i).isNull())    // Find a row with any other value
                {                                                           // than zero.
                    if(i != j)  // If i is not in the right position swap both rows.
                    {
                        solve.swapRows(j, i);
                        var_vec.swapRows(j, i);
                    }
                    done = true; // We found something. We don't need to find another one for
                }                       // this column.
            }
            
           {
               rational temp = var_vec.getMatrixValue(0, j).clone(); // temp only exists in this block.
               temp.div(solve.getMatrixValue(j, j));
               var_vec.setMatrixValue(0, j, temp);                              // Normalize this row
           }
            for(int i = solve.getCols() - 1; i >= 0; i--)                               // of matrix solve and
            {                                                                                                // and vector var_vec.
                rational temp_value = solve.getMatrixValue(i, j).clone();
                temp_value.div(solve.getMatrixValue(j, j));
                solve.setMatrixValue(i, j, temp_value);
            }
            System.out.println(solve.toString());
            System.out.println(var_vec.toString());
            
            for(int i = j + 1; i < solve.getRows(); i++) //Kill all values below.
            {
                if(solve.getMatrixValue(j, i).isNull())
                {
                    continue; // If we got nothing to erase chill out.
                }
                else
                {
                    rational count = solve.getMatrixValue(j, i).clone();      // How much do we have to cut?
                    rational temp = var_vec.getMatrixValue(0, j).clone();  // Temporary rational value for calculation issues.
                    rational temp2 = var_vec.getMatrixValue(0, i).clone();// Temporary rational value for calculation issues.
                    temp.mult(count);                                                       // Modify rows we have to.
                    temp2.subs(temp);
                    var_vec.setMatrixValue(0, i, temp2);
                    for(int k = solve.getCols() - 1; k >= 0; k--)
                    {
                        if(solve.getMatrixValue(k, j).isNull()) // Why shall we do anything if nothing is to do?
                        {
                            continue;
                        }
                        else        
                        {        
                            temp = solve.getMatrixValue(k, j).clone();
                            temp.mult(count);
                            temp2 = solve.getMatrixValue(k, i).clone();
                            temp2.subs(temp);
                            solve.setMatrixValue(k, i, temp2);
                        }
                    }
                }
            }
        }           // Now we got an upper triangular matrix having only ones at its main diagonal.
        for(int j = solve.getCols() - 1; j > 0; j--) // And now the other way around.
        {
            for(int i = j - 1; i >= 0; i--)
            {
                 if(solve.getMatrixValue(j, i).isNull())
                {
                    continue; // If we got nothing to erase chill out.
                }
                else
                {
                    rational count = solve.getMatrixValue(j, i).clone();      // How much do we have to cut?
                    rational temp = var_vec.getMatrixValue(0, j).clone();  // Temporary rational value for calculation issues.
                    rational temp2 = var_vec.getMatrixValue(0, i).clone();// Temporary rational value for calculation issues.
                    temp.mult(count);                                                       // Modify rows we have to.
                    temp2.subs(temp);
                    var_vec.setMatrixValue(0, i, temp2);
                    for(int k = solve.getCols() - 1; k >= 0; k--)
                    {
                        if(solve.getMatrixValue(k, j).isNull()) // Why shall we do anything if nothing is to do?
                        {
                            continue;
                        }
                        else        
                        {        
                            temp = solve.getMatrixValue(k, j).clone();
                            temp.mult(count);
                            temp2 = solve.getMatrixValue(k, i).clone();
                            temp2.subs(temp);
                            solve.setMatrixValue(k, i, temp2);
                        }
                    }
                }
            }
        } // And now, we've got a standard matrix and we have got our solution
          // to the linear equation system by reading the values given by vector
          // var_vec.
        return var_vec;
    }
}