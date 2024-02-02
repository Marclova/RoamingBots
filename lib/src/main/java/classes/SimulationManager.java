package classes;

import java.util.ArrayList;

import classes.bots.Bot;
import classes.bots.BotManager;
import classes.programs.ProgramManager;
import interfaces.CartesianAreaInterface;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

public class SimulationManager implements SimulationManagerInterface {

    private ArrayList<BotInterface> botList = new ArrayList<>();
    private ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
    private BotManagerInterface botManager = new BotManager();
    private ProgramManagerInterface programManager = new ProgramManager();
    private double executionTimeCycle = 1.0;  //cooldown between every bot and program execution

    public SimulationManager() {}

    public SimulationManager(BotManagerInterface botM, ProgramManagerInterface progM) {
        this.botManager = botM;
        this.programManager = progM;
    }

    @Override
    public ArrayList<BotInterface> getBotList() {
        return this.botList;
    }

    @Override
    public ArrayList<? extends CartesianAreaInterface> getTargetList() {
        return this.targetList;
    }

    @Override
    public BotManagerInterface getBotManager() {
        return this.botManager;
    }

    @Override
    public ProgramManagerInterface getProgramManager() {
        return this.programManager;
    }

    @Override
    public double getExecutionTimeCycle() {
        return this.executionTimeCycle;
    }

    @Override
    public void setBotManager(BotManagerInterface botM) {
        this.botManager = botM;
    }

    @Override
    public void setProgramManager(ProgramManagerInterface progM) {
        this.programManager = progM;
    }

    @Override
    public void setExecutionTimeCycle(double t) {
        this.executionTimeCycle = t;
    }

    @Override
    public boolean createTargetsFromTxtFile(String fileAddress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTarget'");
    }

    @Override
    public boolean createBot(double x, double y) {
        return this.createBot(new Bot(x, y));
    }

    @Override
    public boolean createBot(BotInterface botToAdd) {
        return this.botList.add(botToAdd);
    }

    @Override
    public boolean createBot(ArrayList<BotInterface> botListToAdd) {
        return this.botList.addAll(botListToAdd);
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

    // public void simulate() {
    //     this.simulate(1, 0.1);
    // }
    
}
