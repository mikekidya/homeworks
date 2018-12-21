package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerTest {

    @Test
    public void isInfectedTest() {
        OS win = new OS("Windows", 1);
        Computer computer = new Computer("A", win);
        assertFalse(computer.isInfected());
        computer.setInfected(true);
        assertTrue(computer.isInfected());
    }

    @Test
    public void setInfectedTest() {
        OS win = new OS("Windows", 1);
        Computer computer = new Computer("A", win);
        assertFalse(computer.isInfected());
        computer.setInfected(true);
        assertTrue(computer.isInfected());
        computer.setInfected(false);
        assertFalse(computer.isInfected());
    }

    @Test
    public void getConnectedComputersTest() {
        OS win = new OS("Windows", 1);
        Computer first = new Computer("First", win);
        Computer second = new Computer("Second", win);
        assertEquals(first.getConnectedComputers().size(), 0);
        assertEquals(second.getConnectedComputers().size(), 0);
        first.addConnected(second);
        assertEquals(first.getConnectedComputers().size(), 1);
        assertEquals(first.getConnectedComputers().get(0), second);
        assertEquals(second.getConnectedComputers().size(), 0);
    }

    @Test
    public void infectedConnectedTest() {
        OS win = new OS("Windows", 1);
        Computer computer = new Computer("A", win);
        assertFalse(computer.hasInfectedConnectedComputer());
        computer.addInfectedConnected();
        assertTrue(computer.hasInfectedConnectedComputer());
    }


    @Test
    public void toStringTest() {
        OS win = new OS("Windows", 1);
        Computer computer = new Computer("A", win);
        assertEquals(computer.toString(), "A: Not Infected");
        computer.setInfected(true);
        assertEquals(computer.toString(), "A: Infected");

    }

    @Test
    public void equalsTest() {
        OS win = new OS("Windows", 1);
        Computer computerA1 = new Computer("A", win);
        assertFalse(computerA1.equals(new Computer("B", win)));
        assertFalse(computerA1.equals(new OS("macOS", 1)));
        assertTrue(computerA1.equals(new Computer("A", win)));

    }
}