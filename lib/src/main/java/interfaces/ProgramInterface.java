package interfaces;

import java.util.ArrayList;

/**
 * Contains the list of tasks to execute in every simulation cycle and the number of repetitions.
 * 
 * A program's "taskList" can contain other objects commands //TODO Serch online for type checkers and add exemples
 * 
 * The Program's type is established depending on his parameters:
 * -Program with "counter" grater than 0: Repeat Program
 * -Program with "counter" equal to -1: Infinite Program
 * -program with "counter" equal to -1 and
 *      with not empty "labelToCheck" and "botCheckList": Label Program
 */
public interface ProgramInterface<B extends BotInterface> {

    //getters
    public ArrayList<Runnable> getTaskList();
    public ArrayList<B> getBotToCheckList();

    //condition parameters (getters and setters)
    public int getCounter();
    public String getLabelToCheck();
    public void setCounter(int counter);
    public void setLabelToCheck(String label);

    
    /**
     * Executes once all the tasks in the "taskList".
     * It acts differently depending on the type of program:
     * 
     * Repeat Program: Executes the task if the counter is grater than 0, decreasing it.
     * Infinite Program: Executes the tasks without checking the counter nor the labelToCheck.
     * Label Program: Executes the tasks if none of the bots' labels in the "botToCheckList"
     *                  matches with the "labelToCheck".
     *                  Otherwise the counter is set to 0 (turning into an expired Repeat Program).
     */
    public void executeTasks();

    /**
     * Checks if any bots in the "botToCheckList" has matching label with the "labelToCheck".
     * 
     * @return True if there's at least one bot with a matching label. False otherwise.
     */
    public boolean checkLabel();
}
