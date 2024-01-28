package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.ProgramInterface;

/**
 * A generic program installed into a bot able to execute its own tasks
 */
public abstract class AbstractProgram implements ProgramInterface {

    private ArrayList<BotCommand> taskList;
    private boolean expired = false;

    public AbstractProgram(ArrayList<BotCommand> taskList) {
        this.taskList = taskList;
    }

    @Override
    public ArrayList<BotCommand> getTaskList() {
        return this.taskList;
    }

    @Override
    public boolean isExpired() {
        return this.expired;
    }

    @Override
    public void setExpiredFlag(boolean bool) {
        this.expired = bool;
    }

    @Override
    public abstract void executeTasks();
    
}
