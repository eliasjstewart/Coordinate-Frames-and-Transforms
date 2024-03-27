public class test {
   
    public static void main(String[] args) throws IllegalCoordinateException {

        CoordinateFrame sys = new CoordinateFrame(70.0, 23.0, 90);

        //sys.printMatrix(sys.HomogeneousTransform(6, -2, 10, 1));

        sys.printMatrix(sys.TransformationToPoint(-5,2,-12,1));
    }
}
