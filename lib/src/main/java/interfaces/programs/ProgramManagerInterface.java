package interfaces.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
import interfaces.bots.BotInterface;

/**
 * Creates and manages all the bot's programs.
 */
public interface ProgramManagerInterface {
    
    // //getters and setters
    // public SimulationManagerInterface getSimulationManager();
    // public void setSimulationManager(SimulationManagerInterface simM);

    
    /**
     * Executes all the Programs' tasks.
     */
    public void executePrograms(ArrayList<BotInterface> botList);

    /**
     * Deletes all the Counter Programs considered expired
     *      the Target Program whom bot has reached destination
     *      and all Label Program whom bot has detected the right label.
     *
     * @param botList List of all bots in the simulation plane
     * @param targetList List of all the target area in the simulation plane
     */
    public void deleteExpiredPrograms(ArrayList<BotInterface> botList, ArrayList<CartesianArea> targetList);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks a defined amount of times.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of bots' tasks to execute.
     * @param repetitions The program's number of simulation cycle of life (counter value).
     */
    public void createRepeatingProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, int repetitions);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks
     *      an infinite amount of times.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of bots' tasks to execute.
     */
    public void createInfiniteProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks
     *      until it reaches for the given target.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of commands to execute.
     * @param targetLabelToReach The target label the bot is going to look for.
     */
    public void createTargetProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, String targetLabelToReach);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks
     *      until it detects the given label from another bot.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of commands to execute.
     * @param targetLabelToReach The target label the bot is going to look for by detection.
     * @param detectionDistance
     */
    public void createLabelProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList,
                                    String labelToDetect, double detectionDistance);
}
