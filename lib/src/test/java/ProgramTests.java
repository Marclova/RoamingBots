import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import classes.bots.Bot;
import classes.programs.RepeatingProgram;
import classes.programs.InfiniteProgram;
import classes.programs.LabelProgram;
import classes.programs.ProgramManager;
import classes.programs.TargetProgram;
import classes.targets.Square;
import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
import interfaces.bots.BotInterface;
import interfaces.programs.ProgramManagerInterface;

public class ProgramTests {

    @Test
    public void programsIllegalArgumentTests() {

        ArrayList<CartesianArea> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(0, 0);
        LabelProgram labelProgram = new LabelProgram(taskList, "label", 5);
        TargetProgram targetProgram = new TargetProgram(taskList, "target");

        assertThrows(IllegalArgumentException.class, () -> {new RepeatingProgram(null, 0);});
        assertThrows(IllegalArgumentException.class, () -> {new RepeatingProgram(taskList, -1);});
        assertThrows(IllegalArgumentException.class, () -> {new RepeatingProgram(taskList, 0).setCounter(-1);});

        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(null, "label", 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, "", 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, null, 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, "label", 0);});
        assertThrows(IllegalArgumentException.class, () -> {labelProgram
                                                                                .isExpired(null, botList);});
        assertThrows(IllegalArgumentException.class, () -> {labelProgram
                                                                                .isExpired(bot, null);});
        // assertThrows(IllegalArgumentException.class, () -> {labelProgram
        //                                                                         .checkLabel(null, botList);});
        // assertThrows(IllegalArgumentException.class, () -> {labelProgram
        //                                                                         .checkLabel(bot, null);});

        assertThrows(IllegalArgumentException.class, () -> {new TargetProgram(null, "label");});
        assertThrows(IllegalArgumentException.class, () -> {new TargetProgram(taskList, "");});
        assertThrows(IllegalArgumentException.class, () -> {new TargetProgram(taskList, null);});
        assertThrows(IllegalArgumentException.class, () -> {targetProgram
                                                                                .isExpired(null, targetList);});
        assertThrows(IllegalArgumentException.class, () -> {targetProgram
                                                                                .isExpired(bot, null);});
        // assertThrows(IllegalArgumentException.class, () -> {targetProgram
        //                                                                         .checkTarget(null, targetList);});
        // assertThrows(IllegalArgumentException.class, () -> {targetProgram
        //                                                                         .checkTarget(bot, null);});
    }

    @Test
    public void programManagerIllegalArgumentException() {

        ProgramManagerInterface programManager = new ProgramManager();
        ArrayList<CartesianArea> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(0, 0);

        assertThrows(IllegalArgumentException.class, () -> {programManager.deleteExpiredProgramsAndThenExecute(null, targetList);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.deleteExpiredProgramsAndThenExecute(botList, null);});

        assertThrows(IllegalArgumentException.class, () -> {programManager.executeBotProgram(null, botList, targetList);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.executeBotProgram(bot, null, targetList);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.executeBotProgram(bot, botList, null);});

        assertThrows(IllegalArgumentException.class, () -> {programManager.createRepeatingProgram(null, taskList, 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createRepeatingProgram(bot, null, 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createRepeatingProgram(bot, taskList, 0);});

        assertThrows(IllegalArgumentException.class, () -> {programManager.createInfiniteProgram(null, taskList);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createInfiniteProgram(bot, null);});

        assertThrows(IllegalArgumentException.class, () -> {programManager.createLabelProgram(null, taskList, "label", 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createLabelProgram(bot, null, "label", 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createLabelProgram(bot, taskList, "", 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createLabelProgram(bot, taskList, null, 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createLabelProgram(bot, taskList, "label", 0);});

        assertThrows(IllegalArgumentException.class, () -> {programManager.createTargetProgram(null, taskList, "target");});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createTargetProgram(bot, null, "target");});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createTargetProgram(bot, taskList, "");});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createTargetProgram(bot, taskList, null);});
    }

    @Test
    public void expirationTests() {

        ArrayList<CartesianArea> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(0, 0);
        botList.add(bot);
        RepeatingProgram counterProgram = new RepeatingProgram(taskList, 1);
        LabelProgram labelProgram = new LabelProgram(taskList, "label", 5);
        TargetProgram targetProgram = new TargetProgram(taskList, "target");

        assertFalse(counterProgram.isExpired());
        counterProgram.setCounter(0);
        assertTrue(counterProgram.isExpired());

        assertFalse(new InfiniteProgram(taskList).isExpired());
        
        assertFalse(labelProgram.isExpired(bot, botList));
        BotInterface emittingBot = new Bot(2, 2);
        emittingBot.startEmittingSignalLabel("label");
        botList.add(emittingBot);
        assertTrue(labelProgram.isExpired(bot, botList));
        
        assertFalse(targetProgram.isExpired(bot, targetList));
        targetList.add(new Square(-1, -1, 2, 2, "target"));
        assertTrue(targetProgram.isExpired(bot, targetList));
    }

    @Test
    public void taskAssignmentTests() {

        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            botList.add(new Bot(0, 0));
        }
        ArrayList<BotCommand> taskList = new ArrayList<>();
        taskList.add((bot) -> bot.IsEmittingSignal());
        RepeatingProgram counterProgram = new RepeatingProgram(taskList, 1);
        InfiniteProgram infiniteProgram = new InfiniteProgram(taskList);
        LabelProgram labelProgram = new LabelProgram(taskList, "label", 1);
        TargetProgram targetProgram = new TargetProgram(taskList, "target");
        botList.get(0).addProgram(counterProgram);
        botList.get(1).addProgram(infiniteProgram);
        botList.get(2).addProgram(labelProgram);
        botList.get(3).addProgram(targetProgram);

        for (BotInterface bot : botList) {
            assertFalse(bot.getProgramList().get(0).getTaskList().get(0).execute(bot));
        }
    }

    @Test
    public void programsCreationTests() {

        ProgramManagerInterface programManager = new ProgramManager();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        taskList.add((bot) -> bot.IsEmittingSignal());
        BotInterface bot = new Bot(0, 0);

        RepeatingProgram repeatingProgramToCheck = programManager.createRepeatingProgram(bot, taskList, 5);
        assertEquals(bot.getProgramList().get(0), repeatingProgramToCheck);
        assertEquals(taskList, repeatingProgramToCheck.getTaskList());
        assertTrue(repeatingProgramToCheck.getCounter() == 5);

        InfiniteProgram infiniteProgramToCheck = programManager.createInfiniteProgram(bot, taskList);
        assertEquals(infiniteProgramToCheck, bot.getProgramList().get(1));
        assertEquals(taskList, infiniteProgramToCheck.getTaskList());

        LabelProgram labelProgramToCheck = programManager.createLabelProgram(bot, taskList, "label",
                                                                                5);
        assertEquals(labelProgramToCheck, bot.getProgramList().get(2));
        assertEquals(taskList, labelProgramToCheck.getTaskList());
        assertEquals(labelProgramToCheck.getLabelToDetect(), "label");
        assertTrue(labelProgramToCheck.getDetectingDistance() == 5);

        TargetProgram targetProgramToCheck = programManager.createTargetProgram(bot, taskList, "target");
        assertEquals(targetProgramToCheck, bot.getProgramList().get(3));
        assertEquals(taskList, labelProgramToCheck.getTaskList());
        assertEquals(targetProgramToCheck.getTargetToReach(), "target");
    }
}