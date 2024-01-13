package interfaces;

import java.util.ArrayList;

/**
 * Contains the list of tasks to execute in every simulation cycle and the number of repetitions.
 * 
 * A program's "taskList" doesn't necessary contains tasks about just a single bot.
 * 
 * The Program's type is established depending on his parameters:
 * -Program with "counter" grater than 0: Repeat Program
 * -Program with "counter" equal to -1: Infinite Program
 * -program with "counter" equal to -1 and with not empty "labelToCheck": Label Program
 */
public interface ProgramInterface {

    ArrayList<Runnable> taskList = new ArrayList<Runnable>(); //list of references of bots' methods
    ArrayList<BotInterface> botToCheckList = new ArrayList<BotInterface>(); //list of bots for label check

    //condition parameters
    int counter = 0;
    String labelToCheck = "";

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
