package classes.programs;

import java.util.ArrayList;

import classes.services.containers.Coordinates;
import functionalInterfaces.BotCommand;
import interfaces.CartesianAreaInterface;
import interfaces.programs.expirationCheckRequirements.TargetListExpirationCheck;

/**
 * A program that executes its tasks until its bot has reached the given target.
 */
public class TargetProgram extends AbstractProgram implements TargetListExpirationCheck {

    String targetToReach;

    public TargetProgram(ArrayList<BotCommand> taskList, String target) {
        super(taskList);
        this.checkNotEmptyStrings(target);

        this.targetToReach = target;
    }

    public String getTargetToReach() {
        return this.targetToReach;
    }

    @Override
    public boolean isExpired(Coordinates coordinatesToCheck, ArrayList<CartesianAreaInterface> targetList) {
        this.checkNotNullObjects(coordinatesToCheck, targetList);

        for (CartesianAreaInterface target : targetList) {
            if(target.getLabel().equals(this.targetToReach) && target.checkAreaIntersection(coordinatesToCheck))
            {
                return true;
            }
        }
        return false;
    }
}
