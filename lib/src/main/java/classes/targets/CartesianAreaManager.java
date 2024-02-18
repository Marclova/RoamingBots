package classes.targets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import classes.services.ArgumentCheckerService;
import classes.services.containers.Coordinates;
import interfaces.targets.CartesianAreaInterface;
import interfaces.targets.CartesianAreaManagerInterface;

/**
 * Class responsible to create targets.
 */
public class CartesianAreaManager implements CartesianAreaManagerInterface {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    private ArrayList<CartesianAreaInterface> targetList = new ArrayList<>();

    @Override
    public ArrayList<CartesianAreaInterface> getTargetList() {
        return this.targetList;
    }

    @Override
    public void createTarget(CartesianAreaInterface target) {
        argumentCheckerService.checkNotNullObjects(target);

        this.targetList.add(target);
    }

    @Override
    public ArrayList<CartesianAreaInterface> createTargetsFromTxtFile(String fileName) throws FileNotFoundException {
        argumentCheckerService.checkNotEmptyStrings(fileName);

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

    //Private methods

    /**
     * Accepts a line from the read file.
     *  If the string stream respects the format, a new cartesian area is created.
     * 
     * @param stringArray The string array containing the words in one of the read file's line.
     * @return The created object as interface type. Null if any error has been caught.
     */
    private CartesianAreaInterface createSpecificCartesianAreaFromText(String[] stringArray) {
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
