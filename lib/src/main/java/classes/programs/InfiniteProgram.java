package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.BotInterface;
import interfaces.CartesianArea;

/**
 * A program able to execute its tasks without expiring.
 */
public class InfiniteProgram extends AbstractProgram {

    public InfiniteProgram(ArrayList<BotCommand> taskList) {
        super(taskList);
    }

    @Override
    public boolean isExpired(BotInterface botToProgram, ArrayList<BotInterface> botList, ArrayList<CartesianArea> targetList) {
        return false;
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }
}
