package classes.services.graphic;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

import classes.services.abstractServices.ArgumentChecker;
import classes.services.containers.Coordinates;
// import interfaces.CartesianAreaInterface;
// import interfaces.bots.BotInterface;

/**
 * Contains the simulation plane's angle borders within everything exists.
 */
public class LimitCoordinates extends ArgumentChecker {
    
    private double lowestX = Double.MAX_VALUE;
    private double lowestY = Double.MAX_VALUE;
    private double highestX = Double.MIN_VALUE;
    private double highestY = Double.MIN_VALUE;

    public double getLowestX() {
        return this.lowestX;
    }

    public double getHighestX() {
        return this.highestX;
    }

    public double getLowestY() {
        return this.lowestY;
    }

    public double getHighestY() {
        return this.highestY;
    }

    // public void updateLimitCoordinates(ArrayList<BotInterface> botList,
    //                                 ArrayList<CartesianAreaInterface> targetList) {
    //     this.checkNotNullObjects(botList, targetList);

    //     Map<Coordinates, PointOfInterest> mapToReturn = new HashMap<>();

    //     for (BotInterface bot : botList) {
    //         this.actuallyUpdateLimitCoordinates(bot.getCoordinates());
    //         mapToReturn.put(bot.getCoordinates(), PointOfInterest.BOT);
    //     }
    //     for (CartesianAreaInterface target : targetList) {
    //         this.
    //     }
    // }

    // //Private methods

    public void updateLimitCoordinates(Coordinates coordinates) {
        this.checkNotNullObjects(coordinates);

        double cordX = coordinates.x;
        double cordY = coordinates.y;
        cordX = Math.round(cordX);
        cordY = Math.round(cordY);

        if(cordX < this.lowestX) {this.lowestX = cordX;}
        if(cordY < this.lowestY) {this.lowestY = cordY;}
        if(cordX > this.highestX) {this.highestX = cordX;}
        if(cordY > this.highestY) {this.highestY = cordY;}
    }
}