import javax.swing.*;

/**
 * Gabe Ponce
 * 11/18/2024
 * Project: Snaking
 * File: GameFrame.java
 * Description:
 **/
public class GameFrame extends JFrame {
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
