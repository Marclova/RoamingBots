package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.programs.expirationCheckRequirements.NoArgsExpirationCheck;

/**
 * A program able to execute its tasks a limited amount of times
 */
public class RepeatingProgram extends AbstractProgram implements NoArgsExpirationCheck {

    private int counter;

    public RepeatingProgram(ArrayList<BotCommand> taskList, int counter) {
        super(taskList);
        this.checkGraterThanZeroValues(counter);

        this.counter = counter;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int n) {
        this.checkGraterThanZeroValues(n+1);
        this.counter = n;
    }

    @Override
    public boolean isExpired() {
        return this.counter <= 0;
    }
}