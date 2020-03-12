import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class UserPanel extends JPanel implements JavaArcade, MouseListener,
        MouseMotionListener, ActionListener, KeyListener {

    private GameState running;
    private ArrayList<Brick> BrickList;
    private Ball ball;
    private Bar bar;
    private BufferedImage heart;
    private int lives, points, mouseX, mouseY;
    private static int rounds = 0;
    Timer timer;
    Color[] colorList = new Color[] {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN,
                                    Color.PINK, Color.YELLOW};

    private Color[] pickColors() {
        int colorNum = (int) (Math.random() * colorList.length);
        int colorNum1 = colorNum;
        while (colorNum1 == colorNum)
            colorNum1 = (int) (Math.random() * colorList.length);
        Color[] returnArray = new Color[] {colorList[colorNum], colorList[colorNum1]};
        return returnArray;
    }

    public UserPanel (int width, int length) {
        ball = new Ball();
        bar = new Bar();
        BrickList = new ArrayList<Brick>();
        try {
            heart = ImageIO.read(new File("uglyheart.jpg"));
        }
        catch (IOException e){
            System.out.println("heart file invalid");
        }
        //"this", i.e. UserPanel, is both a MouseListener and a MouseMotionListener
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        timer = new Timer(500, this);
    }

    public void actionPerformed(ActionEvent e) {
        checkStats();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        //switch statement for moving bar left/right depending on which
        //key is pressed (left/right arrow)
        //if space bar is pressed pause/resume game
        switch(e.getKeyCode()) {
            case (VK_RIGHT):
                bar.moveRight();
                break;
            case (VK_LEFT):
                bar.moveLeft();
                break;
            case(VK_SPACE):
                if (running == GameState.PAUSED)
                    resumeGame();
                else if (running == GameState.PLAYING)
                    pauseGame();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Brick b : BrickList) {
            b.draw();
        }
        for (int i = 0; i < lives; i++) {
            //todo: draw heart, somehow
        }
        ball.draw();
        bar.draw();
    }

    //changes coordinates of ball so that next time the screen is repainted,
    //the ball will be drawn in that new place
    public void checkStats() {
        ball.move();
    }

    public void mouseMoved(MouseEvent e) {
        bar.moveToMouse(mouseX, mouseY);
    }

    public GameState isRunning() {
        return running;
    }

    public void startGame() {
        Color[] c = pickColors();
        for(int i  = 0; i < BrickList.size(); i++) {
            BrickList.add(new Brick(c[i%2], mouseX, mouseY));
        }
        timer.start();
        //todo: add starting message
        lives = 3;
        points = 0;
        running = GameState.PLAYING;
    }

    public String getGameName(){
        return "Brick Breaker (!)";
    }

    public void pauseGame() {
        timer.stop();
        running = GameState.PAUSED;
    }

    public void resumeGame() {
        timer.start();
        running = GameState.PLAYING;
    }

    public String getInstructions() {
        return "break the bricks. control the bar using the mouse or " +
                "left and right arrow keys.";
    }

    public String getCredits() {
        return "adi briskin & jessica liao";
    }

    public String getHighScore() {
        //todo: get high score
        String highScore = " ";
        return highScore;
    }

    public void stopGame() {
        //todo: add "thank you for playing" message
        timer.stop();
        running = GameState.STOPPED;
    }

    public int getPoints() {
        return points;
    }

    public int getRounds() {
        return rounds;
    }

    public void updateMouseCoordinates(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void setDisplay(GameStats d) {
        //todo: figure out
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

}