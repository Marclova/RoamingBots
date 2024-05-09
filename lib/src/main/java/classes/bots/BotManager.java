package classes.bots;

import java.util.ArrayList;

import classes.services.ArgumentCheckerService;
import classes.services.containers.Coordinates;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;

/**
 * Class responsible to contain and create bots.
 */
public class BotManager implements BotManagerInterface {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    private ArrayList<BotInterface> botList = new ArrayList<>();

    @Override
    public ArrayList<BotInterface> getBotList() {
        return this.botList;
    }

    @Override
    public BotInterface createBot(Coordinates coordinates) {
        argumentCheckerService.checkNotNullObjects(coordinates);
        
        Bot bot = new Bot(coordinates);
        this.botList.add(bot);
        return bot;
    }

    @Override
    public boolean createBot(BotInterface botToAdd) {
        argumentCheckerService.checkNotNullObjects(botToAdd);

        return this.botList.add(botToAdd);
    }

    @Override
    public boolean createBot(ArrayList<BotInterface> botListToAdd) {
        argumentCheckerService.checkNotNullObjects(botListToAdd);

        return this.botList.addAll(botListToAdd);
    }

    @Override
    public boolean createRandomBots(int quantity, Coordinates coordinates1, Coordinates coordinates2) {
        argumentCheckerService.checkGraterThanZeroValues(quantity);
        argumentCheckerService.checkNotNullObjects(coordinates1, coordinates2);
        if(coordinates1.x > coordinates2.x ||
            coordinates1.y > coordinates2.y)
        {
            throw new IllegalArgumentException();
        }

        ArrayList<BotInterface> botsToAdd = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            double randomXCoordinate = Math.random() * (coordinates2.x - coordinates1.x + 1) + coordinates1.x;
            double randomYCoordinate = Math.random() * (coordinates2.y - coordinates1.y + 1) + coordinates1.y;
            botsToAdd.add(new Bot(new Coordinates(randomXCoordinate, randomYCoordinate)));
        }
        return this.createBot(botsToAdd);
    }

    @Override
    public boolean moveBot(BotInterface botToMove, double movementTime) {
        argumentCheckerService.checkNotNullObjects(botToMove);
        argumentCheckerService.checkGraterThanZeroValues(movementTime);

        double botMovementTimer = botToMove.getMovementTimer();
        if(botMovementTimer == 0 || botToMove.getSpeed() == 0) //The bot is not going to move.
        {
            return false;
        }

        if(movementTime <= botMovementTimer) //bot makes full movement
        {
            botToMove.setMovementTimer(botMovementTimer - movementTime);
        }
        else //bot moves as much as it can
        {
            movementTime = botMovementTimer;
            botToMove.setMovementTimer(0);
        }
        Coordinates oldCoordinates = botToMove.getCoordinates();
        return !( oldCoordinates.equals(botToMove.proceed(movementTime)) ); //actual movement
    }
}
