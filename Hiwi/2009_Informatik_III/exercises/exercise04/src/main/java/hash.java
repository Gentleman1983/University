
public class hash 
{
    public static void main(String args[])
    {
        int i = 0, end = 9999999;
        long arr[] = new long[11];
        int i1, i2, i3, i4, i5, i6, i7; 
        int tmp;
        
        for (int j = 0; j < 11; j++)
        {
            arr[j] = 0;
        }
        
        while(i <= end)
        {
            i1 = i / 1000000;
            i2 = (i - i1 * 1000000) / 100000;
            i3 = (i - i1 * 1000000 - i2 * 100000) / 10000;
            i4 = (i - i1 * 1000000 - i2 * 100000 - i3 * 10000) / 1000;
            i5 = (i - i1 * 1000000 - i2 * 100000 - i3 * 10000 - i4 * 1000) / 100;
            i6 = (i - i1 * 1000000 - i2 * 100000 - i3 * 10000 - i4 * 1000 - i5 * 100) / 10;
            i7 = i % 10;
            tmp = (i1 + 2 * i2 + 3 * i3 + 4 * i4 + 5 * i5 + 6 * i6 + 7 * i7) % 11;
            arr[tmp] += 1;
            i++;
        }
        
        for (int j = 0; j < 11; j++)
        {
            System.out.println("Bucket " + j + " enthaelt " + arr[j] + " Elemtente.");
        }
    }
}
