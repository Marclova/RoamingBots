package classes.services.graphic;

import classes.services.ArgumentCheckerService;
import classes.services.containers.Coordinates;

/**
 * Contains the simulation plane's angle borders within everything exists.
 */
public class LimitCoordinates {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();
    
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

    public void updateLimitCoordinates(Coordinates coordinates) {
        argumentCheckerService.checkNotNullObjects(coordinates);

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