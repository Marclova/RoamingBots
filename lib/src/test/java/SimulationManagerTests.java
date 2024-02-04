import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import classes.SimulationManager;
import classes.bots.BotManager;
import classes.programs.ProgramManager;
import functionalInterfaces.BotCommand;
import interfaces.SimulationManagerInterface;
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

        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(0, 1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(1, 0);});
    }

    @Test
    public void simulationManagerGettersAndSettersTests() {

        BotManagerInterface botManager = new BotManager();
        ProgramManagerInterface programManager = new ProgramManager();
        SimulationManagerInterface simulationManager = new SimulationManager(botManager, programManager);
        assertEquals(botManager, simulationManager.getBotManager());
        assertEquals(programManager, simulationManager.getProgramManager());

        BotManagerInterface botManager2 = new BotManager();
        ProgramManagerInterface programManager2 = new ProgramManager();
        simulationManager.setBotManager(botManager2);
        simulationManager.setProgramManager(programManager2);
        simulationManager.setExecutionTimeCycle(0.1);
        assertEquals(botManager2, simulationManager.getBotManager());
        assertEquals(programManager2, simulationManager.getProgramManager());
        assertTrue(simulationManager.getExecutionTimeCycle() == 0.1);
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
