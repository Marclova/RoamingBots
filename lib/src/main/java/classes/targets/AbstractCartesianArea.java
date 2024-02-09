package classes.targets;

import classes.services.containers.Coordinates;
import interfaces.CartesianAreaInterface;

public abstract class AbstractCartesianArea implements CartesianAreaInterface {
    
    private Coordinates coordinates;
    private String label;

    public AbstractCartesianArea(Coordinates coordinates, String label) {
        if(coordinates == null || label == null)
        {
            throw new NullPointerException();
        }
        if(label.isEmpty())
        {
            throw new IllegalArgumentException("Label must contain at least one character.");
        }

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
