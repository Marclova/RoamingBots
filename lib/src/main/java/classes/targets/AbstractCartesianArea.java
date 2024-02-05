package classes.targets;

import classes.services.containers.Coordinates;
import interfaces.CartesianAreaInterface;

public abstract class AbstractCartesianArea implements CartesianAreaInterface {
    
    private double xPosition;
    private double yPosition;
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

        this.xPosition = coordinates.x;
        this.yPosition = coordinates.y;
        this.label = label;
    }

    @Override
    public double getXCoordinate() {
        return this.xPosition;
    }

    @Override
    public double getYCoordinate() {
        return this.yPosition;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public abstract boolean checkAreaIntersection(Coordinates coordinatesToCheck);
}
