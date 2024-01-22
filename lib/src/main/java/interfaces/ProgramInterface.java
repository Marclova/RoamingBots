package interfaces;

import java.util.ArrayList;

import enumerations.ProgramTypesEnum;

/**
 * Contains the list of tasks to execute in every simulation cycle and the number of repetitions.
 * 
 * A program's "taskList" can contain other objects commands. //TODO Rewrite this documentation
 */
public interface ProgramInterface<B extends BotInterface<B>> {

    //getters
    public ArrayList<Runnable> getTaskList();
    public ProgramTypesEnum getProgramType();

    //condition parameters (getters and setters)
    public int getCounter();
    public void setCounter(int counter);
    public String getTargetLabelToCheck();

    
    /**
     * Executes once all the tasks in the "taskList".
     * It acts differently depending on the type of program:
     * 
     * Repeat Program: Executes the task if the counter is grater than 0, decreasing it.
     * Infinite Program: Executes the tasks without checking the counter nor the labelToCheck.
     * Target Program: Executes the tasks until one of the bots' labels in the "botToCheckList"
     *                  matches with the "labelToCheck".
     *                  Otherwise the counter is set to 0 (turning into an expired Repeat Program).
     */
    public void executeTasks();

    // /**
    //  * Checks if any bots in the "botToCheckList" has matching label with the "labelToCheck".
    //  * 
    //  * @return True if there's at least one bot with a matching label. False otherwise.
    //  */
    // public boolean checkTarget();
}
