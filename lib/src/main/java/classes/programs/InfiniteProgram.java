package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.programs.expirationCheckRequirements.NoArgsExpirationCheck;

/**
 * A program able to execute its tasks without expiring.
 */
public class InfiniteProgram extends AbstractProgram implements NoArgsExpirationCheck {

    public InfiniteProgram(ArrayList<BotCommand> taskList) {
        super(taskList);
    }

    @Override
    public boolean isExpired() {
        return this.getTaskList().isEmpty();
    }
}
