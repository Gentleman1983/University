/**
 * Allgemeines Programmierpraktikum SS2008
 * Excercise 5 - Christian Otto - NormRational
 */

import java.util.*;

/**
 * This class tests my normalized rational values
 * by using the "Polsche Normalform".
 * @author cotto
 */
public class PolscheNormalform 
{
    public static void main(String[] EventArgs)
    {
        LinkedList<String> Events = new LinkedList<String>();
        LinkedList<NormRational> nrlist = new LinkedList<NormRational>();
        
        for(int i = 0; i < EventArgs.length; i++) //Save all Arguments into Linked List.
        {
            Events.add(EventArgs[i]);
        }
        
        boolean error = false;
        
        while(!Events.isEmpty()) // Let's calculate...
        {
            String event = Events.removeFirst();
            if(Character.isDigit(event.charAt(0))) //Do we have a number?
            {
                char[] temp_chars = event.toCharArray();
                long value = 0;
                
                for(int i = 0; i < temp_chars.length; i++)
                {
                    value *= 10;
                    value += Character.getNumericValue(temp_chars[i]);
                }
                
                nrlist.add(new NormRational(value, 1));
            }
            else if(nrlist.size() < 2) //How do we calculate only by having one or less values?
            {
                Events.clear();
                System.out.println("Syntax Error: Use e.g. java PolscheNormalform 1 2 3 4 * 5 + - /");
                error = true;
            }
            else if(event.charAt(0) == '*')
            {
                NormRational temp = nrlist.removeLast();
                temp.mult(nrlist.removeLast());
                nrlist.add(temp);
            }
            else if(event.charAt(0) == '/')
            {
                NormRational temp1 = nrlist.removeLast();
                NormRational temp2 = nrlist.removeLast();
                temp2.div(temp1);
                nrlist.add(temp2);
            }
            else if(event.charAt(0) == '+')
            {
                NormRational temp = nrlist.removeLast();
                temp.add(nrlist.removeLast());
                nrlist.add(temp);
            }
            else if(event.charAt(0) == '-')
            {
                NormRational temp1 = nrlist.removeLast();
                NormRational temp2 = nrlist.removeLast();
                temp2.mult(temp1);
                nrlist.add(temp2);
            }
            else
            {
                Events.clear();
                System.out.println("Syntax Error: Use e.g. java PolscheNormalform 1 2 3 4 * 5 + - /");
                error = true;
            }
        } // Now we should have only one value in our nrlist.
        if(!error)
        {
            System.out.println(nrlist.getFirst().toString());
        }
    }
}
