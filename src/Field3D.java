package src;

import src.Field2D;

public class Field3D extends Field2D {

    public double zHeading;
    public static double MIN_HEIGHT = 0;
    public static double MAX_HEIGHT = 18;
    // both in inches
    public Field3D(double x, double y, double z, double heading,double zHeading) throws IllegalCoordinateException {
            super(x, y,heading);
            boolean isOutBoundsZ = More.check(z, MAX_HEIGHT) || Less.check(z, MIN_HEIGHT);
            if(isOutBoundsZ){
                throw new IllegalCoordinateException("Not Reachable!! Parameters outside of coordinate frame!");
            }

    }





}
