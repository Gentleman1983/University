import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZufallsTest
{
    public static void main(String args[])
    {
        int k = 20;
        int j = 0;
        BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
        
        int[] zahlen = Zufallszahlen.ziehen(k, 100);
        
        for(int i = 0; i < zahlen.length; i++)
        {
            System.out.print(zahlen[i]);
            System.out.print(" ");
        }
        
        System.out.println();
        
        while(j != k)
        {
            System.out.print("\nGeben Sie eie Zahl ein: ");
            try
            {
                j = Integer.parseInt(din.readLine());
                if(k < j)
                {
                    System.out.print("Die Zahl ist zu gross\n");
                }
                else if(k > j)
                {
                    System.out.print("Die Zahl ist zu klein\n");
                }
                else
                {
                    System.out.print("Die Zahl ist richtig!\n");
                }
            }
            catch(NumberFormatException e)
            {
                // TODO Auto-Generated Catch-Block
                e.printStackTrace();
                System.out.print("\nFehlerhafte Eingabe!!!");
                break;
            }
            catch(IOException e)
            {
                // TODO Auto-Generated Cath-Block
                e.printStackTrace();
                System.out.print("\nFehlerhafte Eingabe!!!");
                break;
            }
        }
    }
}