package interfaces;

import java.util.ArrayList;

/**
 * It contains and executes all the elements of the simulation.
 */
public interface SimulationManagerInterface<B extends BotInterface<B>> { //TODO finish defining interface

    //Getters and setters
    public ArrayList<B> getBotList();
    public ArrayList<? extends CartesianArea> getTargetList();
    public BotManagerInterface<B> getBotManager();
    public ProgramManagerInterface<B> getProgramManager();
    public void setBotManager(BotManagerInterface<B> botM);
    public void setProgramManager(ProgramManagerInterface<B> progM);

    //Duration of a simulation cycle expressed in seconds (getters and setters)
    public double getExecutionTimeCycle();
    public void setExecutionTimeCycle();

    /**
     * Initialize a new botManager and sets it as parameter.
     *      The eventual old botManager will be deleted by the garbage collector.
     * @param botM The botManager to set.
     */
    public void createBotManager(BotManagerInterface<B> botM);

    /**
     * Initialize a new programManager and sets it as parameter.
     *      The eventual old programManager will be deleted by the garbage collector.
     * 
     * @param progM The programManager to set.
     */
    public void createProgramManager(ProgramManagerInterface<B> progM);

    /**
     * Adds the given cartesian shape into the "targetList".
     * 
     * @param target The target to add into the list.
     * @return True if the target has been successfully created. False otherwise.
     */
    public <C extends CartesianArea> boolean createTarget(C target);

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
     * Starts the simulation with the current settings for the given amount of time.
     * 
     * @param progressionTime The time in seconds in witch
     * @param coolDownTime The time in seconds of pause between every new status update.
     */
    public void simulate(double progressionTime, double coolDownTime);
}
