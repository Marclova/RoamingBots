package interfaces;

import java.util.ArrayList;

/**
 * The class extending this interface will have the responsibility to create and move bots
 *      within the simulation area and through the targets.  
 */
public interface BotManagerInterface<B extends BotInterface> {

    //getters and setters
    public ArrayList<B> getBotList();
    public ArrayList<? extends CartesianArea> getTargetList();
    public void setBotList();
    public void setTargetList();

    //Duration of a simulation cycle expressed in seconds (getters and setters)
    public double getSimulationTimeCycle();
    public void setSimulationTimeCycle();


    /**
     * Adds a robot with the given coordinates into the "botList"
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return True if the bot is correctly created. False otherwise.
     */
    public boolean createBot(double x, double y);

    /**
     * Adds the given bot into the "botList"
     * 
     * @param bot The bot to add
     * @return  True if the bot is correctly created. False otherwise.
     */
    public boolean createBot(B botToAdd);

    /**
     * Adds the given bots into the "botList"
     * 
     * @param botListToAdd The bot list to add
     * @return True if the bots are correctly created. False otherwise.
     */
    public boolean createBot(ArrayList<B> botListToAdd);

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
    public boolean createRandomBots(int quantity, double x1, double y1, double x2, double y2);

    /**
     * Moves the bots depending on their own orientation and speed
     *      in relation to the simulationTimeCycle.
     * 
     * @return True if the bots' disposition has been changed. False if no bot has moved.
     */
    public boolean moveBots();
}
