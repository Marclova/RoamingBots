package interfaces.programs.expirationCheckRequirements;

import interfaces.bots.BotInterface;

import java.util.ArrayList;

import classes.services.containers.Coordinates;

/**
 * Interface for programs that check their expiration requiring the bot's coordinates and a BotList.
 */
public interface BotListExpirationCheck {
    
    /**
     * Checks if this program is expired.
     * 
     * @param botToCheck The bot owning this program.
     * @param botList The list of all the bots in the simulation plane.
     * @return True if the program results expired. False otherwise.
     */
    public boolean isExpired(Coordinates botCoordinates, ArrayList<BotInterface> botList);
}
