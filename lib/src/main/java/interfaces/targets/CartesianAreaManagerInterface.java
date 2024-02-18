package interfaces.targets;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The class implementing this interface will have the responsibility to create the simulation targets.
 */
public interface CartesianAreaManagerInterface {

    public ArrayList<CartesianAreaInterface> getTargetList();

    /**
     * Adds the given target into the targetList.
     * 
     * @param target The target to add.
     */
    public void createTarget(CartesianAreaInterface target);

    /**
     * Adds the given cartesian shape into the "targetList" reading a txt file.
     * 
     * @param fileAddress The name of the file in the local file system to read.
     * @return The list of targets that has been created.
     */
    public ArrayList<CartesianAreaInterface> createTargetsFromTxtFile(String fileName) throws FileNotFoundException;
}