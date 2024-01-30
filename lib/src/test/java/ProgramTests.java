import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import classes.bots.Bot;
import classes.programs.CounterProgram;
import classes.programs.InfiniteProgram;
import classes.programs.LabelProgram;
import classes.programs.TargetProgram;
import classes.targets.Square;
import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
import interfaces.bots.BotInterface;

public class ProgramTests {

    @Test
    public void illegalArgumentTests() {

        ArrayList<CartesianArea> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(0, 0);
        CounterProgram counterProgram = new CounterProgram(taskList, 1);
        LabelProgram labelProgram = new LabelProgram(taskList, "label", 5);
        TargetProgram targetProgram = new TargetProgram(taskList, "target");

        assertThrows(IllegalArgumentException.class, () -> {new CounterProgram(null, 0);});
        assertThrows(IllegalArgumentException.class, () -> {new CounterProgram(taskList, -1);});
        assertThrows(IllegalArgumentException.class, () -> {new CounterProgram(taskList, 0).setCounter(-1);});
        assertThrows(IllegalArgumentException.class, () -> {counterProgram
                                                                    .isExpired(null, null, null);});

        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(null, "label", 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, "", 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, null, 5);});
        assertThrows(IllegalArgumentException.class, () -> {new LabelProgram(taskList, "label", 0);});
        assertThrows(IllegalArgumentException.class, () -> {labelProgram
                                                                            .isExpired(null, botList, null);});
        assertThrows(IllegalArgumentException.class, () -> {labelProgram
                                                                            .isExpired(bot, null, null);});

        assertThrows(IllegalArgumentException.class, () -> {new TargetProgram(null, "label");});
        assertThrows(IllegalArgumentException.class, () -> {new TargetProgram(taskList, "");});
        assertThrows(IllegalArgumentException.class, () -> {new TargetProgram(taskList, null);});
        assertThrows(IllegalArgumentException.class, () -> {targetProgram
                                                                                .isExpired(null, null, targetList);});
        assertThrows(IllegalArgumentException.class, () -> {targetProgram
                                                                                .isExpired(bot, null, null);});
    }

    @Test
    public void expirationTests() {

        ArrayList<CartesianArea> targetList = new ArrayList<>();
        ArrayList<BotCommand> taskList = new ArrayList<>();
        ArrayList<BotInterface> botList = new ArrayList<>();
        BotInterface bot = new Bot(0, 0);
        botList.add(bot);
        CounterProgram counterProgram = new CounterProgram(taskList, 1);
        LabelProgram labelProgram = new LabelProgram(taskList, "label", 5);
        TargetProgram targetProgram = new TargetProgram(taskList, "target");

        assertFalse(counterProgram.isExpired(bot, null, null));
        counterProgram.setCounter(0);
        assertTrue(counterProgram.isExpired(bot, null, null));

        assertFalse(new InfiniteProgram(taskList).isExpired(null, null, null));
        
        assertFalse(labelProgram.isExpired(bot, botList, null));
        BotInterface emittingBot = new Bot(2, 2);
        emittingBot.startEmittingSignalLabel("label");
        botList.add(emittingBot);
        assertTrue(labelProgram.isExpired(bot, botList, null));
        
        assertFalse(targetProgram.isExpired(bot, null, targetList));
        targetList.add(new Square(-1, -1, 2, 2, "target"));
        assertTrue(targetProgram.isExpired(bot, null, targetList));
    }

    @Test
    public void taskAssignmentTests() {

        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            botList.add(new Bot(0, 0));
        }
        ArrayList<BotCommand> taskList = new ArrayList<>();
        taskList.add((bot) -> bot.IsEmittingSignal());
        CounterProgram counterProgram = new CounterProgram(taskList, 1);
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
}