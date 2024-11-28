import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * Gabe Ponce
 * 11/18/2024
 * Project: Snaking
 * File: GamePanel.java
 * Description:
 **/
public class GamePanel extends JPanel implements ActionListener {

    final int x[] = new int[ScreenInfo.GAME_UNITS];
    final int y[] = new int[ScreenInfo.GAME_UNITS];
    int bodyParts = 6;
    Apple apple = new Apple();
    ArrayList<Poison> poisons = new ArrayList<Poison>();
    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    char direction = 'R';
    int poisonCounter = 5;
    int obstacleCounter = 10;
    boolean running = false;

    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(ScreenInfo.SCREEN_WIDTH, ScreenInfo.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        startGame();
    }

    public void startGame(){
        apple.newObject();
        running = true;
        timer = new Timer(ScreenInfo.DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

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



            for (int i = 0; i < bodyParts; i++){
                if(i == 0){
                    g.setColor(new Color(175, 225, 175));
                    g.fillRect(x[i], y[i], ScreenInfo.UNIT_SIZE, ScreenInfo.UNIT_SIZE);
                }else{
                    g.setColor(new Color(80, 200, 120));
                    g.fillRect(x[i], y[i], ScreenInfo.UNIT_SIZE, ScreenInfo.UNIT_SIZE);
                }
            }
            g.setColor(Color.RED);
            g.setFont( new Font("Impact", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("SCORE: "+ apple.applesEaten, (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("SCORE: "+ apple.applesEaten))/2,g.getFont().getSize());
        }else {
            gameOver(g);
        }
    }

    public void move(){
        for (int i = bodyParts; i>0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction){
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


    public void checkCollision(){
        //check if head hits itself
        for(int i = bodyParts; i>0; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }

        //check if head touches left border
        if(x[0] < 0){
            running = false;
        }
        //checks right border
        if (x[0] > ScreenInfo.SCREEN_WIDTH){
            running = false;
        }
        //checks top border
        if (y[0] < 0){
            running = false;
        }
        //checks bottom border
        if (y[0] > ScreenInfo.SCREEN_WIDTH){
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g){
        //Game over text
        g.setColor(Color.RED);
        g.setFont( new Font("Impact", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2,ScreenInfo.SCREEN_HEIGHT/2);
        g.setColor(Color.RED);
        g.setFont( new Font("Impact", Font.BOLD, 30));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("SCORE: "+ apple.applesEaten, (ScreenInfo.SCREEN_WIDTH - metrics.stringWidth("SCORE: "+ apple.applesEaten))/2,g.getFont().getSize());
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            bodyParts = apple.checkApple(bodyParts, x[0], y[0]);
            while (poisonCounter <= apple.applesEaten){
                poisons.add(new Poison());
                poisons.get(poisons.size()-1).newObject();
                poisonCounter += 5;
            }
            while (obstacleCounter <= apple.applesEaten){
                obstacles.add(new Obstacle());
                obstacles.get(obstacles.size()-1).newObject();
                obstacleCounter += 10;
            }
            for (Poison poison : poisons){
                running = poison.checkPoison(x[0], y[0]);
            }
            for (Obstacle obstacle : obstacles){
                running = obstacle.checkWall(x[0], y[0]);
            }
            checkCollision();

        }
        repaint();
    }
}
