import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import classes.SimulationManager;
import classes.bots.BotManager;
import classes.programs.ProgramManager;
import classes.programs.RepeatingProgram;
import classes.programs.TargetProgram;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import classes.targets.CartesianAreaManager;
import classes.targets.Rectangle;
import functionalInterfaces.BotCommand;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;
import interfaces.targets.CartesianAreaManagerInterface;

public class SimulationManagerTests {
    
    Coordinates zeroCoordinates = new Coordinates(0, 0);

    @Test
    public void simulationManagerExceptionTests() {

        ProgramManagerInterface progM = new ProgramManager();
        BotManagerInterface botM = new BotManager();
        CartesianAreaManagerInterface cartAreaM = new CartesianAreaManager();
        SimulationManager simulationManager = new SimulationManager(botM, progM, cartAreaM);

        assertThrows(NullPointerException.class, () -> {new SimulationManager(null, progM, cartAreaM);});
        assertThrows(NullPointerException.class, () -> {new SimulationManager(botM, null, cartAreaM);});
        assertThrows(NullPointerException.class, () -> {new SimulationManager(botM, progM, null);});

        assertThrows(NullPointerException.class, () -> {simulationManager.setBotManager(null);});
        assertThrows(NullPointerException.class, () -> {simulationManager.setProgramManager(null);});

        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(0, 1, 1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(1, 0, 1, 1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(1, 1, -0.001, 1);});
        assertThrows(IllegalArgumentException.class, () -> {simulationManager.simulate(1, 1, 1, 0);});
    }

    @Test
    public void simulationManagerGettersAndSettersTests() {

        BotManagerInterface botManager = new BotManager();
        ProgramManagerInterface programManager = new ProgramManager();
        CartesianAreaManagerInterface cartesianAreaManager = new CartesianAreaManager();
        SimulationManagerInterface simulationManager = new SimulationManager(botManager, programManager, cartesianAreaManager);
        assertEquals(botManager, simulationManager.getBotManager());
        assertEquals(programManager, simulationManager.getProgramManager());

        BotManagerInterface botManager2 = new BotManager();
        ProgramManagerInterface programManager2 = new ProgramManager();
        simulationManager.setBotManager(botManager2);
        simulationManager.setProgramManager(programManager2);
        assertEquals(botManager2, simulationManager.getBotManager());
        assertEquals(programManager2, simulationManager.getProgramManager());
    }

    @Test
    public void simulationTest() {

        ProgramManagerInterface programManager = new ProgramManager();
        BotManagerInterface botManager = new BotManager();
        CartesianAreaManagerInterface cartesianAreaManager = new CartesianAreaManager();
        SimulationManager simulationManager = new SimulationManager(botManager, programManager, cartesianAreaManager);

        cartesianAreaManager.createTarget(new Rectangle(new Coordinates(-1, 9.5), "target", 3, 1));
        BotInterface botToProgram1 = botManager.createBot(zeroCoordinates);
        BotInterface botToProgram2 = botManager.createBot(zeroCoordinates);

        ArrayList<BotCommand> taskList1 = new ArrayList<>();
        taskList1.add((bot) -> bot.setMove(new DirectionalVectors(0, 1), 1));
        taskList1.add((bot) -> bot.setContinueMotion(20));
        ArrayList<BotCommand> taskList2 = new ArrayList<>();
        ArrayList<BotCommand> taskList3 = new ArrayList<>();
        taskList3.add((bot) -> bot.stopMotion());

        RepeatingProgram rp1 = programManager.createRepeatingProgram(botToProgram1, taskList1, 1);  //start moving North for 20 seconds
        TargetProgram tp = programManager.createTargetProgram(botToProgram1, taskList2, "target");  //do nothing until reaching the target
        RepeatingProgram rp2 = programManager.createRepeatingProgram(botToProgram1, taskList3, 1);  //stop moving after expiration of the previous task
        RepeatingProgram rp3 = programManager.createRepeatingProgram(botToProgram2, taskList1, 1);  //the second bot will move for 20 seconds

        simulationManager.simulate(1, 1, 0, 1);
        assertTrue(botToProgram1.getProgramList().get(0).equals(rp1) && rp1.isExpired());
        assertTrue(botToProgram1.getCoordinates().y == 1 && botToProgram1.getMovementTimer() == 19);
        assertTrue(botToProgram2.getCoordinates().y == 1 && botToProgram2.getMovementTimer() == 19);

        simulationManager.simulate(50, 1, 0, 1);
        assertTrue(tp.isExpired(botToProgram1.getCoordinates(), cartesianAreaManager.getTargetList()) && rp2.isExpired()); //all bot1 tasks expired
        assertTrue(rp3.isExpired());
        assertTrue(botToProgram1.getProgramList().isEmpty() && botToProgram2.getProgramList().isEmpty()); //all tasks removed
        assertTrue(botToProgram1.getCoordinates().y == 10 && botToProgram1.getMovementTimer() == 0); //Has stopped onto the target
        assertTrue(botToProgram2.getCoordinates().y == 20 && botToProgram2.getMovementTimer() == 0); //Has continued for 20 meters
    }
}
