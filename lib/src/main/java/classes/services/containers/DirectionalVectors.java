package classes.services.containers;

import classes.services.abstractServeces.ArgumentChecker;

/**
 * Directional vectors container class with public parameters used to simplify methods' parameter requests.
 */
public class DirectionalVectors extends ArgumentChecker {
    
    public double xVector;
    public double yVector;

    public DirectionalVectors(double xVector, double yVector) {
        this.checkInsideIntervalValues(-1, 1, xVector, yVector);
        
        this.xVector = xVector;
        this.yVector = yVector;
    }
}
