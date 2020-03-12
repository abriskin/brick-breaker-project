import java.awt.*;

public abstract class GameObject {

    private int centerX, centerY;
    private Color color;
    private boolean filled;
    private static int panelWidth = 600; //All enemies will share this information

    public GameObject(Color c, int x, int y) {
        color = c;
        centerX = x;
        centerY = y;
        filled = true;
    }

    public static void setPanelWidth(int w) {
        panelWidth = w;
    }
    public boolean isFilled(){
        return filled;
    }
    public int getX(){
        return centerX;
    }

    public void setX(int x) {
        centerX = x;
    }
    public void setY(int y){
        centerY = y;
    }
    public int getY(){
        return centerY;
    }

    public Color getColor(){
        return color;
    }

    public int getPanelWidth(){
        return panelWidth;
    }

    public abstract void move();
    public abstract void draw(Graphics g);


}
