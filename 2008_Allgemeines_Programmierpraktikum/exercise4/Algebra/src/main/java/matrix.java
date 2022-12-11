/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 4 - Christian Otto - Algebra
 */

import java.util.*;

/**
 * This class represents my matrices.
 * @author cotto
 */
public class matrix 
{
    // Class values
    private int cols;
    private int rows;
    private rational[][] mat_val;
    
    // Constructors
    
    /**
     * Creates a matrix.
     * @param cols Number of columns.
     * @param rows Number of rows.
     */
    matrix(int cols, int rows)
    {
        this.setDim(cols, rows);
    }
    
    /**
     * Creates a matrix and it is being filled with
     * values.
     * @param cols Number of columns.
     * @param rows Number of rows.
     * @param val Linked list of rows of the matrix.
     * @throws java.lang.IllegalArgumentException
     */
    
    matrix(int cols, int rows, LinkedList<rational[]> val) throws IllegalArgumentException
    {
        if((val.size() != rows) && (val.getFirst().length != cols))
        {
            throw new IllegalArgumentException("matrix: Array doesn't fit matrix dimensions!");
        }
        else
        {
            this.setDim(cols, rows);
            for(int r = 0; r < rows; r++)
            {
                rational[] matrix_row = val.removeFirst();
                for(int c=0; c < cols; c++)
                {
                    mat_val[c][r] = matrix_row[c];
                }
            }
        }
    }
    
    // Class methods
    
    /**
     * Multiplies to matrices.
     * @param mat1 Matrix this is multiplied with.
     * @return Result.
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    
    public matrix mult(matrix mat1) throws ArrayIndexOutOfBoundsException
    {
        if(this.cols != mat1.getRows())
        {
            throw new ArrayIndexOutOfBoundsException("mult: dimensions of matrices don't fit.");
        }
        else
        {
            matrix ret_matrix = new matrix(mat1.getCols(), this.rows);
            
            for(int c = 0; c < mat1.getCols(); c++)
            {
                for(int r = 0; r < this.rows; r++)
                {
                    for(int i = 0; i < this.cols; i++)
                    {   // ret_matrix[c][r] = ret_matrix[c][r] + this.mat_val[i][r] * mat1.mat_val[c][i]
                        if(ret_matrix.getMatrixValue(c, r) == null)
                        {
                            ret_matrix.setMatrixValue(c, r, new rational(0, 1));
                        }
                        rational temp = this.getMatrixValue(i, r).clone();
                        temp.mult(mat1.getMatrixValue(c, i));
                        temp.add(ret_matrix.getMatrixValue(c, r));
                        ret_matrix.setMatrixValue(c, r, temp);
                    }
                }
            }
            return ret_matrix;
        }
    }
    
    /**
     * Multiplies mat1 and mat2.
     * @param mat1 Matrix being multiplied by mat2.
     * @param mat2 Matrix multiplied to mat1.
     * @return Result.
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    
    public static matrix mult(matrix mat1, matrix mat2) throws ArrayIndexOutOfBoundsException
    {
         if(mat1.getCols() != mat2.getRows())
        {
            throw new ArrayIndexOutOfBoundsException("mult: dimensions of matrices don't fit.");
        }
        else
        {
             return mat1.mult(mat2);
        }
    }
    
    /**
     * Sets Dimension of cols, rows, and the value 
     * array.
     * @param cols Number of columns.
     * @param rows Number of rows.
     */
    
    private void setDim(int cols, int rows)
    {
        this.cols = cols;
        this.rows = rows;
        mat_val = new rational[cols][rows];
    }
    
    /**
     * Sets a field of the Matrix.
    * @param col Number of column [0...(cols-1)].
     * @param row Number of row [0...(rows-1)].
     * @param value Value to be set.
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    
    public void setMatrixValue(int col, int row, rational value) throws ArrayIndexOutOfBoundsException
    {
        if((col >= this.cols) || (col < 0))
        {
            throw new ArrayIndexOutOfBoundsException("getMatrixValue: col doen't fit matrix dimensions!");
        }
        else if((row >= this.rows) || (row < 0))
        {
            throw new ArrayIndexOutOfBoundsException("getMatrixValue: row doen't fit matrix dimensions!");
        }
        else
        {
            mat_val[col][row] = value;
        }
    }
    
    /**
     * Swaps two rows of a matrix.
     * @param row1 Row exchanged by row2.
     * @param row2 Row exchanged by row1.
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    
    public void swapRows(int row1, int row2) throws ArrayIndexOutOfBoundsException
    {
        if((row1 >= this.rows) || (row1 < 0))
        {
            throw new ArrayIndexOutOfBoundsException("getMatrixValue: row1 doen't fit matrix dimensions!");
        }
        else if((row2 >= this.rows) || (row2 < 0))
        {
            throw new ArrayIndexOutOfBoundsException("getMatrixValue: row2 doen't fit matrix dimensions!");
        }
        else
        {
            rational temp; // Field to store our temporary references
        
            for(int c = 0; c < this.cols; c++) // Let's swap!
            {
                temp = mat_val[c][row1];
                mat_val[c][row1] = mat_val[c][row2];
                mat_val[c][row2] = temp;
            }
        }
    }
    
    /**
     * Returns the complete Matrix!
     * @return 
     */
    
    public rational[][] getMatrix()
    {
        return mat_val;
    }
    
    /**
     * Returns a field of the matrix.
     * @param col Number of column [0...(cols-1)].
     * @param row Number of row [0...(rows-1)].
     * @return Field of the matrix.
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    
    public rational getMatrixValue(int col, int row) throws ArrayIndexOutOfBoundsException
    {
        if((col >= cols) || (col < 0))
        {
            throw new ArrayIndexOutOfBoundsException("getMatrixValue: col doen't fit matrix dimensions!");
        }
        else if((row >= rows) || (row < 0))
        {
            throw new ArrayIndexOutOfBoundsException("getMatrixValue: row doen't fit matrix dimensions!");
        }
        else
        {
            return mat_val[col][row];
        }
    }
    
    /**
     * Returns number of rows.
     * @return Number of rows.
     */
    
    public int getRows()
    {
        return this.rows;
    }
    
    /**
     * Returns number of cols.
     * @return Number of cols.
     */
    
    public int getCols()
    {
        return this.cols;
    }
    
    /**
     * Clones current matrix.
     * @return Cloned matrix.
     */
    
    public matrix clone()
    {
        matrix ret_matrix = new matrix(this.cols, this.rows);
        
        for(int c = 0; c < this.cols; c++)
        {
            for(int r = 0; r < this.rows; r++)
            {
                ret_matrix.setMatrixValue(c, r, this.mat_val[c][r]);
            }
        }
        
        return ret_matrix;
    }
    
    /**
     * Checks equality of two matrices.
     * @param mat1 Matrix compared to this.
     * @return true, if both matrices are equal
     */
    
    public boolean equals(matrix mat1)
    {
        boolean ret_value = true;
        
        if((this.cols != mat1.getCols()) || (this.rows != mat1.getRows()))
        {
            ret_value = false; // Wrong dimension!
        }
        else
        {
            for(int c = 0; c <= this.cols; c++)
            {
                for(int r = 0; r <= this.rows; r++)
                {
                    if(this.mat_val[c][r].equals(mat1.getMatrixValue(c, r)))
                    {
                        continue; // Don't do anything. But it looks quite more
                    }                   // pretty than the negated way to build this
                    else              // argument.
                    {
                        ret_value = false; // Found wrong value
                    }
                }
            }
        }
        
        return ret_value; // true, if both matrices are equal.
    }
    
    /**
     * Checks equality of two matrices.
     * @param mat1 Matrix compared to mat2.
     * @param mat2 Matrix compared to mat1.
     * @return true, if both matrices are equal
     */
    
    public static boolean equals(matrix mat1, matrix mat2)
    {
        return mat1.equals(mat2);
    }
    
    /**
     * Returns matrix as a string.
     * @return Matrix as a string.
     */
    
    public String toString()
    {
        String ret_string = "";
        
        for(int r = 0; r < this.rows; r++)
        {
            ret_string = ret_string + "(\t";
            
            for(int c = 0; c < this.cols; c++)
            {
                ret_string = ret_string + mat_val[c][r].toString() + "\t";
            }
            
            ret_string = ret_string + ")\n";
        }
        
        return ret_string;
    }
    
    /**
     * Checks if matrix is vector.
     * @return true, if matrix is vector.
     */
    
    public boolean isVector()
    {
        if((this.cols == 1) || (this.rows == 1))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Checks if matrix is quadratic.
     * @return true, if matrix is quadratic.
     */
    
    public boolean isQuadratic()
    {
        if(this.cols == this.rows)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}