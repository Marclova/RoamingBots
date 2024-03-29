package classes.targets;

import classes.services.ArgumentCheckerService;
import classes.services.containers.Coordinates;

/**
 * A rectangle-shaped target with custom position, dimensions and label.
 */
public class Rectangle extends AbstractCartesianArea {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    private double width;
    private double height;

    public Rectangle(Coordinates coordinates, String label, double width, double height) {
        super(coordinates, label);
        argumentCheckerService.checkGraterThanZeroValues(width, height);

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
        argumentCheckerService.checkNotNullObjects(coordinatesToCheck);

        double xToCheck = coordinatesToCheck.x;
        double yToCheck = coordinatesToCheck.y;
        double thisX = this.getCoordinates().x;
        double thisY = this.getCoordinates().y;
        return ( thisX <= xToCheck ) && ( (thisX + this.width) >= xToCheck ) &&
                ( thisY <= yToCheck ) && ( (thisY + this.height) >= yToCheck );
    }
}
