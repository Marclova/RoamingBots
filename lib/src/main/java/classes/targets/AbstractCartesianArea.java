package classes.targets;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
import interfaces.targets.CartesianAreaInterface;

/**
 * Abstract class containing all the common instances and methods between the various types of targets.
 */
public abstract class AbstractCartesianArea extends ArgumentChecker implements CartesianAreaInterface {
    
    private Coordinates coordinates;
    private String label;

    public AbstractCartesianArea(Coordinates coordinates, String label) {
        this.checkNotNullObjects(coordinates);
        this.checkNotEmptyStrings(label);

        this.coordinates = coordinates;
        this.label = label;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public abstract boolean checkAreaIntersection(Coordinates coordinatesToCheck);
}
