package classes.targets;

import interfaces.CartesianAreaInterface;

public abstract class AbstractCartesianArea implements CartesianAreaInterface {
    
    private double xPosition;
    private double yPosition;
    private String label;

    public AbstractCartesianArea(double xPosition, double yPosition, String label) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
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
    public abstract boolean checkAreaIntersection(double xToCheck, double yToCheck);
}
