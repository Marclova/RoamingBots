package interfaces;

import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;
import interfaces.targets.CartesianAreaManagerInterface;

/**
 * It contains and executes all the elements of the simulation.
 */
public interface SimulationManagerInterface {

    //Getters and setters
    public CartesianAreaManagerInterface getCartesianAreaManager();
    public BotManagerInterface getBotManager();
    public ProgramManagerInterface getProgramManager();
    public void setBotManager(BotManagerInterface botM);
    public void setProgramManager(ProgramManagerInterface progM);
    public void setCartesianAreaManager(CartesianAreaManagerInterface cartAreaM);

    /**
     * Runs the simulation with the current settings for the given amount of time.
     * 
     * @param progressionTime The time in seconds that the simulation will run.
     * @param executionDuration The time required for any execution to get complied.
     *                          The lower is this value, the higher will be the execution accuracy.
     * @param coolDownTime The time in seconds of pause between every new status update.
     *                      The lower is this value, the faster will be the simulation.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    public void simulate(double progressionTime, double executionDuration, double coolDownTime, double zoom);
}
