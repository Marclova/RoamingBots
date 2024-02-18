package classes.targets;

import classes.services.ArgumentCheckerService;
import classes.services.containers.Coordinates;

/**
 * A circle-shaped target with custom position, dimensions and label.
 */
public class Circle extends AbstractCartesianArea {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    private double radius;

    public Circle(Coordinates coordinates, String label, double radius) {
        super(coordinates, label);
        argumentCheckerService.checkGraterThanZeroValues(radius);

        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public boolean checkAreaIntersection(Coordinates coordinatesToCheck) {
        argumentCheckerService.checkNotNullObjects(coordinatesToCheck);

        return (Math.abs(this.getCoordinates().x - coordinatesToCheck.x) <= this.radius &&
                Math.abs(this.getCoordinates().y - coordinatesToCheck.y) <= this.radius);
    }
}
