package info3blatt4aufgabe3a;

public class Main {

    public static void main(String[] args) {
        int[] buckets = new int[220];
        int min, max;

        for(int i = 1; i < 26; i++){
            for(int j = 0; j < 27; j++){
                for(int k = 0; k < 10; k++){
                    for(int l = 0; l < 11; l++){
                        int hashValue = calculateHashValue(i, j, k, l);
                        buckets[hashValue]++;
                    }
                }
            }
        }

        min = buckets[0];
        max = buckets[0];
        for(int i = 0; i < 220; i++){
            System.out.println("Bucket " + i + ": " + buckets[i]);

            if(min > buckets[i]){
                min = buckets[i];
            }
            else if (max < buckets[i]){
                max = buckets[i];
            }
        }

        System.out.println("Min: " + min + "\nMax: " + max + "\nStreuung: " + (max-min));
    }

    private static int calculateHashValue(int b1, int b2, int z1, int z2) {
        return (b1 + b2*29 + z1*41 + z2*71) % 220;
    }

}
