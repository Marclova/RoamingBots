package interfaces;

import java.util.ArrayList;

/**
 * Interface for bots' base behavior
 */
public interface BotInterface {

    public ArrayList<ProgramInterface> getProgramList();

    //coordinates (getters and setters)
    public double getXPosition();
    public double getYPosition();
    public void setXPosition(double xCoordinate);
    public void setYPosition(double yCoordinate);

    //direction and speed (getters and setters)
    public double getDirectionAngle();  //0° = right, 90° = up, 180° = left, 270° = down
    public double getSpeed();
    public double getMovementTimer();
    public String getFollowingLabel();
    public double getFollowingDistance();
    public void setDirectionAngle(double degrees);
    public void setSpeed(double speed);
    public void setMovementTimer(double seconds);
    public void setFollowingLabel(String labelToFollow);
    public void setFollowingDistance(double followingDistance);

    //signal emission
    public String getSignalLabel();
    public boolean IsEmittingSignal();
    public void setSignalLabel(String labelToEmit);
    public void setEmittingSignal(boolean isEmitting);
    

    /**
     * Sets the bot speed and angleDirection. Also the movementTimer is set to 1.
     * At least one of the two parameters x and y must be different from 0.
     * 
     * @param x The x vector for movement direction (value between -1 and 1)
     * @param y The y vector for movement direction (value between -1 and 1)
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if the method is executed correctly. False otherwise.
     */
    public boolean setMove(double x, double y, double speed);

    /**
     * Sets the bot speed and angleDirection. Also the movementTimer is set to 1.
     * 
     * @param x1 The lower value of the x vector's for the interval (value between -1 and 1)
     * @param y1 The lower value of the y vector's for the interval (value between -1 and 1)
     * @param x2 The higher value of the x vector's for the interval (value between "x1" and 1)
     * @param y2 The higher value of the y vector's for the interval (value between "y1" and 1)
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if the method is executed correctly. False otherwise.
     */
    public boolean setMoveRandom(double x1, double y1, double x2, double y2, double speed);

    /**
     * Sets the bot in a direction so that following one or more bots within the given distance
     *      which is emitting the corresponding label for one simulation cycle.
     * 
     * @param Label The label identifier to find.
     * @param dist The distance within research.
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if the method is executed correctly. False otherwise.
     */
    public boolean setFollow(String Label, double dist, double speed, ArrayList<BotInterface> botList);

    /**
     * Checks if the bot is currently detecting the label that it is supposed to follow.
     * 
     * @return True if the "followingLabel" has been detected in range. False otherwise.
     */
    public boolean isDetectingLabel(ArrayList<BotInterface> botList, String labelToDetect);

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
     * Moves the bot depending on its own settings.
     * 
     * @param movementTime The time given to move (0 < value <= 1).
     * @return True if the bot has moved.
     */
    public boolean proceed(double movementTime);

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
