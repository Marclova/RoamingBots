package interfaces.OptionalBordersManaging;

import java.util.ArrayList;

import interfaces.BotInterface;

/**
 * Interface for classes which implement outer borders and
 *      need to manage bots interacting with said borders.
 */
public interface HasBorders {
    
    //Responsible to manage the bots nearby the borders
    BorderManagerInterface borderManager = null;


    /**
     * Uses the "borderManager" to manage the bots which are about to exit the borders.
     * 
     * @param botList The list of bots to manage.
     * @return A modified disposition where the problem is solved if "borderManager" isn't null.
     *              Returns "botList" otherwise.
     */
    default public ArrayList<BotInterface> manageBorders(ArrayList<BotInterface> botList)
    {
        if (this.borderManager != null)
        {
            return this.borderManager.manageBotsOnBorders(botList);
        }
        else
        {
            return botList;
        }
    }
}
