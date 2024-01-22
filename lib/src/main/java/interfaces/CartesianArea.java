package interfaces;

/**
 * Interface to define an object that occupies an area in the cartesian plane.
 * 
 * Parameters for the area dimension and shape are not provided.
 */
public interface CartesianArea {
    
    //getters
    public double getXCoordinate();
    public double getYCoordinate();
    public String getLabel();

    
    /**
     * Checks if a given coordinate is inside the area of this object.
     * 
     * @return True if the given coordinate is inside the area. False otherwise.
     */
    public boolean checkAreaIntersection(double xToCheck, double yToCheck);
}
