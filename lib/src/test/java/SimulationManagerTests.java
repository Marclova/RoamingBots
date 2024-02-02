import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import classes.SimulationManager;
import classes.bots.Bot;
import classes.bots.BotManager;
import classes.programs.ProgramManager;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

public class SimulationManagerTests {
    
    @Test
    public void simulationManagerIllegalArgumentExceptionTests() {

        SimulationManagerInterface simulationManager = new SimulationManager();
        BotManagerInterface botM = new BotManager();
        ProgramManagerInterface progM = new ProgramManager();

        assertThrows(IllegalArgumentException.class, () -> {new SimulationManager(null, progM);});
        assertThrows(IllegalArgumentException.class, () -> {new SimulationManager(botM, null);});

        assertThrows(IllegalArgumentException.class, () -> {simulationManager.setBotManager(null);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.setProgramManager(null);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.setExecutionTimeCycle(0);});

        assertThrows(IllegalArgumentException.class, () -> {simulationManager.createTargetsFromTxtFile(null);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.createTargetsFromTxtFile("");});
        assertThrows(FileNotFoundException.class, () -> {simulationManager.createTargetsFromTxtFile("ciao");}); //TODO controlla l'errore lanciato

        assertThrows(IllegalArgumentException.class, () -> {simulationManager.createBot((BotInterface)null);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.createBot((ArrayList<BotInterface>)null);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.createRandomBots(0, -1, -1, 1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.createRandomBots(1, 1, -1, -1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.createRandomBots(1, -1, 1, 1, -1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(0, 1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(1, 0);});
    }

    @Test
    public void botCreationTests() {

        ArrayList<BotInterface> botListToCheck;
        BotInterface botToCheck;
        BotManagerInterface botManager = new BotManager();
        ProgramManagerInterface programManager = new ProgramManager();
        SimulationManagerInterface simulationManager = new SimulationManager(botManager, programManager);
        ArrayList<BotInterface> botList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            botList.add(new Bot(i, i));
        }

        assertEquals(botManager, simulationManager.getBotManager());
        assertEquals(programManager, simulationManager.getProgramManager());

        simulationManager.createBot(botList);
        assertEquals(botList, simulationManager.getBotList());
        
        simulationManager.createBot(6, 6);
        botListToCheck = simulationManager.getBotList();
        botToCheck = botListToCheck.get(botListToCheck.size()-1);
        assertTrue(botToCheck.getXPosition() == 6 && botToCheck.getYPosition() == 6);

        BotInterface botToInsert = new Bot(90, 90);
        simulationManager.createBot(botToInsert);
        assertTrue(simulationManager.getBotList().contains(botToInsert));

        simulationManager = new SimulationManager();
        simulationManager.createRandomBots(10, -10, -10, 10, 10);
        botListToCheck = simulationManager.getBotList();
        assertTrue(botListToCheck.size() == 10);
        for (BotInterface bot : botListToCheck) {
            assertTrue(bot.getXPosition() >= -10 && bot.getXPosition() <= 10 &&
                        bot.getYPosition() >= -10 && bot.getYPosition() <= 10);
        }
        assertFalse(botListToCheck.get(0).getXPosition() == botListToCheck.get(1).getXPosition() &&
                    botListToCheck.get(0).getXPosition() == botListToCheck.get(2).getXPosition() &&
                    botListToCheck.get(0).getXPosition() == botListToCheck.get(3).getXPosition());  //It will happen extremely unlikely by chance in case of malfunction
    }
    // ProgramManagerInterface programManager = new ProgramManager();
    // ArrayList<CartesianArea> targetList = new ArrayList<>();
    // targetList.add(new Square(-1, 10, 3, 0, "target"));
    // BotInterface botToProgram = new Bot(0, 0);
    // ArrayList<BotCommand> taskList1 = new ArrayList<>();
    // taskList1.add((bot) -> bot.setMove(0, 1, 1));
    // taskList1.add((bot) -> bot.setContinueMotion(20));
    // ArrayList<BotCommand> taskList2 = new ArrayList<>();
    // ArrayList<BotCommand> taskList3 = new ArrayList<>();
    // taskList3.add((bot) -> bot.stopMotion());

    // programManager.createRepeatingProgram(botToProgram, taskList1, 1);  //start moving north
    // programManager.createTargetProgram(botToProgram, taskList2, "target");  //do nothing until reaching the target
    // programManager.createRepeatingProgram(botToProgram, taskList3, 1);  //stop moving after expiration of the previous task
}
