package classes.programs;

import java.util.ArrayList;

import classes.services.abstractServices.ArgumentChecker;
import functionalInterfaces.BotCommand;
import interfaces.CartesianAreaInterface;
import interfaces.bots.BotInterface;
import interfaces.programs.ProgramInterface;
import interfaces.programs.ProgramManagerInterface;
import interfaces.programs.expirationCheckRequirements.BotListExpirationCheck;
import interfaces.programs.expirationCheckRequirements.NoArgsExpirationCheck;
import interfaces.programs.expirationCheckRequirements.TargetListExpirationCheck;

/**
 * Class responsible to create and insert program into bots, execute them and delete them.
 */
public class ProgramManager extends ArgumentChecker implements ProgramManagerInterface {    

    @Override
    public void deleteExpiredAndThenExecuteAllPrograms(ArrayList<BotInterface> botList,
                                                    ArrayList<CartesianAreaInterface> targetList) {
        this.checkNotNullObjects(botList, targetList);

        for (BotInterface bot : botList) {
            int botProgramsCount = bot.getProgramList().size();

            if(botProgramsCount == 0) //no elements to operate on
            {
                continue;
            }
            if(this.botActiveProgramIsExpired(bot, botList, targetList))
            {
                bot.removeActiveProgram();
                if(botProgramsCount == 1) //no more elements left
                {
                    continue;
                }
            }
            bot.getActiveProgram().executeTasks(bot);
        }
    }

    @Override
    public RepeatingProgram createRepeatingProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, int repetitions) {
        this.checkNotNullObjects(botToProgram,taskList);
        this.checkGraterThanZeroValues(repetitions);

        return ( botToProgram.addProgram(new RepeatingProgram(taskList, repetitions)) );
    }

    @Override
    public InfiniteProgram createInfiniteProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList) {
        this.checkNotNullObjects(botToProgram,taskList);

        return ( botToProgram.addProgram(new InfiniteProgram(taskList)) );
    }

    @Override
    public TargetProgram createTargetProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList,
                                                String targetLabelToReach) {
        this.checkNotNullObjects(botToProgram,taskList);
        this.checkNotEmptyStrings(targetLabelToReach);

        return ( botToProgram.addProgram(new TargetProgram(taskList, targetLabelToReach)) );
    }

    @Override
    public LabelProgram createLabelProgram(BotInterface botToProgram, ArrayList<BotCommand> taskList, String labelToDetect,
                                            double detectionDistance) {
        this.checkNotNullObjects(botToProgram,taskList);
        this.checkNotEmptyStrings(labelToDetect);
        this.checkGraterThanZeroValues(detectionDistance);

        return ( botToProgram.addProgram(new LabelProgram(taskList, labelToDetect, detectionDistance)) );
    }
    
    /**
     * Checks if the given bot's active program is expired.
     * 
     * @param bot Bot owning the program to check.
     * @param botList Parameter needed for expiration check.
     * @param targetList Parameter needed for expiration check.
     * @return True if the bot's active program is expired. False otherwise.
     */
    private boolean botActiveProgramIsExpired(BotInterface bot,
                                            ArrayList<BotInterface> botList,
                                            ArrayList<CartesianAreaInterface> targetList) { //code smell which lowers slightly the code's extendibility
        this.checkNotNullObjects(bot, botList, targetList);

        ProgramInterface activeProgram = bot.getActiveProgram();
        if(activeProgram instanceof NoArgsExpirationCheck)
        {
            return ((NoArgsExpirationCheck)activeProgram).isExpired();
        }
        else if(activeProgram instanceof BotListExpirationCheck)
        {
            return ((BotListExpirationCheck)activeProgram).isExpired(bot.getCoordinates(), botList);
        }
        else if(activeProgram instanceof TargetListExpirationCheck)
        {
            return ((TargetListExpirationCheck)activeProgram).isExpired(bot.getCoordinates(), targetList);
        }

        throw new ClassCastException("The given program class does not implement any of the expected 'expiration check interfaces'");
    }
}
