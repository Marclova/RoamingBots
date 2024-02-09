package classes.bots;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;

public class BotManager extends ArgumentChecker implements BotManagerInterface {

    ArrayList<BotInterface> botList = new ArrayList<>();

    @Override
    public ArrayList<BotInterface> getBotList() {
        return this.botList;
    }

    @Override
    public BotInterface createBot(Coordinates coordinates) {
        this.checkNotNullObjects(coordinates);
        
        Bot bot = new Bot(coordinates);
        this.botList.add(bot);
        return bot;
    }

    @Override
    public void createBot(BotInterface botToAdd) {
        this.checkNotNullObjects(botToAdd);

        this.botList.add(botToAdd);
    }

    @Override
    public boolean createBot(ArrayList<BotInterface> botListToAdd) {
        this.checkNotNullObjects(botListToAdd);

        return this.botList.addAll(botListToAdd);
    }

    @Override
    public boolean createRandomBots(int quantity, Coordinates coordinates1, Coordinates coordinates2) {
        this.checkGraterThanZeroValues(quantity);
        this.checkNotNullObjects(coordinates1, coordinates2);
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
    public boolean moveAllBots(double movementTime) {
        this.checkGraterThanZeroValues(movementTime);

        boolean flag = false;
        for (BotInterface bot : botList) {
            double actualMovementTime = movementTime;
            if(actualMovementTime <= bot.getMovementTimer()) //bot makes full movement
            {
                bot.setMovementTimer(bot.getMovementTimer() - actualMovementTime);
            }
            else //bot moves as much as it can
            {
                actualMovementTime = bot.getMovementTimer();
                bot.setMovementTimer(0);
            }
            double botDistanceMovement = bot.getSpeed() * actualMovementTime;
            double botRadiantDirectionAngle = Math.toRadians( bot.getDirectionAngle() );

            double botDeltaX = botDistanceMovement * Math.cos(botRadiantDirectionAngle);
            botDeltaX = new BigDecimal(botDeltaX)
                            .setScale(2, RoundingMode.HALF_UP).doubleValue();
            double botDeltaY = botDistanceMovement * Math.sin(botRadiantDirectionAngle);
            botDeltaY = new BigDecimal(botDeltaY)
                            .setScale(2, RoundingMode.HALF_UP).doubleValue();

            boolean relevantDeltas = !(botDeltaX == 0 && botDeltaY == 0);
            flag = flag || relevantDeltas; //true if something has changed
            if(relevantDeltas)
            {
                bot.incrementCoordinates(new Coordinates(botDeltaX, botDeltaY));
            }
        }
        return flag;
    }
}
