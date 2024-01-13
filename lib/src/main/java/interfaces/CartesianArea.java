package interfaces;

/**
 * Interface to define an object that occupies an area in the cartesian plane.
 * 
 * Parameters for the area dimension and shape are not provided.
 */
public interface CartesianArea {
    
    double xCoordinate = 0;
    double yCoordinate = 0;

    
    /**
     * Checks if a given coordinate is inside the area of this object.
     * 
     * @return True if the given coordinate is inside the area. False otherwise.
     */
    public boolean checkAreaIntersection(double xToCheck, double yToCheck);
}
