package classes.programs;

import java.util.ArrayList;

import classes.services.ArgumentCheckerService;
import classes.services.containers.Coordinates;
import functionalInterfaces.BotCommand;
import interfaces.programs.expirationCheckRequirements.TargetListExpirationCheck;
import interfaces.targets.CartesianAreaInterface;

/**
 * A program that executes its tasks until its bot has reached the given target.
 */
public class TargetProgram extends AbstractProgram implements TargetListExpirationCheck {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    String targetToReach;

    public TargetProgram(ArrayList<BotCommand> taskList, String target) {
        super(taskList);
        argumentCheckerService.checkNotEmptyStrings(target);

        this.targetToReach = target;
    }

    public String getTargetToReach() {
        return this.targetToReach;
    }

    @Override
    public boolean isExpired(Coordinates coordinatesToCheck, ArrayList<CartesianAreaInterface> targetList) {
        argumentCheckerService.checkNotNullObjects(coordinatesToCheck, targetList);

        for (CartesianAreaInterface target : targetList) {
            if(target.getLabel().equals(this.targetToReach) && target.checkAreaIntersection(coordinatesToCheck))
            {
                return true;
            }
        }
        return false;
    }
}
