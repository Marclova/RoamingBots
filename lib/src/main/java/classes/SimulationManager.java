package classes;

import java.util.ArrayList;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.graphic.GraphicServiceManager;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;
import interfaces.targets.CartesianAreaInterface;
import interfaces.targets.CartesianAreaManagerInterface;

/**
 * Responsible to save the simulation's status and to call all the main methods.
 */
public class SimulationManager extends ArgumentChecker implements SimulationManagerInterface {

    GraphicServiceManager graphicServiceManager = new GraphicServiceManager();

    private BotManagerInterface botManager;
    private ProgramManagerInterface programManager;
    private CartesianAreaManagerInterface cartesianAreaManager;

    public SimulationManager(BotManagerInterface botM, ProgramManagerInterface progM, CartesianAreaManagerInterface cartAreaM) {
        this.checkNotNullObjects(botM, progM, cartAreaM);

        this.botManager = botM;
        this.programManager = progM;
        this.cartesianAreaManager = cartAreaM;
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
    public CartesianAreaManagerInterface getCartesianAreaManager() {
        return this.cartesianAreaManager;
    }

    @Override
    public void setBotManager(BotManagerInterface botM) {
        this.checkNotNullObjects(botM);

        this.botManager = botM;
    }

    @Override
    public void setProgramManager(ProgramManagerInterface progM) {
        this.checkNotNullObjects(progM);

        this.programManager = progM;
    }

    @Override
    public void setCartesianAreaManager(CartesianAreaManagerInterface cartAreaM) {
        this.checkNotNullObjects(cartAreaM);

        this.cartesianAreaManager = cartAreaM;
    }

    @Override
    public void simulate(double progressionTime, double executionDuration, double coolDownTime, double zoom) {
        this.checkGraterThanZeroValues(progressionTime, executionDuration);
        this.checkZeroOrHigherValues(coolDownTime);
        this.checkGraterThanZeroValues(zoom);

        ArrayList<BotInterface> botList = this.botManager.getBotList();
        ArrayList<CartesianAreaInterface> targetList = this.cartesianAreaManager.getTargetList();
        while (progressionTime > 0) {
            if(executionDuration <= progressionTime)
            {
                progressionTime -= executionDuration;
            }
            else
            {
                executionDuration = progressionTime;
                progressionTime = 0;
            }

            for (BotInterface bot : botList) {
                this.programManager.deleteExpiredAndThenExecuteProgram(bot, botList, targetList);
                this.botManager.moveBot(bot, executionDuration);
            }

            try {
                Thread.sleep((long)(coolDownTime * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.graphicServiceManager.projectConsoleFrame(targetList, this.botManager.getBotList(), zoom);
        }
    }
}
