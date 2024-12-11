import java.awt.*;
import java.util.Random;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: BoardObject.java
 * Description:
 **/

/**
 * blueprint for board objects entities to follow
 * includes the coordinates and a create new object method
 * that spawns a new instance of the object randomly on the board
 */
abstract class BoardObject {
    Random random = new Random();
    int x;
    int y;

    /**
     * creates the object randomly on the gameboard
     */
    public void newObject(){
        x = random.nextInt((int)(ScreenInfo.SCREEN_WIDTH/ScreenInfo.UNIT_SIZE))*ScreenInfo.UNIT_SIZE;
        y = random.nextInt((int)(ScreenInfo.SCREEN_HEIGHT/ScreenInfo.UNIT_SIZE))*ScreenInfo.UNIT_SIZE;
    }

    //each subclass will have a specific check*subclass* collision function
}
