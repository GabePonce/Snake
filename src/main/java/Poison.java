import java.awt.*;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Poison.java
 **/

/**
 * Extending the boardobject class, poison is for poison apple
 * if the player hits the poison apple the game ends
 */
public class Poison extends BoardObject{
    Color color = Color.MAGENTA;

    /**
     * checking if the user collides with the poison apple
     * @param sX snake x coordinates
     * @param sY snake y coordinates
     * @return true if the user doesn't hit the apple
     */
    public boolean checkPoison(int sX, int sY){
        return (sX != x) || (sY != y);
    }
}
