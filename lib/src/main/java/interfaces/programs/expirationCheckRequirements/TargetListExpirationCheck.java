package interfaces.programs.expirationCheckRequirements;

import java.util.ArrayList;

import classes.services.containers.Coordinates;
import interfaces.targets.CartesianAreaInterface;

/**
 * Interface for programs that check their expiration requiring the bot's coordinates and a targetList.
 */
public interface TargetListExpirationCheck {
    
    /**
     * Checks if this object is expired.
     * 
     * @param botToCheck The bot owning this program.
     * @param targetList The list of all the target areas in the simulation plane.
     * @return True if this program results expired. False otherwise.
     */
    public boolean isExpired(Coordinates coordinatesToCheck, ArrayList<CartesianAreaInterface> targetList);
}
