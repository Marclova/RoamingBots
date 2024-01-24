import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.lang.IllegalArgumentException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.Test;

import classes.Bot;
import interfaces.BotInterface;

public class BotTests {
    
    @Test
    public void gettersAndSetters()
    {
        String rightLabel = "rightLabel";
        BotInterface searchingBot = new Bot(0, 0);
        
        assertFalse(searchingBot.IsEmittingSignal());
        assertEquals("", searchingBot.getSignalLabel());
        assertEquals("", searchingBot.getFollowingLabel());
        searchingBot.startEmittingSignalLabel(rightLabel);
        searchingBot.setFollowingLabel(rightLabel);
        searchingBot.setFollowingDistance(3);
        searchingBot.setDirectionAngle(30);

        assertTrue(searchingBot.IsEmittingSignal());
        assertEquals(rightLabel, searchingBot.getSignalLabel());
        assertEquals(rightLabel, searchingBot.getFollowingLabel());
        assertTrue(3 == searchingBot.getFollowingDistance());
        assertTrue(30 == searchingBot.getDirectionAngle());


        assertThrows(IllegalArgumentException.class, () -> {searchingBot.setMove(0, 0, 0);});
        assertThrows(IllegalArgumentException.class, () -> {searchingBot.setMove(-1, 1, -1);});
        assertThrows(IllegalArgumentException.class, () -> {searchingBot.setMove(-1, 1, 0);});
        assertFalse(searchingBot.proceed(1));
        assertTrue(225 == searchingBot.getDirectionAngle());
        assertTrue(0.0 == searchingBot.getXPosition() && 0.0 == searchingBot.getYPosition());
        assertTrue(1 == searchingBot.getMovementTimer());
        searchingBot.setContinueMotion(10);
        assertTrue(10 == searchingBot.getMovementTimer());
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
        assertFalse(searchingBot.isDetectingLabelToFollow(botList));
        botList.add(rightLAbelBot);
        assertTrue(searchingBot.isDetectingLabelToFollow(botList));
    }

    public void movementTests() {

        BotInterface movingBot = new Bot(0, 0);

        movingBot.setMove(-0.5, 0.7, 2);
        assertTrue(125.54 == new BigDecimal(movingBot.getDirectionAngle())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue());
        movingBot.proceed(1);
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
        movingBot.proceed(1);
        assertTrue(-1.79 == new BigDecimal(movingBot.getXPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                                &&
                    0.89 == new BigDecimal(movingBot.getYPosition())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue());
    }
 }
