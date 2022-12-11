public class Aufzaehler
{
    public static void main(String args[])
    {
        char ausdruck[] = "1 - 2 * 3 / 4".toCharArray();
        readTerm(ausdruck);
    }
    
    static String zahlenfeld[] = {"Null", "Eins", "Zwei", "Drei", "Vier", "Fünf", "Sechs", "Sieben", "Acht", "Neun"};
    
    static void readTerm(char f[])
    {
        for(int i = 0; i < f.length; i++)
        {
            String ausgabe = "";
            char c = f[i];
            if(c >= '0' && c <= '9')
            {
                int index = c - '0';
                ausgabe = zahlenfeld[index] + " ";
            }
            else
            {
                switch(c)
                {
                    case ' ':
                    case '\t':
                        break;
                    case '+':
                        ausgabe = "plus ";
                        break;
                    case '-':
                        ausgabe = "minus ";
                        break;
                    case '*':
                        ausgabe = "mal ";
                        break;
                    case '/':
                        ausgabe = "geteilt durch ";
                        break;
                    default:
                        ausgabe = "nicht gültig";
                }
            }
            
            System.out.print(ausgabe);
        }
    }
}