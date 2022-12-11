/**
 * An object of this class is representing an allowed coordinate on a MxN chess board.
 * For solving the knightproblem on a standard chess board set M=N=8.
 */

public class BoardCoordinate 
{
  /**
   * Preset number of columns.
   */
  public static int M = 5; // 1 bis 9
  /**
   * Preset number of rows.
   */
  public static int N = 5; // 1 bis 9
  /**
   * Code of the columns to get a code as found on chess boards.
   */
  private static final String column_list = "ABCDEFGHI";
  /**
   * Code of the rows to get a code as found on chess boards.
   */
  private static final String row_list = "123456789";
  /**
   * Number of the column of the position.
   */
  private int column;

  /**
   * Number of the row of the position.
   */
  private int row;

  /**
   * Creates a coordinate.
   * @param column Number of column.
   * @param row Number of row.
   */

  public BoardCoordinate(int column, int row) 
  {
    if (inRange(column, row)) 
    {
      this.column = column;
      this.row = row;
    } 
    else 
    {
      this.column = 0;
      this.row = 0;
    }
  }

  /**
   * Creates coordinate on chess board.
   * @param coordinate Common coordinates on chess boards as A1, A5 or H3.
   */

  public BoardCoordinate(String coordinate) 
  {
    this(0, 0);

    if (inRange(coordinate)) 
    {
      char column_char = coordinate.charAt(0);
      this.column = column_list.indexOf(column_char + "");

      char row_char = coordinate.charAt(1);
      this.row = (N - 1) - row_list.indexOf(row_char + "");
    }
  }
  
  /**
   * Sets Dimension of the chess boards.
   * @param column Number of columns.
   * @param row Number of rows.
   */
  
  public static void setDimension(int set_col, int set_row)
  {
    M = set_col;
    N = set_row;
    System.out.println("Setting: Cols=" + set_col + ", Rows=" + set_row);
  }

  /**
   * Checks position (column, row) existing on chess board.
   * @param column Number of the column.
   * @param row Number of the row.
   * @return true, if coordinate exists.
   */

  public static boolean inRange(int column, int row) 
  {
    return (column >= 0) && (column < M) && (row >= 0) && (row < N);
  }

  /**
   * Checks position on the board.
   * @param coordinate Coordinate on chess board.
   * @return true, if coordinate exists.
   */

  public static boolean inRange(String coordinate) 
  {
    if (coordinate.length() == 2) 
    {
      char column_char = coordinate.charAt(0);
      char row_char = coordinate.charAt(1);
      return inRange(column_list.indexOf(column_char + ""), row_list.indexOf(row_char + ""));
    } 
    else 
    {
      return false;
    }
  }

  /**
   * Returns number of column.
   * @return Number of column.
   */
  public int getColumn() 
  {
    return this.column;
  }

  /**
   * Returns number of row.
   * @return Number of row.
   */
  public int getRow() 
  {
    return this.row;
  }
}