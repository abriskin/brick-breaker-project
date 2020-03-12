import java.awt.*;

public abstract class GameObject {

    private int centerX, centerY;
    private Color color;
    private boolean filled;
    private static int panelWidth; //All enemies will share this information

    public GameObject(Color c, int x, int y) {
        color = c;
        centerX = x;
        centerY = y;
        filled = true;
    }

    public static void setPanelWidth(int w) {
        panelWidth = w;
    }
    public int isFilled(){
        return filled;
    }
    public int getX(){
        return centerX;
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
