package group144.kidyankin;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private ArrayList<LinkedList<String>> table;
    private HashFunction hashFunction;
    private int elementNumber = 0;
    private int conflictNumber = 0;
    
    HashTable(int size, HashFunction hashFunction) {
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
        this.hashFunction = hashFunction;
    }

    public void add(String element) {
        int index = hashFunction.hash(element, table.size());
        if (table.get(index).size() == 1) {
            conflictNumber++;
        }
        table.get(hashFunction.hash(element, table.size())).add(element);
        elementNumber++;
    }

    public void delete(String element) throws ElementNotFoundException {
        int index = hashFunction.hash(element, table.size());
        if (table.get(index).remove(element)){
            elementNumber--;
            if (table.get(index).size() == 1) {
                conflictNumber--;
            }
        } else {
            throw new ElementNotFoundException();
        }
    }

    public boolean contains(String element) {
        return table.get(hashFunction.hash(element, table.size())).contains(element);
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public int getConflictNumber() {
        return conflictNumber;
    }

    public int getMaxListSize() {
        int result = 0;
        for (LinkedList list: table) {
            result = Integer.max(result, list.size());
        }
        return result;
    }

    public double loadFactor() {
        return (double) getElementNumber() / table.size();
    }
}
