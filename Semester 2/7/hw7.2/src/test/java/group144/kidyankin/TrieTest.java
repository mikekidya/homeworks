package group144.kidyankin;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/** Tests for trie data structure */
public class TrieTest {

    @Test
    public void addTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        assertTrue(trie.add("peter"));
        assertTrue(trie.add("piper"));
        assertTrue(trie.add("hello"));
        assertTrue(trie.add("pet"));
        assertFalse(trie.add("hello"));
        assertTrue(trie.add("pete"));

    }

    @Test
    public void containsTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        trie.add("peter");
        trie.add("pet");
        trie.add("happy");
        trie.add("happiness");
        assertTrue(trie.contains("peter"));
        assertTrue(trie.contains("pet"));
        assertTrue(trie.contains("happy"));
        assertTrue(trie.contains("happiness"));
        assertFalse(trie.contains("pete"));
        assertFalse(trie.contains("pe"));
        assertFalse(trie.contains("rocket"));
        assertFalse(trie.contains("happi"));
    }

    @Test
    public void removeTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        trie.add("peter");
        trie.add("pet");
        trie.add("happy");
        trie.add("happiness");
        assertFalse(trie.remove("pete"));
        assertFalse(trie.remove("happ"));
        assertTrue(trie.remove("pet"));
        assertTrue(trie.remove("peter"));
        assertTrue(trie.remove("happiness"));
        assertTrue(trie.remove("happy"));
    }

    @Test
    public void sizeTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        assertEquals(0, trie.size());
        trie.add("peter");
        trie.add("pet");
        assertEquals(2, trie.size());
        trie.add("happy");
        trie.add("happiness");
        assertEquals(4, trie.size());
        trie.remove("happ");
        assertEquals(4, trie.size());
        trie.remove("pet");
        assertEquals(3, trie.size());
        trie.remove("pet");
        assertEquals(3, trie.size());
    }

    @Test
    public void howManyStartWithPrefixTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        trie.add("peter");
        trie.add("pet");
        trie.add("happy");
        trie.add("happiness");
        assertEquals(0, trie.howManyStartWithPrefix("a"));
        assertEquals(2, trie.howManyStartWithPrefix("pet"));
        assertEquals(2, trie.howManyStartWithPrefix("pe"));
        assertEquals(1, trie.howManyStartWithPrefix("pete"));
        assertEquals(2, trie.howManyStartWithPrefix("happ"));
    }

    @Test(expected = UnsupportedWordSymbol.class)
    public void addExceptionTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        trie.add("Hello");
    }

    @Test(expected = UnsupportedWordSymbol.class)
    public void containsExceptionTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        trie.contains("Hello");
    }

    @Test(expected = UnsupportedWordSymbol.class)
    public void removeExceptionTest() throws UnsupportedWordSymbol {
        Trie trie = new Trie();
        trie.remove("Hello");
    }

    @Test
    public void serializeTest() throws IOException, ClassNotFoundException, UnsupportedWordSymbol {
        Trie trie = new Trie();
        trie.add("hello");
        trie.add("hear");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        trie.serialize(outputStream);
        trie.add("guitar");
        trie.remove("hello");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        trie.deserialize(inputStream);
        assertEquals(2, trie.size());
        assertFalse(trie.contains("guitar"));
        assertTrue(trie.contains("hello"));
    }

}