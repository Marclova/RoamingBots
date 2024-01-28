package interfaces;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;

/**
 * Contains the list of tasks to execute in every simulation cycle and the number of repetitions.
 * 
 * A program's "taskList" can contain other objects commands. //TODO Rewrite this documentation
 */
public interface ProgramInterface {

    //getters and setter
    public ArrayList<BotCommand> getTaskList();
    public boolean isExpired();
    public void setExpiredFlag(boolean bool);
    
    /**
     * Executes once all the tasks in the "taskList".
     * It acts differently depending on the type of program.
     */
    public void executeTasks();
}
