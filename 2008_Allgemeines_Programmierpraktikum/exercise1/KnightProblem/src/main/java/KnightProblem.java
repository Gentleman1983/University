import java.util.*;

/**
 * The algorithm for the knightproblem
 */

public class KnightProblem 
{

  /**
   * Counts IDs of the boards used.
   */

  private static long board_id = 0;

  /**
   * Looks for all solutions of the knightproblem in relation to the 
   * initial position on the board
   * @param start Initial position
   * @param debug Debug mode
   * @return List of solutions
   */

  public static LinkedList solve(BoardCoordinate start, boolean debug) 
  {
    LinkedList<Object> board_solutions = new LinkedList<Object>(); // Create linked list for solutions
    BoardOfKnightMoves empty_board = new BoardOfKnightMoves();

    solve(board_solutions, empty_board, start, debug); // Solve the problem!

    return board_solutions; // Return all solutions!
  }

  /**
   * Solves the knightproblem by using the current MxN-board an the 
   * coordinate of the coming move
   * @param results The list of results
   * @param current_board Das aktuelle MxN-Brett.
   * @param coordinate Die Brett-Koordinate des nächsten Zuges.
   * @param debug Debug mode.
   */

  private static void solve(LinkedList<Object> results, 
          BoardOfKnightMoves current_board, BoardCoordinate coordinate, 
          boolean debug) 
  {

    current_board.setKnightMoveAt(coordinate); // the move
    boolean noresults = results.isEmpty();

    if (current_board.hasSolution()) 
    {
      if (!debug || noresults)
      {
        results.add(current_board.clone()); // Add solution to list of results
        System.out.println("Board " + board_id++ + " has solution...");
      }
    } 
    else 
    {
      board_id++;
      // Only for possible moves
      LinkedList nextMoves = current_board.nextKnightMoves(coordinate);
      Iterator iter = nextMoves.listIterator();
      noresults = true; // Reset noresults for not being in debug mode
      while (noresults && iter.hasNext()) 
      {
        if(debug)
        {
          noresults = results.isEmpty(); // We found the first result!
        }
        solve(results, current_board, (BoardCoordinate)iter.next(), debug);
      }
    }
    current_board.unsetKnightMoveAt(coordinate);
  }

  /**
   * Main method searching for solutions of the knightproblem.
   * This static method uses any argument as coordinate on a chess board.
   * @param EventArgs Arguments written on the console, 
   * structure: [debug-mode][ColumnsRows]  other arguments*
   * * = only chess coordinates as A7, B1, G3 or H4 allowed.
   */

  public static void main(String[] EventArgs) 
  {
    boolean debug = false;
    int i = 0;
    
    if (!BoardCoordinate.inRange(EventArgs[i].toUpperCase()) && // Handle special syntax
                 Integer.parseInt(EventArgs[i]) < 100 && 
                 (Integer.parseInt(EventArgs[i]) == 0 ||
                 Integer.parseInt(EventArgs[i]) == 1 ||
                 Integer.parseInt(EventArgs[i]) > 10))
      {
        if (Integer.parseInt(EventArgs[i]) == 1)
        {
            debug = true; // Activate debug mode
            System.out.println("Debug mode => max. 1 Result!");
            i++;
        }
        else if (Integer.parseInt(EventArgs[i]) == 0)
        {
          i++;  
        }
        
        if (!BoardCoordinate.inRange(EventArgs[i].toUpperCase()) && 
                  10 < Integer.parseInt(EventArgs[i]))
        {
          int col = Integer.parseInt(EventArgs[i]) / 10;
          int row = Integer.parseInt(EventArgs[i]) % 10;
          BoardCoordinate.setDimension(col, row); // Set custom board dimensions
          i++;
        }
      }
    
    for(; i < EventArgs.length; i++) 
    {
      if (BoardCoordinate.inRange(EventArgs[i].toUpperCase())) 
      {
        LinkedList results = solve(new BoardCoordinate(EventArgs[i].toUpperCase()), debug);
       
        System.out.print("\nStart(" + EventArgs[i] + ") => ");

        if (results.size() > 0) 
        {
          System.out.println(results.size() + " Result(s)");
        } 
        else 
        {
          System.out.println("no Results");
        }
        System.out.println();

        Iterator iter = results.listIterator();
        while (iter.hasNext()) 
        {
          System.out.println(iter.next());
        }

      } else {
        System.out.println("Bad Argument: '" + EventArgs[i] + "'\n");
      }
    }
  }
} 
