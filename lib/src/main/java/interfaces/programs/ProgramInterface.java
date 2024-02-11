package interfaces.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;

/**
 * Contains the list of tasks to execute in every simulation cycle.
 */
public interface ProgramInterface {

    //getters and setter
    public ArrayList<BotCommand> getTaskList();
    
    /**
     * Executes once all the tasks in the "taskList".
     * 
     * @param bot The bot owning this program.
     * @return True if at least one command caused any variations to the bot.
     *          False if no commands has caused any changes.
     */
    public boolean executeTasks(BotInterface bot);
}