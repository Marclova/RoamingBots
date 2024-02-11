package classes.targets;

import classes.services.containers.Coordinates;

/**
 * A rectangle-shaped target with custom position, dimensions and label.
 */
public class Rectangle extends AbstractCartesianArea {

    private double width;
    private double height;

    public Rectangle(Coordinates coordinates, String label, double width, double height) {
        super(coordinates, label);
        this.checkGraterThanZeroValues(width, height);

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
    public boolean checkAreaIntersection(Coordinates coordinatesToCheck) {
        this.checkNotNullObjects(coordinatesToCheck);

        double xToCheck = coordinatesToCheck.x;
        double yToCheck = coordinatesToCheck.y;
        double thisX = this.getCoordinates().x;
        double thisY = this.getCoordinates().y;
        return ( thisX <= xToCheck ) && ( (thisX + this.width) >= xToCheck ) &&
                ( thisY <= yToCheck ) && ( (thisY + this.height) >= yToCheck );
    }
}
