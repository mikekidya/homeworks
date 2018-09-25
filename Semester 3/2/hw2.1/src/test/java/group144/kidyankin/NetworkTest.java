package group144.kidyankin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NetworkTest {

    @Test
    public void setFirstInfectedTest() {
        OS macOS = new OS("macOS", 0.2);
        OS win = new OS("Windows", 0.6);
        Computer a = new Computer("A", macOS);
        Computer b = new Computer("B", win);

        assertFalse(a.isInfected());
        assertFalse(b.isInfected());

        Network network = new Network();
        network.addComputer(a);
        network.addComputer(b);
        network.setFirstInfected(a);

        assertTrue(a.isInfected());
        assertFalse(b.isInfected());
    }

    @Test
    public void addComputerTest() {
        OS macOS = new OS("macOS", 0.2);
        OS win = new OS("Windows", 0.6);
        Computer a = new Computer("A", macOS);
        Computer b = new Computer("B", win);
        Network network = new Network();

        assertEquals(0, network.getComputers().size());
        network.addComputer(a);
        assertEquals(1, network.getComputers().size());
        network.addComputer(b);
        assertEquals(2, network.getComputers().size());

    }

    @Test
    public void getComputersTest() {
        OS macOS = new OS("macOS", 0.2);
        OS win = new OS("Windows", 0.6);
        Computer a = new Computer("A", macOS);
        Computer b = new Computer("B", win);
        Network network = new Network();
        network.addComputer(a);
        network.addComputer(b);
        List<Computer> EXPECTED = new ArrayList<>();
        EXPECTED.add(a);
        EXPECTED.add(b);
        assertEquals(EXPECTED, network.getComputers());
    }

    @Test
    public void hedgehogTest() {
        OS noOS = new OS("noOS", 0);
        OS bolgenOS = new OS("bolgenOS", 1);

        Computer a = new Computer("A", noOS);
        Computer b = new Computer("B", bolgenOS);
        Computer c = new Computer("C", bolgenOS);
        Computer d = new Computer("D", noOS);
        a.addConnected(b);
        b.addConnected(d);
        a.addConnected(c);
        a.addConnected(d);
        d.addConnected(a);

        Network network = new Network();
        network.addComputer(a);
        network.addComputer(b);
        network.addComputer(c);
        network.addComputer(d);
        network.setFirstInfected(a);

        network.modelStep();
        assertTrue(b.isInfected());
        assertTrue(c.isInfected());
        assertFalse(d.isInfected());

        network.modelStep();
        assertTrue(b.isInfected());
        assertTrue(c.isInfected());
        assertFalse(d.isInfected());
    }

    @Test
    public void probabilityTest() {
        OS macOS = new OS("macOS", 0.2);
        OS win = new OS("Windows", 0.6);

        int counter = 0;
        int numberOfTests = 10000;
        for (int i = 0; i < numberOfTests; i++) {
            Computer a = new Computer("A", macOS);
            Computer b = new Computer("B", win);
            Network network = new Network();
            network.addComputer(a);
            network.addComputer(b);
            a.addConnected(b);
            network.setFirstInfected(a);
            network.modelStep();
            counter += b.isInfected() ? 1 : 0;
        }
        assertEquals(0.6, (double) counter / numberOfTests, 0.1);
    }

    @Test
    public void lineTest() {
        OS macOS = new OS("macOS", 0.2);
        OS bolgenOS = new OS("BolgenOS", 1);

        Computer a = new Computer("A", macOS);
        Computer b = new Computer("B", bolgenOS);
        Computer c = new Computer("C", bolgenOS);
        Computer d = new Computer("D", bolgenOS);
        Computer e = new Computer("E", bolgenOS);
        a.addConnected(b);
        b.addConnected(c);
        c.addConnected(d);
        d.addConnected(e);
        Network network = new Network();
        network.addComputer(a);
        network.addComputer(b);
        network.addComputer(c);
        network.addComputer(d);
        network.addComputer(e);
        network.setFirstInfected(a);

        assertTrue(a.isInfected());
        assertFalse(b.isInfected());
        network.modelStep();
        assertTrue(b.isInfected());
        assertFalse(c.isInfected());
        network.modelStep();
        assertTrue(c.isInfected());
        network.modelStep();
        network.modelStep();
        assertTrue(e.isInfected());

    }
}