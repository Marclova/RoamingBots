package interfaces;

import java.util.ArrayList;

import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

/**
 * It contains and executes all the elements of the simulation.
 */
public interface SimulationManagerInterface {

    //Getters and setters
    public ArrayList<? extends CartesianAreaInterface> getTargetList();
    public BotManagerInterface getBotManager();
    public ProgramManagerInterface getProgramManager();
    public void setBotManager(BotManagerInterface botM);
    public void setProgramManager(ProgramManagerInterface progM);

    //Duration of a simulation cycle expressed in seconds (getters and setters)
    public double getExecutionTimeCycle();
    public void setExecutionTimeCycle(double t);

    /**
     * Adds the given target into the targetList
     * 
     * @param target The target to add.
     */
    public void createTarget(CartesianAreaInterface target);

    /**
     * Adds the given cartesian shape into the "targetList".
     * 
     * @param fileAddress The location of the file in the local file system.
     * @return The number of targets that has been created.
     */
    public int createTargetsFromTxtFile(String fileAddress);

    /**
     * Starts the simulation with the current settings for the given amount of time.
     * 
     * @param progressionTime The time in seconds in witch
     * @param coolDownTime The time in seconds of pause between every new status update.
     */
    public void simulate(double progressionTime, double coolDownTime);
}
