import java.awt.*;

public class Ball extends GameObject{
    private int radius;
    private int velocity, xDirection, yDirection;
    public Ball(int x, int y, int round) {
        super(x, y);
        radius = 10;
        xDirection = 0;
        yDirection = 0;
        velocity = 5 * round + 10;
    }

    public void draw(Graphics g){ // need to draw them in the right coordinates
        Color oldColor = g.getColor();
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
            g.fillOval(super.getX() - radius/2, super.getY() - radius/2, radius, radius);
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
        g.setColor(oldColor);
    }

////hold up this needs editing.
    public void move() {
        int xVal = getX();
        int yVal = getY();
        if(xVal + radius > super.getPanelWidth()){  //include getWidth() so we bounce off on the right edge
            xDirection=0; //negative;
            xVal-=velocity;
            //if()//TODO fill in the y coordinates and values so that they change accordinly.
        }
        else if(xVal - radius < 0){

            xVal+=velocity;
            xDirection = 1; //positive
        }
        else
        {
            if(xDirection >= 1)
                xVal+=velocity;
            else
                xVal-=velocity;
        }
        super.setX(xVal);

    }

}
