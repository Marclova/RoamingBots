package classes.targets;

import interfaces.CartesianArea;

public class Square implements CartesianArea {

    private double xPosition;
    private double yPosition;
    private double width;
    private double height;
    private String label;

    public Square(double xPosition, double yPosition, double width, double height, String label) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
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
    public boolean checkAreaIntersection(double xToCheck, double yToCheck) {
        return ( xToCheck >= this.xPosition && xToCheck <= (this.xPosition + this.width) &&
                    yToCheck >= this.yPosition && yToCheck <= (this.yPosition + this.height) );
    }
    
}
