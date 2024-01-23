import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.lang.IllegalArgumentException;
import java.lang.Math;
import java.util.ArrayList;

import org.junit.Test;

import classes.Bot;
import interfaces.BotInterface;

public class BotTests {
    
    @Test
    public void botClassTest()
    {
        String rightLabel = "rightLabel";
        BotInterface searchingBot = new Bot(0, 0);
        
        assertFalse(searchingBot.IsEmittingSignal());
        assertEquals("", searchingBot.getSignalLabel());
        assertEquals("", searchingBot.getFollowingLabel());
        searchingBot.startEmittingSignalLabel(rightLabel);

        assertTrue(searchingBot.IsEmittingSignal());
        assertEquals(rightLabel, searchingBot.getSignalLabel());

        assertThrows(IllegalArgumentException.class, () -> {searchingBot.setMove(0, 0, 0);});
        assertThrows(IllegalArgumentException.class, () -> {searchingBot.setMove(-1, 1, -1);});
        assertThrows(IllegalArgumentException.class, () -> {searchingBot.setMove(-1, 1, 0);});
        assertFalse(searchingBot.proceed(1));
        assertTrue(225 == searchingBot.getDirectionAngle());
        assertTrue(0.0 == searchingBot.getXPosition() && 0.0 == searchingBot.getYPosition());
        assertTrue(1 == searchingBot.getMovementTimer());
        searchingBot.setContinueMotion(10);
        assertTrue(10 == searchingBot.getMovementTimer());
        

        BotInterface wrongLabelBot = new Bot(1, 1);
        BotInterface emptyLabelBot = new Bot(-1, -1);
        BotInterface notEmittingBot = new Bot(0, 2);
        BotInterface farAwayBot = new Bot(3, 3);
        BotInterface rightLAbelBot = new Bot(0, -2);
        wrongLabelBot.setSignalLabel("wrongLabel");
        wrongLabelBot.setEmittingSignal(true);
        emptyLabelBot.setEmittingSignal(true);
        farAwayBot.setSignalLabel(rightLabel);
        farAwayBot.setEmittingSignal(true);
        rightLAbelBot.setSignalLabel(rightLabel);
        rightLAbelBot.setEmittingSignal(true);

        ArrayList<BotInterface> botList = new ArrayList<>();
        botList.add(wrongLabelBot);
        botList.add(emptyLabelBot);
        botList.add(notEmittingBot);
        botList.add(farAwayBot);
        assertFalse(searchingBot.isDetectingLabelToFollow(botList));
        botList.add(rightLAbelBot);
        assertTrue(searchingBot.isDetectingLabelToFollow(botList));

        searchingBot.setFollow(rightLabel, 3, 1, botList);  //coordinates = (0, 0)
        assertTrue(3 == searchingBot.getFollowingDistance() && 1 == searchingBot.getSpeed());
        assertTrue(270 == searchingBot.getDirectionAngle());
        assertTrue(searchingBot.proceed(1));
        assertTrue(0 == searchingBot.getXPosition() && -1 == searchingBot.getYPosition());

        rightLAbelBot.setXPosition(-1);  //Coordinates = (-1, -2)
        searchingBot.setFollow(rightLabel, 3, 1, botList);
        assertTrue(225 == searchingBot.getDirectionAngle());
        assertTrue(searchingBot.proceed(1));  //expected new coordinates = (-1,4..., -2,4...)
        assertTrue(-Math.sqrt(2) == searchingBot.getXPosition() && (-1-Math.sqrt(2)) == searchingBot.getYPosition());

        BotInterface movingBot = new Bot(0, 0);
        movingBot.setMove(-0.5, 0.7, 2);
        assertTrue(125.537677791974 == searchingBot.getDirectionAngle());
        movingBot.proceed(1);
        assertTrue(-1.162476387438 == movingBot.getXPosition() && 1.627466942414 == movingBot.getYPosition());

        searchingBot.setXPosition(0);
        searchingBot.setYPosition(0);
        rightLAbelBot.setXPosition(-1);
        rightLAbelBot.setYPosition(0);
        farAwayBot.setXPosition(-1);
        farAwayBot.setYPosition(1);
        searchingBot.setFollow(rightLabel, 3, 2, botList);
        assertTrue(116.565051177078 == searchingBot.getDirectionAngle());
    }
}
