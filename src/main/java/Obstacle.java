import java.awt.*;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Obstacle.java
 * Description:
 **/

/**
 * Obstacle class extending a board object wall that if the user runs into they
 * die
 */
public class Obstacle extends BoardObject{
    Color color = Color.WHITE;

    /**
     * checking if the user collides with the poison apple
     * @param sX snake x coordinates
     * @param sY snake y coordinates
     * @return true if the user doesn't hit the apple
     */
    public boolean checkWall(int sX, int sY){
        return (sX != x) || (sY != y);
    }
}
