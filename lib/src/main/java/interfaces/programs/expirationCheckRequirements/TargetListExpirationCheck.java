package interfaces.programs.expirationCheckRequirements;

import interfaces.CartesianAreaInterface;

import java.util.ArrayList;

import classes.services.containers.Coordinates;

public interface TargetListExpirationCheck {
    
    /**
     * Checks if this program is expired depending on its own parameters and implementation
     * 
     * @param botToCheck The bot owning this program.
     * @param targetList The list of all the target areas in the simulation plane.
     * @return True if the program results expired, False otherwise.
     */
    public boolean isExpired(Coordinates botCoordinates, ArrayList<CartesianAreaInterface> targetList);
}
