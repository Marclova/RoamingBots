package examples;

import java.util.ArrayList;

import classes.SimulationManager;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import classes.targets.Circle;
import classes.targets.Rectangle;
import functionalInterfaces.BotCommand;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;

public class DeterministicExample {
    public static void main(String[] args) {

        SimulationManager simulationManager = new SimulationManager();
        ProgramManagerInterface programManager = simulationManager.getProgramManager();
        BotManagerInterface botManager = simulationManager.getBotManager();
        ArrayList<BotInterface> botList = botManager.getBotList();

        //settings for movement directions
        DirectionalVectors leftSlightlyUp = new DirectionalVectors(-1, 0.2);
        DirectionalVectors left = new DirectionalVectors(-1, 0);


        //settings for programs
        ArrayList<BotCommand> doNothing = new ArrayList<>();

        ArrayList<BotCommand> beacon = new ArrayList<>();
        beacon.add((bot) -> bot.startEmittingSignalLabel("destination"));

        ArrayList<BotCommand> stop = new ArrayList<>();
        stop.add((bot) -> bot.stopMotion());
        
        ArrayList<BotCommand> readyToDepart = new ArrayList<>();
        readyToDepart.add((bot) -> bot.setContinueMotion(50));
        
        ArrayList<BotCommand> proceedStorm1 = new ArrayList<>();  //moves slightly upwards
        proceedStorm1.add((bot) -> bot.setMove(leftSlightlyUp, 1.1));
        proceedStorm1.add((bot) -> bot.startEmittingSignalLabel("storm1"));

        ArrayList<BotCommand> proceedStorm2 = new ArrayList<>();  //moves slower
        proceedStorm2.add((bot) -> bot.setMove(left, 1));
        proceedStorm2.add((bot) -> bot.startEmittingSignalLabel("storm2"));

        ArrayList<BotCommand> followStorm1 = new ArrayList<>();
        followStorm1.add((bot) -> bot.setFollow("storm1", 5.5, 0.4, botList));

        ArrayList<BotCommand> followStorm2 = new ArrayList<>();
        followStorm2.add((bot) -> bot.setFollow("storm2", 5.5, 0.4, botList));

        ArrayList<BotCommand> ReachDestination = new ArrayList<>();
        ReachDestination.add((bot) -> bot.setFollow("destination", 15, 1, botList));


        //creating targets
        simulationManager.createTarget(new Circle(new Coordinates(-11, -0.2), "goal", 3.55));  //the bot's goal
        simulationManager.createTarget(new Rectangle(new Coordinates(-5.7, -9), "line", 1.1, 18));
        

        //creating bots
        BotInterface goalBot = botManager.createBot(new Coordinates(-11, -0.2));  //bot inside the goal

        BotInterface stormBot1 = botManager.createBot(new Coordinates(8.6, 3.834));  //starting bot
        BotInterface stormBot2 = botManager.createBot(new Coordinates(5.8, -6.0009));

        BotInterface botToRetrieve1 = botManager.createBot(new Coordinates(-3, 3));  //bot initially waiting
        BotInterface botToRetrieve2 = botManager.createBot(new Coordinates(-3, -3));


        //assigning programs
        for (BotInterface bot : botList) {  //sets every bot so that they may start moving
            programManager.createRepeatingProgram(bot, readyToDepart, 1);
        }

        programManager.createRepeatingProgram(goalBot, beacon, 1);  //the goal bot is the destination
        
        programManager.createRepeatingProgram(stormBot1, proceedStorm1, 1); //starts moving
        programManager.createTargetProgram(stormBot1, doNothing, "line"); //waits for reaching the "line"
        programManager.createTargetProgram(stormBot1, ReachDestination, "goal"); //moves towards the goal
        programManager.createRepeatingProgram(stormBot1, stop, 1); //stops once reached for the goal

        programManager.createRepeatingProgram(stormBot2, proceedStorm2, 1); //starts moving
        programManager.createTargetProgram(stormBot2, doNothing, "line"); //waits for reaching the "line"
        programManager.createTargetProgram(stormBot2, ReachDestination, "goal"); //moves towards the goal
        programManager.createRepeatingProgram(stormBot2, stop, 1); //stops once reached for the goal

        programManager.createLabelProgram(botToRetrieve1, doNothing, "storm1", 4.95); //waits for the stormBot1
        programManager.createInfiniteProgram(botToRetrieve1, followStorm1); //follows forever

        programManager.createLabelProgram(botToRetrieve2, doNothing, "storm2", 4.95); //waits for the stormBot2
        programManager.createInfiniteProgram(botToRetrieve2, followStorm2); //follows forever

        simulationManager.simulate(50, 0.6, 0.3, 1.5);
    }
}