package classes.services.graphic;

import java.util.ArrayList;

import interfaces.CartesianAreaInterface;
import interfaces.bots.BotInterface;

public class GraphicServiceManager {

    ConsoleGraphicService consoleGraphicService = new ConsoleGraphicService();
    
    /**
     * Prints on console the current simulation status.
     * 
     * @param targetList The cartesian areas to represent.
     * @param botList The bots to represent.
     */
    public void projectConsoleFrame(ArrayList<CartesianAreaInterface> targetList, ArrayList<BotInterface> botList) {

        this.consoleGraphicService.printSimulationPlane(targetList, botList);
    }
}
