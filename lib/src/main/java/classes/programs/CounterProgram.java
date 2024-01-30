package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
import interfaces.bots.BotInterface;

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

    public void setCounter(int n) {
        //TODO implement
        throw new UnsupportedOperationException("Unimplemented method 'setCounter'");
    }

    @Override
    public boolean isExpired(BotInterface botToProgram, ArrayList<BotInterface> botList, ArrayList<CartesianArea> targetList) {
        return this.counter <= 0;
    }

    @Override
    public void executeTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTasks'");
    }
}