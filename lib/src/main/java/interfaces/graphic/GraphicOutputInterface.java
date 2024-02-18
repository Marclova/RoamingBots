package interfaces.graphic;

import java.util.ArrayList;

import interfaces.bots.BotInterface;
import interfaces.targets.CartesianAreaInterface;

/**
 * The class implementing this interface will have the responsibility to provide the graphic component.
 */
public interface GraphicOutputInterface {
    
    /**
     * Prints on console the current simulation status.
     * 
     * @param targetList The cartesian areas to represent.
     * @param botList The bots to represent.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    public void printSimulationPlane(ArrayList<CartesianAreaInterface> targetList, ArrayList<BotInterface> botList, double zoom);
}
