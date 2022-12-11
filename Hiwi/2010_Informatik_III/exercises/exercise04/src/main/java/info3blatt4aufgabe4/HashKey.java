package info3blatt4aufgabe4;

public class HashKey implements Hashable{
    private String value = "";

    public HashKey(String value){
        this.value = value;
    }

    public int hashValue() {
        int hash = 0;

        for(char c: this.value.toCharArray()){
            hash = hash * 256 + Character.getNumericValue(c);
            hash = hash % 23;
        }

        return hash;
    }

    public String getValue(){
        return this.value;
    }

}
