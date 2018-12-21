package group144.kidyankin;

import java.util.List;

public class FixedGenerator implements RandomGenerator {

    private List<Double> values;

    public FixedGenerator(List<Double> values) {
        this.values = values;
    }

    @Override
    public double nextDouble() {
        double result = values.get(0);
        values.remove(0);
        return result;
    }
}
