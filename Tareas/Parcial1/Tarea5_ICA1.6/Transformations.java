//Guillermo Arturo Hernández Tapia	A01321776
//José María Montiel Palacios		A01323942

public class Transformations{
    /**
     * Rotates the original figure and move it from original position.
     *
     * @param arrayX: the points on the X-Axis
     * @param arrayY: the points on the Y-Axis
     * @param angle: the angle of rotation
     * @param utils: object that implement some general methods like avgPoints.
     */
    public void rotation(double[] arrayX, double[] arrayY, double angle, Utils utils) {
        double avgX = utils.avgPoints(arrayX);
        double avgY = utils.avgPoints(arrayY);

        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));

        //Moves half of the size
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] -= avgX;
            arrayY[i] -= avgY;
        }

        //Rotates the figure
        for (int i = 0; i < arrayX.length; i++) {

            double x = arrayX[i];
            double y = arrayY[i];

            arrayX[i] =  ((x * cos) - (y * sin));
            arrayY[i] =  ((x * sin) + (y * cos));
        }

        //Returns to the original postion
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] += avgX;
            arrayY[i] += avgY;
        }
    }    
}