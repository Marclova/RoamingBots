package interfaces.bots;

import java.util.ArrayList;

import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import interfaces.programs.ProgramInterface;

/**
 * Interface for bots' essential behavior.
 */
public interface BotInterface {

    /**
     * The first program in the bot's programList is considered "active", while the other are "in queue".
     * 
     * @return The first program in the bot's programList.
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
    

    /**
     * Sets the bot's speed and angleDirection.
     * 
     * @param dirVectors The directional vectors defining the direction.
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if any bot's setting has changed. False otherwise.
     */
    public boolean setMove(DirectionalVectors dirVectors, double speed);

    /**
     * Sets the bot's speed and angleDirection.
     * 
     * @param dirVectors1 Lower directional vectors.
     * @param dirVectors2 Higher directional vectors.
     * @param speed The bot's velocity expressed in m/s.
     * @return True if any bot's setting has changed. False otherwise.
     */
    public boolean setMoveRandom(DirectionalVectors dirVectors1, DirectionalVectors dirVectors2, double speed);

    /**
     * Sets the bot's speed and angleDirection so that it can follow one or more bots within the given distance
     *      and which is emitting the corresponding label for one simulation cycle.
     *      If more bots are detected, then an average will be taken as coordinates to follow.
     * 
     * @param Label The label identifier to detect.
     * @param dist The distance within research.
     * @param speed The bot's velocity expressed in m/s.
     * @param botList The list of all bots in the simulation plane.
     * @return True if any of the bot's setting has been changed. False otherwise.
     */
    public boolean setFollow(String label, double dist, double speed, ArrayList<BotInterface> botList);

    /**
     * Checks if there are any bots emitting the given label within the given distance from this bot.
     * 
     * @param botList The list of all bots.
     * @param labelToDetect The label that this bot has to detect from other bots.
     * @param detectingDistance The scanning distance.
     * @return True if the label to detect has been detected by this bot. False otherwise.
     */
    public boolean isDetectingLabel(ArrayList<BotInterface> botList, String labelToDetect, double detectingDistance);

    /**
     * Sets a time limit for the current bot's motion.
     * 
     * @param seconds Time in seconds that the bot his going to move.
     * @return True if the time limit has been set. False if it's unchanged.
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
     * @return True if the signal emission has been started or changed. False if there are no changes.
     */
    public boolean startEmittingSignalLabel(String label);

    /**
     * Sets the bot's "emitSignal" flag to false and erases the bot's "signalToEmit".
     * 
     * @return True if the "emitSignal" flag has been changed or if the "signalToEmit" has been erased.
     *          False if nothing has changed.
     */
    public boolean stopEmittingSignalLabel();
}
