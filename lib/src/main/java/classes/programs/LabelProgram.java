package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.BotInterface;
import interfaces.CartesianArea;

/**
 * A program that executes its tasks until the bot has detected the given signal label.
 */
public class LabelProgram extends AbstractProgram{

    String labelToDetect;
    double detectingDistance;

    public LabelProgram(ArrayList<BotCommand> taskList, String label, double detectingDistance) {
        super(taskList);
        this.labelToDetect = label;
        this.detectingDistance = detectingDistance;
    }

    public String getTargetToReach() {
        return this.labelToDetect;
    }

    @Override
    public boolean isExpired(BotInterface botToProgram, ArrayList<BotInterface> botList, ArrayList<CartesianArea> targetList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExpired'");
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }
}
