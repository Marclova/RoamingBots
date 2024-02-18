package classes.services.graphic;

import java.util.ArrayList;

import classes.services.ArgumentCheckerService;
import interfaces.bots.BotInterface;
import interfaces.targets.CartesianAreaInterface;

public class GraphicServiceManager {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    ConsoleGraphicService consoleGraphicService = new ConsoleGraphicService();
    
    /**
     * Prints on console the current simulation status.
     * 
     * @param targetList The cartesian areas to represent.
     * @param botList The bots to represent.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    public void projectConsoleFrame(ArrayList<CartesianAreaInterface> targetList, ArrayList<BotInterface> botList, double zoom) {
        argumentCheckerService.checkNotNullObjects(targetList, botList);
        argumentCheckerService.checkGraterThanZeroValues(zoom);

        this.consoleGraphicService.printSimulationPlane(targetList, botList, zoom);
    }
}
