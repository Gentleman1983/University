package info3blatt4aufgabe4;

import java.util.LinkedList;

public class LinkedHashTable {
    private LinkedList<LinkedList<Hashable>> table;

    public LinkedHashTable (int size){
        this.table = new LinkedList<LinkedList<Hashable>>();

        for(int i = 0; i < size; i++){
            this.table.addLast(new LinkedList<Hashable>());
        }
    }

    public boolean isEmpty(){
        boolean result = true;

        for(LinkedList<Hashable> bucket: this.table){
            if(!bucket.isEmpty()){
                result = false;
                break;
            }
        }

        return result;
    }

    public <T extends Hashable> void insert(T object){
        LinkedList<Hashable> bucket = this.table.get(object.hashValue());

        bucket.addLast(object);
    }

    public <T extends Hashable> void delete(T object){
        LinkedList<Hashable> bucket = this.table.get(object.hashValue());

        bucket.remove(object);
    }

    public <T extends Hashable> boolean lookUp(T object){
        LinkedList<Hashable> bucket = this.table.get(object.hashValue());

        return bucket.contains(object);
    }




}
