package interfaces;

import classes.services.containers.Coordinates;

/**
 * Interface to define an object that occupies an area in the cartesian plane.
 * 
 * Parameters for the area dimension and shape are not provided.
 */
public interface CartesianAreaInterface {
    
    //getters
    public double getXCoordinate();
    public double getYCoordinate();
    public String getLabel();

    
    /**
     * Checks if a given coordinates are inside the area of this object.
     * 
     * @param coordinatesToCheck The coordinates of an element in the simulation panel.
     * @return True if the given coordinate is inside the area. False otherwise.
     */
    public boolean checkAreaIntersection(Coordinates coordinatesToCheck);
}
