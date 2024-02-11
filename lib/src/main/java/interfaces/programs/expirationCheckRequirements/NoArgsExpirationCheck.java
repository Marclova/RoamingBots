package interfaces.programs.expirationCheckRequirements;

/**
 * Interfaces for programs that check their expiration without requiring any parameter.
 */
public interface NoArgsExpirationCheck {

    /**
     * Checks if this object is expired.
     * 
     * @return True if this program results expired. False otherwise.
     */
    public boolean isExpired();
    
}
