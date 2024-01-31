package interfaces.programs;

import interfaces.CartesianArea;
import interfaces.bots.BotInterface;

import java.util.ArrayList;

public interface TargetListExpirationCheck {
    
    /**
     * Checks if this program is expired depending on its own parameters and implementation
     * 
     * @param botToCheck The bot owning this program.
     * @param targetList The list of all the target areas in the simulation plane.
     * @return True if the program results expired, False otherwise.
     */
    public boolean isExpired(BotInterface botToCheck, ArrayList<CartesianArea> targetList);
}
