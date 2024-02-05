package interfaces.bots;

import java.util.ArrayList;

import classes.services.containers.Coordinates;

/**
 * The class extending this interface will have the responsibility to create and move bots
 *      within the simulation area.  
 */
public interface BotManagerInterface {

    //getter
    public ArrayList<BotInterface> getBotList();

    /**
     * Adds a robot with the given coordinates into the "botList"
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return The bot that has been created.
     */
    public BotInterface createBot(double x, double y);

    /**
     * Adds the given bot into the "botList"
     * 
     * @param bot The bot to add
     */
    public void createBot(BotInterface botToAdd);

    /**
     * Adds the given bots into the "botList"
     * 
     * @param botListToAdd The bot list to add
     * @return True if at least one the bots are correctly created. False otherwise.
     */
    public boolean createBot(ArrayList<BotInterface> botListToAdd);

    /**
     * Created a given quantity of bots with random coordinates
     * 
     * @param quantity The amount of bots to create.
     * @param x1 The lower x coordinate.
     * @param y1 The lower y coordinate.
     * @param x2 The higher x coordinate.
     * @param y2 The higher y coordinate.
     * @return True if all the bots are correctly created. False otherwise.
     */
    public boolean createRandomBots(int quantity, Coordinates coordinates1, Coordinates coordinates2);

    /**
     * Moves the bots depending on their own orientation, speed and movementTimer.
     *      
     * @param movementTime The time given to every bot to move. (max. 1 second)
     * @return True if the bots' disposition has been changed. False if no bot has moved.
     */
    public boolean moveAllBots(double movementTime);
}
