package interfaces.OptionalBordersManaging;

import java.util.ArrayList;

import interfaces.BotInterface;

/**
 * Interface for classes which implement outer borders and
 *      need to manage bots interacting with said borders.
 */
public interface HasBorders {
    
    //getters and setters
    public double getLowerX();
    public double getLowerY();
    public double getHigherX();
    public double getHigherY();
    public void setLowerX();
    public void setLowerY();
    public void setHigherX();
    public void setHigherY();


    /**
     * Resolves the problem concerning the bots exiting from the simulation's borders.
     * 
     * @param botList The botList in which bots may be going too far.
     * @return A modified disposition where the borders problem is solved.
     */
    public ArrayList<BotInterface> manageBotsOnBorders(ArrayList<BotInterface> botList);
}
