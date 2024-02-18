package classes.programs;

import java.util.ArrayList;

import classes.services.ArgumentCheckerService;
import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;
import interfaces.programs.expirationCheckRequirements.BotListExpirationCheck;

/**
 * A program that executes its tasks until the bot has detected the given signal label.
 */
public class LabelProgram extends AbstractProgram implements BotListExpirationCheck {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    String labelToDetect;
    double detectingDistance;

    public LabelProgram(ArrayList<BotCommand> taskList, String label, double detectingDistance) {
        super(taskList);
        argumentCheckerService.checkNotEmptyStrings(label);
        argumentCheckerService.checkGraterThanZeroValues(detectingDistance);

        this.labelToDetect = label;
        this.detectingDistance = detectingDistance;
    }

    public String getLabelToDetect() {
        return this.labelToDetect;
    }

    public double getDetectingDistance() {
        return this.detectingDistance;
    }

    @Override
    public boolean isExpired(BotInterface bot, ArrayList<BotInterface> botList) {
        argumentCheckerService.checkNotNullObjects(bot, botList);

        return ( bot.isDetectingLabel(botList, this.labelToDetect, this.detectingDistance) );
    }
}
