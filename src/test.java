package src;

import src.CoordinateFrame;
import src.Field3D;
import src.IllegalCoordinateException;

public class test {
   
    public static void main(String[] args) throws IllegalCoordinateException {

        CoordinateFrame sys = new CoordinateFrame(69.0, 23.0, 90);

        sys.printMatrix(sys.HomogeneousTransform(6, -2, 10, 1));

        //sys.printMatrix(sys.TransformationToPoint(-5,2,-12,1));

        Field3D field3D = new Field3D(70, 56.0, 0.0,90, 90);
    }
}
