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

public class RandomExample {
    public static void main(String[] args) {
        
        SimulationManager simulationManager = new SimulationManager();
        ProgramManagerInterface programManager = simulationManager.getProgramManager();
        BotManagerInterface botManager = simulationManager.getBotManager();
        ArrayList<BotInterface> botList = botManager.getBotList();

        //values
        double generationLimits = 20;
        int searchingBotsQuantity = 15;
        int leaderBotsQuantity = 5;
        double botsDetectingDistance = 4.5;
        double botsSpeed = 1.5;
        Coordinates lowerCoordinates = new Coordinates(-generationLimits, -generationLimits);
        Coordinates higherCoordinates = new Coordinates(generationLimits, generationLimits);
        double simulationDuration = 50;
        double executionDuration = 0.5;
        double coolDownTime = 0.5;
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
        simulationManager.createTarget(new Circle(centralBot.getCoordinates(), "null", minimalLength)); //mark the center

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
            simulationManager.createTarget(new Rectangle(new Coordinates(x, y), "null", (minimalLength*2), (minimalLength*2)));

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
