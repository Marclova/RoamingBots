import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

import classes.SimulationManager;
import classes.bots.BotManager;
import classes.programs.ProgramManager;
import classes.programs.RepeatingProgram;
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

public class SimulationManagerTests {
    
    @Test
    public void simulationManagerExceptionTests() {

        SimulationManagerInterface simulationManager = new SimulationManager();
        BotManagerInterface botM = new BotManager();
        ProgramManagerInterface progM = new ProgramManager();

        assertThrows(NullPointerException.class, () -> {new SimulationManager(null, progM);});
        assertThrows(NullPointerException.class, () -> {new SimulationManager(botM, null);});

        assertThrows(NullPointerException.class, () -> {simulationManager.setBotManager(null);});
        assertThrows(NullPointerException.class, () -> {simulationManager.setProgramManager(null);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.setExecutionTimeCycle(0);});

        assertThrows(NullPointerException.class, () -> {simulationManager.createTarget(null);});

        assertThrows(NullPointerException.class, () -> {simulationManager.createTargetsFromTxtFile(null);});
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
    
    @Test
    public void simulationTest() {

    SimulationManager simulationManager = new SimulationManager();
    ProgramManagerInterface programManager = simulationManager.getProgramManager();
    BotManagerInterface botManager = simulationManager.getBotManager();

    BotInterface botToProgram1 = botManager.createBot(0, 0);
    BotInterface botToProgram2 = botManager.createBot(0, 0);
    ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
    targetList.add(new Rectangle(new Coordinates(-1, 9.5), "target", 3, 1));

    ArrayList<BotCommand> taskList1 = new ArrayList<>();
    taskList1.add((bot) -> bot.setMove(new DirectionalVectors(0, 1), 1));
    taskList1.add((bot) -> bot.setContinueMotion(20));
    ArrayList<BotCommand> taskList2 = new ArrayList<>();
    ArrayList<BotCommand> taskList3 = new ArrayList<>();
    taskList3.add((bot) -> bot.stopMotion());

    RepeatingProgram rp1 = programManager.createRepeatingProgram(botToProgram1, taskList1, 1);  //start moving North for 20 seconds
    TargetProgram tp = programManager.createTargetProgram(botToProgram1, taskList2, "target");  //do nothing until reaching the target
    RepeatingProgram rp2 = programManager.createRepeatingProgram(botToProgram1, taskList3, 1);  //stop moving after expiration of the previous task
    programManager.createRepeatingProgram(botToProgram2, taskList1, 1);  //the second bot will move for 20 seconds

    simulationManager.simulate(1, 1);
    assertTrue(botToProgram1.getProgramList().get(0).equals(rp1) && rp1.isExpired());
    assertTrue(botToProgram1.getYPosition() == 1 && botToProgram1.getMovementTimer() == 19);
    assertTrue(botToProgram2.getYPosition() == 1 && botToProgram2.getMovementTimer() == 19);

    simulationManager.simulate(50, 1);
    assertTrue(tp.isExpired(botToProgram1.getCoordinates(), targetList) && rp2.isExpired()); //all bot1 tasks expired
    assertTrue(botToProgram1.getProgramList().isEmpty() && botToProgram2.getProgramList().isEmpty()); //all tasks removed
    assertTrue(botToProgram1.getYPosition() == 10 && botToProgram1.getMovementTimer() == 0); //Has stopped onto the target
    assertTrue(botToProgram1.getYPosition() == 20 && botToProgram1.getMovementTimer() == 0); //Has continued for 20 meters
    }
}
