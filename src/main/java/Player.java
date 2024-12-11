import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


/**
 * Gabe Ponce
 * 11/27/2024
 * Project: Snaking
 * File: Player.java
 **/

/**
 * the player class that holds all the relevant information regarding the actual snake
 * that the player controls. It defines the keys used by either player one or two and holds the
 * coordinates and size of the snake as well as the direction the snake is going and its colors
 */
public class Player {

    final int x[] = new int[ScreenInfo.GAME_UNITS];
    final int y[] = new int[ScreenInfo.GAME_UNITS];
    KeyListener myKeyAdapter;
    int bodyParts = 6;
    char direction = 'R';
    Color headColor;
    Color bodyColor;

    /**
     * constructor for the snake object that defines if the snake is either player one or
     * player two and passes that to the key adaptor defining the controls for either snake.
     * also defines the color of the snake
     * @param playerNumber will change the keys depending on if its player one or player two
     * @param hColor head color of the snake
     * @param bColor body color of the snake
     */
    //make a constructor expecially for the move keys and pass them to the key adapter class
    public Player(int playerNumber, Color hColor, Color bColor) {
        this.myKeyAdapter = new MyKeyAdapter(playerNumber);
        this.headColor = hColor;
        this.bodyColor = bColor;
    }

    /**
     * utilizes the current "direction" of the snake to move
     * the snake by changing its x and y coordinate coordinates
     * accordingly
     */
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - ScreenInfo.UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + ScreenInfo.UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - ScreenInfo.UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + ScreenInfo.UNIT_SIZE;
                break;
        }
    }


    /**
     * method that checks if the snake has collided with a perilous
     * object or a border.
     * @param poisons poison apples
     * @param obstacles deadly obstacles
     * @return boolean the game state will be updated based on this
     */
    public boolean checkCollision(ArrayList<Poison> poisons, ArrayList<Obstacle> obstacles){
        //check if head hits itself
        for(int i = bodyParts; i>0; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])){
                return false;
            }
        }

        //check if head touches left border
        if(x[0] < 0){
            return false;
        }
        //checks right border
        if (x[0] > ScreenInfo.SCREEN_WIDTH){
            return false;
        }
        //checks top border
        if (y[0] < 0){
            return false;
        }
        //checks bottom border
        if (y[0] > ScreenInfo.SCREEN_WIDTH){
            return false;
        }

        for (Poison poison : poisons){
            if(!poison.checkPoison(x[0], y[0])){
                return false;
            }
        }
        for (Obstacle obstacle : obstacles){
            if (!obstacle.checkWall(x[0], y[0])){
                return false;
            }
        }


        return true;
    }

    /**
     * keyAdapter for the player that defines the controls
     * and interprets them
     */
    public class MyKeyAdapter extends KeyAdapter {
        int player;

        /**
         * constructor taking in if the user is player one or two
         * @param player
         */
        public MyKeyAdapter(int player) {
            this.player = player;
        }

        /**
         * defines and interprets the player's controls depending on the
         * player number, updates the direction member based on the key pressed.
         * @param e the event to be processed
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (player == 1){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_W:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
            }else if (player == 2){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
            }

        }
    }
}
