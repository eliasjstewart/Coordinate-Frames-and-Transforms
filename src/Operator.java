package src;

public interface Operator {
   
    boolean check(double setPoint,double desiredValue);

}

interface Operation {

    void inchesToTiles(double inches, double tiles);
    void tilestoInches(double tiles, double inches);
}
