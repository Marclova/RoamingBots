package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.ProgramInterface;

/**
 * A generic program installed into a bot able to execute its own tasks
 */
public abstract class AbstractProgram implements ProgramInterface {

    private ArrayList<BotCommand> taskList;

    public AbstractProgram(ArrayList<BotCommand> taskList) {
        this.taskList = taskList;
    }

    @Override
    public ArrayList<BotCommand> getTaskList() {
        return this.taskList;
    }

    @Override
    public abstract void executeTasks();
    
}
