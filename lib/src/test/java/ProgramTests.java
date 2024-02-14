import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import classes.SimulationManager;
import classes.bots.Bot;
import classes.programs.RepeatingProgram;
import classes.programs.InfiniteProgram;
import classes.programs.LabelProgram;
import classes.programs.ProgramManager;
import classes.programs.TargetProgram;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import classes.targets.Rectangle;
import functionalInterfaces.BotCommand;
import interfaces.CartesianAreaInterface;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

public class ProgramTests {

    Coordinates positiveCoordinates = new Coordinates(1, 1);
    Coordinates negativeCoordinates = new Coordinates(-1, -1);
    Coordinates zeroCoordinates = new Coordinates(0, 0);
    DirectionalVectors positiveVectors = new DirectionalVectors(1, 1);
    DirectionalVectors negativeVectors = new DirectionalVectors(-1, -1);

    @Test
    public void programsIllegalArgumentTests() {

        ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(zeroCoordinates);
        RepeatingProgram repeatingProgram = new RepeatingProgram(taskList, 1);
        LabelProgram labelProgram = new LabelProgram(taskList, "label", 5);
        TargetProgram targetProgram = new TargetProgram(taskList, "target");

        assertThrows(NullPointerException.class, () -> {new RepeatingProgram(null, 0);});
        assertThrows(IllegalArgumentException.class, () -> {new RepeatingProgram(taskList, -1);});
        assertThrows(IllegalArgumentException.class, () -> {new RepeatingProgram(taskList, 0);});
        assertThrows(IllegalArgumentException.class, () -> {repeatingProgram.setCounter(-1);});

        assertThrows(NullPointerException.class, () -> {new InfiniteProgram(null);});

        assertThrows(NullPointerException.class, () -> {new LabelProgram(null, "label", 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, "", 5);});
        assertThrows(NullPointerException.class, () -> {new LabelProgram(taskList, null, 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, "label", 0);});
        assertThrows(NullPointerException.class, () -> {labelProgram.isExpired(null, botList);});
        assertThrows(NullPointerException.class, () -> {labelProgram.isExpired(bot, null);});

        assertThrows(NullPointerException.class, () -> {new TargetProgram(null, "label");});
        assertThrows(IllegalArgumentException.class, () -> {new TargetProgram(taskList, "");});
        assertThrows(NullPointerException.class, () -> {new TargetProgram(taskList, null);});
        assertThrows(NullPointerException.class, () -> {targetProgram.isExpired(null, targetList);});
        assertThrows(NullPointerException.class, () -> {targetProgram.isExpired(bot.getCoordinates(), null);});
    }

    @Test
    public void programManagerExceptionTests() {

        ProgramManagerInterface programManager = new ProgramManager();
        ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(zeroCoordinates);

        assertThrows(NullPointerException.class, () -> {programManager.deleteExpiredAndThenExecuteAllPrograms(null, targetList);});
        assertThrows(NullPointerException.class, () -> {programManager.deleteExpiredAndThenExecuteAllPrograms(botList, null);});

        assertThrows(NullPointerException.class, () -> {programManager.createRepeatingProgram(null, taskList, 5);});
        assertThrows(NullPointerException.class, () -> {programManager.createRepeatingProgram(bot, null, 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createRepeatingProgram(bot, taskList, 0);});

        assertThrows(NullPointerException.class, () -> {programManager.createInfiniteProgram(null, taskList);});
        assertThrows(NullPointerException.class, () -> {programManager.createInfiniteProgram(bot, null);});

        assertThrows(NullPointerException.class, () -> {programManager.createLabelProgram(null, taskList, "label", 5);});
        assertThrows(NullPointerException.class, () -> {programManager.createLabelProgram(bot, null, "label", 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createLabelProgram(bot, taskList, "", 5);});
        assertThrows(NullPointerException.class, () -> {programManager.createLabelProgram(bot, taskList, null, 5);});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createLabelProgram(bot, taskList, "label", 0);});

        assertThrows(NullPointerException.class, () -> {programManager.createTargetProgram(null, taskList, "target");});
        assertThrows(NullPointerException.class, () -> {programManager.createTargetProgram(bot, null, "target");});
        assertThrows(IllegalArgumentException.class, () -> {programManager.createTargetProgram(bot, taskList, "");});
        assertThrows(NullPointerException.class, () -> {programManager.createTargetProgram(bot, taskList, null);});
    }

    @Test
    public void expirationTests() {

        ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        taskList.add((b) -> b.IsEmittingSignal());
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(zeroCoordinates);
        botList.add(bot);
        RepeatingProgram counterProgram = new RepeatingProgram(taskList, 1);
        LabelProgram labelProgram = new LabelProgram(taskList, "label", 5);
        TargetProgram targetProgram = new TargetProgram(taskList, "target");

        assertFalse(counterProgram.isExpired());
        counterProgram.setCounter(0);
        assertTrue(counterProgram.isExpired());

        assertFalse(new InfiniteProgram(taskList).isExpired());
        
        assertFalse(labelProgram.isExpired(bot, botList));
        BotInterface emittingBot = new Bot(new Coordinates(2, 2));
        emittingBot.startEmittingSignalLabel("label");
        botList.add(emittingBot);
        assertTrue(labelProgram.isExpired(bot, botList));
        
        assertFalse(targetProgram.isExpired(bot.getCoordinates(), targetList));
        targetList.add(new Rectangle(new Coordinates(-1, -1), "target", 2, 2));
        assertTrue(targetProgram.isExpired(bot.getCoordinates(), targetList));
    }

    @Test
    public void taskAssignmentTests() {

        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            botList.add(new Bot(zeroCoordinates));
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
        BotInterface bot = new Bot(zeroCoordinates);

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

    @Test
    public void programManagerExecutionTests() {

        SimulationManagerInterface simulationManager = new SimulationManager();
        ArrayList<CartesianAreaInterface> targetList = simulationManager.getTargetList();
        BotManagerInterface botManager = simulationManager.getBotManager();
        ProgramManagerInterface programManager = simulationManager.getProgramManager();
        ArrayList<BotInterface> botList = botManager.getBotList();

        BotInterface botToProgram = botManager.createBot(zeroCoordinates);
        ArrayList<BotCommand> taskList = new ArrayList<>();
        taskList.add((bot) -> bot.setMove(new DirectionalVectors(0, 1), 1));
        taskList.add((bot) -> bot.setContinueMotion(1));
        
        programManager.createRepeatingProgram(botToProgram, taskList, 10); //sets movements for 1 second 10 times
        
        while (!botToProgram.getProgramList().isEmpty()) {
            programManager.deleteExpiredAndThenExecuteAllPrograms(botList, targetList);
            botManager.moveAllBots(1);
        }
        assertTrue(botToProgram.getCoordinates().x == 0.0 && botToProgram.getCoordinates().y == 10.0);

        taskList.add((bot) -> bot.setContinueMotion(10));
        botToProgram = new Bot(zeroCoordinates);
        botList.clear();
        botList.add(botToProgram);
        programManager.createRepeatingProgram(botToProgram, taskList, 1); //sets movement for 10 seconds 1 time
        programManager.deleteExpiredAndThenExecuteAllPrograms(botList, targetList);
        programManager.deleteExpiredAndThenExecuteAllPrograms(botList, targetList);

        assertTrue(botToProgram.getProgramList().isEmpty());
        botManager.moveAllBots(20); //it just has 10 seconds of movement
        assertTrue(botToProgram.getCoordinates().x == 0 && botToProgram.getCoordinates().y == 10);
    }
}