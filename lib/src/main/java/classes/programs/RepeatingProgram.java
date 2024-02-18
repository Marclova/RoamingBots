package classes.programs;

import java.util.ArrayList;

import classes.services.ArgumentCheckerService;
import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;
import interfaces.programs.expirationCheckRequirements.NoArgsExpirationCheck;

/**
 * A program able to execute its tasks a limited amount of times.
 */
public class RepeatingProgram extends AbstractProgram implements NoArgsExpirationCheck {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    private int counter;

    public RepeatingProgram(ArrayList<BotCommand> taskList, int counter) {
        super(taskList);
        argumentCheckerService.checkGraterThanZeroValues(counter);

        this.counter = counter;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int n) {
        argumentCheckerService.checkGraterThanZeroValues(n+1);

        this.counter = n;
    }

    /**
     * Executes once all the tasks in the "taskList" and then decreases the program's counter.
     * 
     * @param bot The bot owning this program.
     * @return True if every command has been executed correctly.
     *          False if at least least one of them has caused no changes.
     */
    @Override
    public boolean executeTasks(BotInterface bot) {
        argumentCheckerService.checkNotNullObjects(bot);
        if(this.counter == 0)
        {
            return false;
        }
        
        boolean flag = false;
        for (BotCommand task : this.getTaskList()) {
            flag = task.execute(bot) || flag;
        }
        this.setCounter(this.counter - 1);
        return flag;
    }

    @Override
    public boolean isExpired() {
        return this.counter <= 0;
    }
}