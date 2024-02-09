package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import classes.bots.BotManager;
import classes.programs.ProgramManager;
import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
import classes.targets.Circle;
import classes.targets.Rectangle;
import interfaces.CartesianAreaInterface;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

public class SimulationManager extends ArgumentChecker implements SimulationManagerInterface {

    private ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();
    private BotManagerInterface botManager = new BotManager();
    private ProgramManagerInterface programManager = new ProgramManager();
    private double executionTimeCycle = 1.0;  //cooldown between every bot and program execution

    public SimulationManager() {}

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
    public double getExecutionTimeCycle() {
        return this.executionTimeCycle;
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
    public void setExecutionTimeCycle(double t) {
        this.checkGraterThanZeroValues(t);

        this.executionTimeCycle = t;
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
        File file = new File("C:\\Users\\PC\\source\\repos\\RoamingBots\\lib\\src\\inputFiles\\"+fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] resultArray = scanner.nextLine().split(" ");
            CartesianAreaInterface target = this.createSpecificCartesianArea(resultArray);
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
    public void simulate(double progressionTime, double executionDuration, double coolDownTime) {
        this.checkGraterThanZeroValues(progressionTime, executionDuration);
        this.checkZeroOrHigherValues(coolDownTime);

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

            this.programManager.deleteExpiredAndThenExecuteAllPrograms(this.botManager.getBotList(), this.targetList);
            this.botManager.moveAllBots(executionDuration);
            try {
                Thread.sleep((long)(coolDownTime * 1000));
                // this.wait((long)(coolDownTime * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Accepts the result of the reading of a file.
     *  If the string stream respects the format, a new cartesian area is created.
     * 
     * @param stringArray The string array containing the words in one of the read file's line.
     * @return The created object as interface type. Null if any error has been caught.
     */
    public CartesianAreaInterface createSpecificCartesianArea(String[] stringArray) {
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

    // @SuppressWarnings("unchecked")
    // private Class<? extends CartesianAreaInterface> extractClassFromString(String className) {
    //     switch (className) {
    //         case "Circle":
    //             return Circle.class;
        
    //         case "Rectangle":
    //             return Rectangle.class;

    //         default:
    //             return null;
    //     }
    // }
}
