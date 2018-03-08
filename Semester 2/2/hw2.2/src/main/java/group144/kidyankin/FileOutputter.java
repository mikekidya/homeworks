package group144.kidyankin;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FileOutputter extends CommonSpiralWriter {
    @Override
    public void printSpiral(int[][] array) throws FileNotFoundException {
        super.printSpiral(array, new PrintStream("output.txt"));
    }
}
