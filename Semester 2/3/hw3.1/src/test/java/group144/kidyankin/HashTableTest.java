package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void addTest() {
        HashTable hashTable = new HashTable(37, new StringLengthHash());
        hashTable.add("Peter");
        hashTable.add("Piper");
        hashTable.add("Pepper");
        assertEquals(3, hashTable.getElementNumber());
    }

    @Test
    public void deleteTest() throws ElementNotFoundException {
        HashTable hashTable = new HashTable(37, new StringLengthHash());
        hashTable.add("Peter");
        hashTable.add("Piper");
        hashTable.add("Pepper");
        hashTable.delete("Piper");
        assertFalse(hashTable.contains("Piper"));
        assertEquals(2, hashTable.getElementNumber());
    }

    @Test(expected = ElementNotFoundException.class)
    public void deleteExceptionTest() throws ElementNotFoundException {
        HashTable hashTable = new HashTable(37, new StringLengthHash());
        hashTable.add("Peter");
        hashTable.add("Piper");
        hashTable.add("Pepper");
        hashTable.delete("Misha");
    }

    @Test
    public void containsTest() throws ElementNotFoundException {
        HashTable hashTable = new HashTable(37, new StringLengthHash());
        hashTable.add("Peter");
        hashTable.add("Piper");
        hashTable.add("Pepper");
        assertTrue(hashTable.contains("Peter"));
        assertTrue(hashTable.contains("Piper"));
        hashTable.delete("Piper");
        assertTrue(hashTable.contains("Peter"));
        assertTrue(hashTable.contains("Pepper"));
        assertFalse(hashTable.contains("Piper"));
        hashTable.delete("Peter");
        assertFalse(hashTable.contains("Peter"));
    }

    @Test
    public void getElementNumberTest() throws ElementNotFoundException {
        HashTable hashTable = new HashTable(37, new StringLengthHash());
        hashTable.add("Peter");
        hashTable.add("Piper");
        hashTable.add("Pepper");
        assertEquals(3, hashTable.getElementNumber());
        hashTable.delete("Peter");
        assertEquals(2, hashTable.getElementNumber());
        hashTable.delete("Pepper");
        assertEquals(1, hashTable.getElementNumber());
    }

    @Test
    public void getConflictNumberTest() throws ElementNotFoundException {
        HashTable hashTable = new HashTable(37, new StringLengthHash());
        hashTable.add("Peter");
        hashTable.add("Piper");
        hashTable.add("Pepper");
        assertEquals(1, hashTable.getConflictNumber());
        hashTable.delete("Peter");
        assertEquals(0, hashTable.getConflictNumber());
        hashTable.add("Picked");
        assertEquals(1, hashTable.getConflictNumber());
    }

    @Test
    public void getMaxListSizeTest() throws ElementNotFoundException {
        HashTable hashTable = new HashTable(37, new StringLengthHash());
        hashTable.add("Peter");
        hashTable.add("Piper");
        hashTable.add("Pepper");
        assertEquals(2, hashTable.getMaxListSize());
        hashTable.delete("Peter");
        assertEquals(1, hashTable.getMaxListSize());
        hashTable.add("Picked");
        hashTable.add("Picked");
        assertEquals(3, hashTable.getMaxListSize());
    }
}