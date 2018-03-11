package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {

    @Test
    public void addToHeadTest() {
        UniqueList<Integer> list = new UniqueList<>();
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(2), list.pop());
    }

    @Test(expected = ElementAlreadyAddedException.class)
    public void addToHeadExceptionTest() {
        UniqueList<Integer> list = new UniqueList<>();
        list.add(1);
        list.add(1);
    }

    @Test
    public void addWithIndexTest() {
        UniqueList<String> list = new UniqueList<>();
        list.add("Peter");
        list.add("Piper", 0);
        assertEquals("Peter", list.pop());
    }

    @Test(expected = ElementAlreadyAddedException.class)
    public void addWithIndexExceptionTest() {
        UniqueList<String> list = new UniqueList<>();
        list.add("Peter");
        list.add("Piper", 0);
        list.add("Peter", 1);
    }
}