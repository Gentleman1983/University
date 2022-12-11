import java.util.Random;

public class sorter 
{
    Random random = new Random();

    public int insertionSort(Comparable Arr[])
    {
        cmpcnt = 0;
        for(int i = 0; i < Arr.length; i++) 
        {
            Comparable key = Arr[i];
            int j = i;
            
            for(; j > 0 && increaseCC() && Arr[j - 1].compareTo(key) > 0;) 
            {
                Arr[j] = Arr[j - 1];
                j = j - 1;
            }
            Arr[j] = key;
        }
        return cmpcnt;
    }

    private int cmpcnt;

    private boolean increaseCC() 
    {
        cmpcnt++;
        return true;
    }

    public int quickSort(Comparable[] A, boolean rand) 
    {
        cmpcnt = 0;
        recursiveQuickSort(A, 0, A.length-1, rand);
        return cmpcnt;
    }

    void recursiveQuickSort(Comparable[] Arr, int start, int ende, boolean rand) 
    {
        if(start < ende) 
        {
            int p = partition(Arr, start, ende, rand);
            recursiveQuickSort(Arr, start, p - 1, rand);
            //printList(Arr);
            recursiveQuickSort(Arr, p + 1, ende, rand);
            //printList(Arr);
        }
    }

    private int partition(Comparable[] Arr, int start, int ende, boolean rand) 
    {
        if(rand) 
        {
            int p = start + random.nextInt(ende-start+1);
            swap(Arr, start, p);
        }
        int p = start;
        for(int n = p + 1; n <= ende; n++)
        {
            if(increaseCC() && Arr[n].compareTo(Arr[p]) < 0) 
            {
                swap(Arr, n, p + 1);
                swap(Arr, p, p + 1);
                p++;
            }
        }
        return p;
    }

    private void swap(Comparable[] Arr, int index1, int index2) 
    {
        Comparable temp = Arr[index1];
        Arr[index1] = Arr[index2];
        Arr[index2] = temp;
    }
    
    public int heapSort(Comparable[] Arr, int heapsize) 
    {
        if(heapsize <= 1)
        {
            return -1;
        }
	cmpcnt = 0;
	buildheap(Arr, heapsize);
	for(int h = heapsize - 1; h >= 1; h--) 
        {
            swap(Arr, 0, h);
            reheap(Arr, 0, h);
	}
	return cmpcnt;
    }
    
    private void buildheap(Comparable[] Arr, int n) 
    {
        for(int j = (n / 2) - 1; j >= 0; j--)
        {
            reheap(Arr, j, n);
        }
    }
    
    private void reheap(Comparable[] Arr, int k, int l) 
    {
        if(2 * k + 1 >= l)
        {
            return;
        }
	int MaxSon = 0;
	if(2 * k + 1 == l - 1)
        {
            MaxSon = 2 * k + 1;
        }
	else 
        {
            if(increaseCC() && Arr[2 * k + 1].compareTo(Arr[2 * k + 2]) > 0)
            {
                MaxSon = 2 * k + 1;
            }
            else
            {
                MaxSon = 2 * k + 2;
            }
	}
        if(increaseCC() && Arr[k].compareTo(Arr[MaxSon]) >= 0)
        {
            return;
        }
	swap(Arr, k, MaxSon);
	reheap(Arr, MaxSon, l);
    }
    
    protected static SimpleKey[] generateSKey(int Arrlength) 
    {
        SimpleKey SKArr[] = new SimpleKey[Arrlength];
	for(int i = 0; i < SKArr.length; i++) 
        {
            int val = i % 2;
            SKArr[i] = new SimpleKey(new Integer(val));
	}
	return SKArr;
    }
    
        protected static ExtendedKey[] generateEKey(int Arrlength) 
    {
        ExtendedKey EKArr[] = new ExtendedKey[Arrlength];

	for(int i = 0; i < EKArr.length; i++) 
        {
            int val = i % 2;
            EKArr[i] = new ExtendedKey(new Integer(val), new Integer(i));
	}
	return EKArr;
    }
    
    public static void main(String[] EventArgs) 
    {
        int n;
        if(EventArgs.length != 1) // Bei fehlender Eingabe gebe Warnung aus und verwende Standard-Wert!
        {
            System.out.println("Verwende Standardarraylänge von 42 Einträgen.\nFür eigene Arraylänge benutze Aufruf: java sorter <Size of Array>");
            n = 42;
        }
        else // Ansonsten wandle die Eingabe in eine Integer um!
        {
            n = Integer.parseInt(EventArgs[0]);
        }
        
        SimpleKey[] skey = generateSKey(n);
        ExtendedKey[] ekey = generateEKey(n);
        
        int result = 0, result2 = 0, result3 = 0, result4 = 0, result5 = 0, result6 = 0, result7 = 0, result8 = 0, result9 = 0, result10 = 0;
        sorter S = new sorter(); // Anlegen der Sorterklasse
        
        Integer[] liste = new Integer[n]; //Anlegen der Liste für Aufgabenteil
        Integer[] liste2 = new Integer[n];//4b
        Integer[] liste3 = new Integer[n];//Anlegen der zuf. Liste
        Integer[] dummy = new Integer[n]; // Dummy-Liste
        
        for(int i = 0; i < n; i++) // Fuellen der aufsteigenden Liste
        {
            liste[i] = java.lang.Integer.valueOf(i + 1);
        }
        
        for(int i = 0; i < n; i++) // Fuellen der absteigenden Liste
        {
            liste2[n - i - 1]=java.lang.Integer.valueOf(i + 1);
        }
        
        for(int i = 0; i < n; i++) // Fuellen der Dummy-Liste
        {
            dummy[i] = java.lang.Integer.valueOf(i + 1);
        }
        
        Random getNumber = new Random();
            
        for(int i = 0; i < n; ) // Fuellen der zufaelligen Liste
        {
            int idx = getNumber.nextInt(n);
            if(dummy[idx] != java.lang.Integer.valueOf(0))
            {
                liste3[i] = dummy[idx];
                dummy[idx] = java.lang.Integer.valueOf(0);
                i++;
            }
        }
        
        System.out.print("Zufälliges Array ");
        for(int i = 0; i < n; i++)
        {
            if(i < n - 1)
            {
                System.out.print(liste3[i] + ",");
            }
            else
            {
                System.out.println(liste3[i]);
            }
        }
        result = S.insertionSort(liste3);
        System.out.println("InsertionSort: " + result);
        result = S.quickSort(liste3,true);
        result2 = S.quickSort(liste3,true);
        result3 = S.quickSort(liste3,true);
        result4 = S.quickSort(liste3,true);
        result5 = S.quickSort(liste3,true);
        System.out.println("Randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSort(liste3,false);
        System.out.println("QuickSort: " + result);
        result = S.heapSort(liste3,liste3.length);
        System.out.println("HeapSort: " + result);
        
        System.out.println("\nSortiertes Array 1,...," + n); //Aufgabenteil 4b) aufsteigende Liste
        result = S.insertionSort(liste);
        System.out.println("InsertionSort: " + result);
        result = S.quickSort(liste,true);
        result2 = S.quickSort(liste,true);
        result3 = S.quickSort(liste,true);
        result4 = S.quickSort(liste,true);
        result5 = S.quickSort(liste,true);
        System.out.println("Randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSort(liste,false);
        System.out.println("QuickSort: " + result);
        result = S.heapSort(liste,liste.length);
        System.out.println("HeapSort: " + result);
        
        System.out.println("\nSortiertes Array " + n + ",...,1"); //Aufgabenteil 4b) absteigende Liste
        result = S.insertionSort(liste2);
        System.out.println("InsertionSort: " + result);
        result = S.quickSort(liste2,true);
        result2 = S.quickSort(liste2,true);
        result3 = S.quickSort(liste2,true);
        result4 = S.quickSort(liste2,true);
        result5 = S.quickSort(liste2,true);
        System.out.println("Randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSort(liste2,false);
        System.out.println("QuickSort: " + result);
        result = S.heapSort(liste2,liste2.length);
        System.out.println("HeapSort: " + result);
        
        System.out.println("\nPrüfung von Keys der Länge 10:");
        result = S.insertionSort(skey);
        result2 = S.insertionSort(ekey);
        System.out.println("InsertionSort: " + result + " (SimpleKey), " + result2 + " (ExtendedKey)");
        skey = generateSKey(10);
        ekey = generateEKey(10);
        result = S.quickSort(skey,false);
        result2 = S.quickSort(ekey,false);
        System.out.println("QuickSort: " + result + " (SimpleKey), " + result2 + " (ExtendedKey)");
        System.out.print("Randomisierter QuickSort: ");
        skey = generateSKey(10);
        result = S.quickSort(skey,true);
        skey = generateSKey(10);
        result2 = S.quickSort(skey,true);
        skey = generateSKey(10);
        result3 = S.quickSort(skey,true);
        skey = generateSKey(10);
        result4 = S.quickSort(skey,true);
        skey = generateSKey(10);
        result5 = S.quickSort(skey,true);
        ekey = generateEKey(10);
        result6 = S.quickSort(ekey,true);
        ekey = generateEKey(10);
        result7 = S.quickSort(ekey,true);
        ekey = generateEKey(10);
        result8 = S.quickSort(ekey,true);
        ekey = generateEKey(10);
        result9 = S.quickSort(ekey,true);
        ekey = generateEKey(10);
        result10 = S.quickSort(ekey,true);
        System.out.println(result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5 + " (SimpleKey), " + result6 + ", " + result7 + ", " + result8 + ", " + result9 + ", " + result10 + " (ExtendedKey)");
        skey = generateSKey(10);
        ekey = generateEKey(10);
        result = S.heapSort(skey,skey.length);
        result2 = S.heapSort(ekey,ekey.length);
        System.out.println("HeapSort: " + result + " (SimpleKey), " + result2 + " (ExtendedKey)");
        System.out.println("Sortierter SimpleKey-Heap:");
        for(int i = 0; i < 10; i++)
        {
            if(i!=9)
            {
                System.out.print("|\t" + i + "\t");
            }
            else
            {
                System.out.println("|\t" + i + "\t|");
            }
        }
        for(int i = 0; i < 10; i++)
        {
            if(i!=9)
            {
                System.out.print("|\t" + Integer.parseInt(skey[i].getKey().toString()) + "\t");
            }
            else
            {
                System.out.println("|\t" + Integer.parseInt(skey[i].getKey().toString()) + "\t|");
            }
        }
        System.out.println("Sortierter ExtendedKey-Heap:");
        for(int i = 0; i < 10; i++)
        {
            if(i!=9)
            {
                System.out.print("|\t" + i + "\t");
            }
            else
            {
                System.out.println("|\t" + i + "\t|");
            }
        }
        for(int i = 0; i < 10; i++)
        {
            if(i!=9)
            {
                System.out.print("|\t(" + Integer.parseInt(ekey[i].getKey().toString()) + "," + Integer.parseInt(ekey[i].getPos().toString()) + ")\t");
            }
            else
            {
                System.out.println("|\t(" + Integer.parseInt(ekey[i].getKey().toString()) + "," + Integer.parseInt(ekey[i].getPos().toString()) + ")\t|");
            }
        }
        
        System.out.println("\nPrüfung von Keys der Länge 10:");
        result = S.insertionSort(skey);
        result2 = S.insertionSort(ekey);
        System.out.println("InsertionSort: " + result + " (SimpleKey), " + result2 + " (ExtendedKey)");
        skey = generateSKey(n);
        ekey = generateEKey(n);
        result = S.quickSort(skey,false);
        result2 = S.quickSort(ekey,false);
        System.out.println("QuickSort: " + result + " (SimpleKey), " + result2 + " (ExtendedKey)");
        System.out.print("Randomisierter QuickSort: ");
        skey = generateSKey(n);
        result = S.quickSort(skey,true);
        skey = generateSKey(n);
        result2 = S.quickSort(skey,true);
        skey = generateSKey(n);
        result3 = S.quickSort(skey,true);
        skey = generateSKey(n);
        result4 = S.quickSort(skey,true);
        skey = generateSKey(n);
        result5 = S.quickSort(skey,true);
        ekey = generateEKey(n);
        result6 = S.quickSort(ekey,true);
        ekey = generateEKey(n);
        result7 = S.quickSort(ekey,true);
        ekey = generateEKey(n);
        result8 = S.quickSort(ekey,true);
        ekey = generateEKey(n);
        result9 = S.quickSort(ekey,true);
        ekey = generateEKey(n);
        result10 = S.quickSort(ekey,true);
        System.out.println(result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5 + " (SimpleKey), " + result6 + ", " + result7 + ", " + result8 + ", " + result9 + ", " + result10 + " (ExtendedKey)");
        skey = generateSKey(n);
        ekey = generateEKey(n);
        result = S.heapSort(skey,skey.length);
        result2 = S.heapSort(ekey,ekey.length);
        System.out.println("HeapSort: " + result + " (SimpleKey), " + result2 + " (ExtendedKey)");
        System.out.println("Sortierter SimpleKey-Heap:");
        for(int i = 0; i < n; i++)
        {
            if(i != n - 1)
            {
                System.out.print("|\t" + i + "\t");
            }
            else
            {
                System.out.println("|\t" + i + "\t|");
            }
        }
        for(int i = 0; i < n; i++)
        {
            if(i != n - 1)
            {
                System.out.print("|\t" + Integer.parseInt(skey[i].getKey().toString()) + "\t");
            }
            else
            {
                System.out.println("|\t" + Integer.parseInt(skey[i].getKey().toString()) + "\t|");
            }
        }
        System.out.println("Sortierter ExtendedKey-Heap:");
        for(int i = 0; i < n; i++)
        {
            if(i != n - 1)
            {
                System.out.print("|\t" + i + "\t");
            }
            else
            {
                System.out.println("|\t" + i + "\t|");
            }
        }
        for(int i = 0; i < n; i++)
        {
            if(i != n - 1)
            {
                System.out.print("|\t(" + Integer.parseInt(ekey[i].getKey().toString()) + "," + Integer.parseInt(ekey[i].getPos().toString()) + ")\t");
            }
            else
            {
                System.out.println("|\t(" + Integer.parseInt(ekey[i].getKey().toString()) + "," + Integer.parseInt(ekey[i].getPos().toString()) + ")\t|");
            }
        }
    }
}