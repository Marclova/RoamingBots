package examples;

import classes.SimulationManager;
import classes.services.containers.Coordinates;
import classes.targets.Rectangle;

public class exampleMain1 {
    public static void main(String[] args) {
        SimulationManager simulationManager = new SimulationManager();

        simulationManager.createTarget(new Rectangle(new Coordinates(0, 0), "r", 10, 10));
        simulationManager.createTarget(new Rectangle(new Coordinates(3, 3), "outerR", 4, 4));
        simulationManager.getBotManager().createBot(new Coordinates(13, 7));

        simulationManager.simulate(1, 1, 0);
    }
}