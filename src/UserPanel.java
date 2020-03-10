import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class UserPanel extends JPanel implements JavaArcade, MouseListener,
        ActionListener, MouseMotionListener {

    private boolean running;
    private ArrayList BrickList;
    private Ball ball;
    private Bar bar;
    private int lives, points;
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
        lives = 3;
        points = 0;
        ball = new Ball();
        bar = new Bar();
        BrickList = new ArrayList<Brick>();
        //define mouseListener
        //define actionListener
        //define timer
        //set key controls
    }

    public void actionPerformed(ActionEvent e) {
        checkStats();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        //switch statment for moving bar left/right depending on which
        //key is pressed (left/right arrow)
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //for loop: draw all blocks from brickList
        //for loop (length of lives): draw hearts
        ball.draw();
        bar.draw();
    }

    //changes coordinates of ball so that next time the screen is repainted,
    //the ball will be drawn in that new place
    public void checkStats() {
        ball.move();
    }

    public void mouseMoved(MouseEvent e) {
        bar.moveToMouse(e.getX(), e.getY());
    }

    public boolean isRunning() {
        return running;
    }

    public void startGame() {
        //pick brick colors & assign them to brickList
        Color[] c = pickColors();
        for(int i  = 0; i < BrickList.size(); i++) {
            BrickList.add(new Brick(c[i%2]);
        }

        //start timers, draw everything for the first time, maybe display
        //some sort of "press space to start" message
        System.out.print("Click here to start!");
        //if the person does click, then start
        running = true;
    }

    public String getGameName(){
        return "Brick Breaker (!)";
    }

    public void pauseGame() {
        //stop timer
        //the description says "save your scores" but idk why the scores
        //would reset at all
        running = false;
    }

    public void resumeGame() {
        //start timer
        running = true;
    }

    public String getInstructions() {
        return "break the bricks. control the bar using the mouse or " +
                "left and right arrow keys.";
    }

    public String getCredits() {
        return "adi briskin & jessica liao";
    }

    public String getHighScore() {
        //add actual highScore
        String highScore = " ";
        return highScore;
    }

    public void stopGame() {
        //stop timer
        points = 0;
        lives = 3;
    }

    public int getPoints() {
        return points;
    }

    public void setDisplay(GameStats d) {
        //no clue ngl
    }

    public void mouseClicked(MouseEvent e) {
        if (!running)
            resumeGame();
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}
}