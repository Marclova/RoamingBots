package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.programs.expirationCheckRequirements.ExpirationCheck;

/**
 * A program able to execute its tasks a limited amount of times
 */
public class RepeatingProgram extends AbstractProgram implements ExpirationCheck {

    private int counter;

    public RepeatingProgram(ArrayList<BotCommand> taskList, int counter) {
        super(taskList);
        this.counter = counter;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int n) {
        if(n < 0)
        {
            throw new IllegalArgumentException("The counter must be 0 or grater.");
        }
        this.counter = n;
    }

    @Override
    public boolean isExpired() {
        return this.counter <= 0;
    }
}