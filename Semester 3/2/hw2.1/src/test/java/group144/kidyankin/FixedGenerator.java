package group144.kidyankin;

import java.util.List;

public class FixedGenerator implements RandomGenerator {

    private List<Double> values;
    private int currentIndex = 0;

    public FixedGenerator(List<Double> values) {
        this.values = values;
    }

    @Override
    public double nextDouble() {
        double result = values.get(currentIndex);
        currentIndex = (currentIndex + 1) % values.size();
        return result;
    }
}
