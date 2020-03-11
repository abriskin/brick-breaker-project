import java.awt.*;

public abstract class Rectangle {
    private int width, height;
    private boolean filled;
    private static int panelWidth; //All enemies will share this information
    private static int games = 0;

    public Rectangle(Color c, int x, int y, int w) {
        super(c, x, y);
        width = w;
        height = w/3;
    }

    public static void setPanelWidth(int w) {
        panelWidth = w;
    }
    public void draw(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
        if (filled)
            g.fillRect(centerX - width/2, centerY - height/2, width, height);
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
        g.setColor(oldColor);
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


}
