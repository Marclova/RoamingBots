package interfaces.programs;

import interfaces.bots.BotInterface;

import java.util.ArrayList;

public interface BotListExpirationCheck {
    
    /**
     * Checks if this program is expired depending on its own parameters and implementation
     * 
     * @param botToCheck The bot owning this program.
     * @param botList The list of all the bots in the simulation plane.
     * @return True if the program results expired, False otherwise.
     */
    public boolean isExpired(BotInterface botToCheck, ArrayList<BotInterface> botList);
}
