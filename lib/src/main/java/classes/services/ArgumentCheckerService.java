package classes.services;

public class ArgumentCheckerService {
    
    /**
     * Checks if the given objects are not null and throws exception if they are.
     * 
     * @param objects The objects to check.
     * @throws NullPointerException If at least one of the given objects is null.
     */
    public void checkNotNullObjects(Object... objects) throws NullPointerException {
        for (Object obj : objects) {
            if(obj == null)
            {
                throw new NullPointerException("ArgumentChecker's Exception");
            }
        }
    }

    /**
     * Checks if the given numbers are grater than 0 and throws exception if they're not.
     * 
     * @param values The numbers to check.
     * @throws NullPointerException If at least one of the numbers is 0 or lower.
     */
    public void checkGraterThanZeroValues(double... values) throws NullPointerException {
        for (double n : values) {
            if(n <= 0)
            {
                throw new IllegalArgumentException("ArgumentChecker's Exception");
            }
        }
    }
    /**
     * Checks if the given values are equal to 0 or higher.
     * 
     * @param values The numbers to check.
     * @throws IllegalArgumentException If at least one of the numbers is lower than 0.
     */
    public void checkZeroOrHigherValues(double... values) {
        for (double n : values) {
            if(n < 0)
            {
                throw new IllegalArgumentException("ArgumentChecker's Exception");
            }
        }
    }

    /**
     * Checks if the given numbers are inside the defined interval (extremes included) and throws exception if they're not.
     * 
     * @param v1 Lower value of the interval.
     * @param v2 Higher value of the interval.
     * @param values The numbers to check.
     * @throws IllegalArgumentException If at least one of the numbers is outside of the interval.
     */
    public void checkInsideIntervalValues(double v1, double v2, double... values) throws IllegalArgumentException {
        for (double n : values) {
            if (n < v1 || n > v2)
            {
                throw new IllegalArgumentException("ArgumentChecker's Exception");
            }
        }
    }

    /**
     * Checks if the given strings are not empty or blank and throws exception if they are.
     * 
     * @param args The strings to check
     * @throws NullPointerException If at least one of the strings is null.
     * @throws IllegalArgumentException If at least one of the string is empty or blank.
     */
    public void checkNotEmptyStrings(String... args) throws NullPointerException, IllegalArgumentException {
        for (String s : args) {
            if(s == null)
            {
                throw new NullPointerException("ArgumentChecker's Exception");
            }
            if(s.isBlank())
            {
                throw new IllegalArgumentException("ArgumentChecker's Exception");
            }

        }
    }
}
