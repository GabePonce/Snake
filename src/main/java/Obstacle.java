import java.awt.*;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Obstacle.java
 * Description:
 **/
public class Obstacle extends BoardObject{
    Color color = Color.WHITE;

    public boolean checkWall(int sX, int sY){
        return (sX != x) || (sY != y);
    }
}
