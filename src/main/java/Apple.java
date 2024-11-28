import java.awt.*;
import java.util.Random;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Apple.java
 * Description:
 **/
public class Apple extends BoardObject {
    int applesEaten;
    Color color = Color.red;


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
