import org.junit.Test;

import classes.services.containers.Coordinates;
import classes.targets.Circle;
import classes.targets.Rectangle;
import interfaces.CartesianAreaInterface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class TargetTests {
    
    Coordinates zeroCoordinates = new Coordinates(0,0);

    @Test
    public void targetExceptionTests() {
        
        assertThrows(NullPointerException.class, () -> {new Rectangle(zeroCoordinates, null, 1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Rectangle(zeroCoordinates, "", 1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Rectangle(zeroCoordinates, "target", 0, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Rectangle(zeroCoordinates, "target", 1, 0);});

        assertThrows(NullPointerException.class, () -> {new Circle(zeroCoordinates, null, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Circle(zeroCoordinates, "", 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Circle(zeroCoordinates, "target", 0);});
    }

    @Test
    public void targetCreationAndCheckTests() {

        Rectangle rectangle = new Rectangle(zeroCoordinates, "target", 10, 20);
        Circle circle = new Circle(zeroCoordinates, "target", 10);
        ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
        targetList.add(circle);
        targetList.add(rectangle);

        for (CartesianAreaInterface c : targetList) {
            assertTrue(c.getXCoordinate() == 0 && c.getYCoordinate() == 0);
            assertEquals("target", c.getLabel());
            assertTrue(c.checkAreaIntersection(new Coordinates(5, 5)));
        }

        assertTrue(circle.getRadius() == 10);
        assertTrue(rectangle.getHeight() == 10 && rectangle.getWidth() == 20);
    }
}