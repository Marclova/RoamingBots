package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.programs.ExpirationCheck;

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
        //TODO implement
        throw new UnsupportedOperationException("Unimplemented method 'setCounter'");
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }

    @Override
    public boolean isExpired() {
        return this.counter <= 0;
    }
}