package classes.services.abstractServeces;

@SuppressWarnings("unused") //I hope this is not code smell. I want to avoid code repetitions and grant more readability...
public abstract class ArgumentChecker {
    
    /**
     * Checks if the given object is not null and throws exception if is not.
     * 
     * @param obj The object to check.
     * @throws NullPointerException If the given object is null.
     */
    private void checkNullObject(Object obj) throws NullPointerException {
        if(obj == null)
        {
            throw new NullPointerException();
        }
    }

    /**
     * Checks if the given numbers are grater than zero and throws exception if they're not.
     * 
     * @param values The numbers to check.
     * @throws NullPointerException If at least one of the numbers is 0 or lower.
     */
    private void checkPositiveValues(double... values) throws NullPointerException {
        for (double n : values) {
            if(n <= 0)
            {
                throw new NullPointerException();
            }
        }
    }

    /**
     * Checks if the given numbers are inside the defined interval (extremes included).
     * 
     * @param v1 Lower value of the interval.
     * @param v2 Higher value of the interval.
     * @param values The numbers to check.
     * @throws IllegalArgumentException If at least one of the numbers is outside of the interval.
     */
    private void checkInsideIntervalValues(double v1, double v2, double... values) throws IllegalArgumentException {
        if(v2 < v1) //I'm not blocking the execution for this
        {
            double v = v2;
            v2 = v1;
            v1 = v;
        }
        for (double n : values) {
            if (n < v1 || n > v2)
            {
                throw new IllegalArgumentException();
            }
        }
    }
}
