import javax.swing.*;
import java.awt.*;
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

    Color[] pickColors() {
        //return 2 random colors from colorList
    }

    public UserPanel (int width, int length) {
        lives = 3;
        points = 0;
        //make brickList
        ball = new Ball();
        bar = new Bar();
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

    public boolean running() {

    }

    public void startGame();
    public String getGameName(){
        return "Brick - Breaker";
    }
    public void pauseGame();
    public String getInstructions();
    public String getCredits();
    public String getHighScore();
    public void stopGame();
    public int getPoints();
    public void setDisplay();
}