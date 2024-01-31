package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
import interfaces.bots.BotInterface;
import interfaces.programs.TargetListExpirationCheck;

/**
 * A program that executes its tasks until the bot has reached the given target
 */
public class TargetProgram extends AbstractProgram implements TargetListExpirationCheck {

    String targetToReach;

    public TargetProgram(ArrayList<BotCommand> taskList, String target) {
        super(taskList);
        this.targetToReach = target;
    }

    public String getTargetToReach() {
        return this.targetToReach;
    }

    // /**
    //  * Checks if the given Bot has reached a target which label's equal with the 'targetToReach'
    //  * 
    //  * @param botToCheck
    //  * @param targetList
    //  * @return
    //  */
    // public boolean checkTarget(BotInterface botToCheck, ArrayList<CartesianArea> targetList) {
    //     //TODO implement
    //     throw new UnsupportedOperationException("Unimplemented method 'checkTarget'");
    // }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }

    @Override
    public boolean isExpired(BotInterface botToCheck, ArrayList<CartesianArea> targetList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExpired'");
    }
}
