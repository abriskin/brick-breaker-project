import java.awt.*;

public class Ball extends GameObject{
    private int radius;
    private int direction, velocity;
    private int rounds;
    public Brick(int x, int y, int round) {
        super(Color.DARK_GRAY, x, y);
        radius = 10;
        direction = 0;
        rounds = round;
        velocity = 5 * round + 10;
    }

    public static void setPanelWidth(int w) {
        panelWidth = w;
    }
    public void draw(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
            g.fillOval(super.getX() - radius/2, super.getY() - radius/2, radius, radius);
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
        g.setColor(oldColor);
    }

////hold up this needs editing.
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


        if
    }

}
