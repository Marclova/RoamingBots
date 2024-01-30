package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
import interfaces.bots.BotInterface;

/**
 * A program that executes its tasks until the bot has reached the given target
 */
public class TargetProgram extends AbstractProgram{

    String targetToReach;

    public TargetProgram(ArrayList<BotCommand> taskList, String target) {
        super(taskList);
        this.targetToReach = target;
    }

    public String getTargetToReach() {
        return this.targetToReach;
    }

    @Override
    public boolean isExpired(BotInterface botToProgram, ArrayList<BotInterface> botList, ArrayList<CartesianArea> targetList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExpired'");
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }
}
