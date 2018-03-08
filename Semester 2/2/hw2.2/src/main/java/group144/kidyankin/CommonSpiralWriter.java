package group144.kidyankin;

import java.io.PrintStream;

abstract class CommonSpiralWriter implements SpiralWriter {

    void printSpiral(int[][] array, PrintStream stream) {
        int leftSteps = 1;
        int downSteps = 2;
        int rightSteps = 2;
        int upSteps = 2;

        int iCurrent = array.length / 2;
        int jCurrent = array.length / 2;

        stream.print("Elements in spiral order: ");
        stream.print(array[iCurrent][jCurrent] + " ");

        while (!(iCurrent == 0 && jCurrent == array.length - 1)) {
            iCurrent--;
            stream.print(array[iCurrent][jCurrent] + " ");

            for (int k = 0; k < leftSteps; k++) {
                jCurrent--;
                stream.print(array[iCurrent][jCurrent] + " ");
            }

            for (int k = 0; k < downSteps; k++) {
                iCurrent++;
                stream.print(array[iCurrent][jCurrent] + " ");
            }

            for (int k = 0; k < rightSteps; k++) {
                jCurrent++;
                stream.print(array[iCurrent][jCurrent] + " ");
            }

            for (int k = 0; k < upSteps; k++) {
                iCurrent--;
                stream.print(array[iCurrent][jCurrent] + " ");
            }

            leftSteps += 2;
            downSteps += 2;
            rightSteps += 2;
            upSteps += 2;
        }
    }
}
