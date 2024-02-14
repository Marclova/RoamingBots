package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;
import interfaces.programs.expirationCheckRequirements.BotListExpirationCheck;

/**
 * A program that executes its tasks until the bot has detected the given signal label.
 */
public class LabelProgram extends AbstractProgram implements BotListExpirationCheck {

    String labelToDetect;
    double detectingDistance;

    public LabelProgram(ArrayList<BotCommand> taskList, String label, double detectingDistance) {
        super(taskList);
        this.checkNotEmptyStrings(label);
        this.checkGraterThanZeroValues(detectingDistance);

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
        this.checkNotNullObjects(bot, botList);

        return ( bot.isDetectingLabel(botList, this.labelToDetect, this.detectingDistance) );
    }
}
