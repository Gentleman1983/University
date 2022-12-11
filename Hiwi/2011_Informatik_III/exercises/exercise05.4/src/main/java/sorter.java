import java.util.Random;

public class sorter
{
    Random random = new Random();

    // Counter for key comparations.

    private int cmpcnt;

    // This method is used to increase the counter for key comparations.
    private boolean increaseCC()
    {
        cmpcnt++;
        return true;
    }

    // Insertion Sort.
    @SuppressWarnings("unchecked")
    public int insertionSort(Comparable Arr[])
    {
        cmpcnt = 0;
        for(int i = 0; i < Arr.length; i++)
        {
            Comparable key = Arr[i];
            for(int j = i; j > 0 && increaseCC() && Arr[j - 1].compareTo(key) > 0; j--)
            {
                Arr[j] = Arr[j - 1];
            }
            Arr[j] = key;
        }
        return cmpcnt;
    }

    // QuickSort.
    public int quickSort(Comparable[] A, boolean rand)
    {
        cmpcnt = 0;
        recursiveQuickSort(A, 0, A.length - 1, rand);
        return cmpcnt;
    }

    void recursiveQuickSort(Comparable[] Arr, int start, int end, boolean rand)
    {
        if(start < end)
        {
            // Find cut element.
            int cut = partition(Arr, start, end, rand);

            // Sort left part of the array.
            recursiveQuickSort(Arr, start, cut - 1, rand);

            // Sort right part of the array.
            recursiveQuickSort(Arr, cut + 1, end, rand);
            
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(Comparable[] Arr, int start, int end, boolean rand)
    {
        int pivot;

        if(rand)
        {
            // Select random cut.
            pivot = start + random.nextInt(end - start);
        }
        else
        {
            // Select center element as cut.
            pivot = (end - start) / 2;
        }

        Comparable pivotElement = Arr[pivot];

        // Initialize pointers.
        int left = start;
        int right = end;

        // Move elements to the correct side of the pivot element.
        while (left < right) {
            if (increaseCC() && Arr[right].compareTo(pivotElement) > 0) {
                right--;
            }
            else if (increaseCC() && Arr[left].compareTo(pivotElement) <= 0){
                left++;
            }
            else {  // Must swap array.
                swap(Arr, left, right);
            }
        }

        swap(Arr, start, right);
        return right;

    }

    private void swap(Comparable[] Arr, int index1, int index2)
    {
        Comparable temp = Arr[index1];
        Arr[index1] = Arr[index2];
        Arr[index2] = temp;
    }

    // This second implementation was programmed to have a very small size of key comparations.
    public int quickSortMod(Comparable[] list, boolean randomize) {
		cmpcnt = 0;
		recursiveQuickSortMod(list, 0, list.length - 1, randomize);
		return cmpcnt;
	}

	protected void recursiveQuickSortMod(Comparable[] list, int first, int last,
			boolean randomize) {
		if (first < last) {
			int p = partitionMod(list, first, last, randomize);
			recursiveQuickSortMod(list, first, p - 1, randomize);
			recursiveQuickSortMod(list, p + 1, last, randomize);
		}
	}

    @SuppressWarnings("unchecked")
	protected int partitionMod(Comparable[] list, int first, int last,
			boolean randomize) {
		if (randomize) {
			int pivot = first + random.nextInt(last - first + 1);
			swap(list, first, pivot);
		}
		int pivot = first;
		for (int n = pivot + 1; n <= last; n++) {
			if (increaseCC() && list[n].compareTo(list[pivot]) < 0) {
				swap(list, n, pivot + 1);
				swap(list, pivot, pivot + 1);
				pivot++;
			}
		}
		return pivot;
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
        result = S.insertionSort(liste3.clone());
        System.out.println("InsertionSort: " + result);
        result = S.quickSort(liste3.clone(),true);
        result2 = S.quickSort(liste3.clone(),true);
        result3 = S.quickSort(liste3.clone(),true);
        result4 = S.quickSort(liste3.clone(),true);
        result5 = S.quickSort(liste3.clone(),true);
        System.out.println("Randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSort(liste3.clone(),false);
        System.out.println("QuickSort: " + result);
        result = S.quickSortMod(liste3.clone(),true);
        result2 = S.quickSortMod(liste3.clone(),true);
        result3 = S.quickSortMod(liste3.clone(),true);
        result4 = S.quickSortMod(liste3.clone(),true);
        result5 = S.quickSortMod(liste3.clone(),true);
        System.out.println("Mod. randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSortMod(liste3.clone(),false);
        System.out.println("Mod. QuickSort: " + result);
        
        System.out.println("\nSortiertes Array 1,...," + n); //Aufgabenteil 4b) aufsteigende Liste
        result = S.insertionSort(liste.clone());
        System.out.println("InsertionSort: " + result);
        result = S.quickSort(liste.clone(),true);
        result2 = S.quickSort(liste.clone(),true);
        result3 = S.quickSort(liste.clone(),true);
        result4 = S.quickSort(liste.clone(),true);
        result5 = S.quickSort(liste.clone(),true);
        System.out.println("Randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSort(liste.clone(),false);
        System.out.println("QuickSort: " + result);
        result = S.quickSortMod(liste.clone(),true);
        result2 = S.quickSortMod(liste.clone(),true);
        result3 = S.quickSortMod(liste.clone(),true);
        result4 = S.quickSortMod(liste.clone(),true);
        result5 = S.quickSortMod(liste.clone(),true);
        System.out.println("Mod. randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSortMod(liste.clone(),false);
        System.out.println("Mod. QuickSort: " + result);
        
        System.out.println("\nSortiertes Array " + n + ",...,1"); //Aufgabenteil 4b) absteigende Liste
        result = S.insertionSort(liste2.clone());
        System.out.println("InsertionSort: " + result);
        result = S.quickSort(liste2.clone(),true);
        result2 = S.quickSort(liste2.clone(),true);
        result3 = S.quickSort(liste2.clone(),true);
        result4 = S.quickSort(liste2.clone(),true);
        result5 = S.quickSort(liste2.clone(),true);
        System.out.println("Randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSort(liste2.clone(),false);
        System.out.println("QuickSort: " + result);
        result = S.quickSortMod(liste2.clone(),true);
        result2 = S.quickSortMod(liste2.clone(),true);
        result3 = S.quickSortMod(liste2.clone(),true);
        result4 = S.quickSortMod(liste2.clone(),true);
        result5 = S.quickSortMod(liste2.clone(),true);
        System.out.println("Mod. randomisierter QuickSort: " + result + ", " + result2 + ", " + result3 + ", " + result4 + ", " + result5);
        result = S.quickSortMod(liste2.clone(),false);
        System.out.println("Mod. QuickSort: " + result);
	}
}
