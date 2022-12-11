import java.io.*;
public class Keyboard{
    private static DataInput stdIn =
            new DataInputStream(System.in);
    public static int readInteger(){
        Integer result = null;
        try{ result = Integer.valueOf(stdIn.readLine()); }
        catch(IOException e){ }
        return result.intValue();
   }
    public static int  readInteger(String s){
        System.out.print(s);
        System.out.flush();
        return readInteger();
    }
    public static double readDouble(){
        Double result = null;
        try{ result = Double.valueOf(stdIn.readLine()); }
        catch(IOException e){ }
        return result.doubleValue();
    }
    public static double readDouble(String s){
        System.out.print(s);
        System.out.flush();
        return readDouble();
    }
    public static boolean readBoolean(){
        Boolean result = null;
        try{ result = Boolean.valueOf(stdIn.readLine());}
        catch(IOException e){ }
        return result.booleanValue();
    }
    public static boolean readBoolean(String s){
        System.out.print(s);
        return readBoolean();
    }
}

/*Hinweis: Im Main-Programm koennt Ihr mit der Methode readInteger einen String ausgeben und gleichzeitig einen Integer-Wert einlesen
int Zahl=KeyBoard.readInteger("....")*/
