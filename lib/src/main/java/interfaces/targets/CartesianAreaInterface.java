package interfaces.targets;

import classes.services.containers.Coordinates;

/**
 * Interface to define an object that occupies an area in the cartesian plane.
 * 
 * Parameters and methods about the area's dimension and shape are not defined in this interface.
 */
public interface CartesianAreaInterface {
    
    //getters
    public Coordinates getCoordinates();
    public String getLabel();

    
    /**
     * Checks if the given coordinates are inside the area of this object.
     * 
     * @param coordinatesToCheck The coordinates of a point in the simulation panel.
     * @return True if the given coordinate are inside the area. False otherwise.
     */
    public boolean checkAreaIntersection(Coordinates coordinatesToCheck);
}
