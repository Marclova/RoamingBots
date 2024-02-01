package classes.programs;

import java.util.ArrayList;

import functionalInterfaces.BotCommand;
import interfaces.CartesianArea;
import interfaces.bots.BotInterface;
import interfaces.programs.ProgramManagerInterface;

public class ProgramManager implements ProgramManagerInterface {    

    @Override
    public void deleteExpiredProgramsAndThenExecute(ArrayList<BotInterface> botList,
            ArrayList<CartesianArea> targetList) {
        // TODO Auto-generated method stub
        this.executeBotProgram(null, botList, targetList);
        throw new UnsupportedOperationException("Unimplemented method 'deleteExpiredProgramsAndThenExecute'");
    }

    private void executeBotProgram(BotInterface botToCheck, ArrayList<BotInterface> botList,
            ArrayList<CartesianArea> targetList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeBotProgram'");
    }

    @Override
    public RepeatingProgram createRepeatingProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, int repetitions) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRepeatingProgram'");
    }

    @Override
    public InfiniteProgram createInfiniteProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createInfiniteProgram'");
    }

    @Override
    public TargetProgram createTargetProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList,
            String targetLabelToReach) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTargetProgram'");
    }

    @Override
    public LabelProgram createLabelProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, String labelToDetect,
            double detectionDistance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createLabelProgram'");
    }
    
}
