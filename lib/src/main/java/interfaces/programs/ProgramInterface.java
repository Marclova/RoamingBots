package interfaces.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;

/**
 * Contains the list of tasks to execute in every simulation cycle and the number of repetitions.
 * 
 * A program's "taskList" can contain other objects commands. //TODO Rewrite this documentation
 */
public interface ProgramInterface {

    //getters and setter
    public ArrayList<BotCommand> getTaskList();
    
    /**
     * Executes once all the tasks in the "taskList".
     * 
     * @param bot The bot owning this program.
     * @return True if every command has been executed correctly.
     *          False if at least least one of them has caused no changes.
     */
    public boolean executeTasks(BotInterface bot);
}
