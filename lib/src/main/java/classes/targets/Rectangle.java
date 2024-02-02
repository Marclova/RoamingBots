package classes.targets;

public class Rectangle extends AbstractCartesianArea {

    private double width;
    private double height;

    public Rectangle(double xPosition, double yPosition, String label, double width, double height) {
        super(xPosition, yPosition, label);
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    @Override
    public boolean checkAreaIntersection(double xToCheck, double yToCheck) {
        return ( xToCheck >= this.getXCoordinate() && xToCheck <= (this.getXCoordinate() + this.width) &&
                    yToCheck >= this.getYCoordinate() && yToCheck <= (this.getYCoordinate() + this.height) );
    }
    
}
