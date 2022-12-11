import java.util.Random;

public class CB 
{
    Random rnd = new Random();
    
    public void print_segment(int size, int max_size, int offset, 
            int max_offset) 
    {
        int i, r;
        
        if(size < 0)
        {
            size=0;
        }
        
        for(i = 0; i < offset; i++)
        {
            System.out.print(" ");
        }
        
        for(i = 0; i < max_size - size; i++)
        {
            System.out.print(" ");
        }
        
        System.out.print("/");
        
        for(i = 0; i < size; i++)
        {
            r = rnd.nextInt();
            
            if(size == max_size)
            {
                if(size % 2 == 0)
                {
                    System.out.print(".~");
                }
                else
                {
                    System.out.print("~.");
                }
            }
            else
            {
                if(r < 0)
                {
                    r = -r;
                }
                r = r % 20;
                
                switch(r)
                {
                    case 0:
                        System.out.print("+ ");
                        break;
                    case 1:
                        System.out.print(" +");
                        break;
                    case 2:
                        System.out.print("o ");
                        break;
                    case 3:
                        System.out.print(" o");
                        break;
                    default:
                        System.out.print("  ");
                        break;
                }
            }
        }
        
        System.out.println("\\");
        
        if((size < max_size) && (offset < max_offset))
        {
            print_segment(size + 1, max_size, offset, max_offset);
        }
        else if(offset > 0)
        {
            print_segment(max_size, max_size + 2, offset - 2, 
                    max_offset);
        }
    }
    
    public static void main(String[] args) 
    {
        CB cb = new CB();
        cb.print_segment(0, 0, 10, 10);  // Die erste 10 gibt die Zahl
                                         // der Zeilen ohne "Girlanden"
                                         // an und die 2. 10 die Zahl
                                         // der Zeilen mit Girlanden!
    }    
}
