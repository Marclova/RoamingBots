package classes.services.containers;

/**
 * Directional vectors container class with public parameters used to simplify methods' parameter requests.
 */
public class DirectionalVectors {
    
    public double xVector;
    public double yVector;

    public DirectionalVectors(double xVector, double yVector) {

        if (xVector < -1 || xVector > 1 || yVector < -1 || yVector > 1)
        {
            throw new IllegalArgumentException("x and y vectors must be within the [-1;1] interval.");
        }
        this.xVector = xVector;
        this.yVector = yVector;
    }
}
