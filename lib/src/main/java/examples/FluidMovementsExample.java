package examples;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import classes.SimulationManager;
import classes.bots.BotManager;
import classes.programs.ProgramManager;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import classes.targets.CartesianAreaManager;
import classes.targets.Circle;
import functionalInterfaces.BotCommand;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;
import interfaces.targets.CartesianAreaManagerInterface;

/**
 * In this example the bot is just moving in random directions,
 *  moving each time just enough to have a detectable movement.
 */
public class FluidMovementsExample {
    public static void main(String[] args) throws FileNotFoundException {
        
        ProgramManagerInterface programManager = new ProgramManager();
        BotManagerInterface botManager = new BotManager();
        CartesianAreaManagerInterface cartesianAreaManager = new CartesianAreaManager();
        SimulationManagerInterface simulationManager = new SimulationManager(botManager, programManager, cartesianAreaManager);

        //values
        Coordinates zeroCoordinates = new Coordinates(0, 0);
        double zoom = 11;
        double minimalLength = 1 / zoom; //minimal length needed by an area to be seen in the simulation.
        double botSpeed = 1;

        botManager.createBot(zeroCoordinates);
        botManager.createBot(new Coordinates(-1, 0));
        botManager.createBot(new Coordinates(1, 0));
        // simulationManager.createTargetsFromTxtFile("FTargetInput.txt");
        cartesianAreaManager.createTarget(new Circle(new Coordinates(-1, 0), "null", minimalLength));
        cartesianAreaManager.createTarget(new Circle(new Coordinates(1, 0), "null", minimalLength));


        ArrayList<BotCommand> moveRandomly = new ArrayList<>();
        moveRandomly.add((bot) -> bot.setContinueMotion(1));
        moveRandomly.add((bot) -> bot.setMoveRandom(new DirectionalVectors(-1, -1), new DirectionalVectors(1, 1),
                                                        botSpeed));

        programManager.createInfiniteProgram(botManager.getBotList().get(0), moveRandomly); //program one bot to move randomly

        simulationManager.simulate(60, 0.1, 0.2, zoom);
    }
}
