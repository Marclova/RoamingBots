package classes.bots;

import java.util.ArrayList;

import interfaces.bots.BotInterface;
import interfaces.programs.ProgramInterface;

public class Bot implements BotInterface {

    private ArrayList<ProgramInterface> programList = new ArrayList<>();

    //position
    private double xPosition;
    private double yPosition;

    //movement
    private double directionAngle = 0;
    private double speed = 0;
    private double movementTimer = 0;
    private String labelToFollow = "";
    private double followingDistance = 0;

    //signal emission
    boolean IsEmittingSignal = false;
    String labelToEmit = "";

    public Bot(double xCoordinate, double yCoordinate) {

    }

    @Override
    public ArrayList<ProgramInterface> getProgramList() {
        return this.programList;
    }

    @Override
    public <P extends ProgramInterface> void addProgram(P program) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProgram'");
    }

    @Override
    public void removeFirstProgram() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirstProgram'");
    }

    @Override
    public double getXPosition() {
        return this.xPosition;
    }

    @Override
    public double getYPosition() {
        return this.yPosition;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IsEmittingSignal'");
    }

    @Override
    public boolean isDetectingLabel(ArrayList<BotInterface> botList, String labelToDetect) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDetectingLabelToFollow'");
    }

    @Override
    public void setXPosition(double xCoordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setXPosition'");
    }

    @Override
    public void setYPosition(double yCoordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setYPosition'");
    }

    @Override
    public void setDirectionAngle(double degrees) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDirectionAngle'");
    }

    @Override
    public void setSpeed(double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSpeed'");
    }

    @Override
    public void setMovementTimer(double seconds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMovementTimer'");
    }

    @Override
    public void setLabelToFollow(String labelToFollow) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFollowingLabel'");
    }

    @Override
    public void setFollowingDistance(double followingDistance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFollowingDistance'");
    }

    @Override
    public void setLabelToEmit(String labelToEmit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSignalLabel'");
    }

    @Override
    public void setEmittingSignal(boolean isEmitting) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmittingSignal'");
    }

    @Override
    public boolean setMove(double xVector, double yVector, double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMove'");
    }

    @Override
    public boolean setMoveRandom(double xVector1, double yVector1, double xVector2, double yVector2, double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMoveRandom'");
    }

    @Override
    public boolean setFollow(String Label, double dist, double speed, ArrayList<BotInterface> botList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFollow'");
    }

    @Override
    public boolean setContinueMotion(double seconds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setContinueMotion'");
    }

    @Override
    public boolean stopMotion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopMotion'");
    }

    @Override
    public boolean proceed(double movementTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'proceed'");
    }

    @Override
    public boolean startEmittingSignalLabel(String label) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startEmittingSignalLabel'");
    }

    @Override
    public boolean stopEmittingSignalLabel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopEmittingSignalLabel'");
    }
}
