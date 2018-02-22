//Guillermo Arturo Hernandez Tapia  A01321776
//Jose Maria Montiel Palacios       A01323942

public class Utils{    
    /**
     * Calculates the maximum element in an integer array
     *
     * @param array: the array to evaluate
     * @return: maximum element in the array
     */
    public double getMaxArrayValue(double[] array) {
        double numMax = array[0];
        int index;

        for (index = 1; index < array.length; index++) {
            if (array[index] > numMax) {
                numMax = array[index];
            }
        }
        return numMax;
    }

    /**
     * Calculates the minimum element in an integer array
     *
     * @param array: the array to evaluate
     * @return: minimum element in the array
     */
    public double getMinArrayValue(double[] array) {
        double numMin = array[0];
        int index;

        for (index = 1; index < array.length; index++) {
            if (array[index] < numMin) {
                numMin = array[index];
            }
        }
        return numMin;
    }

    /**
     * Calcultates the average of the elements in an integer array
     *
     * @param array: the array to evaluate
     * @param scale: the resized scale of the original figure
     * @return
     */
    public double avgPoints(double[] array) {
        double avg = 0;
        for (int i = 0; i < array.length; i++) {
            avg += array[i];
        }
        avg /= array.length;

        return  avg;
    }

    public double avgOfExtremePoints(double[] array) {
        double avg = getMinArrayValue(array)+getMaxArrayValue(array);

        avg /= 2;
        avg = Math.round(avg);

        return  avg;
    }

    public double[] setToOnes(double[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i]=1.00;
        }

        return  array;
    }
}
