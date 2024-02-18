import org.junit.Test;

import classes.services.containers.Coordinates;
import classes.targets.CartesianAreaManager;
import classes.targets.Circle;
import classes.targets.Rectangle;
import interfaces.targets.CartesianAreaInterface;
import interfaces.targets.CartesianAreaManagerInterface;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import java.util.ArrayList;

public class TargetTests {
    
    Coordinates zeroCoordinates = new Coordinates(0,0);

    @Test
    public void cartesianAreaExceptionTests() {
        
        Circle circle = new Circle(zeroCoordinates, "label", 1);
        Rectangle rectangle = new Rectangle(zeroCoordinates, "label", 1, 1);

        assertThrows(NullPointerException.class, () -> {new Rectangle(zeroCoordinates, null, 1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Rectangle(zeroCoordinates, "", 1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Rectangle(zeroCoordinates, "target", 0, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Rectangle(zeroCoordinates, "target", 1, 0);});

        assertThrows(NullPointerException.class, () -> {new Circle(zeroCoordinates, null, 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Circle(zeroCoordinates, "", 1);});
        assertThrows(IllegalArgumentException.class, () -> {new Circle(zeroCoordinates, "target", 0);});

        assertThrows(NullPointerException.class, () -> {circle.checkAreaIntersection(null);});

        assertThrows(NullPointerException.class, () -> {rectangle.checkAreaIntersection(null);});
    }

    @Test
    public void cartesianAreaManagerExceptionTests() {

        CartesianAreaManagerInterface cartesianAreaManager = new CartesianAreaManager();

        assertThrows(NullPointerException.class, () -> {cartesianAreaManager.createTarget(null);});

        assertThrows(NullPointerException.class, () -> {cartesianAreaManager.createTargetsFromTxtFile(null);});
        assertThrows(IllegalArgumentException.class, () -> {cartesianAreaManager.createTargetsFromTxtFile("");});
        assertThrows(FileNotFoundException.class, () -> {cartesianAreaManager.createTargetsFromTxtFile("ciao");});
    }

    @Test
    public void targetCreationTests() {

        CartesianAreaManagerInterface cartesianAreaManager = new CartesianAreaManager();
        ArrayList<CartesianAreaInterface> targetList = cartesianAreaManager.getTargetList();
        cartesianAreaManager.createTarget(new Circle(zeroCoordinates, "circleLabel", 3));
        cartesianAreaManager.createTarget(new Rectangle(zeroCoordinates, "rectangleLabel", 5, 6));

        assertTrue(targetList.size() == 2);
        Circle circle = (Circle)cartesianAreaManager.getTargetList().get(0);
        Rectangle rectangle = (Rectangle)cartesianAreaManager.getTargetList().get(1);

        assertTrue(circle.getCoordinates().equals(zeroCoordinates) && circle.getLabel().equals("circleLabel") &&
                    circle.getRadius() == 3);
        assertTrue(rectangle.getCoordinates() == zeroCoordinates && rectangle.getLabel().equals("rectangleLabel") &&
                    rectangle.getWidth() == 5 && rectangle.getHeight() == 6);

        try {
            cartesianAreaManager.createTargetsFromTxtFile("testTargetInput.txt");
        } catch (FileNotFoundException e) {
            fail(e.getMessage());
        }

        assertTrue(targetList.size() == 4);
        Circle txtCircle = (Circle)targetList.get(2);
        Rectangle txtRectangle = (Rectangle)targetList.get(3);
        assertTrue(txtCircle.getCoordinates().equals(new Coordinates(2, 2)) && txtCircle.getLabel().equals("t4rget!|") &&
                    txtCircle.getRadius() == 3);
        assertTrue(txtRectangle.getCoordinates().equals(zeroCoordinates) && txtRectangle.getLabel().equals("tar-get_") &&
                    txtRectangle.getWidth() == 2 && txtRectangle.getHeight() == 1);
    }
}
