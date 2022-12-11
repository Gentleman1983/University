/**
 * Convert temperatures from °C to °F.
 */

public class Fahrenheit
{

  /**
   * Converts a temperature from celsius to fahrenheit.
   * @param celsius Temperature in celsius.
   * @return Temperature in fahrenheit.
   */ 

 public static int getfahrenheit(int celsius)
  {
    return celsius*9/5+32;
  }

  /**
   * The ultimate main method.
   * @param EventArgs Array of user commands - but without any use. ;)
   */

  public static void main(String[] EventArgs)
  {
    int tc = 0; //Current temperature in °C

    while(tc <= 100)
    {
      System.out.println(tc + "\t| " + getfahrenheit(tc));
      tc += 5;
    }
  }
} 
