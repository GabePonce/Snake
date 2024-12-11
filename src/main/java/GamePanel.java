import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Gabe Ponce
 * 11/18/2024
 * Project: Snaking
 * File: GamePanel.java
 * Description:
 **/

/**
* Swing Panel that holds the game class and general game logic objects for all the other important
* classes are created and utilized here.
* <br>
* This Class is essentially the snake game which allows the user to pick between one and
* two players and then creates the game board with the snake and the game begins. Player one
* utilizes wasd for movement and Player two plays with the arrow keys. Every 5 apples eaten a
* new poison apple appears and every ten a new white obstacle appears.
* */
public class GamePanel extends JPanel implements ActionListener {

//    Player player1 = new Player(2);
    ArrayList<Player> players = new ArrayList<Player>();
    Apple apple = new Apple();
    ArrayList<Poison> poisons = new ArrayList<Poison>();
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    int level = 0;
    int highScore;
    int poisonCounter = 5;
    int obstacleCounter = 10;
    boolean running = false;
    Boolean twoPlayer = null;
    Timer timer;
    Random random;

    /**
    * The Game Window itself with the keylister for deciding how many players to choose
    * from here the game is started
    * */
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(ScreenInfo.SCREEN_WIDTH, ScreenInfo.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new PlayerChoice());

        startGame();
    }


    /**
    * Starts the game, the game speed is dictated by the delay
    * */
    public void startGame(){
        apple.newObject();
        running = true;
        timer = new Timer(ScreenInfo.DELAY, this);
        timer.start();
    }

    /**
    * used when the player dies to restart the game if they choose to play again
    * sets everything back to its initial state so program will function correctly
    * */
    public void resetGame() {
        // Reset game state
        players.clear();
        poisons.clear();
        obstacles.clear();
        apple = new Apple();
        poisonCounter = 5;
        obstacleCounter = 10;
        level = 0;
        highScore = 0;
        running = false;

    }


    /**
    * The function that renders the window graphics
    * Calls a specific function to render specific screens
    * based on if the user has chosen number of players or if the game is running
    * @param g an object of the graphics class that will be used to "draw" the graphics for the game
    * */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(twoPlayer == null){
            choosePlayers(g);
        }else if (running){
            draw(g);
        }else{
            gameOver(g);
        }

    }

    /**
    * The functions that draws the actual game sequence of snake and adds the obstacles based on score
    * @param g an object of the graphics class that will be used to "draw" the graphics for the game
    * */
    public void draw(Graphics g){

        if(running){
            for (int i=0; i<ScreenInfo.SCREEN_HEIGHT/ScreenInfo.UNIT_SIZE; i++){
                g.drawLine(i*ScreenInfo.UNIT_SIZE, 0 , i*ScreenInfo.UNIT_SIZE, ScreenInfo.SCREEN_HEIGHT);
                g.drawLine(0, i*ScreenInfo.UNIT_SIZE, ScreenInfo.SCREEN_WIDTH, i*ScreenInfo.UNIT_SIZE);
            }


            g.setColor(apple.color);
            g.fillOval(apple.x, apple.y, ScreenInfo.UNIT_SIZE, ScreenInfo.UNIT_SIZE);

            for (Poison poison : poisons){
                g.setColor(poison.color);
                g.fillOval(poison.x, poison.y, ScreenInfo.UNIT_SIZE, ScreenInfo.UNIT_SIZE);
            }

            for (Obstacle obstacle : obstacles){
                g.setColor(obstacle.color);
                g.fillRect(obstacle.x, obstacle.y, ScreenInfo.UNIT_SIZE, ScreenInfo.UNIT_SIZE);
            }


            for (Player player : players){
                for (int i = 0; i < player.bodyParts; i++){
                    if(i == 0){
                        g.setColor(player.headColor);
                        g.fillRect(player.x[i], player.y[i], ScreenInfo.UNIT_SIZE, ScreenInfo.UNIT_SIZE);
                    }else{
                        g.setColor(player.bodyColor);
                        g.fillRect(player.x[i], player.y[i], ScreenInfo.UNIT_SIZE, ScreenInfo.UNIT_SIZE);
                    }
                }
            }

            g.setColor(Color.RED);
            g.setFont( new Font("Impact", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("SCORE: "+ apple.applesEaten, (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("SCORE: "+ apple.applesEaten))/2,g.getFont().getSize());
            g.drawString("Level: " + level, (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("Level: " + level))/2, (int) (ScreenInfo.SCREEN_HEIGHT*0.99));
        }


    }

    /**
    * The screen prompting users to select a player count
    * @param g an object of the graphics class that will be used to "draw" the graphics for the game
    * */
    public void choosePlayers(Graphics g) {
        g.setColor(Color.CYAN);
        g.setFont(new Font("Impact", Font.BOLD, 30));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Choose Numbers Of Players: ", (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("Choose Numbers Of Players:"))/2, (ScreenInfo.SCREEN_HEIGHT/2));
        g.drawString("Press 1 : 1 Player || Press 2: 2 Players", (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("Press 1 for 1 Player or 2 for 2 Players"))/2, (int) (ScreenInfo.SCREEN_HEIGHT*0.6));
    }



    /**
    * The game over screen that is displayed when a player looses
    * @param g an object of the graphics class that will be used to "draw" the graphics for the game
    * */
    public void gameOver(Graphics g){
        //Game over text
        g.setColor(Color.RED);
        g.setFont( new Font("Impact", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2,ScreenInfo.SCREEN_HEIGHT/2);
        g.setFont( new Font("Impact", Font.BOLD, 30));
        metrics = getFontMetrics(g.getFont());
        g.drawString("SCORE: "+ apple.applesEaten, (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("SCORE: "+ apple.applesEaten))/2,g.getFont().getSize());
        g.drawString("High Score: " + highScore, (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("High Score: " + highScore))/2, (int) (ScreenInfo.SCREEN_HEIGHT*0.7));
        g.drawString("Press Space to Play Again", (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("Press Space to Play Again") )/2, (int)(ScreenInfo.SCREEN_HEIGHT*0.9));
    }


    /**
    * Provides various game logic operations game status and the users keyboard input
    * adds the correct number of players based on user decision, does collision checks
    * for all the "game objects" and for the walls, as well as writing a highscore to a file
    * if a highscore is achieved.
    * @param e object of the ActionEvent class that represents an event generated by an action such as a key press
    * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (twoPlayer != null){
            if (players.isEmpty()){
                players.add(new Player(1, new Color(175, 225, 175), new Color(80, 200, 120)));
                this.addKeyListener(players.getFirst().myKeyAdapter);
            }else if (players.size() == 1 && twoPlayer){
                players.add(new Player(2, new Color(255, 255, 215), new Color(255, 255, 90)));
                this.addKeyListener(players.get(1).myKeyAdapter);
            }

            if (running) {
                while (poisonCounter <= apple.applesEaten){
                    poisons.add(new Poison());
                    poisons.get(poisons.size()-1).newObject();
                    poisonCounter += 5;
                    level ++;
                }
                while (obstacleCounter <= apple.applesEaten){
                    obstacles.add(new Obstacle());
                    obstacles.getLast().newObject();
                    obstacleCounter += 10;
                }
                for (Player player : players) {
                    player.move();
                    player.bodyParts = apple.checkApple(player.bodyParts, player.x[0], player.y[0]);
                    running = player.checkCollision(poisons, obstacles);
                }

                if (players.size() == 2){
                    running = (players.get(0).checkCollision(poisons, obstacles) && players.get(1).checkCollision(poisons, obstacles));
                }else{
                    running = players.getFirst().checkCollision(poisons, obstacles);
                }

                if(!running) {
                    timer.stop();

                    try {
                        File hScoreFile = new File("score.txt");
                        Scanner myReader = new Scanner(hScoreFile);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            highScore = Integer.parseInt(data);
                        }
                        myReader.close();
                    } catch (FileNotFoundException nF) {
                        System.out.println("An error occurred.");
                        nF.printStackTrace();
                    }

                    if (apple.applesEaten > highScore){
                        try {
                            FileWriter myWriter = new FileWriter("score.txt");
                            highScore = apple.applesEaten;
                            myWriter.write(String.valueOf(highScore));
                            myWriter.close();

                        } catch (IOException iO) {
                            System.out.println("An error occurred.");
                            iO.printStackTrace();
                        }
                    }
                }
            }
        }

        repaint();
    }

    /**
    * key adapter that to identify when the user presses 1 or 2 to select amount of players and
    * space to restart the game if the player has lost
    * */
    public class PlayerChoice extends KeyAdapter {

        /**
         * a method to interpret the key pressed by the user when deciding how many players and if to play
         * again
         * @param e the key pressed by the user
         */
        @Override
        public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                       twoPlayer = false;
                       break;
                    case KeyEvent.VK_2:
                        twoPlayer = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!running){
                            resetGame();
                            twoPlayer = null;
                            startGame();
                        }
                        break;
                }

        }
    }
}
