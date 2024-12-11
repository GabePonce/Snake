import java.awt.*;
import java.util.Random;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Apple.java
 * Description:
 **/

/**
 * the apple object that increases the score when collision happens with the head of the snake
 */
public class Apple extends BoardObject {
    int applesEaten;
    Color color = Color.red;

    /**
     * chacks the collision between the apple and the snake and increases score/bodypart count
     * @param bodyParts the snake's bodypart count
     * @param sX snake head x
     * @param sY snake head 7
     * @return bodyparts to properly update the bodypart count
     */
    public int checkApple(int bodyParts, int sX, int sY){
        if((sX == x) && (sY == y)){
            bodyParts++;
            applesEaten++;
            newObject();
            return bodyParts;
        }else{
            return bodyParts;
        }
    }
}
