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
}