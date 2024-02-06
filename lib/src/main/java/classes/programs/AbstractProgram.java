package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;
import interfaces.programs.ProgramInterface;

import classes.services.abstractServeces.ArgumentChecker;

/**
 * A generic program installed into a bot able to execute its own tasks
 */
public abstract class AbstractProgram extends ArgumentChecker implements ProgramInterface {

    private ArrayList<BotCommand> taskList;

    public AbstractProgram(ArrayList<BotCommand> taskList) {
        this.checkNotNullObjects(taskList);

        this.taskList = taskList;
    }

    @Override
    public ArrayList<BotCommand> getTaskList() {
        return this.taskList;
    }

    @Override
    public boolean executeTasks(BotInterface bot) {
        this.checkNotNullObjects(bot);

        boolean flag = false;
        for (BotCommand task : this.taskList) {
            flag = task.execute(bot) || flag;
        }
        return flag;
    }
    
}
