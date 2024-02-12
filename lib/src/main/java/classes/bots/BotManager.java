package classes.bots;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;

/**
 * Class responsible to contain, create and move bots.
 */
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
    public boolean createBot(BotInterface botToAdd) {
        this.checkNotNullObjects(botToAdd);

        return this.botList.add(botToAdd);
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

        boolean flag = false; //true if something has changed
        for (BotInterface bot : botList) {
            double botMovementTimer = bot.getMovementTimer();
            if(botMovementTimer == 0 || bot.getSpeed() == 0) //The bot is not going to move.
            {
                continue;
            }

            double actualMovementTime = movementTime;
            if(actualMovementTime <= botMovementTimer) //bot makes full movement
            {
                bot.setMovementTimer(botMovementTimer - actualMovementTime);
            }
            else //bot moves as much as it can
            {
                actualMovementTime = botMovementTimer;
                bot.setMovementTimer(0);
            }
            flag = this.actuallyMoveSpecificBot(bot, actualMovementTime) || flag;
        }
        return flag;
    }

    //Public methods

    /**
     * Effectively updates the bot's coordinates so that it can move.
     * 
     * @param bot The bot to move.
     * @param movementTime The amount of time which the bot's going to move.
     * @return True if the bot has moved. False otherwise.
     */
    private boolean actuallyMoveSpecificBot(BotInterface bot, double movementTime) {
        this.checkNotNullObjects(bot);
        this.checkZeroOrHigherValues(movementTime);

        double botDistanceMovement = bot.getSpeed() * movementTime;
        double botRadiantDirectionAngle = Math.toRadians( bot.getDirectionAngle() );

        double botDeltaX = botDistanceMovement * Math.cos(botRadiantDirectionAngle);
        botDeltaX = new BigDecimal(botDeltaX)
                        .setScale(2, RoundingMode.HALF_UP).doubleValue();
        double botDeltaY = botDistanceMovement * Math.sin(botRadiantDirectionAngle);
        botDeltaY = new BigDecimal(botDeltaY)
                        .setScale(2, RoundingMode.HALF_UP).doubleValue();

        boolean relevantDeltas = !(botDeltaX == 0 && botDeltaY == 0);
        if(relevantDeltas)
        {
            bot.incrementCoordinates(new Coordinates(botDeltaX, botDeltaY));
        }
        return relevantDeltas;
    }
}
