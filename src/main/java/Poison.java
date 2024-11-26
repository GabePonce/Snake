import java.awt.*;

/**
 * Gabe Ponce
 * 11/26/2024
 * Project: Snaking
 * File: Poison.java
 * Description:
 **/
public class Poison extends Apple{
    Color color = Color.MAGENTA;
    public boolean checkCollide(int x, int y){
        return (x != appleX) || (y != appleY);
    }
}
