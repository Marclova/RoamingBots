package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;

/**
 * A program able to execute its tasks a limited amount of times
 */
public class CounterProgram extends AbstractProgram {

    private int counter;

    public CounterProgram(ArrayList<BotCommand> taskList, int counter) {
        super(taskList);
        this.counter = counter;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter() {
        //TODO implement
        throw new UnsupportedOperationException("Unimplemented method 'setCounter'");
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }
}