/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blatt7;

/**
 *
 * @author Christian Otto
 */
import java.util.*;

public class Sucher{
    static int keyComp = 0;
    static int modComp = 0;
    static boolean abbruch = false;

    public static int quicksort(int[] f, int links, int rechts) {
        int l = links;
        int r = rechts;

        if(l >= r) {
            return -1;
        }

        int pivot = f[r];
        while(l < r) {
            while(l < r) {
                keyComp++;
                if(f[l] > pivot) {
                    break;
                }
                else {
                    l++;
                }
            }
            while(l < r) {
                keyComp++;
                if(f[r] < pivot) {
                    break;
                }
                else {
                    r--;
                }
            }
            if(l < r) {
                f = tausche(f,l,r);
            }
        }
        f = tausche(f,r,rechts);

        quicksort(f,links,r-1);
        quicksort(f,r+1,rechts);

        return keyComp;
    }

    public static int quickmod(int[] f, int links, int rechts,int s) {
        if(abbruch == true) {
            return -1;
        }

        int l = links;
        int r = rechts;

        if(l >= r) {
            return -1;
        }
        int pivot = f[r];

        while(l < r) {
            while(l < r) {
                keyComp++;
                if(f[l] > pivot) {
                    break;
                }
                else {
                    l++;
                }
            }
            while(l < r) {
                keyComp++;
                if(f[r] < pivot) {
                    break;
                }
                else {
                    r--;
                }
            }
            if(l < r) {
                f = tausche(f,l,r);
            }
        }
        f = tausche(f,r,rechts);

        if(pivot != s) {
            if((links == r - 1) && (f[r-1] == s)) {
                modComp = keyComp;
                abbruch = true;
            }
            if((r + 1 == rechts) && (f[r+1] == s)) {
                modComp = keyComp;
                abbruch = true;
            }
            quickmod(f,links,r-1,s);
            quickmod(f,r+1,rechts,s);
        }
        else {
            modComp = keyComp; 
            abbruch = true;
        }
        return modComp;
    }

    public static int[] tausche(int[] f, int a, int b) {
        int tmp = f[a];
        f[a] = f[b];
        f[b] = tmp;
        return f;
    }

    public static void main(String args[]) {
        double qsmittel = 0, qmmittel = 0;
        int n = 42, wdh = 10000;
        if(args.length == 0) {
            System.out.println("Waehle n = 42");
        }
        else {
            n = Integer.parseInt(args[0]);
        }
	if(args.length <= 1) {
            System.out.println("Waehle wdh = 10000");
        }
        else {
            wdh = Integer.parseInt(args[1]);
        }

        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
        
        System.out.print("\n\n\n");
        System.out.println("=============================");
        System.out.println("Zum Vergleich:");
        System.out.println("=============================");
        
        qsmittel = 0;
        qmmittel = 0;
        n = 10;
        System.out.println("n = 10");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
        
        qsmittel = 0;
        qmmittel = 0;
        n = 20;
        System.out.println("n = 20");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
        
        qsmittel = 0;
        qmmittel = 0;
        n = 50;
        System.out.println("n = 50");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
        
        qsmittel = 0;
        qmmittel = 0;
        n = 100;
        System.out.println("n = 100");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
        
        qsmittel = 0;
        qmmittel = 0;
        n = 1000;
        System.out.println("n = 1.000");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
        
        qsmittel = 0;
        qmmittel = 0;
        n = 10000;
        System.out.println("n = 10.000");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
        
        qsmittel = 0;
        qmmittel = 0;
        n = 100000;
        System.out.println("n = 100.000");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        
        qsmittel = 0;
        qmmittel = 0;
        n = 1000000;
        System.out.println("n = 1.000.000");
        for(int m = 0; m < wdh; m++) {
            int[] feld = new int[n];
            int[] feld2 = new int[n];
            feld = generatePerm(n);

            for(int i = 0; i < n; i++) {
                feld2[i] = feld[i];
            }

            Random Zuf = new Random();
            int j = Zuf.nextInt(n) + 1;
            keyComp = 0;
            qsmittel += quicksort(feld,0,n-1);
            keyComp = 0;
            abbruch = false;
            qmmittel += quickmod(feld2,0,n-1,j);
        }
        System.out.println("Mittelwert von Quicksort:	" + (qsmittel/wdh));
        System.out.println("Mittelwert von Quickmod:	" + (qmmittel/wdh));
	System.out.println("Gesparte Vergleiche:	" + (100*(qsmittel-qmmittel)/qsmittel));
    }
	
    public static int[] generatePerm(int n) {
        int i, j, z;
        int[] f = new int[n];
        for(i = 0; i < n; i++) {
            while(f[i] == 0) {
                Random zufall = new Random();
                z = zufall.nextInt(n+1);
                boolean passt = true;

                for(j = 0; j <= i; j++) {
                    if(f[j] == z) {
                        passt = false;
                    }
                }
                if(passt == true) {
                    f[i] = z;
                }
            }
        }

        return f;
    }

    public static String printArr(int[] f) {
        String output = "";
        for(int i = 0; i < f.length; i++) {
            output += f[i] + " ";
        }
        return output;
    }
}