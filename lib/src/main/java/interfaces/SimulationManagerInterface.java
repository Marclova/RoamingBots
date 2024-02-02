package interfaces;

import java.util.ArrayList;

import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

/**
 * It contains and executes all the elements of the simulation.
 */
public interface SimulationManagerInterface {

    //Getters and setters
    public ArrayList<BotInterface> getBotList();
    public ArrayList<? extends CartesianAreaInterface> getTargetList();
    public BotManagerInterface getBotManager();
    public ProgramManagerInterface getProgramManager();
    public void setBotManager(BotManagerInterface botM);
    public void setProgramManager(ProgramManagerInterface progM);

    //Duration of a simulation cycle expressed in seconds (getters and setters)
    public double getExecutionTimeCycle();
    public void setExecutionTimeCycle();

    /**
     * Initialize a new botManager and sets it as parameter.
     *      The eventual old botManager will be deleted by the garbage collector.
     * @param botM The botManager to set.
     */
    public void createBotManager(BotManagerInterface botM);

    /**
     * Initialize a new programManager and sets it as parameter.
     *      The eventual old programManager will be deleted by the garbage collector.
     * 
     * @param progM The programManager to set.
     */
    public void createProgramManager(ProgramManagerInterface progM);

    /**
     * Adds the given cartesian shape into the "targetList".
     * 
     * @param fileAddress The location of the file in the local file system.
     * @return True if the target has been successfully created. False otherwise.
     */
    public boolean createTargetsFromTxtFile(String fileAddress);

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
    public boolean createBot(BotInterface botToAdd);

    /**
     * Adds the given bots into the "botList"
     * 
     * @param botListToAdd The bot list to add
     * @return True if the bots are correctly created. False otherwise.
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
    public boolean createRandomBots(int quantity, double x1, double y1, double x2, double y2);

    /**
     * Starts the simulation with the current settings for the given amount of time.
     * 
     * @param progressionTime The time in seconds in witch
     * @param coolDownTime The time in seconds of pause between every new status update.
     */
    public void simulate(double progressionTime, double coolDownTime);
}
