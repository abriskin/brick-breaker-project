import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.awt.event.KeyEvent.*;

public class UserPanel extends JPanel implements JavaArcade, MouseListener,
        MouseMotionListener, ActionListener, KeyListener {

    private int colorChangeTimer = 0;
    private boolean win;
    private GameState running;
    private GameStats game;
    private ArrayList<Brick> BrickList;
    private Ball ball;
    private Bar bar;
    private BufferedImage heart;
    private int points;
    private static int lives;
    private int highScore;
    private ArrayList<GameObject> GameObjectArray = new ArrayList<GameObject>();
    Timer timer;

    public UserPanel(int width, int length) {
        bar = new Bar(300, 425);
        ball = new Ball(300, 400);
        BrickList = new ArrayList<Brick>();
        GameObjectArray.add(bar);
        GameObjectArray.add(ball);
        try {
            heart = ImageIO.read(new File("uglyheart.jpg"));
        } catch (IOException e) {
            System.out.println("heart file invalid");
        }
        //"this", i.e. UserPanel, is both a MouseListener and a MouseMotionListener
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        timer = new Timer(10, this);
        points = 0;
        highScore = getHighScore();

    }

    public void actionPerformed(ActionEvent e) {
        checkStats();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        //switch statement for moving bar left/right depending on which
        //key is pressed (left/right arrow)
        //if space bar is pressed pause/resume game
        switch (e.getKeyCode()) {
            case (VK_RIGHT):
                bar.moveRight();
                break;
            case (VK_LEFT):
                bar.moveLeft();
                break;
            case (VK_SPACE):
                if (running == GameState.PAUSED)
                    resumeGame();
                else if (running == GameState.PLAYING)
                    pauseGame();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (win) {
            timer.stop();
            g.setColor(Color.BLACK);
            g.fillRect(0,0,1000,1000);
            Font font = new Font ("Verdana", Font.BOLD, 27);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("YOU WIN",  200, 200);
            getHighScore();
        }
        else if (running == GameState.STOPPED) {
            timer.stop();
            g.setColor(Color.BLACK);
            g.fillRect(0,0,1000,1000);
            Font font = new Font("Verdana", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER",200,200);
            game.gameOver(getPoints());
        }
        else {
            setBackground(Color.BLACK);
            for (GameObject GO : GameObjectArray) {
                GO.draw(g);
            }
            for (Brick b : BrickList) {
                b.draw(g);
            }
            for (int i = 0; i < lives; i++) {
                g.drawImage(scale(heart, 20, 20), 550 - i * 25, 10, null);
            }
            game.update(points, getHighScore());
            if (!(running == GameState.PLAYING)) {
                Font font = new Font("Verdana", Font.BOLD, 20);
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("Welcome to brick breaker!! YOu are gay.", 50,
                        100);
                g.drawString("Move the bar using the mouse or left and right keys", 50,
                        200);
                g.drawString("Loser.", 50, 300);
            }
        }
    }

    //changes coordinates of ball so that next time the screen is repainted,
    //the ball will be drawn in that new place
    public void checkStats() {

        if (BrickList.size() == 0) {
            win = true;
        }
        else {
            if (ball.move(didItHitBrick(), didItHitBar(), whereHit(ball, findHitBrick()), bar)) {
                subtractLife();
                ball.reset();
            }
            colorChangeTimer++;
            if (colorChangeTimer % 5 == 0) {
                for (GameObject GO : GameObjectArray) {
                    GO.changeColor();
                }

                for (Brick b: BrickList) {
                    b.changeColor();
                }
            }
        }

    }
    private boolean didItHitBrick(){
        for(int i = 0; i < BrickList.size(); i++){ // did it hit a brick, and so if it did times hit will go up
            // checks if it's in both ranges
            // if true breaks out and does other stuff
            if (hit(ball, BrickList.get(i))) {
                addPoint();
                if (BrickList.get(i).wasHit() > 1)
                    BrickList.remove(i);
                return true;
            }
        }
        return false;
    }

    private Brick findHitBrick(){
        for(int i = 0; i < BrickList.size(); i++){
            if(hit(ball, BrickList.get(i))){
                return BrickList.get(i);
            }
        }
        Brick b = new Brick(4, 5);
        return b;
    }

    private int whereHit(Ball b, MyRectangle r){
        for(int i = 0; i <=b.getRadius(); i++){
            if(b.getX()+i == r.getX()-r.getWidth()/2 && b.getY() + i >= r.getY()-r.getHeight()/2 && b.getY() + i <= r.getY() + r.getHeight()/2)
                return 1;
            else if(b.getX()+i >= r.getX() - r.getWidth()/2 && b.getX() + i <= r.getX() + r.getWidth()/2 && b.getY() + i == r.getY()-r.getHeight()/2){
                return 2;
            }
            else if(b.getX() + i == r.getX() + r.getWidth()/2 && b.getY() + i >= r.getY() - r.getHeight()/2
                    && b.getY() + i <= r.getY() + r.getHeight()/2){
                return 3;
            }
            else if(b.getX()+i >= r.getX() - r.getWidth()/2 && b.getX() + i <= r.getX() + r.getWidth()/2 && b.getY() + i == r.getY() + r.getHeight()/2){
                return 4;
            }
        }
        return 0;
    }

    private boolean didItHitBar(){
        if(hit(ball, bar)){
            return true;
        }
        return false;
    }

    private void addPoint(){
        points++;
    }

    private boolean hit(Ball b, MyRectangle r){
        for(int i = 0; i <=b.getRadius(); i++){
            if(b.getX()+ i >= r.getX() - (r.getWidth()/2) && b.getX() + i <= r.getX() + r.getWidth()/2
                    && b.getY() + i >= r.getY()  - (r.getHeight()/2) && b.getY() + i <= r.getY() + r.getHeight()/2 ){
                return true;
            }
        }
        return false;
    }

    public void mouseMoved(MouseEvent e) {
        bar.moveToMouse(e.getX());
    }

    public GameState isRunning() {
        return running;
    }

    public void startGame() {
        win = false;
        lives = 3;
        ball.reset();
        bar.reset();
        setBackground(Color.BLACK);
        BrickList.clear();
        BrickList.add(new Brick(100 + 100, 100 + 50));
        for(int i  = 0; i < 5; i++) {
            for(int a = 0; a < 5; a++) {
                BrickList.add(new Brick(100 + 100*a, 100 + 50 * i));
            }
        }
        timer.start();
        points = 0;
        running = GameState.PLAYING;
    }

    public String getGameName() {
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

    public int getHighScore() {
        try {
            Scanner fileReader = new Scanner(new File(
                    "/Users/JessicaL/IdeaProjects/brick-breaker-project/src/highscores.txt"));
            highScore = fileReader.nextInt();
            fileReader.close();

            if (points > highScore) {
                PrintWriter writer = new PrintWriter(new File("highScores.txt"));
                writer.print(points);
                writer.close();
               highScore = points;
            }
        }
        catch (IOException e){
            System.out.println("file not found");
        }
        return highScore;
    }

    public void stopGame() {
        running = GameState.STOPPED;
    }

    public int getPoints() {
        return points;
    }

    public void subtractLife() {
        lives--;
        if (lives == 0) {
            running = GameState.STOPPED;
        }
    }

    //i literally don't know what this method does but i copy and
    //pasted it from stackoverflow and it works
    public static BufferedImage scale(BufferedImage heart, int w, int h) {
        BufferedImage img =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = heart.getWidth();
        int hh = heart.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = heart.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }

    public void setDisplay(GameStats d) {
        game = d;
        d.update(points, getHighScore());
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}