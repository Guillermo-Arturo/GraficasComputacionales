//Guillermo Arturo Hernandez Tapia	A01321776
//Jose Maria Montiel Palacios		A01323942

public class Transformations{
    /**
     * Rotates the original figure and move it from original position.
     *
     * @param arrayX: the points on the X-Axis
     * @param arrayY: the points on the Y-Axis
     * @param angle: the angle of rotation
     */
    public void rotation(double[] arrayX, double[] arrayY, double angle, Utils utils) {
        double avgX = utils.avgPoints(arrayX);
        double avgY = utils.avgPoints(arrayY);
        
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));

        double[][] rotationMat={{cos,-sin,0.0},{sin,cos,0.0},{0.0,0.0,1.0}};

        int size=arrayX.length;

        //Moves half of the size
        for (int i = 0; i < size; i++) {
            arrayX[i] -= avgX;
            arrayY[i] -= avgY;
        }

        int rows = rotationMat.length;
        int columns = rotationMat[0].length;

        double[] result = new double[rows];
        for(int i=0; i < size; i++){
            double[] vector = {arrayX[i], arrayY[i], 1.0};
            for (int row = 0; row < rows; row++) {
                double sum = 0;
                for (int column = 0; column < columns; column++) {
                    sum += rotationMat[row][column] * vector[column];
                }
                result[row] = sum;
                System.out.println(sum);
            }
            System.out.println();
            arrayX[i] = result[0];
            arrayY[i] = result[1];
        }

        /*Returns to the original postion*/
        for (int i = 0; i < size; i++) {
            arrayX[i] += avgX;
            arrayY[i] += avgY;
        }

    } 
    public void scale(double[] arrayX, double[] arrayY, double scale, Utils utils) {
        double avgX = utils.avgPoints(arrayX);
        double avgY = utils.avgPoints(arrayY);

        double[][] rotationMat={{scale,0.0,0.0},{0.0,scale,0.0},{0.0,0.0,1.0}};
        /*
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(rotationMat[i][j]+"  --  ");
            }
            System.out.println();
        }
        */

        int size=arrayX.length;
        double[] arrayW=new double[size];           //Creates the complement using 1's
        arrayW=utils.setToOnes(arrayW);
        /*  Create the axuliar arrays to save the soluction from the product of matrices.*/
        double[] arrayXPrime=new double[size];
        double[] arrayYPrime=new double[size];
        double[] arrayWPrime=new double[size];


        //Moves half of the size
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] -= avgX;
            arrayY[i] -= avgY;
        }

        //Rotates the figure
        for (int i = 0; i < 3; i++){ // i being the rows for the rotationMat
            for(int j=0; j<size;j++){ // j being the columns of arrayX and arrayY arrays.
                if(i==0){
                    arrayXPrime[j]=rotationMat[i][0]*arrayX[j]  +  rotationMat[i][1]*arrayY[j]  +  rotationMat[i][2]*arrayW[j];
                }else if(i==1){
                    arrayYPrime[j]=rotationMat[i][0]*arrayX[j]  +  rotationMat[i][1]*arrayY[j]  +  rotationMat[i][2]*arrayW[j];
                }else if(i==2){
                    arrayWPrime[j]=rotationMat[i][0]*arrayX[j]  +  rotationMat[i][1]*arrayY[j]  +  rotationMat[i][2]*arrayW[j];
                }
            }
            /*
            double x = arrayX[i];
            double y = arrayY[i];

            arrayX[i] =  ((x * cos) - (y * sin));
            arrayY[i] =  ((x * sin) + (y * cos));
            */
        }
        /* Must return the prime arrays to the original arrays*/

        for (int i = 0; i < size; i++) {
            arrayX[i] = arrayXPrime[i];
        }
        for (int i = 0; i < size; i++) {
            arrayY[i] = arrayYPrime[i];
        }

        /*Returns to the original postion*/
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] += avgX;
            arrayY[i] += avgY;
        }

    } 
     
    public void move(double[] arrayX, double[] arrayY, int key, Utils utils, double angleDirection) {
        switch(key){
            case 38:{ // up Arrow
                angleDirection+=-90.0;
                break;
            }
            case 39:{ // right Arrow
                angleDirection+=0.0;
                break;
            }
            case 40:{ // down Arrow
                angleDirection+=+90;
                break;
            }
            case 37:{ // left Arrow
                angleDirection+=+180;
                break;
            }
        }

        double avgX = utils.avgPoints(arrayX);
        double avgY = utils.avgPoints(arrayY);

        double y = 3.0*Math.sin(Math.toRadians(angleDirection));
        double x = 3.0*Math.cos(Math.toRadians(angleDirection));

        double[][] rotationMat={{1.0,0.0,x},{0.0,1.0,y},{0.0,0.0,1.0}};
        
        int size=arrayX.length;
        double[] arrayW=new double[size];           //Creates the complement using 1's
        arrayW=utils.setToOnes(arrayW);
        /*  Create the axuliar arrays to save the soluction from the product of matrices.*/
        double[] arrayXPrime=new double[size];
        double[] arrayYPrime=new double[size];
        double[] arrayWPrime=new double[size];


        //Moves half of the size
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] -= avgX;
            arrayY[i] -= avgY;
        }

        //moves the figure
        for (int i = 0; i < 3; i++){ // i being the rows for the rotationMat
            for(int j=0; j<size;j++){ // j being the columns of arrayX and arrayY arrays.
                if(i==0){
                    arrayXPrime[j]=rotationMat[i][0]*arrayX[j]  +  rotationMat[i][1]*arrayY[j]  +  rotationMat[i][2]*arrayW[j];
                }else if(i==1){
                    arrayYPrime[j]=rotationMat[i][0]*arrayX[j]  +  rotationMat[i][1]*arrayY[j]  +  rotationMat[i][2]*arrayW[j];
                }else if(i==2){
                    arrayWPrime[j]=rotationMat[i][0]*arrayX[j]  +  rotationMat[i][1]*arrayY[j]  +  rotationMat[i][2]*arrayW[j];
                }
            }
            /*
            double x = arrayX[i];
            double y = arrayY[i];

            arrayX[i] =  ((x * cos) - (y * sin));
            arrayY[i] =  ((x * sin) + (y * cos));
            */
        }
        /* Must return the prime arrays to the original arrays*/

        for (int i = 0; i < size; i++) {
            arrayX[i] = arrayXPrime[i];
        }
        for (int i = 0; i < size; i++) {
            arrayY[i] = arrayYPrime[i];
        }

        //Returns to the original postion
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] += avgX;
            arrayY[i] += avgY;
        }
    } 

}