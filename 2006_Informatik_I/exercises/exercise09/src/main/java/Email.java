public class Email 
{
    private String sender;
    private String[] recipients;
    private String subject;
    private String content;
    
    public Email(String absender, String[] empfaenger, 
            String betreff, String inhalt) 
    {
        int i = 0;
        recipients = new String[empfaenger.length];
        
        sender = absender;
        subject = betreff;
        content = inhalt;
        while(i < empfaenger.length)
        {
            recipients[i]=empfaenger[i];
            
            i++;
        }
    }
    
    public void print()
    {
        System.out.println("Absender: " + sender);
        System.out.print("Empfänger: ");
        for(int i = 0; i != recipients.length; i++)
        {
            System.out.print(recipients[i] + "      ");
        }
        System.out.println("\nBetreff: " + subject);
        System.out.println("\n\n" + content);
    }
    
    public void sort()
    {
        char[] cArr = content.toCharArray();
        boolean sortiert = true;
        
        do // Implementierter Bubblesort
        {
            sortiert = true;
          
            for (int i = 1; i < cArr.length; i++) 
            {
                if (cArr[i - 1] > cArr[i]) 
                {
                    final char tmp = cArr[i - 1];
                    cArr[i - 1] = cArr[i];
                    cArr[i] = tmp;
                    sortiert = false;
                }
            }
      } 
      while (!sortiert);
      
      content = new String(cArr);
    }
    
    public void sortIte()      //Anfang der Sortieralgorythmen für die iterative Sortierung.
    {
        char[] cArr = content.toCharArray();
        boolean sortiert = true;
        
        for (int index=0; index<cArr.length-1; index++)
        { //durchlaufe das Array & suche Minimum des unsortierten rechten Teilarrays
            int minIdx = minimum(cArr, index, cArr.length-1);
            vertausche(cArr,index,minIdx);
        }
      
        content = new String(cArr);
    }
    
    private void vertausche(char[] array, int x, int y)
    {  
        char zwischenspeicher;
        zwischenspeicher = array[x];
        array[x] = array[y];
        array[y] = zwischenspeicher;
    }
    
    private int minimum(char[] array, int anfang, int ende)
    {
        int minIdx = anfang;
        for (int index=anfang+1; index<=ende; index++)
        { //durchlaufe das Array
            if (array[index] < array[minIdx])
            {
                minIdx = index;                  //neues Minimum gefunden
            }
        }
        return minIdx;
    }
}
