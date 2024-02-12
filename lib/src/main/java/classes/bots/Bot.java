package classes.bots;

import java.util.ArrayList;
import java.util.Arrays;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import interfaces.bots.BotInterface;
import interfaces.programs.ProgramInterface;

/**
 * A bot moving around the simulation plane.
 *  It's behavior is highly programmable by adding programs into it.
 */
public class Bot extends ArgumentChecker implements BotInterface {

    private ArrayList<ProgramInterface> programList = new ArrayList<>();

    //position
    private Coordinates coordinates;

    //movement
    private double directionAngle = 0;
    private double speed = 0;
    private double movementTimer = 0;
    private String labelToFollow = "";
    private double followingDistance = 0;

    //signal emission
    boolean IsEmittingSignal = false;
    String labelToEmit = "";

    public Bot(Coordinates coordinates) {
        this.checkNotNullObjects(coordinates);

        this.coordinates = coordinates;
    }

    @Override
    public ArrayList<ProgramInterface> getProgramList() {
        return this.programList;
    }

    @Override
    public ProgramInterface getActiveProgram() {
        if(this.programList.isEmpty())
        {
            return null;
        }
        return this.programList.get(0);
    }

    @Override
    public <P extends ProgramInterface> P addProgram(P program) {
        this.checkNotNullObjects(program);

        if(this.programList.add(program))
        {
            return program;
        }
        return null;
    }

    @Override
    public boolean removeActiveProgram() {
        if(this.programList.isEmpty())
        {
            return false;
        }
        this.programList.remove(0);
        return true;
    }

    @Override
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    @Override
    public double getDirectionAngle() {
        return this.directionAngle;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public double getMovementTimer() {
        return this.movementTimer;
    }

    @Override
    public String getLabelToFollow() {
        return this.labelToFollow;
    }

    @Override
    public double getFollowingDistance() {
        return this.followingDistance;
    }

    @Override
    public String getLabelToEmit() {
        return this.labelToEmit;
    }

    @Override
    public boolean IsEmittingSignal() {
        return this.IsEmittingSignal;
    }

    @Override
    public boolean isDetectingLabel(ArrayList<BotInterface> botList, String labelToDetect, double detectingDistance) {
        this.checkNotNullObjects(botList);
        this.checkNotEmptyStrings(labelToDetect);
        this.checkGraterThanZeroValues(detectingDistance);

        for (BotInterface bot : botList) {
            if(bot == this)
            {
                continue;
            }

            if(bot.IsEmittingSignal() && bot.getLabelToEmit().equals(labelToDetect) &&
                bot.getCoordinates().calculateDistanceFrom(this.coordinates) <= detectingDistance)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.checkNotNullObjects(coordinates);

        this.coordinates = coordinates;
    }

    @Override
    public Coordinates incrementCoordinates(Coordinates relativeCoordinatesToSum) {
        this.checkNotNullObjects(relativeCoordinatesToSum);

        this.setCoordinates(new Coordinates(this.coordinates.x + relativeCoordinatesToSum.x,
                                            this.coordinates.y + relativeCoordinatesToSum.y));
        return this.coordinates;
    }

    @Override
    public void setDirectionAngle(double degrees) {
        this.checkInsideIntervalValues(0, (360 - 0.00000001), degrees); //accuracy of 10^-8

        this.directionAngle = degrees;
    }

    @Override
    public void setSpeed(double speed) {
        this.checkZeroOrHigherValues(speed);

        this.speed = speed;
    }

    @Override
    public void setMovementTimer(double seconds) {
        this.checkZeroOrHigherValues(seconds);

        this.movementTimer = seconds;
    }

    @Override
    public void setLabelToFollow(String labelToFollow) {
        this.checkNotEmptyStrings(labelToFollow);

        this.labelToFollow = labelToFollow;
    }

    @Override
    public void setFollowingDistance(double followingDistance) {
        this.checkGraterThanZeroValues(followingDistance);

        this.followingDistance = followingDistance;
    }

    @Override
    public boolean setMove(DirectionalVectors dirVectors, double speed) {
        this.checkNotNullObjects(dirVectors);
        this.checkGraterThanZeroValues(speed);

        double newAngle = dirVectors.getDirectionalDegrees();
        if(newAngle == this.directionAngle && speed == this.speed)
        {
            return false;
        }
        this.setDirectionAngle(newAngle);
        this.setSpeed(speed);
        return true;
    }

    @Override
    public boolean setMoveRandom(DirectionalVectors dirVectors1, DirectionalVectors dirVectors2, double speed) {
        this.checkNotNullObjects(dirVectors1,dirVectors2);
        this.checkGraterThanZeroValues(speed);
        if(dirVectors1.xVector > dirVectors2.xVector ||
            dirVectors1.yVector > dirVectors2.yVector)
        {
            throw new IllegalArgumentException();
        }

        double randomXVector = 0;
        double randomYVector = 0;
        while( randomXVector == 0 && randomYVector == 0 ) //The couple (0,0) must not be generated
        {
            randomXVector = Math.random() * (dirVectors2.xVector - dirVectors1.xVector) + dirVectors1.xVector;
            randomYVector = Math.random() * (dirVectors2.yVector - dirVectors1.yVector) + dirVectors1.yVector;
        }
        return this.setMove(new DirectionalVectors(randomXVector, randomYVector), speed);
    }

    @Override
    public boolean setFollow(String label, double dist, double speed, ArrayList<BotInterface> botList) {
        this.checkNotEmptyStrings(label);
        this.checkGraterThanZeroValues(dist, speed);
        this.checkNotNullObjects(botList);

        boolean flag = !(this.labelToFollow.equals(label) && this.followingDistance == dist &&
                        this.speed == speed);

        this.setLabelToFollow(label);
        this.setFollowingDistance(dist);
        this.setSpeed(speed);
        ArrayList<BotInterface> botToFollowList = this.getBotsToFollow(botList);
        Coordinates botToFollowAverageCoordinates = this.calculateAverageBotsCoordinates(botToFollowList);
        
        if(botToFollowAverageCoordinates != null)
        {
            double oldAngle = this.directionAngle;
            this.setDirectionAngle(this.calculateDirectionAngleTowardsCoordinates(botToFollowAverageCoordinates));
            return ( flag || (oldAngle != this.directionAngle) );
        }
        return flag;
    }

    @Override
    public boolean setContinueMotion(double seconds) {
        this.checkGraterThanZeroValues(seconds);

        if(this.movementTimer == seconds)
        {
            return false;
        }
        this.movementTimer = seconds;
        return true;
    }

    @Override
    public boolean stopMotion() {
        if(this.speed == 0 && movementTimer == 0)
        {
            return false;
        }
        this.speed = 0;
        this.movementTimer = 0;
        return true;
    }

    @Override
    public boolean startEmittingSignalLabel(String label) {
        this.checkNotEmptyStrings(label);

        if(this.labelToEmit.equals(label) && this.IsEmittingSignal == true)
        {
            return false;
        }
        this.labelToEmit = label;
        this.IsEmittingSignal = true;
        return true;
    }

    @Override
    public boolean stopEmittingSignalLabel() {
        if(this.labelToEmit.isEmpty() && this.IsEmittingSignal == false)
        {
            return false;
        }
        this.labelToEmit = "";
        this.IsEmittingSignal = false;
        return true;
    }

    //Private methods

    /**
     * Looks for all the detectable bots within the following distance with the right emitting label.
     * 
     * @return A list of detected bots that this bot may follow.
     */
    private ArrayList<BotInterface> getBotsToFollow(ArrayList<BotInterface> botList) {
        this.checkNotNullObjects(botList);

        ArrayList<BotInterface> listToReturn = new ArrayList<>();
        for (BotInterface bot : botList) {
            if(bot.equals(this))
            {
                continue;
            }
            if(this.isDetectingLabel(new ArrayList<>(Arrays.asList(bot)), this.labelToFollow, this.followingDistance))
            {
                listToReturn.add(bot);
            }
        }
        return listToReturn;
    }

    /**
     * Calculates the average coordinates between all the given bots. 
     * 
     * @param botList The list of bots to consider.
     * @return The average coordinates.
     */
    private Coordinates calculateAverageBotsCoordinates(ArrayList<BotInterface> botList) {
        this.checkNotNullObjects(botList);
        if(botList.isEmpty())
        {
            return null;
        }

        int n = botList.size();
        double averageX = 0;
        double averageY = 0;
        for (BotInterface bot : botList) {
            Coordinates botCoordinates = bot.getCoordinates();
            averageX += botCoordinates.x;
            averageY += botCoordinates.y;
        }
        averageX /= n;
        averageY /= n;
        
        return new Coordinates(averageX, averageY);
    }

    /**
     * calculates the direction angle expressed in degrees which will consent the bot's
     *  to point towards the given coordinates position.
     * 
     * @param coordinatesToPoint The coordinates to point to.
     * @return The angle with which the bot can point to the given coordinates.
     */
    private double calculateDirectionAngleTowardsCoordinates(Coordinates coordinatesToPoint) {
        this.checkNotNullObjects(coordinatesToPoint);

        double xVector = coordinatesToPoint.x - this.coordinates.x;
        double yVector = coordinatesToPoint.y - this.coordinates.y;
        while (Math.abs(xVector) > 1 || Math.abs(yVector) > 1) //directional vectors must be between 1 and -1
        {
            xVector /= 2;
            yVector /= 2;
        }
        return (new DirectionalVectors(xVector, yVector).getDirectionalDegrees());
    }
}
