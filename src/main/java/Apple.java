import java.awt.*;
import java.util.Random;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Apple.java
 * Description:
 **/
public class Apple {
    Random random = new Random();
    int appleX;
    int appleY;
    int applesEaten;
    Color color = Color.red;
    public void newApple(){
        appleX = random.nextInt((int)(ScreenInfo.SCREEN_WIDTH/ScreenInfo.UNIT_SIZE))*ScreenInfo.UNIT_SIZE;
        appleY = random.nextInt((int)(ScreenInfo.SCREEN_HEIGHT/ScreenInfo.UNIT_SIZE))*ScreenInfo.UNIT_SIZE;
    }

    public void checkApple(int bodyParts, int x, int y){
        if((x == appleX) && (y == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
}
