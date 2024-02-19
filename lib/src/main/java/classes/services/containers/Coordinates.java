package classes.services.containers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.common.base.Objects;

import classes.services.ArgumentCheckerService;

/**
 * Coordinates container class with public parameters used to simplify methods' parameter requests.
 *  Coordinates don't get modified, but substituted.
 */
public class Coordinates {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();
    
    public final double x;
    public final double y;

    public Coordinates(double x, double y) {
        double roundedX = new BigDecimal(x)
                            .setScale(2, RoundingMode.HALF_UP).doubleValue();
        double roundedY = new BigDecimal(y)
                            .setScale(2, RoundingMode.HALF_UP).doubleValue();    

        this.x = roundedX;
        this.y = roundedY;
    }

    /**
     * Calculates the hypotenuse from the difference of the two pairs of coordinates.
     * 
     * @param otherCoordinates The point from which the distance will be calculated.
     * @return The distance from this coordinates and the other coordinate.
     */
    public double calculateDistanceFrom(Coordinates otherCoordinates) {
        argumentCheckerService.checkNotNullObjects(otherCoordinates);

        double deltaX = Math.abs( this.x - otherCoordinates.x );
        double deltaY = Math.abs( this.y - otherCoordinates.y );

        return Math.sqrt( deltaX*deltaX + deltaY*deltaY );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Coordinates other = (Coordinates) o;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash *= 11 + Objects.hashCode(this.x);
        hash *= 11 + Objects.hashCode(this.y);
        return hash;
    }
}
