package interfaces;

import java.util.ArrayList;

/**
 * Creates and manages all the bot's programs every simulation cycle
 */
public interface ProgramManagerInterface {
    
    ArrayList<ProgramInterface> programList = new ArrayList<ProgramInterface>();

    /**
     * Deletes all the Programs whom counter value is 0.
     */
    public void deleteExpiredPrograms();

    /**
     * Executes all the Programs' tasks
     *      and then it deletes the ones which counter has reached 0.
     * Programs with a negative counter value are considered with infinite repetitions.
     */
    public void executeAndDeletePrograms();

    /**
     * Creates a Program that executes all the given tasks a defined amount of times.
     * 
     * @param taskList The list of bots' tasks to execute.
     * @param repetitions The program's number of simulation cycle of life (counter value).
     * @return True if a new program has been created. False otherwise.
     */
    public boolean createRepeatingProgram(ArrayList<Runnable> taskList, int repetitions);

    /**
     * Creates a Program that executes all the given tasks an infinite amount of times.
     *      The Program's counter will be set to -1.
     * 
     * @param taskList The list of bots' tasks to execute.
     * @return True if a new program has been created. False otherwise.
     */
    public boolean createInfiniteProgram(ArrayList<Runnable> taskList);

    /**
     * Creates a Program that executes all the given tasks until a specific label is revealed in the bot.
     *      The Program's counter will be set to -1.
     * 
     * @param taskList
     * @param labelToCheck
     * @return
     */
    public boolean createLabelCheckProgram(ArrayList<Runnable> taskList, String labelToCheck);
}
