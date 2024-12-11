import javax.swing.*;

/**
 * Gabe Ponce
 * 11/18/2024
 * Project: Snaking
 * File: GameFrame.java
 **/

/**
 * The actual window of the game
 */
public class GameFrame extends JFrame {
    /**
     * constructor that adds the game panel, sets the window title
     * makes the window not resizable an object of this is created
     * in main to make start the game
     */
    GameFrame(){

        this.add(new GamePanel());

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
