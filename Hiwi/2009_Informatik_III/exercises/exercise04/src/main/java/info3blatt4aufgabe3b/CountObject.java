package info3blatt4aufgabe3b;

public  class CountObject implements Comparable<CountObject>{
    private int l1min = 0;
    private int l2min = 0;
    private int n1min = 0;
    private int n2min = 0;
    private int l1max = 0;
    private int l2max = 0;
    private int n1max = 0;
    private int n2max = 0;
    private int count = 0;

    public CountObject(int l1min, int l2min, int n1min, int n2min, int l1max, int l2max, int n1max, int n2max, int count){
        this.l1min = l1min;
        this.l2min = l2min;
        this.n1min = n1min;
        this.n2min = n2min;
        this.l1max = l1max;
        this.l2max = l2max;
        this.n1max = n1max;
        this.n2max = n2max;
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public String toString(){
        return "L1: " + toLetter(this.l1min) + "-" + toLetter(this.l1max) + ", L2: " + toLetter(this.l2min) + "-" + toLetter(this.l2max) + ", N1: " + toNumber(this.n1min) + "-" + toNumber(this.n1max) + ", N2: " + toNumber(this.n2min) + "-" + toNumber(this.n2max) + " (" + this.count + ")";
    }

    private String toLetter(int letter_id){
        String letter = "";
        switch(letter_id){
            case 0: {letter += "A"; break;}
            case 1: {letter += "B"; break;}
            case 2: {letter += "C"; break;}
            case 3: {letter += "D"; break;}
            case 4: {letter += "E"; break;}
            case 5: {letter += "F"; break;}
            case 6: {letter += "G"; break;}
            case 7: {letter += "H"; break;}
            case 8: {letter += "I"; break;}
            case 9: {letter += "J"; break;}
            case 10: {letter += "K"; break;}
            case 11: {letter += "L"; break;}
            case 12: {letter += "M"; break;}
            case 13: {letter += "N"; break;}
            case 14: {letter += "O"; break;}
            case 15: {letter += "P"; break;}
            case 16: {letter += "Q"; break;}
            case 17: {letter += "R"; break;}
            case 18: {letter += "S"; break;}
            case 19: {letter += "T"; break;}
            case 20: {letter += "U"; break;}
            case 21: {letter += "V"; break;}
            case 22: {letter += "W"; break;}
            case 23: {letter += "X"; break;}
            case 24: {letter += "Y"; break;}
            case 25: {letter += "Z"; break;}
            case 26: {letter += "{}"; break;}
        }
        return letter;
    }

    private String toNumber(int number_id){
        String number = "";
        switch(number_id){
            case 0: {number += "0"; break;}
            case 1: {number += "1"; break;}
            case 2: {number += "2"; break;}
            case 3: {number += "3"; break;}
            case 4: {number += "4"; break;}
            case 5: {number += "5"; break;}
            case 6: {number += "6"; break;}
            case 7: {number += "7"; break;}
            case 8: {number += "8"; break;}
            case 9: {number += "9"; break;}
            case 10: {number += "{}"; break;}
        }
        return number;
    }

    public int compareTo(CountObject object) {
        return this.count - object.count;
    }
}
