package interfaces.programs;

import java.util.ArrayList;

import classes.programs.InfiniteProgram;
import classes.programs.LabelProgram;
import classes.programs.RepeatingProgram;
import classes.programs.TargetProgram;
import functionalInterfaces.BotCommand;
import interfaces.CartesianAreaInterface;
import interfaces.bots.BotInterface;

/**
 * Creates, manages and executes all the bot's programs.
 */
public interface ProgramManagerInterface {

    /**
     * Removes the first program in the given bot's programList if this results expired.
     *  Then executes the first program in the bot's programList.
     * 
     * @param bot   The given bot which program must be checked.
     * @param botList   List of bots which programs must be executed.
     * @param targetList    Parameter used for program expiration check.
     */
    public void deleteExpiredAndThenExecuteAllPrograms(ArrayList<BotInterface> botList, ArrayList<CartesianAreaInterface> targetList);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks a defined amount of times.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of bots' tasks to execute.
     * @param repetitions The program's number of simulation cycle of life (counter value).
     * @return The assigned program.
     */
    public RepeatingProgram createRepeatingProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, int repetitions);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks
     *      an infinite amount of times.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of bots' tasks to execute.
     */
    public InfiniteProgram createInfiniteProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks
     *      until it reaches for the given target.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of commands to execute.
     * @param targetLabelToReach The target label the bot is going to look for.
     * @return The assigned program.
     */
    public TargetProgram createTargetProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, String targetLabelToReach);

    /**
     * Creates and gives to the given bot a Program that executes all the given tasks
     *      until it detects the given label from another bot.
     * 
     * @param botToProgram The bot to install the program into.
     * @param taskList The list of commands to execute.
     * @param targetLabelToReach The target label the bot is going to look for by detection.
     * @param detectionDistance The distance within the bot will detect other bot's signal emission.
     * @return The assigned program.
     */
    public LabelProgram createLabelProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList,
                                    String labelToDetect, double detectionDistance);
}
