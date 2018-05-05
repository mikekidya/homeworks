package group144.kidyankin;

import java.io.*;

/**
 * Class realizing trie data structure.
 * Collect words which are containing only letters a...z
 */
public class Trie implements Serializable {

    private int ALPHABET_SIZE = 26;
    private Node head = new Node();
    private int size = 0;

    /**
     * Adds a word into trie
     *
     * @param element the value should be added
     * @return <tt>true</tt> if the trie has changed
     * @throws UnsupportedWordSymbol if element contains not Roman small letters
     */
    public boolean add(String element) throws UnsupportedWordSymbol {
        boolean result = head.add(element, 0);
        if (result) {
            size++;
        }
        return result;
    }

    /**
     * Returns <tt>true</tt> if the trie contains this word
     *
     * @param element the value should be checked
     * @return <tt>true</tt> if the trie contains this word
     * @throws UnsupportedWordSymbol if element contains not Roman small letters
     */
    public boolean contains(String element) throws UnsupportedWordSymbol {
        return head.contains(element, 0);
    }

    /**
     * Removes a word from trie
     *
     * @param element the value should be removed
     * @return <tt>true</tt> if the trie has changed
     * @throws UnsupportedWordSymbol if element contains not Roman small letters
     */
    public boolean remove(String element) throws UnsupportedWordSymbol {
        boolean result = head.remove(element, 0);
        if (result) {
            size--;
        }
        return result;
    }

    /**
     * Returns the number of words in the trie
     *
     * @return the number of words in the trie
     */
    public int size() {
        return size;
    }

    /**
     * Returns the number of words in the trie which starts with given prefix
     *
     * @param prefix the prefix of words should be counted
     * @return the number of words in the trie which starts with given prefix
     * @throws UnsupportedWordSymbol if element contains not Roman small letters
     */
    public int howManyStartWithPrefix(String prefix) throws UnsupportedWordSymbol {
        return head.howManyStartWithPrefix(prefix, 0);
    }

    /**
     * Puts object in output stream to save it.
     *
     * @param out Output Stream where object will be saved
     * @throws IOException if something wrong with output stream
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(this);
    }

    /**
     * Gets object from input stream and replace current one with it.
     *
     * @param in Input Stream where object will be gotten from
     * @throws IOException if something wrong with inputStream stream
     * @throws ClassNotFoundException if class definition of a serialized object cannot be found
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        Trie newTrie = (Trie) ois.readObject();
        head = newTrie.head;
        size = newTrie.size;
    }

    /** Class realizing trie node */
    private class Node implements Serializable {

        private boolean isTerminal = false;
        private Node[] next = new Node[ALPHABET_SIZE];
        private int suffixNumber = 0;

        public boolean add(String element, int currentIndex) throws UnsupportedWordSymbol {
            if (currentIndex == element.length()) {
                if (isTerminal) {
                    return false;
                }
                isTerminal = true;
                suffixNumber++;
                return true;
            }
            if (isIncorrectSymbol(element.charAt(currentIndex))) {
                throw new UnsupportedWordSymbol();
            }
            if (next[element.charAt(currentIndex) - 'a'] == null) {
                next[element.charAt(currentIndex) - 'a'] = new Node();
            }
            boolean isAdded = next[element.charAt(currentIndex) - 'a'].add(element, currentIndex + 1);
            if (isAdded) {
                suffixNumber++;
            }
            return isAdded;
        }

        public boolean contains(String element, int currentIndex) throws UnsupportedWordSymbol {
            if (currentIndex == element.length()) {
                return isTerminal;
            }
            if (isIncorrectSymbol(element.charAt(currentIndex))) {
                throw new UnsupportedWordSymbol();
            }
            return next[element.charAt(currentIndex) - 'a'] != null &&
                   next[element.charAt(currentIndex) - 'a'].contains(element, currentIndex + 1);
        }

        public boolean remove(String element, int currentIndex) throws UnsupportedWordSymbol {
            if (currentIndex == element.length()) {
                if (!isTerminal) {
                    return false;
                }
                suffixNumber--;
                isTerminal = false;
                return true;
            }
            if (isIncorrectSymbol(element.charAt(currentIndex))) {
                throw new UnsupportedWordSymbol();
            }
            boolean isRemoved = next[element.charAt(currentIndex) - 'a'] != null &&
                                next[element.charAt(currentIndex) - 'a'].remove(element, currentIndex + 1);
            if (isRemoved) {
                suffixNumber--;
            }
            return isRemoved;
        }

        public int howManyStartWithPrefix(String prefix, int currentIndex) throws UnsupportedWordSymbol {
            if (currentIndex == prefix.length()) {
                return suffixNumber;
            }
            if (isIncorrectSymbol(prefix.charAt(currentIndex))) {
                throw new UnsupportedWordSymbol();
            }
            if (next[prefix.charAt(currentIndex) - 'a'] == null) {
                return 0;
            }
            return next[prefix.charAt(currentIndex) - 'a'].howManyStartWithPrefix(prefix, currentIndex + 1);
        }

        private boolean isIncorrectSymbol(char symbol) {
            return symbol < 'a' || symbol > 'z';
        }
    }

}
