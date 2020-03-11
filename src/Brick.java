import java.awt.*;
public class Brick extends Rectangle{
    private int centerX, centerY, width, height;
    private Color color;
    private int direction, velocity;
    private boolean filled;
    private static int panelWidth; //All enemies will share this information
    private static int games = 0;

    public Brick(Color c, int x, int y, int w) {
        color = c;
        width = w;
        height = w/3;
        centerX = x;
        centerY = y;
        filled = true;
        direction = 0;
        games++;
        velocity = 5 * games;

    }

    public static void setPanelWidth(int w) {
        panelWidth = w;
    }
    public void draw(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(color);
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

    public void move(){
        int xVal = getX();

        if(xVal + width > panelWidth){  //include getWidth() so we bounce off on the right edge
            direction=0; //negative;
            xVal-=velocity;
        }

        else if(xVal - width < 0){

            xVal+=velocity;
            direction = 1; //positive
        }

        else
        {
            if(direction == 1)
                xVal+=velocity;
            else
                xVal-=velocity;
        }
        centerX = xVal;
    }

}
