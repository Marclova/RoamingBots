package classes.services.containers;

import classes.services.abstractServices.ArgumentChecker;

/**
 * Coordinates container class with public parameters used to simplify methods' parameter requests.
 */
public class Coordinates extends ArgumentChecker {
    
    public final double x;
    public final double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the hypotenuse from the difference of the two pairs of coordinates.
     * 
     * @param otherCoordinates The point from which the distance will be calculated.
     * @return The distance from this coordinates and the other coordinate.
     */
    public double calculateDistanceFrom(Coordinates otherCoordinates) {
        this.checkNotNullObjects(otherCoordinates);

        double deltaX = Math.abs( this.x - otherCoordinates.x );
        double deltaY = Math.abs( this.y - otherCoordinates.y );

        return Math.sqrt( deltaX*deltaX + deltaY*deltaY );
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
        {
            return false;
        }
        Coordinates other = (Coordinates)obj;
        return ( this.x == other.x &&
                    this.y == other.y );
    }
}
