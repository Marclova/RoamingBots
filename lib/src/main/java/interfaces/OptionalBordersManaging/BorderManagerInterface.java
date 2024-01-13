package interfaces.OptionalBordersManaging;

import java.util.ArrayList;

import interfaces.BotInterface;

public interface BorderManagerInterface {
    
    double lowerX = -5;
    double lowerY = -5;
    double higherX = 5;
    double higherY = 5;

    /**
     * Resolves the problem concerning the bots exiting from the simulation's borders.
     * 
     * @param botList The botList in which bots may be going too far.
     * @return A modified disposition where the borders problem is solved.
     */
    public ArrayList<BotInterface> manageBotsOnBorders(ArrayList<BotInterface> botList);
}