import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;

import org.junit.Test;

import classes.bots.Bot;
import classes.bots.BotManager;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;

public class BotTests {

    Coordinates positiveCoordinates = new Coordinates(1, 1);
    Coordinates negativeCoordinates = new Coordinates(-1, -1);
    Coordinates zeroCoordinates = new Coordinates(0, 0);
    DirectionalVectors positiveVectors = new DirectionalVectors(1, 1);
    DirectionalVectors negativeVectors = new DirectionalVectors(-1, -1);
    
    @Test
    public void botExceptionTests() {

        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(zeroCoordinates);

        assertThrows(IllegalArgumentException.class, () -> {bot.setDirectionAngle(-1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setDirectionAngle(360);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setSpeed(-1);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setMovementTimer(-1);});

        assertThrows(IllegalArgumentException.class, () -> {bot.setContinueMotion(0);});

        assertThrows(IllegalArgumentException.class, () -> {bot.setFollowingDistance(-1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setMove(positiveVectors, 0);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setMoveRandom(negativeVectors,positiveVectors, 0);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setMoveRandom(positiveVectors, negativeVectors, 1);});
        
        assertThrows(NullPointerException.class, () -> {bot.setFollow(null, 1, 1, botList);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow("", 1, 1, botList);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow("label", 0, 1, botList);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow("label", 1, 0, botList);});
        assertThrows(NullPointerException.class, () -> {bot.setFollow("label", 1, 1, null);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.proceed(0);});

        assertThrows(NullPointerException.class, () -> {bot.isDetectingLabel(null, "label", 1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.isDetectingLabel(botList, "", 1);});
        assertThrows(NullPointerException.class, () -> {bot.isDetectingLabel(botList, null, 1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.isDetectingLabel(botList, "label", 0);});
        
        assertThrows(NullPointerException.class, () -> {bot.addProgram(null);});

        assertThrows(NullPointerException.class, () -> {bot.startEmittingSignalLabel(null);});
        assertThrows(IllegalArgumentException.class, () -> {bot.startEmittingSignalLabel("");});

        // assertThrows(IllegalArgumentException.class, () -> {bot.proceed(-1);});
    }

    @Test
    public void botManagerExceptionTests() {

        BotManagerInterface botManager = new BotManager();
        BotInterface bot = new Bot(zeroCoordinates);

        assertThrows(NullPointerException.class, () -> {botManager.createBot((BotInterface)null);});
        assertThrows(NullPointerException.class, () -> {botManager.createBot((ArrayList<BotInterface>)null);});
        
        assertThrows(IllegalArgumentException.class, () -> {botManager.createRandomBots(0, negativeCoordinates, positiveCoordinates);});
        assertThrows(IllegalArgumentException.class, () -> {botManager.createRandomBots(1, positiveCoordinates, negativeCoordinates);});
        
        assertThrows(NullPointerException.class, () -> {botManager.moveBot(null, 1);});
        assertThrows(IllegalArgumentException.class, () -> {botManager.moveBot(bot, 0);});
    }

    @Test
    public void gettersAndSetters()
    {
        BotManagerInterface botManager = new BotManager();
        String rightLabel = "rightLabel";
        BotInterface bot = botManager.createBot(zeroCoordinates);
        Coordinates botCoordinates = bot.getCoordinates();

        assertFalse(bot.IsEmittingSignal());
        assertEquals("", bot.getLabelToEmit());
        assertEquals("", bot.getLabelToFollow());

        bot.startEmittingSignalLabel(rightLabel);
        assertTrue(bot.IsEmittingSignal());
        assertEquals(rightLabel, bot.getLabelToEmit());

        bot.setLabelToFollow(rightLabel);
        assertEquals(rightLabel, bot.getLabelToFollow());

        bot.setFollowingDistance(3);
        assertTrue(3 == bot.getFollowingDistance());

        bot.setDirectionAngle(90);
        assertTrue(90 == bot.getDirectionAngle());

        bot.setContinueMotion(10);
        assertTrue(10 == bot.getMovementTimer());
        bot.setSpeed(0.5);
        assertTrue(0.5 == bot.getSpeed());
        bot.setContinueMotion(10);
        assertTrue(10 == bot.getMovementTimer());

        bot.setSpeed(0);
        bot.setMovementTimer(20);
        assertTrue(20 == bot.getMovementTimer());

        assertFalse(botManager.moveBot(bot,1));
        assertTrue(0.0 == botCoordinates.x && 0.0 == botCoordinates.y);

        bot.setSpeed(1);
        
        assertTrue(botManager.moveBot(bot,1));
        assertTrue(0.0 == bot.getCoordinates().x && 1.0 == bot.getCoordinates().y);
        assertTrue(botManager.moveBot(bot,1));

        bot.stopEmittingSignalLabel();
        bot.stopMotion();

        assertFalse(bot.IsEmittingSignal());
        assertEquals("", bot.getLabelToEmit());
        assertFalse(botManager.moveBot(bot,1));

    }

    @Test
    public void botCreationTests() {

        ArrayList<BotInterface> botListToCheck;
        BotInterface botToCheck;
        Coordinates botToCheckCoordinates;
        BotManagerInterface botManager = new BotManager();
        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            botList.add(new Bot(new Coordinates(i, i)));
        }

        botManager.createBot(botList);
        assertEquals(botList, botManager.getBotList());
        
        botManager.createBot(new Coordinates(6, 6));
        botListToCheck = botManager.getBotList();
        botToCheck = botListToCheck.get(botListToCheck.size()-1);
        botToCheckCoordinates = botToCheck.getCoordinates();
        assertTrue(botToCheckCoordinates.x == 6 && botToCheckCoordinates.y == 6);

        BotInterface botToInsert = new Bot(new Coordinates(90, 90));
        botManager.createBot(botToInsert);
        assertTrue(botManager.getBotList().contains(botToInsert));

        botManager = new BotManager();
        botManager.createRandomBots(10, negativeCoordinates, positiveCoordinates);
        botListToCheck = botManager.getBotList();
        assertTrue(botListToCheck.size() == 10);
        for (BotInterface bot : botListToCheck) {
            Coordinates botCoordinates = bot.getCoordinates();
            assertTrue(botCoordinates.x >= -10 && botCoordinates.x <= 10 &&
                        botCoordinates.y >= -10 && botCoordinates.y <= 10);
        }
        assertFalse(botListToCheck.get(0).getCoordinates().x == botListToCheck.get(1).getCoordinates().x &&
                    botListToCheck.get(0).getCoordinates().x == botListToCheck.get(2).getCoordinates().x &&
                    botListToCheck.get(0).getCoordinates().x == botListToCheck.get(3).getCoordinates().x);  //It will happen extremely unlikely by chance in case of malfunction
    }

    @Test
    public void detectingTests() {
        
        ArrayList<BotInterface> botList = new ArrayList<>();
        String rightLabel = "rightLabel";
        BotInterface searchingBot = new Bot(new Coordinates(0, 0));
        BotInterface wrongLabelBot = new Bot(new Coordinates(1, 1));
        BotInterface notEmittingBot = new Bot(new Coordinates(0, 2));
        BotInterface farAwayBot = new Bot(new Coordinates(3, 3));
        BotInterface rightLAbelBot = new Bot(new Coordinates(0, -2));

        searchingBot.setFollow(rightLabel, 2.5, 1, botList);
        wrongLabelBot.startEmittingSignalLabel("wrongLabel");
        farAwayBot.startEmittingSignalLabel(rightLabel);
        rightLAbelBot.startEmittingSignalLabel(rightLabel);
        
        botList.add(wrongLabelBot);
        botList.add(notEmittingBot);
        botList.add(farAwayBot);
        assertFalse(searchingBot.isDetectingLabel(botList, searchingBot.getLabelToFollow(), searchingBot.getFollowingDistance()));
        botList.add(rightLAbelBot);
        assertTrue(searchingBot.isDetectingLabel(botList, searchingBot.getLabelToFollow(), searchingBot.getFollowingDistance()));
    }

    @Test
    public void movementTests() {

        BotManagerInterface botManager = new BotManager();
        BotInterface movingBot = botManager.createBot(zeroCoordinates);

        movingBot.setMove(new DirectionalVectors(-0.5, 0.7), 2);
        movingBot.setContinueMotion(1);
        assertTrue(125.54 == movingBot.getDirectionAngle());
        botManager.moveBot(movingBot,1);
        assertTrue(-1.16 == movingBot.getCoordinates().x && 1.63 == movingBot.getCoordinates().y);

        botManager = new BotManager();    
        movingBot = botManager.createBot(zeroCoordinates);

        String rightLabel = "rightLabel";
        BotInterface bot1 = new Bot(new Coordinates(-1, 0));
        bot1.startEmittingSignalLabel(rightLabel);
        BotInterface bot2 = new Bot(new Coordinates(-1, 1));
        bot2.startEmittingSignalLabel(rightLabel);
        ArrayList<BotInterface> botList = new ArrayList<>();
        botList.add(bot1);
        botList.add(bot2);

        movingBot.setFollow(rightLabel, 3, 2, botList);
        movingBot.setContinueMotion(1);
        assertTrue(153.43 == movingBot.getDirectionAngle());
        botManager.moveBot(movingBot,1);
        assertTrue(-1.79 == movingBot.getCoordinates().x && 0.89 == movingBot.getCoordinates().y);
    }

    @Test
    public void randomMovementTests() {

        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            botList.add(new Bot(zeroCoordinates));
        }

        for (BotInterface bot : botList) {
            bot.setMoveRandom(negativeVectors, positiveVectors, 1);
        }

        assertFalse(botList.get(0).getDirectionAngle() == botList.get(1).getDirectionAngle() &&
                    botList.get(0).getDirectionAngle() == botList.get(2).getDirectionAngle() &&
                    botList.get(0).getDirectionAngle() == botList.get(3).getDirectionAngle());  //It will happen extremely unlikely by chance in case of malfunction
    }

    @Test
    public void moveAllBotsTest() {

        BotManagerInterface botManager = new BotManager();
        BotInterface bot1 = botManager.createBot(zeroCoordinates);
        BotInterface bot2 = botManager.createBot(zeroCoordinates);
        BotInterface bot3 = botManager.createBot(zeroCoordinates);
        bot1.setMove(new DirectionalVectors(-1, 0), 1);
        bot1.setContinueMotion(1);
        bot2.setMove(new DirectionalVectors(1, -0.6), 1);
        bot2.setContinueMotion(1);
        bot3.setMove(new DirectionalVectors(-0.5, -0.99), 2);
        bot3.setContinueMotion(1);

        botManager.moveBot(bot1,1);
        botManager.moveBot(bot2,1);
        botManager.moveBot(bot3,1);
        double x1 = bot1.getCoordinates().x;
        double y1 = bot1.getCoordinates().y;
        double x2 = bot2.getCoordinates().x;
        double y2 = bot2.getCoordinates().y;
        double x3 = bot3.getCoordinates().x;
        double y3 = bot3.getCoordinates().y;

        assertTrue(x1 == -1 && y1 == 0);
        assertTrue(0.86 == x2 && -0.51 == y2);
        assertTrue(-0.90 == x3 && -1.79 == y3);
    }
 }
