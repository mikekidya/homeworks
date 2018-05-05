package group144.kidyankin;

import org.junit.Test;

import java.util.*;

import static group144.kidyankin.SecondPartTasks.*;
import static org.junit.Assert.assertEquals;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        assertEquals(Arrays.asList("Peter", "Peck", "Peppers"),
                findQuotes(Arrays.asList("Peter", "Piper", "Picked", "Peck", "Peppers"), "Pe"));

        assertEquals(Arrays.asList("she", "seashells", "seashore"),
                findQuotes(Arrays.asList("she", "sells", "seashells", "seashore", "sure"), "sh"));

    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(3.1415 / 4, piDividedBy4(), 0.001);
    }

    @Test
    public void testFindPrinter() {
        List<String> peter = Arrays.asList("Peter", "Piper", "Picked", "Peck", "Peppers");
        List<String> she = Arrays.asList("she", "sells", "sure");
        List<String> screaming = Collections.singletonList("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Map<String, List<String>> artists = new HashMap<>();
        artists.put("peter", peter);
        artists.put("she", she);

        assertEquals("peter", findPrinter(artists));

        artists.put("screaming", screaming);
        assertEquals("screaming", findPrinter(artists));
    }

    @Test
    public void testCalculateGlobalOrder() {
        List<Map<String, Integer>> orders = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            orders.add(new HashMap<>());
        }

        orders.get(0).put("A", 2);
        orders.get(0).put("B", 3);
        orders.get(0).put("C", 5);

        orders.get(1).put("A", 3);
        orders.get(1).put("C", 5);
        orders.get(1).put("D", 100);

        orders.get(2).put("B", 1);
        orders.get(2).put("C", 5);
        orders.get(2).put("D", 1);

        orders.get(3).put("A", 3);
        orders.get(3).put("C", 5);
        orders.get(3).put("E", 1000);

        Map<String, Integer> result = new HashMap<>();
        result.put("A", 8);
        result.put("B", 4);
        result.put("C", 20);
        result.put("D", 101);
        result.put("E", 1000);

        assertEquals(result, calculateGlobalOrder(orders));
    }
}