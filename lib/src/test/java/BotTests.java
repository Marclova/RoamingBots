import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.lang.IllegalArgumentException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.Test;

import classes.bots.Bot;
import classes.bots.BotManager;
import classes.containers.Coordinates;
import classes.containers.DirectionalVectors;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;

public class BotTests {

    Coordinates positiveCoordinates = new Coordinates(1, 1);
    Coordinates negativeCoordinates = new Coordinates(-1, -1);
    Coordinates zeroCoordinates = new Coordinates(0, 0);
    DirectionalVectors positiveVectors = new DirectionalVectors(1, 1);
    DirectionalVectors negativeVectors = new DirectionalVectors(-1, -1);
    DirectionalVectors zeroVectors = new DirectionalVectors(0, 0);
    
    @Test
    public void botIllegalArgumentTests() {

        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(0, 0);

        assertThrows(IllegalArgumentException.class, () -> {bot.setDirectionAngle(-1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setDirectionAngle(360);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setSpeed(0);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setSpeed(-1);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setMovementTimer(-1);});

        assertThrows(IllegalArgumentException.class, () -> {bot.setFollowingDistance(-1);});

        assertThrows(IllegalArgumentException.class, () -> {bot.setMove(zeroVectors, -1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setMove(positiveVectors, 0);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setMoveRandom(zeroVectors, zeroVectors, 1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setMoveRandom(negativeVectors,positiveVectors, 0);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setMoveRandom(positiveVectors, negativeVectors, 1);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow(null, 1, 1, botList);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow("", 1, 1, botList);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow("label", 0, 1, botList);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow("label", 1, 0, botList);});
        assertThrows(IllegalArgumentException.class, () -> {bot.setFollow("label", 1, 1, null);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.isDetectingLabel(null, "label", 1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.isDetectingLabel(botList, "", 1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.isDetectingLabel(botList, null, 1);});
        assertThrows(IllegalArgumentException.class, () -> {bot.isDetectingLabel(botList, "label", 0);});
        
        assertThrows(IllegalArgumentException.class, () -> {bot.setContinueMotion(-1);});

        assertThrows(IllegalArgumentException.class, () -> {bot.startEmittingSignalLabel(null);});
        assertThrows(IllegalArgumentException.class, () -> {bot.startEmittingSignalLabel("");});

        // assertThrows(IllegalArgumentException.class, () -> {bot.proceed(-1);});
    }

    @Test
    public void botManagerIllegalArgumentException() {

        BotManager botManager = new BotManager();

        assertThrows(IllegalArgumentException.class, () -> {botManager.createBot((BotInterface)null);});
        assertThrows(IllegalArgumentException.class, () -> {botManager.createBot((ArrayList<BotInterface>)null);});
        
        assertThrows(IllegalArgumentException.class, () -> {botManager.createRandomBots(0, negativeCoordinates, positiveCoordinates);});
        assertThrows(IllegalArgumentException.class, () -> {botManager.createRandomBots(1, positiveCoordinates, negativeCoordinates);});
        
        assertThrows(IllegalArgumentException.class, () -> {botManager.moveAllBots(0);});
        assertThrows(IllegalArgumentException.class, () -> {botManager.moveAllBots(1);});
    }

    @Test
    public void gettersAndSetters()
    {
        BotManagerInterface botManager = new BotManager();
        String rightLabel = "rightLabel";
        BotInterface bot = botManager.createBot(0, 0);

        assertFalse(bot.IsEmittingSignal());
        assertEquals("", bot.getLabelToEmit());
        assertEquals("", bot.getLabelToFollow());
        bot.startEmittingSignalLabel(rightLabel);
        bot.setLabelToFollow(rightLabel);
        bot.setFollowingDistance(3);
        bot.setDirectionAngle(90);
        bot.setContinueMotion(10);

        assertTrue(bot.IsEmittingSignal());
        assertEquals(rightLabel, bot.getLabelToEmit());
        assertEquals(rightLabel, bot.getLabelToFollow());
        assertTrue(3 == bot.getFollowingDistance());
        assertTrue(90 == bot.getDirectionAngle());
        assertTrue(10 == bot.getMovementTimer());
        assertFalse(botManager.moveAllBots(1));
        // assertFalse(bot.proceed(1));
        assertTrue(0.0 == bot.getXPosition() && 0.0 == bot.getYPosition());

        bot.setSpeed(1);
        
        assertTrue(botManager.moveAllBots(1));
        // assertTrue(bot.proceed(1));
        assertTrue(0.0 == bot.getXPosition() && 1.0 == bot.getYPosition());
        assertTrue(botManager.moveAllBots(1));
        // assertTrue(bot.proceed(1));

        bot.stopEmittingSignalLabel();
        bot.stopMotion();

        assertFalse(bot.IsEmittingSignal());
        assertEquals("", bot.getLabelToEmit());
        assertFalse(botManager.moveAllBots(1));
        // assertFalse(bot.proceed(1));

    }

    @Test
    public void botCreationTests() {

        ArrayList<BotInterface> botListToCheck;
        BotInterface botToCheck;
        BotManagerInterface botManager = new BotManager();
        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            botList.add(new Bot(i, i));
        }

        botManager.createBot(botList);
        assertEquals(botList, botManager.getBotList());
        
        botManager.createBot(6, 6);
        botListToCheck = botManager.getBotList();
        botToCheck = botListToCheck.get(botListToCheck.size()-1);
        assertTrue(botToCheck.getXPosition() == 6 && botToCheck.getYPosition() == 6);

        BotInterface botToInsert = new Bot(90, 90);
        botManager.createBot(botToInsert);
        assertTrue(botManager.getBotList().contains(botToInsert));

        botManager = new BotManager();
        botManager.createRandomBots(10, negativeCoordinates, positiveCoordinates);
        botListToCheck = botManager.getBotList();
        assertTrue(botListToCheck.size() == 10);
        for (BotInterface bot : botListToCheck) {
            assertTrue(bot.getXPosition() >= -10 && bot.getXPosition() <= 10 &&
                        bot.getYPosition() >= -10 && bot.getYPosition() <= 10);
        }
        assertFalse(botListToCheck.get(0).getXPosition() == botListToCheck.get(1).getXPosition() &&
                    botListToCheck.get(0).getXPosition() == botListToCheck.get(2).getXPosition() &&
                    botListToCheck.get(0).getXPosition() == botListToCheck.get(3).getXPosition());  //It will happen extremely unlikely by chance in case of malfunction
    }

    @Test
    public void detectingTests() {
        
        String rightLabel = "rightLabel";
        BotInterface searchingBot = new Bot(0, 0);
        BotInterface wrongLabelBot = new Bot(1, 1);
        BotInterface emptyLabelBot = new Bot(-1, -1);
        BotInterface notEmittingBot = new Bot(0, 2);
        BotInterface farAwayBot = new Bot(3, 3);
        BotInterface rightLAbelBot = new Bot(0, -2);
        wrongLabelBot.startEmittingSignalLabel("wrongLabel");
        farAwayBot.startEmittingSignalLabel(rightLabel);
        rightLAbelBot.startEmittingSignalLabel(rightLabel);
        emptyLabelBot.setEmittingSignal(true);
        

        ArrayList<BotInterface> botList = new ArrayList<>();
        botList.add(wrongLabelBot);
        botList.add(emptyLabelBot);
        botList.add(notEmittingBot);
        botList.add(farAwayBot);
        assertFalse(searchingBot.isDetectingLabel(botList, rightLabel, searchingBot.getFollowingDistance()));
        botList.add(rightLAbelBot);
        assertTrue(searchingBot.isDetectingLabel(botList, rightLabel, searchingBot.getFollowingDistance()));
    }

    @Test
    public void movementTests() {

        BotManagerInterface botManager = new BotManager();
        BotInterface movingBot = botManager.createBot(0, 0);

        movingBot.setMove(new DirectionalVectors(-0.5, 0.7), 2);
        assertTrue(125.54 == new BigDecimal(movingBot.getDirectionAngle())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue());
        botManager.moveAllBots(1);
        // movingBot.proceed(1);
        assertTrue(-1.16 == new BigDecimal(movingBot.getXPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                                &&
                    1.63 == new BigDecimal(movingBot.getYPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue());

        movingBot = new Bot(0, 0);
        String rightLabel = "rightLabel";
        BotInterface bot1 = new Bot(-1, 0);
        BotInterface bot2 = new Bot(-1, 1);
        ArrayList<BotInterface> botList = new ArrayList<>();
        botList.add(bot1);
        botList.add(bot2);

        movingBot.setFollow(rightLabel, 3, 2, botList);
        assertTrue(153.44 == new BigDecimal(movingBot.getDirectionAngle())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue());
        botManager.moveAllBots(1);
        // movingBot.proceed(1);
        assertTrue(-1.79 == new BigDecimal(movingBot.getXPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                                &&
                    0.89 == new BigDecimal(movingBot.getYPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue());
    }

    @Test
    public void randomMovementTests() {

        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            botList.add(new Bot(0, 0));
        }

        for (BotInterface bot : botList) {
            bot.setMoveRandom(negativeVectors, positiveVectors, 1);
            assertTrue(bot.getXPosition() < -1 && bot.getXPosition() > 1 &&
                        bot.getYPosition() < -1 && bot.getYPosition() > 1); //It will happen extremely unlikely by chance in case of malfunction
        }

        assertFalse(botList.get(0).getXPosition() == botList.get(1).getXPosition() &&
                    botList.get(0).getXPosition() == botList.get(2).getXPosition() &&
                    botList.get(0).getXPosition() == botList.get(3).getXPosition());  //It will happen extremely unlikely by chance in case of malfunction
    }

    @Test
    public void moveAllBotsTest() {

        BotManagerInterface botManager = new BotManager();
        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            botList.add(new Bot(0, 0));    
        }
        BotInterface bot1 = botList.get(0);
        BotInterface bot2 = botList.get(1);
        BotInterface bot3 = botList.get(2);
        bot1.setMove(new DirectionalVectors(-1, 0), 1);
        bot2.setMove(new DirectionalVectors(1, -0.6), 1);
        bot3.setMove(new DirectionalVectors(-0.5, -0.99), 2);

        botManager.moveAllBots(1);

        assertTrue(-1 == bot1.getXPosition() && 0 == bot1.getYPosition());
        assertTrue(0.86 == new BigDecimal(bot2.getXPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                                && 
                    -0.51 == new BigDecimal(bot2.getYPosition())
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue());
        assertTrue(-0.90 == new BigDecimal(bot3.getXPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                                &&
                    -1.79 == new BigDecimal(bot3.getYPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue());
    }
 }
