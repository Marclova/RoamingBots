package classes;

import java.util.ArrayList;

import interfaces.BotInterface;
import interfaces.BotManagerInterface;
import interfaces.CartesianArea;
import interfaces.ProgramManagerInterface;
import interfaces.SimulationManagerInterface;

public class SimulationManager implements SimulationManagerInterface {

    @Override
    public ArrayList<BotInterface> getBotList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBotList'");
    }

    @Override
    public ArrayList<? extends CartesianArea> getTargetList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetList'");
    }

    @Override
    public BotManagerInterface getBotManager() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBotManager'");
    }

    @Override
    public ProgramManagerInterface getProgramManager() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProgramManager'");
    }

    @Override
    public void setBotManager(BotManagerInterface botM) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBotManager'");
    }

    @Override
    public void setProgramManager(ProgramManagerInterface progM) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setProgramManager'");
    }

    @Override
    public double getExecutionTimeCycle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getExecutionTimeCycle'");
    }

    @Override
    public void setExecutionTimeCycle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setExecutionTimeCycle'");
    }

    @Override
    public void createBotManager(BotManagerInterface botM) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBotManager'");
    }

    @Override
    public void createProgramManager(ProgramManagerInterface progM) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProgramManager'");
    }

    @Override
    public <C extends CartesianArea> boolean createTarget(C target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTarget'");
    }

    @Override
    public boolean createBot(double x, double y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBot'");
    }

    @Override
    public boolean createBot(BotInterface botToAdd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBot'");
    }

    @Override
    public boolean createBot(ArrayList<BotInterface> botListToAdd) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBot'");
    }

    @Override
    public boolean createRandomBots(int quantity, double x1, double y1, double x2, double y2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRandomBots'");
    }

    @Override
    public void simulate(double progressionTime, double coolDownTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'simulate'");
    }
    
}
