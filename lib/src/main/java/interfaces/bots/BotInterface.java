package interfaces.bots;

import java.util.ArrayList;

import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import interfaces.programs.ProgramInterface;

/**
 * Interface for bots' base behavior
 */
public interface BotInterface {

    /**
     * The first program in the bot's programList is considered "active", while the other are "in queue"
     * 
     * @return The first program in the bot's programList
     */
    public ProgramInterface getActiveProgram();
    public ArrayList<ProgramInterface> getProgramList();
    public <P extends ProgramInterface> P addProgram(P program);
    public boolean removeActiveProgram();

    //coordinates (getters and setters)
    public Coordinates getCoordinates();
    public void setCoordinates(Coordinates coordinates);
    /**
     * Increments the current bot's coordinates with the given relative coordinates.
     * 
     * @param relativeCoordinatesToSum The coordinates to sum with the bot's.
     * @return The bot's new position.
     */
    public Coordinates incrementCoordinates(Coordinates relativeCoordinatesToSum);

    //direction and speed (getters and setters)
    public double getDirectionAngle();  //0° = right, 90° = up, 180° = left, 270° = down
    public double getSpeed();
    public double getMovementTimer();
    public String getLabelToFollow();
    public double getFollowingDistance();
    public void setDirectionAngle(double degrees);
    public void setSpeed(double speed);
    public void setMovementTimer(double seconds);
    public void setLabelToFollow(String labelToFollow);
    public void setFollowingDistance(double followingDistance);

    //signal emission
    public String getLabelToEmit();
    public boolean IsEmittingSignal();
    // public void setLabelToEmit(String labelToEmit);
    // public void setEmittingSignal(boolean isEmitting);
    

    /**
     * Sets the bot speed and angleDirection. Also the movementTimer is set to 1.
     * At least one of the two parameters x and y must be different from 0.
     * 
     * @param x The x vector for movement direction (value between -1 and 1)
     * @param y The y vector for movement direction (value between -1 and 1)
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if any bot's setting has changed. False otherwise.
     */
    public boolean setMove(DirectionalVectors dirVectors, double speed);

    /**
     * Sets the bot speed and angleDirection. Also the movementTimer is set to 1.
     * 
     * @param x1 The lower value of the x vector's for the interval (value between -1 and 1)
     * @param y1 The lower value of the y vector's for the interval (value between -1 and 1)
     * @param x2 The higher value of the x vector's for the interval (value between "x1" and 1)
     * @param y2 The higher value of the y vector's for the interval (value between "y1" and 1)
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if any bot's setting has changed. False otherwise.
     */
    public boolean setMoveRandom(DirectionalVectors dirVectors1, DirectionalVectors dirVectors2, double speed);

    /**
     * Sets the bot in a direction so that following one or more bots within the given distance
     *      which is emitting the corresponding label for one simulation cycle.
     * 
     * @param Label The label identifier to find.
     * @param dist The distance within research.
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @param botList The list of all bots in the simulation plane.
     * @return True if any bot's setting has changed. False otherwise.
     */
    public boolean setFollow(String label, double dist, double speed, ArrayList<BotInterface> botList);

    /**
     * Checks if this bot is currently detecting the label that it is supposed to follow.
     * 
     * @param botList The list of all bots
     * @param labelToDetect The label that this bot has to detect from other bots
     * @param detectingDistance The distance within this bot detects the other bot during this specific scanning.
     * @return True if the label to detect has been detected by this bot from other bots in range. False otherwise.
     */
    public boolean isDetectingLabel(ArrayList<BotInterface> botList, String labelToDetect, double detectingDistance);

    /**
     * Sets a time limit for the current bot's motion.
     * 
     * @param seconds In-simulation time before the bot stops.
     * @return True if the time limit has been set or updated. False if the bot is already stopped.
     */
    public boolean setContinueMotion(double seconds);

    /**
     * Stops the current bot's motion settings.
     * 
     * @return True if the bot has been stopped. False if the bot was already stopped.
     */
    public boolean stopMotion();

    /**
     * Sets the bot's "signalLabel" and sets its "emitSignal" flag to true.
     * 
     * @param label The label identifier to set.
     * @return True if the signal has been started or changed. False if there are no changes.
     */
    public boolean startEmittingSignalLabel(String label);

    /**
     * Sets the bot's "emitSignal" flag to false.
     * 
     * @return True if the "emitSignal" flag has been changed. False if it was already set on false.
     */
    public boolean stopEmittingSignalLabel();
}
