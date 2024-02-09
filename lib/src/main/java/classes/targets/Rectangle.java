package classes.targets;

import classes.services.containers.Coordinates;

public class Rectangle extends AbstractCartesianArea {

    private double width;
    private double height;

    public Rectangle(Coordinates coordinates, String label, double width, double height) {
        super(coordinates, label);

        if(width <= 0 || height <= 0)
        {
            throw new IllegalArgumentException("Width and height must be grater than 0.");
        }

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
        double xToCheck = coordinatesToCheck.x;
        double yToCheck = coordinatesToCheck.y;
        return ( this.getCoordinates().x <= xToCheck ) && ( (this.getCoordinates().x + this.width) >= xToCheck ) &&
                ( this.getCoordinates().y <= yToCheck ) && ( (this.getCoordinates().y + this.height) >= yToCheck );
    }
}
