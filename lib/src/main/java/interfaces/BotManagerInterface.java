package interfaces;

// import java.util.ArrayList;

/**
 * The class extending this interface will have the responsibility to create and move bots
 *      within the simulation area.  
 */
public interface BotManagerInterface {

    //getters and setters
    public <S extends SimulationManagerInterface> S getSimulationManager();
    public void setSimulationManager(SimulationManagerInterface simM);

    /**
     * Moves the bots depending on their own orientation, speed and movementTimer.
     *      
     * @param movementTime The time given to every bot to move. (max. 1 second)
     * @return True if the bots' disposition has been changed. False if no bot has moved.
     */
    public boolean moveAllBots(double movementTime);
}
