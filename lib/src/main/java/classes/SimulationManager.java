package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
import classes.services.graphic.GraphicServiceManager;
import classes.targets.Circle;
import classes.targets.Rectangle;
import interfaces.CartesianAreaInterface;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

/**
 * Responsible to save the simulation's status and to call all the main methods.
 */
public class SimulationManager extends ArgumentChecker implements SimulationManagerInterface {

    GraphicServiceManager graphicServiceManager = new GraphicServiceManager();

    private ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
    private BotManagerInterface botManager;
    private ProgramManagerInterface programManager;

    public SimulationManager(BotManagerInterface botM, ProgramManagerInterface progM) {
        this.checkNotNullObjects(botM, progM);

        this.botManager = botM;
        this.programManager = progM;
    }

    @Override
    public ArrayList<CartesianAreaInterface> getTargetList() {
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
    public void createTarget(CartesianAreaInterface target) {
        this.checkNotNullObjects(target);

        this.targetList.add(target);
    }

    @Override
    public ArrayList<CartesianAreaInterface> createTargetsFromTxtFile(String fileName) throws FileNotFoundException {
        this.checkNotEmptyStrings(fileName);

        ArrayList<CartesianAreaInterface> listToReturn = new ArrayList<>();
        File file = new File("C:\\Users\\PC\\source\\repos\\RoamingBots\\lib\\src\\main\\inputFiles\\"+fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] resultArray = scanner.nextLine().split(" ");
            CartesianAreaInterface target = this.createSpecificCartesianAreaFromText(resultArray);
            if (target != null)
            {
                listToReturn.add(target);
            }
        }
        scanner.close();
        this.targetList.addAll(listToReturn);
        return listToReturn;
    }

    @Override
    public void simulate(double progressionTime, double executionDuration, double coolDownTime, double zoom) {
        this.checkGraterThanZeroValues(progressionTime, executionDuration);
        this.checkZeroOrHigherValues(coolDownTime);
        this.checkGraterThanZeroValues(zoom);

        ArrayList<BotInterface> botList = this.botManager.getBotList();
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
                this.programManager.deleteExpiredAndThenExecuteProgram(bot, botList, this.targetList);
                this.botManager.moveBot(bot, executionDuration);
            }

            try {
                Thread.sleep((long)(coolDownTime * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.graphicServiceManager.projectConsoleFrame(this.targetList, this.botManager.getBotList(), zoom);
        }
    }

    /**
     * Accepts a line from the read file.
     *  If the string stream respects the format, a new cartesian area is created.
     * 
     * @param stringArray The string array containing the words in one of the read file's line.
     * @return The created object as interface type. Null if any error has been caught.
     */
    public CartesianAreaInterface createSpecificCartesianAreaFromText(String[] stringArray) {
        try {
            String label = stringArray[0];
            double x = Double.valueOf(stringArray[2]);
            double y = Double.valueOf(stringArray[3]);
            Coordinates coordinates = new Coordinates(x, y);
            switch (stringArray[1]) {
                case "Circle":
                    double radius = Double.valueOf(stringArray[4]);
                    return new Circle(coordinates, label, radius);

                case "Rectangle":
                    double width = Double.valueOf(stringArray[4]);
                    double height = Double.valueOf(stringArray[5]);
                    return new Rectangle(coordinates, label, width, height);
            
                default:
                    return null;
            }
        }catch(Exception e) {
            return null;
        }
    }
}
