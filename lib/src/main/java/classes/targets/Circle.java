package classes.targets;

import classes.services.containers.Coordinates;

public class Circle extends AbstractCartesianArea {

    private double radius;

    public Circle(Coordinates coordinates, String label, double radius) {
        super(coordinates, label);
        if(radius <= 0)
        {
            throw new IllegalArgumentException("Radius must be grater than 0.");
        }

        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public boolean checkAreaIntersection(Coordinates coordinatesToCheck) {
        return (Math.abs(this.getCoordinates().x - coordinatesToCheck.x) <= this.radius &&
                Math.abs(this.getCoordinates().y - coordinatesToCheck.y) <= this.radius);
    }
}
