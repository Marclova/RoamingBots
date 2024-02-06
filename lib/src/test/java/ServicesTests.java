import static org.junit.Assert.assertThrows;

import org.junit.Test;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;

public class ServicesTests {
    
    @Test
    public void coordinatesExceptionTest() {
        Coordinates coordinates = new Coordinates(0, 0);

        assertThrows(NullPointerException.class, () -> {coordinates.calculateDistanceFrom(null);});
    }

    @Test
    public void directionalVectorsExceptionTests() {
        assertThrows(IllegalArgumentException.class, () -> {new DirectionalVectors(0, 0);});
        assertThrows(IllegalArgumentException.class, () -> {new DirectionalVectors(-2, 0);});
        assertThrows(IllegalArgumentException.class, () -> {new DirectionalVectors(2, 0);});
        assertThrows(IllegalArgumentException.class, () -> {new DirectionalVectors(0, -2);});
        assertThrows(IllegalArgumentException.class, () -> {new DirectionalVectors(0, 2);});
    }

    @Test
    public void argumentCheckerExceptionTests() {
        ArgumentChecker argumentChecker = new ArgumentChecker() {};

        assertThrows(NullPointerException.class, () -> {argumentChecker.checkNotNullObjects((Object)null);});

        assertThrows(NullPointerException.class, () -> {argumentChecker.checkGraterThanZeroValues(null);});
        assertThrows(IllegalArgumentException.class, () -> {argumentChecker.checkGraterThanZeroValues(0);});

        assertThrows(NullPointerException.class, () -> {argumentChecker.checkInsideIntervalValues(-1, 1, null);});
        assertThrows(IllegalArgumentException.class, () -> {argumentChecker.checkInsideIntervalValues(1, -1, 0.5);}); //wrong interval definition
        assertThrows(IllegalArgumentException.class, () -> {argumentChecker.checkInsideIntervalValues(-1, 1, 2);});

        assertThrows(NullPointerException.class, () -> {argumentChecker.checkNotEmptyStrings((String)null);});
        assertThrows(IllegalArgumentException.class, () -> {argumentChecker.checkNotEmptyStrings("");});
        assertThrows(IllegalArgumentException.class, () -> {argumentChecker.checkNotEmptyStrings(" ");});
    }
}
