package classes.programs;

import java.util.ArrayList;

import classes.services.containers.Coordinates;
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
        for (CartesianAreaInterface target : targetList) {
            if(target.checkAreaIntersection(botCoordinates))
            {
                return true;
            }
        }
        return false;
    }
}
