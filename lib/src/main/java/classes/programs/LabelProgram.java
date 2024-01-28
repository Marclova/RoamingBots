package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.BotInterface;

/**
 * A program that executes its tasks until the bot has detected the given signal label.
 */
public class LabelProgram extends AbstractProgram{

    String labelToDetect;
    double detectingDistance;

    public LabelProgram(ArrayList<BotCommand> taskList, String label) {
        super(taskList);
        this.labelToDetect = label;
    }

    public String getTargetToReach() {
        return this.labelToDetect;
    }

    /**
     * Checks if the bot can detect any other bot emitting the correct label signal within the detecting distance.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param botList The list of all existing bots (the current bot may be included).
     */
    public void checkLabel(double x, double y, ArrayList<BotInterface> botList) {
        //TODO implement
        throw new UnsupportedOperationException("Unimplemented method 'checkTarget'");
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }
}
