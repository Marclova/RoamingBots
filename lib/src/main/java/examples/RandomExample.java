package examples;

import java.util.ArrayList;

import classes.SimulationManager;
import classes.bots.BotManager;
import classes.programs.ProgramManager;
import classes.services.containers.Coordinates;
import classes.services.containers.DirectionalVectors;
import classes.targets.CartesianAreaManager;
import classes.targets.Circle;
import classes.targets.Rectangle;
import functionalInterfaces.BotCommand;
import interfaces.SimulationManagerInterface;
import interfaces.bots.BotInterface;
import interfaces.bots.BotManagerInterface;
import interfaces.programs.ProgramManagerInterface;
import interfaces.targets.CartesianAreaManagerInterface;

/**
 *  In this example there is a center to reach and two kind of bots named "leaders" and "searching".
 *      The leader bots, starting in square spaces, are the only ones able to lead towards the center,
 *          so they wait to have at least one bot to lead in order to depart for the center.
 *      The searching bot instead move randomly towards the plane looking for a leader bot to follow.
 *          Once a searching bot is following a leader, it will start emitting a "follow me" label as well,
 *              increasing the chance to create a bigger fleet.
 *      Once reached the center the bots will not stop emitting the "follow me" label in order to
 *          make the center to eventual searching bots passing nearby.
 *  The available time is limited and there are no borders, so not all the bots may arrive at destination.
 */
public class RandomExample {
    public static void main(String[] args) {
        
        ProgramManagerInterface programManager = new ProgramManager();
        BotManagerInterface botManager = new BotManager();
        CartesianAreaManagerInterface cartesianAreaManager = new CartesianAreaManager();
        SimulationManagerInterface simulationManager = new SimulationManager(botManager, programManager, cartesianAreaManager);
        ArrayList<BotInterface> botList = botManager.getBotList();

        //values
        double heightGenerationLimits = 15;
        double widthGenerationLimits = heightGenerationLimits * 1.1;
        int searchingBotsQuantity = 15;
        int leaderBotsQuantity = 5;
        double botsDetectingDistance = 4.5;
        double botsSpeed = 1.5;
        Coordinates lowerCoordinates = new Coordinates(-widthGenerationLimits, -heightGenerationLimits);
        Coordinates higherCoordinates = new Coordinates(widthGenerationLimits, heightGenerationLimits);
        double simulationDuration = 50;
        double executionDuration = 0.4;
        double coolDownTime = 0.3;
        double zoom = 1.45;


        //configurations for programs
        ArrayList<BotCommand> doNothing = new ArrayList<>();

        ArrayList<BotCommand> readyToDepart = new ArrayList<>();
        readyToDepart.add((bot) -> bot.setContinueMotion(simulationDuration));

        ArrayList<BotCommand> stop = new ArrayList<>();
        stop.add((bot) -> bot.stopMotion());

        ArrayList<BotCommand> isTheCenter = new ArrayList<>();
        isTheCenter.add((bot) -> bot.startEmittingSignalLabel("center"));
        
        ArrayList<BotCommand> lookForTheLabel = new ArrayList<>();
        lookForTheLabel.add((bot) -> bot.setMoveRandom(new DirectionalVectors(-1, -1), new DirectionalVectors(1, 1),
                                                        botsSpeed));

        ArrayList<BotCommand> readyToLead = new ArrayList<>();
        readyToLead.add((bot) -> bot.startEmittingSignalLabel("follow me"));

        ArrayList<BotCommand> leadTowardsTheCenter = new ArrayList<>();
        leadTowardsTheCenter.add((bot) -> bot.startEmittingSignalLabel("follow me"));
        leadTowardsTheCenter.add((bot) -> bot.setFollow("center", Double.MAX_VALUE, (botsSpeed / 1.8), botList));

        ArrayList<BotCommand> followTheLeader = new ArrayList<>();
        followTheLeader.add((bot) -> bot.startEmittingSignalLabel("follow me"));
        followTheLeader.add((bot) -> bot.setFollow("follow me", (botsDetectingDistance * 2), (botsSpeed / 1.8), botList));


        //creating bots
        botManager.createRandomBots((searchingBotsQuantity+leaderBotsQuantity), lowerCoordinates, higherCoordinates);
        //The central bot will not move
        BotInterface centralBot = botManager.createBot(new Coordinates(0, 0));


        //assigning programs
        double minimalLength = 1 / zoom; //minimal length needed by an area to be seen in the simulation.
        programManager.createRepeatingProgram(centralBot, isTheCenter, 1);
        cartesianAreaManager.createTarget(new Circle(centralBot.getCoordinates(), "null", minimalLength)); //mark the center

        for (BotInterface bot : botList) { //preparing all bots to move
            if(bot.equals(centralBot))
            {
                break; //reached the last bot (centralBot)
            }
            else
            {
                programManager.createRepeatingProgram(bot, readyToDepart, 1);
            }
        }
        
        for (int i = 0; i < leaderBotsQuantity; i++) { //programming the leaders and marking their initial position.
            BotInterface leaderBot = botList.get(i);

            double x = leaderBot.getCoordinates().x - minimalLength;
            double y = leaderBot.getCoordinates().y - minimalLength;
            cartesianAreaManager.createTarget(new Rectangle(new Coordinates(x, y), "null", (minimalLength*2), (minimalLength*2)));

            //waits for someone to lead
            programManager.createLabelProgram(leaderBot, readyToLead, "follow me", botsDetectingDistance);
            //starts leading towards the center
            programManager.createRepeatingProgram(leaderBot, leadTowardsTheCenter, 1);
            //stops once reached the center.
            programManager.createLabelProgram(leaderBot, doNothing, "center", (minimalLength*2));
            programManager.createRepeatingProgram(leaderBot, stop, 1);
            // programManager.createRepeatingProgram(leaderBot, isTheCenter, 1);
        }

        for (int i = leaderBotsQuantity; i < (searchingBotsQuantity+leaderBotsQuantity); i++) { //programming the searchers...
            BotInterface searchingBot = botList.get(i);
            //looks for a bot emitting the signal "follow me"...
            programManager.createLabelProgram(searchingBot, lookForTheLabel, "follow me", botsDetectingDistance);
            //... until it reaches for the center and stops.
            programManager.createLabelProgram(searchingBot, followTheLeader, "center", (minimalLength*2));
            programManager.createRepeatingProgram(searchingBot, stop, 1);
        }



        simulationManager.simulate(simulationDuration, executionDuration, coolDownTime, zoom);
    }
}
