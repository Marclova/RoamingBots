package classes.programs;

import java.util.ArrayList;

import classes.containers.Coordinates;
import functionalInterfaces.BotCommand;
import interfaces.CartesianAreaInterface;
import interfaces.programs.expirationCheckRequirements.TargetListExpirationCheck;

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

    @Override
    public boolean isExpired(Coordinates botCoordinates, ArrayList<CartesianAreaInterface> targetList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExpired'");
    }
}
