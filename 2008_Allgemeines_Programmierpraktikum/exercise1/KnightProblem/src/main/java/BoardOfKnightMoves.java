import java.util.*;

/**
 * An object of this class represents a MxN chess board and simplifies the algorithm of the knightproblem.
 * Every position on the board represents the count of the move of a knight.
 */

public class BoardOfKnightMoves implements Cloneable 
{
  
  /**
   * Most recent move of the knight.
   */

  private int move_number;

  /**
   * 2-dimensional array representing MxN chess board.
   * Every entry stores the count of the knight move.
   */

  private int[][] board;

  /**
   * Creates a MxN chess board for knightproblem.
   */

  public BoardOfKnightMoves() {

    this.move_number = 0;
    //System.out.println("M=" + BoardCoordinate.M + ", N=" + BoardCoordinate.N);
    this.board = new int[BoardCoordinate.M][BoardCoordinate.N];

    // Mark all positions not to be visited..
    for (int column = 0; column < BoardCoordinate.M; column++) 
    {
      for (int row = 0; row < BoardCoordinate.N; row++) 
      {
        this.board[column][row] = 0;
      }
    }
  }

  /**
   * Creates a BoardOfKnightMoves and saves the current state of this instance.
   * @return Copy of instance.
   */

  public Object clone() 
  {
    BoardOfKnightMoves return_board = new BoardOfKnightMoves();

    return_board.move_number = this.move_number;

    for (int column = 0; column < BoardCoordinate.M; column++) 
    {
      for (int row = 0; row < BoardCoordinate.N; row++) 
      {
        return_board.board[column][row] = this.board[column][row];
      }
    }
    return return_board;
  }

  /**
   * Has the problem been solved?
   * @return true, if number of move is greater or equal M*N.
   */

  public boolean hasSolution() 
  {
    return this.move_number >= BoardCoordinate.M * BoardCoordinate.N;
  }

  /**
   * Returns list of all possible moves, standing on (column, row).
   * @param column The column.
   * @param row The row.
   * @return The list of possible moves.
   */

  public LinkedList nextKnightMoves(int column, int row) 
  {
    LinkedList<Object> return_list = new LinkedList<Object>();

    if (this.canMoveTo(column + 2, row + 1)) 
    {
      BoardCoordinate move1 = new BoardCoordinate(column + 2, row + 1);
      return_list.add(move1);
    }
    if (this.canMoveTo(column + 1, row + 2)) 
    {
      BoardCoordinate move2 = new BoardCoordinate(column + 1, row + 2);
      return_list.add(move2);
    }
    if (this.canMoveTo(column - 2, row + 1)) 
    {
      BoardCoordinate move3 = new BoardCoordinate(column - 2, row + 1);
      return_list.add(move3);
    }
    if (this.canMoveTo(column - 1, row + 2)) 
    {
      BoardCoordinate move4 = new BoardCoordinate(column - 1, row + 2);
      return_list.add(move4);
    }
    if (this.canMoveTo(column + 2, row - 1)) 
    {
      BoardCoordinate move5 = new BoardCoordinate(column + 2, row - 1);
      return_list.add(move5);
    }
    if (this.canMoveTo(column + 1, row - 2)) 
    {
      BoardCoordinate move6 = new BoardCoordinate(column + 1, row - 2);
      return_list.add(move6);
    }
    if (this.canMoveTo(column - 2, row - 1)) 
    {
      BoardCoordinate move7 = new BoardCoordinate(column - 2, row - 1);
      return_list.add(move7);
    }
    if (this.canMoveTo(column - 1, row - 2)) 
    {
      BoardCoordinate move8 = new BoardCoordinate(column - 1, row - 2);
      return_list.add(move8);
    }
    return return_list;
  }

  /**
   * Returns list of all possible moves from current position.
   * @param coordinate Position(column, row).
   * @return List of moves.
   */

  public LinkedList nextKnightMoves(BoardCoordinate coordinate) 
  {
    return this.nextKnightMoves(coordinate.getColumn(), coordinate.getRow());
  }

  /**
   * Moves knight to position (column, row).
   * @param column Column.
   * @param row Row.
   */

  public void setKnightMoveAt(int column, int row) 
  {
    this.move_number++;
    this.board[column][row] = this.move_number;
  }

  /**
   * Moves knight to position (coordinate).
   * @param coordinate Position(column, row).
   */
  
  public void setKnightMoveAt(BoardCoordinate coordinate) 
  {
    this.setKnightMoveAt(coordinate.getColumn(), coordinate.getRow());
  }

  /**
   * Undo knight move to (column, row).
   * @param column Column.
   * @param row Row.
   */

  public void unsetKnightMoveAt(int column, int row) 
  {
    this.board[column][row] = 0;
    this.move_number--;
  }

  /**
   * Undo knight move to (coordinate).
   * @param coordinate Position (column, row).
   */

  public void unsetKnightMoveAt(BoardCoordinate coordinate) 
  {
    this.unsetKnightMoveAt(coordinate.getColumn(), coordinate.getRow());
  }

  /**
   * Returns status of MxN board as chart.
   * @return Chart as string.
   */
  
  public String toString() 
  {
    String result = "";

    for (int row = 0; row < BoardCoordinate.N; row++) 
    {
      for (int column = 0; column < BoardCoordinate.M; column++) 
      {
        int number = this.board[column][row];
        if (number <= 0) 
        {
          result += "|  ";
        } 
        else if (number < 10) 
        {
          result += "| " + number;
        } 
        else 
        {
          result += "|" + number;
        }
      }
      result += "|\r\n";
    }

    return result;
  }

  /**
   * Checks if field exists or knight already moved to (column, row).
   * @param column Column.
   * @param row Row.
   * @return true, if position (column, row) exists and knight is able to move to.
   */

  private boolean canMoveTo(int column, int row) 
  {
    return BoardCoordinate.inRange(column, row) && (this.board[column][row] <= 0);
  }
}