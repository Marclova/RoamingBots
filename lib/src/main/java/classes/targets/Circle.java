package classes.targets;

public class Circle extends AbstractCartesianArea {

    private double radius;

    public Circle(double xPosition, double yPosition, String label, double radius) {
        super(xPosition, yPosition, label);
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public boolean checkAreaIntersection(double xToCheck, double yToCheck) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkAreaIntersection'");
    }
    
}
