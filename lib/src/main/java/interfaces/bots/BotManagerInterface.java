package interfaces.bots;

import java.util.ArrayList;

import classes.services.containers.Coordinates;

/**
 * The class extending this interface will have the responsibility to create and move bots
 *      through the simulation area.
 */
public interface BotManagerInterface {

    //getter
    public ArrayList<BotInterface> getBotList();

    /**
     * Creates and inserts a robot with the given coordinates into the "botList".
     * 
     * @param coordinates The new bot's coordinates.
     * @return The bot that has been created.
     */
    public BotInterface createBot(Coordinates coordinates);

    /**
     * Inserts the given bot into the "botList".
     * 
     * @param bot The bot to add.
     */
    public boolean createBot(BotInterface botToAdd);

    /**
     * Inserts the given bots into the "botList".
     * 
     * @param botListToAdd The bot list to add.
     * @return True if at least one the bots are correctly created. False otherwise.
     */
    public boolean createBot(ArrayList<BotInterface> botListToAdd);

    /**
     * Creates a given quantity of bots with random coordinates.
     * 
     * @param quantity The amount of bots to create.
     * @param coordinates1 The lower coordinates.
     * @param coordinates2 The higher coordinates.
     * @return True if all the bots are correctly created. False otherwise.
     */
    public boolean createRandomBots(int quantity, Coordinates coordinates1, Coordinates coordinates2);

    /**
     * Moves the bots depending on their own orientation, speed and movementTimer.
     *      
     * @param movementTime The time given to every bot to move.
     * @return True if any of the bots' disposition has been changed. False if no bot has moved.
     */
    public boolean moveAllBots(double movementTime);
}