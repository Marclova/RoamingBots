package classes.targets;

import classes.services.containers.Coordinates;

/**
 * A circle-shaped target with custom position, dimensions and label.
 */
public class Circle extends AbstractCartesianArea {

    private double radius;

    public Circle(Coordinates coordinates, String label, double radius) {
        super(coordinates, label);
        this.checkGraterThanZeroValues(radius);

        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public boolean checkAreaIntersection(Coordinates coordinatesToCheck) {
        this.checkNotNullObjects(coordinatesToCheck);

        return (Math.abs(this.getCoordinates().x - coordinatesToCheck.x) <= this.radius &&
                Math.abs(this.getCoordinates().y - coordinatesToCheck.y) <= this.radius);
    }
}
