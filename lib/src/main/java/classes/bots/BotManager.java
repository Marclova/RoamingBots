package classes.bots;

// import interfaces.SimulationManagerInterface;
import java.util.ArrayList;

import classes.containers.Coordinates;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;

public class BotManager implements BotManagerInterface {

    ArrayList<BotInterface> botList = new ArrayList<>();

    @Override
    public ArrayList<BotInterface> getBotList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBotList'");
    }

    @Override
    public BotInterface createBot(double x, double y) {
        Bot bot = new Bot(x, y);
        this.botList.add(bot);
        return bot;
    }

    @Override
    public void createBot(BotInterface botToAdd) {
        this.botList.add(botToAdd);
    }

    @Override
    public boolean createBot(ArrayList<BotInterface> botListToAdd) {
        return this.botList.addAll(botListToAdd);
    }

    @Override
    public boolean createRandomBots(int quantity, Coordinates coordinates1, Coordinates coordinates2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRandomBots'");
    }

    @Override
    public boolean moveAllBots(double movementTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveAllBots'");
    }
    
}
