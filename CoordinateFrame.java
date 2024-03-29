public class CoordinateFrame {

        public double x,y;
        public static double heading;
        /* Where heading = 0: standard xy plane
           Where heading = 90: x -> y-axis, y -> x-axis
           Where heading = 180: x -> -x-axis, y -> -y-axis
           Where heading = 270: x -> -y-axis, y -> -x-axis
           Where heading = 360: You're back where you started!!
         */
        public static double FIELD_INCHES_PER_GRID = 23.5;
        public static double MIN_INCHES = -70.5;
        public static double MAX_INCHES = 70.5;
        public static double MAX_TILES = 3;
        public static double MIN_TILES = -3;
        public static double STANDARD_HEADING = 0.0;

        public double[][] TranslationalVector(double i, double j, double k){
            return new double[][]{{1,0,0,i},
                                  {0,1,0,j},
                                  {0,0,1,k},
                                  {0,0,0,1}};
        }
        Operator Less = (n, k) -> {
            return n < k;
        };
        Operator More = (n, k) -> {
            return n > k;
        };


    public enum CoordinateSystem{
            Cartesian("CARTESIAN"), Polar("POLAR"), Cylindrical("CYLINDRICAL");
            public final String name;
            CoordinateSystem(String name){
                this.name = name;
            }
        }

        public enum Rotationals{
            Rx(new double[][]{{1,0,0,0},
                              {0,Math.cos(heading),-Math.sin(heading),0},
                              {0,Math.sin(heading),Math.cos(heading), 0},
                              {0,0,0,1}},heading),
            Ry(new double[][]{{Math.cos(heading),0,Math.sin(heading),0},
                              {0,1,0,0},
                              {-Math.sin(heading),0,Math.cos(heading), 0},
                              {0,0,0,1}},heading),
            Rz(new double[][]{{Math.cos(heading),-Math.sin(heading),0,0},
                              {Math.sin(heading),Math.cos(heading),0,0},
                              {0,0,1,0},
                              {0,0,0,1}},heading);

            final double[][] rotMatrix;
            Rotationals(double[][] matrix, double heading){
               rotMatrix = matrix;
            }

        }

    public CoordinateFrame(double x , double y, double heading) throws IllegalCoordinateException {
        boolean isOutBoundsX = More.check(x, MAX_INCHES) || Less.check(x, MIN_INCHES);
        boolean isOutBoundsY = More.check(y, MAX_INCHES) || Less.check(y, MIN_INCHES);
        boolean isOutofBounds = isOutBoundsX || isOutBoundsY;
        if(isOutofBounds) {
                throw new IllegalCoordinateException("Not Reachable!! Parameters outside of coordinate frame!");
            }

        this.x = x;
        this.y = y;
        this.heading = Math.toRadians(heading);
        }

    public static double wrapAngle(double angle) {
        return ((angle % 360) + 360) % 360;
    }

    public static void printMatrix(double[][] M) {
        for (double[] doubles : M) {
            for (int j = 0; j < M[0].length; j++) {
                System.out.print(STR."\{doubles[j]} ");
            }
            System.out.println();
        }
    }

    public static double[][] mult(double[][] A,double[][] B) {
        int i, j, k;

        System.out.println("\nMatrix A:");
        printMatrix(A);
        System.out.println("\nMatrix B:");
        printMatrix(B);

        if (A.length != B.length) {

            System.out.println(
                    "\nMultiplication Not Possible...");
            return A;
        }

        double[][] C = new double[A.length][B[0].length];

        for (i = 0; i < A.length; i++) {
            for (j = 0; j < B[0].length; j++) {
                for (k = 0; k < B.length; k++) {
                    C[i][j] += (int) (A[i][k] * B[k][j]);
                }
            }
        }

        System.out.println("\nResultant Matrix:");

        return C;
    }

    public double[][] HomogeneousTransform(double i, double j, double k, int axis){
            double [][] translation = TranslationalVector(i, j, k);
            Rotationals rotationals = switch (axis) {
                case 1 -> Rotationals.Rx;
                case 2 -> Rotationals.Ry;
                case 3 -> Rotationals.Rz;
                default -> null;
            };
        assert rotationals != null;
        return mult(translation, rotationals.rotMatrix);
    }

    public double[][] TransformationToPoint(double x, double y, double z, double n){
            return mult(HomogeneousTransform(6,-2,10,1), new double[][]{ {x},
                                                                                      {y},
                                                                                      {z},
                                                                                      {n}});
    }




}



