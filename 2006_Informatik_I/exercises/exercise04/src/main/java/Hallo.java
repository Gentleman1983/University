import java.util.*;
import java.io.*;

class Hallo
{
    String text;
    
    Hallo(String text)
    {
        this.text = "Heute ist " + Calendar.getInstance().getTime() + " und " + text;
    }
    
    void ausgabe()
    {
        System.out.println(text);
    }
}