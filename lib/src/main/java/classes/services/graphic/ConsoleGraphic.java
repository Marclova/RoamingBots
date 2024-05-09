package classes.services.graphic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.services.ArgumentCheckerService;
import classes.services.containers.Coordinates;
import classes.targets.Circle;
import classes.targets.Rectangle;
import interfaces.bots.BotInterface;
import interfaces.graphic.GraphicOutputInterface;
import interfaces.targets.CartesianAreaInterface;

/**
 * This class prints on console the current simulation status rounding every coordinates in whole numbers.
 */
public class ConsoleGraphic implements GraphicOutputInterface {
    private ArgumentCheckerService argumentCheckerService = new ArgumentCheckerService();

    //contains all the points to draw bots and target areas.
    Map<Coordinates, PointOfInterest> pointMap;
    //defines the extension of the used simulation plane.
    LimitCoordinates limitCoordinates;
    
    @Override
    public void printSimulationPlane(ArrayList<CartesianAreaInterface> targetList, ArrayList<BotInterface> botList, double zoom) {
        argumentCheckerService.checkNotNullObjects(targetList, botList);
        argumentCheckerService.checkGraterThanZeroValues(zoom);

        pointMap = new HashMap<>();
        limitCoordinates = new LimitCoordinates();

        for (CartesianAreaInterface target : targetList) {
            this.extractInterestingCoordinatesFromCartesianArea(target, zoom);
        }
        //bots will be printed with priority if they'll have the same coordinates of the area's borders.
        for (BotInterface bot : botList) {
            this.extractInterestingCoordinatesFromBot(bot, zoom);
        }

        this.actuallyPrintSimulationPlane();
    }

    //private methods

    /**
     * Extract the bot's coordinates rounded to whole numbers, updates the "limitCoordinates"
     *  and puts the bot's coordinates into the "pointMap" with the flag "BOT".
     * 
     * @param bot The bot to extract the coordinates from.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    private void extractInterestingCoordinatesFromBot(BotInterface bot, double zoom) {
        argumentCheckerService.checkNotNullObjects(bot);
        argumentCheckerService.checkGraterThanZeroValues(zoom);
        
        long botX = Math.round(bot.getCoordinates().x * zoom);
        long botY = Math.round(bot.getCoordinates().y * zoom);
        Coordinates botCoordinates = new Coordinates(botX, botY);

        this.limitCoordinates.updateLimitCoordinates(botCoordinates);
        pointMap.put(botCoordinates, PointOfInterest.BOT);
    }

    /**
     * Extract the target's interesting coordinates rounded to whole numbers, updates the "limitCoordinates"
     *  and puts the target's coordinates into the "pointMap"
     *      with the flags "ANGLE_BORDER","VERTICAL_BORDER" AND "HORIZONTAL_BORDER".
     * 
     * @param target The target to extract the coordinates from.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    private void extractInterestingCoordinatesFromCartesianArea(CartesianAreaInterface target, double zoom) {
        argumentCheckerService.checkNotNullObjects(target);
        argumentCheckerService.checkGraterThanZeroValues(zoom);

        if(target instanceof Circle)
        {
            this.extractInterestingCoordinatesFromCircle((Circle)target, zoom);
        }
        else if (target instanceof Rectangle)
        {
            this.extractInterestingCoordinatesFromRectangle((Rectangle)target, zoom);
        }
        else {throw new ClassCastException();}
    }

    /**
     * Extract the circle's interesting coordinates rounded to whole numbers, updates the "limitCoordinates"
     *  and puts the circle's coordinates into the "pointMap"
     *      with the flags "ANGLE_BORDER","VERTICAL_BORDER" AND "HORIZONTAL_BORDER".
     * 
     * @param circle The circle to extract the coordinates from.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    private void extractInterestingCoordinatesFromCircle(Circle circle, double zoom) {
        argumentCheckerService.checkNotNullObjects(circle);
        argumentCheckerService.checkGraterThanZeroValues(zoom);

        long cordX = Math.round(circle.getCoordinates().x * zoom);
        long cordY = Math.round(circle.getCoordinates().y * zoom);
        long radius = Math.round( ((Circle)circle).getRadius() * zoom );

        this.pointMap.put(new Coordinates(cordX, cordY), PointOfInterest.ANGLE_BORDER);
        for (int i = -1; i <= 1; i+=2) {
            long signedRadius = radius*i;
            this.pointMap.put(new Coordinates(cordX+signedRadius, cordY), PointOfInterest.VERTICAL_BORDER);
            this.pointMap.put(new Coordinates(cordX, cordY+signedRadius), PointOfInterest.HORIZONTAL_BORDER);
            this.limitCoordinates.updateLimitCoordinates(new Coordinates(cordX+signedRadius, cordY));
            this.limitCoordinates.updateLimitCoordinates(new Coordinates(cordX, cordY+signedRadius));
        }
    }

    /**
     * Extract the rectangle's interesting coordinates rounded to whole numbers, updates the "limitCoordinates"
     *  and puts the rectangle's coordinates into the "pointMap"
     *      with the flags "ANGLE_BORDER","VERTICAL_BORDER" AND "HORIZONTAL_BORDER".
     * 
     * @param rectangle The rectangle to extract the coordinates from.
     * @param zoom Multiplier used to expand or contract the plane visualization.
     */
    private void extractInterestingCoordinatesFromRectangle(Rectangle rectangle, double zoom) {
        argumentCheckerService.checkNotNullObjects(rectangle);
        argumentCheckerService.checkGraterThanZeroValues(zoom);

        long cordX = Math.round(rectangle.getCoordinates().x * zoom);
        long cordY = Math.round(rectangle.getCoordinates().y * zoom);
        long width = Math.round(rectangle.getWidth() * zoom );
        long height = Math.round(rectangle.getHeight() * zoom );
        long xLimit = cordX+width;
        long yLimit = cordY+height;

        long[] xCords = {cordX, xLimit};
        long[] yCords = {cordY, yLimit};
        for (long x : xCords) {
            for (long y : yCords) {
                this.pointMap.put(new Coordinates(x, y), PointOfInterest.ANGLE_BORDER);
                this.limitCoordinates.updateLimitCoordinates(new Coordinates(x, y));
            }
        }

        for (long i = cordX+1; i < xLimit; i++) {
            this.pointMap.put(new Coordinates(i, cordY), PointOfInterest.HORIZONTAL_BORDER);
            this.pointMap.put(new Coordinates(i, yLimit), PointOfInterest.HORIZONTAL_BORDER);
        }
        
        for (long i = cordY+1; i < yLimit; i++) {
            this.pointMap.put(new Coordinates(cordX, i), PointOfInterest.VERTICAL_BORDER);
            this.pointMap.put(new Coordinates(xLimit, i), PointOfInterest.VERTICAL_BORDER);
        }
    }

    private void actuallyPrintSimulationPlane() {

        long lowerX = Math.round(this.limitCoordinates.getLowestX());
        long higherX = Math.round(this.limitCoordinates.getHighestX());
        long lowerY = Math.round(this.limitCoordinates.getLowestY());
        long higherY = Math.round(this.limitCoordinates.getHighestY());

        //The map will print one line at a time from left to right and top to bottom
        for (long y = higherY; y >= lowerY; y--) {
            for (long x = lowerX; x <= higherX; x++) {
                PointOfInterest flagToPrint = this.pointMap.get(new Coordinates(x, y));
                
                if(flagToPrint == null)
                {
                    System.out.print("  ");
                }
                else
                {
                    switch (flagToPrint) {
                        case BOT:
                            System.out.print("O ");
                            break;

                        case ANGLE_BORDER:
                            System.out.print("+ ");
                            break;

                        case HORIZONTAL_BORDER:
                            System.out.print("- ");
                            break;

                        case VERTICAL_BORDER:
                            System.out.print("| ");
                            break;
                    
                        default:
                            System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
        this.drawMarkLine(higherX-lowerX);
    }

    /**
     * Prints a separating mark onto the console.
     * 
     * @param length The length of the trait.
     */
    private void drawMarkLine(long length) {
        argumentCheckerService.checkGraterThanZeroValues(length);

        System.out.println();
        for (long i = 0; i < length; i++) {
            System.out.print("===");
        }
        System.out.println();
    }
}
