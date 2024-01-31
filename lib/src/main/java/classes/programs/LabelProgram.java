package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;
import interfaces.programs.BotListExpirationCheck;

/**
 * A program that executes its tasks until the bot has detected the given signal label.
 */
public class LabelProgram extends AbstractProgram implements BotListExpirationCheck {

    String labelToDetect;
    double detectingDistance;

    public LabelProgram(ArrayList<BotCommand> taskList, String label, double detectingDistance) {
        super(taskList);
        this.labelToDetect = label;
        this.detectingDistance = detectingDistance;
    }

    public String getLabelToDetect() {
        return this.labelToDetect;
    }

    public double getDetectingDistance() {
        return this.detectingDistance;
    }

    // /**
    //  * Checks if the given Bot is nearby another bot emitting the 'labelToDetect'
    //  * 
    //  * @param botToCheck The bot owning this program
    //  * @param botList The list of all existing bots in the simulation plane.
    //  * @return True if theres a bot emitting the 'labelToCheck' within the 'detectingDistance'. False otherwise.
    //  */
    // public boolean checkLabel(BotInterface botToCheck, ArrayList<BotInterface> botList) {
    //     //TODO implement
    //     throw new UnsupportedOperationException("Unimplemented method 'checkLabel'");
    // }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }

    @Override
    public boolean isExpired(BotInterface botToCheck, ArrayList<BotInterface> botList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExpired'");
    }
}
