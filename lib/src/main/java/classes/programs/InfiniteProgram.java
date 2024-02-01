package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.programs.ExpirationCheck;

/**
 * A program able to execute its tasks without expiring.
 */
public class InfiniteProgram extends AbstractProgram implements ExpirationCheck {

    public InfiniteProgram(ArrayList<BotCommand> taskList) {
        super(taskList);
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }

    @Override
    public boolean isExpired() {
        return this.getTaskList().isEmpty();
    }
}
