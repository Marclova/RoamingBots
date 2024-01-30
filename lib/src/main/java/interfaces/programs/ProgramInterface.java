package interfaces.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
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
     * Checks if this program is expired depending on its own parameters and implementation
     * 
     * @param botToProgram The bot that is going to actually execute the program's tasks.
     * @param botList The list of all the bots in the simulation plane
     * @param targetList The list of all the target areas in the simulation plane.
     * @return True if the program results expired, False otherwise.
     */
    public boolean isExpired(BotInterface botToProgram, ArrayList<BotInterface> botList, ArrayList<CartesianArea> targetList);
    
    /**
     * Executes once all the tasks in the "taskList".
     * It acts differently depending on the type of program.
     */
    public void executeTasks();
}
