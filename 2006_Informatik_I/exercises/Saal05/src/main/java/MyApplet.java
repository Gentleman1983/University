import java.applet.Applet;
import java.awt.Graphics;

public class MyApplet extends Applet
{
    String name;
    String nr;
    
    public void init()
    {
        name = getParameter("Name");
        nr   = getParameter("Matrikelnr");        
    }
    
    public void paint(Graphics g)
    {
        g.drawString("Name: " + name, 0, 10);
        g.drawString("Matrikelnr: " + nr, 0, 30);
    }
    /*
    * @param args
    */
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
    }

}
