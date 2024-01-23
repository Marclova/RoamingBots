package classes;

import java.util.ArrayList;

import interfaces.BotInterface;
import interfaces.ProgramInterface;

public class Bot implements BotInterface {

    private ArrayList<ProgramInterface> programList = new ArrayList<>();

    private double xPosition = 0;
    private double yPosition = 0;

    private double directionAngle = 0;
    private double speed = 0;
    private double movementTimer = 0;
    private String followingLabel = "";
    private double followingDistance = 0;

    public Bot(double xCoordinate, double yCoordinate) {

    }

    @Override
    public ArrayList<ProgramInterface> getProgramList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProgramList'");
    }

    @Override
    public double getXPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getXPosition'");
    }

    @Override
    public double getYPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getYPosition'");
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
    public double getDirectionAngle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDirectionAngle'");
    }

    @Override
    public double getSpeed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpeed'");
    }

    @Override
    public double getMovementTimer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMovementTimer'");
    }

    @Override
    public String getFollowingLabel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFollowingLabel'");
    }

    @Override
    public double getFollowingDistance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFollowingDistance'");
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
    public void setFollowingLabel(String labelToFollow) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFollowingLabel'");
    }

    @Override
    public void setFollowingDistance(double followingDistance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFollowingDistance'");
    }

    @Override
    public String getSignalLabel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSignalLabel'");
    }

    @Override
    public boolean IsEmittingSignal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IsEmittingSignal'");
    }

    @Override
    public void setSignalLabel(String labelToEmit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSignalLabel'");
    }

    @Override
    public void setEmittingSignal(boolean isEmitting) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEmittingSignal'");
    }

    @Override
    public boolean setMove(double x, double y, double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMove'");
    }

    @Override
    public boolean setMoveRandom(double x1, double y1, double x2, double y2, double speed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMoveRandom'");
    }

    @Override
    public boolean setFollow(String Label, double dist, double speed, ArrayList<BotInterface> botList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFollow'");
    }

    @Override
    public boolean isDetectingLabelToFollow(ArrayList<BotInterface> botList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDetectingLabelToFollow'");
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
