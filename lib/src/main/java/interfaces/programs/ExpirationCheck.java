package interfaces.programs;

public interface ExpirationCheck {

    /**
     * Checks if this program is expired depending on its own parameters and implementation
     * @return True if the program results expired, False otherwise.
     */
    public boolean isExpired();
    
}
