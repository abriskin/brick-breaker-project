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
import java.awt.Point;
import java.awt.Rectangle;

import static java.awt.event.KeyEvent.*;

public class UserPanel extends JPanel implements JavaArcade, MouseListener,
        MouseMotionListener, ActionListener, KeyListener {

    private boolean win, lose;
    private GameState running;
    private ArrayList<Brick> BrickList;
    private Ball ball;
    private Bar bar;
    private BufferedImage heart;
    private int points, mouseX, mouseY;
    private static int lives;
    private int highScore = 0;
    Timer timer;

    public UserPanel(int width, int length) {
        bar = new Bar(300, 425);
        ball = new Ball(300, 400);
        BrickList = new ArrayList<Brick>();
        try {
            heart = ImageIO.read(new File("uglyheart.jpg"));
        } catch (IOException e) {
            System.out.println("heart file invalid");
        }
        //"this", i.e. UserPanel, is both a MouseListener and a MouseMotionListener
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        timer = new Timer(40, this);

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
            g.setColor(Color.BLACK);
            g.fillRect(0,0,1000,1000);
            g.setColor(Color.WHITE);
            g.drawString("YOU WIN",  200, 200);
            win = false;
        }
        else if (lose) {
            timer.stop();
            Font font = new Font ("Verdana", Font.BOLD, 27);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.fillRect(0,0,1000,1000);
            g.setColor(Color.WHITE);
            g.drawString("YOU LOSE",200,200);
            lose = false;
        }
        else {
            for (Brick b : BrickList) {
                b.draw(g);
            }
            for (int i = 0; i < lives; i++) {
                g.drawImage(scale(heart, 20, 20), 530 + i * 25, 10, null);
            }
            ball.draw(g);
            bar.draw(g);

            for (int i = 0; i < BrickList.size(); i++) {
                BrickList.get(i).draw(g);
            }

            if (!(running == GameState.PLAYING)) {
                Font font = new Font("Verdana", Font.BOLD, 27);
                g.setFont(font);
                g.drawString("Welcome to brick breaker!! YOu are gay. Move the" +
                                "bar using the mouse or left and rihgt keys. Loser.", 100,
                        250);
            }
        }
    }

    //changes coordinates of ball so that next time the screen is repainted,
    //the ball will be drawn in that new place
    public void checkStats() {
        /*if(didItHitBrick())
            Brick b = findHitBrick();*/
        if (BrickList.size() == 0)
            win = true;
        else {
            ball.move(didItHitBrick(), didItHitBar(), whereHit(ball, findHitBrick()), bar);
            ball.changeColor();
            for (Brick b : BrickList) {
                b.changeColor();
            }
            bar.changeColor();
        }

    }
    private boolean didItHitBrick(){
        for(int i = 0; i < BrickList.size(); i++){ // did it hit a brick, and so if it did times hit will go up
            // checks if it's in both ranges
            // if true breaks out and does other stuff
            if (hit(ball, BrickList.get(i))) {
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
        return BrickList.get(0);
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
                addPoint();
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
        //BrickList.add(new Brick(100 + 100*3, 100 + 150));
        for(int i  = 0; i < 5; i++) {
            for(int a = 0; a < 5; a++) {
                BrickList.add(new Brick(100 + 100*a, 100 + 50 * i));
            }
        }
        timer.start();
        lives = 3;
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
        int highScore = 0;
        try {
            Scanner fileReader = new Scanner(new File("C:\\Users\\" +
                    "jessi\\IdeaProjects\\brick-breaker-project\\src\\" +
                    "highscores.txt"));
            highScore = fileReader.nextInt();
            fileReader.close();
            if (highScore > points) {
                PrintWriter writer = new PrintWriter(new File("highScores.txt"));
                writer.println(points);
                writer.close();
            }
        }
        catch (IOException e){
            System.out.println("file not found");
        }

        return highScore;
    }

    public void stopGame() {
        points = 0;
        running = GameState.STOPPED;
        lose = true;
    }

    public int getPoints() {
        return points;
    }

    public void subtractLife() {
        lives--;
        if (lives == 0) {
            lose = true;
        }
    }

    public void updateMouseCoordinates(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
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
        d.update(points);
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