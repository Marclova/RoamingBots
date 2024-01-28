package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;

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

    /**
     * Checks if the bot has reached a target with the right label
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param targetList The list of all existing targets
     */
    public void checkTarget(double x, double y, ArrayList<CartesianArea> targetList) {
        //TODO implement
        throw new UnsupportedOperationException("Unimplemented method 'checkTarget'");
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }
}
