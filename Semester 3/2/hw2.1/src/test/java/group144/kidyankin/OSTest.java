package group144.kidyankin;

import org.junit.Test;

import static org.junit.Assert.*;

public class OSTest {

    @Test
    public void getName() {
        OS win = new OS("Windows", 0.6);
        assertEquals("Windows", win.getName());
    }

    @Test
    public void getProbabilityOfInfection() {
        OS win = new OS("Windows", 0.6);
        assertEquals(0.6, win.getProbabilityOfInfection(), 0.01);
    }
}