package classes.services.containers;

import classes.services.abstractServices.ArgumentChecker;

/**
 * Directional vectors container class with public parameters used to simplify methods' parameter requests.
 */
public class DirectionalVectors extends ArgumentChecker {
    
    public final double xVector;
    public final double yVector;

    public DirectionalVectors(double xVector, double yVector) {
        this.checkInsideIntervalValues(-1, 1, xVector, yVector);
        if(xVector == 0 && yVector == 0)
        {
            throw new IllegalArgumentException();
        }
        
        this.xVector = xVector;
        this.yVector = yVector;
    }

    /**
     * Gets the equivalent value of this vector couple expressed in degrees (0° means facing right).
     * 
     * @return The vectors' direction expressed in degrees
     */
    public double getDirectionalDegrees() {
        double absY = Math.abs(this.yVector);
        double hypotenuse = Math.sqrt( this.xVector*this.xVector + this.yVector*this.yVector );
        
        double degrees = Math.toDegrees( Math.asin(absY/hypotenuse) ); //value included between 0 and 90

        if(this.xVector < 0) //is needed to mirror the angle along the y axis
        {
            degrees += 2*(90-degrees); //value included between 90 and 180
        }
        if(this.yVector < 0 ) //is needed to mirror the angle along the x axis
        {
            degrees = 360 - degrees; //value included between 180 and 359,9_
        }
        return degrees;
    }
}
