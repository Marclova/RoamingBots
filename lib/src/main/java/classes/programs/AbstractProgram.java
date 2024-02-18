package classes.programs;

import java.util.ArrayList;

import classes.services.ArgumentCheckerService;
import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;
import interfaces.programs.ProgramInterface;

/**
 * A generic program installed into a bot able to execute its own tasks
 */
public abstract class AbstractProgram implements ProgramInterface {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    private ArrayList<BotCommand> taskList;

    public AbstractProgram(ArrayList<BotCommand> taskList) {
        argumentCheckerService.checkNotNullObjects(taskList);

        this.taskList = taskList;
    }

    @Override
    public ArrayList<BotCommand> getTaskList() {
        return this.taskList;
    }

    @Override
    public boolean executeTasks(BotInterface bot) {
        argumentCheckerService.checkNotNullObjects(bot);

        boolean flag = false;
        for (BotCommand task : this.taskList) {
            flag = task.execute(bot) || flag;
        }
        return flag;
    }
    
}
