import java.awt.*;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Poison.java
 * Description:
 **/
public class Poison extends BoardObject{
    Color color = Color.MAGENTA;

    public boolean checkPoison(int sX, int sY){
        return (sX != x) || (sY != y);
    }
}
