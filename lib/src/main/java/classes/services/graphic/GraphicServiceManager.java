package classes.services.graphic;

import java.util.ArrayList;

import classes.services.abstractServices.ArgumentChecker;
import interfaces.CartesianAreaInterface;
import interfaces.bots.BotInterface;

public class GraphicServiceManager extends ArgumentChecker {

    ConsoleGraphicService consoleGraphicService = new ConsoleGraphicService();
    
    /**
     * Prints on console the current simulation status.
     * 
     * @param targetList The cartesian areas to represent.
     * @param botList The bots to represent.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    public void projectConsoleFrame(ArrayList<CartesianAreaInterface> targetList, ArrayList<BotInterface> botList, double zoom) {
        this.checkNotNullObjects(targetList, botList);
        this.checkGraterThanZeroValues(zoom);

        this.consoleGraphicService.printSimulationPlane(targetList, botList, zoom);
    }
}
