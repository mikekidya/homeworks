package group144.kidyankin;

import java.util.Random;

public class ConcreteGenerator implements RandomGenerator {

    private static Random random = new Random();

    @Override
    public double nextDouble() {
        return random.nextDouble();
    }
}
