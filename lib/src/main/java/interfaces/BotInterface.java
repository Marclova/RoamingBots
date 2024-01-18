package interfaces;

/**
 * Interface for bots' base behavior
 */
public interface BotInterface {

    //coordinates (getters and setters)
    public double getXPosition();
    public double getYPosition();
    public void setXPosition(double xCoordinate);
    public void setYPosition(double yCoordinate);

    //direction and speed (getters and setters)
    public double getXVector();
    public double getYVector();
    public double getSpeed();
    public void setXVector(double xVector);
    public void setYVector(double yVector);
    public void setSpeed(double speed);

    //signal emission
    public String getSignalLabel();
    public boolean IsEmittingSignal();
    public void setSignalLabel(String label);
    public void setEmittingSignal(boolean isEmitting);
    

    /**
     * Sets the bot in a direction indicated by the x and y values at the given velocity
     *      for one simulation cycle.
     * At least one of the two parameters x and y must be different from 0.
     * 
     * @param x The x vector for movement direction (value between -1 and 1)
     * @param y The y vector for movement direction (value between -1 and 1)
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if the method is executed correctly. False otherwise.
     */
    public boolean move(double x, double y, double speed);

    /**
     * Sets the bot in a random direction based on the given interval at the given velocity
     *      for one simulation cycle.
     * 
     * @param x1 The lower value of the x vector's for the interval (value between -1 and 1)
     * @param y1 The lower value of the y vector's for the interval (value between -1 and 1)
     * @param x2 The higher value of the x vector's for the interval (value between "x1" and 1)
     * @param y2 The higher value of the y vector's for the interval (value between "y1" and 1)
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if the method is executed correctly. False otherwise.
     */
    public boolean moveRandom(double x1, double y1, double x2, double y2, double speed);

    /**
     * Sets the bot's "signalLabel" and sets its "emitSignal" flag to true.
     * 
     * @param label The label identifier to set.
     * @return True if the signal has been started or changed. False if there are no changes.
     */
    public boolean signalLabel(String label);

    /**
     * Sets the bot's "emitSignal" flag to false.
     * 
     * @return True if the "emitSignal" flag has been changed. False if it was already set on false.
     */
    public boolean unsignalLabel();

    /**
     * Sets the bot in a direction so that following one ore more bots within the given distance
     *      which is emitting the corresponding label.
     * 
     * @param Label The label identifier to find.
     * @param dist The distance within research.
     * @param speed Sets the robot velocity expressed in m/s (a coordinate unit corresponds to a metre)
     * @return True if the method is executed correctly. False otherwise.
     */
    public boolean follow(String Label, double dist, double speed);

    /**
     * Sets a time limit for the current bot's motion.
     * 
     * @param seconds In-simulation time before the bot stops.
     * @return True if the time limit has been set or updated. False if the bot is already stopped.
     */
    public boolean continueMotion(double seconds);

    /**
     * Stops the current bot's motion settings.
     * 
     * @return True if the bot has been stopped. False if the bot was already stopped.
     */
    public boolean stopMotion();
}
