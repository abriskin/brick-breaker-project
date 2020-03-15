import java.awt.*;

public class Ball extends GameObject {
    private int radius;
    private int velocity, xDirection, yDirection;

    public Ball(int x, int y) {
        super(x, y);
        radius = 10; // Note, the radius is actually a diameter
        xDirection = 0;
        yDirection = 1;
        velocity = 15;
    }

    public void reset() {
        setXY(300, 400);
        velocity = 15;
        xDirection = 0;
        yDirection = 0;
    }

    public void draw(Graphics g) { // need to draw them in the right coordinates
        Color oldColor = g.getColor();
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
        g.fillOval(super.getX() - radius / 2, super.getY() - radius / 2, radius, radius);
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
        g.setColor(oldColor);
    }

    public int getRadius() {
        return radius;
    }

    ////hold up this needs editing.
    public void move(int where) {
        int xVal = getX();
        int yVal = getY();
        if (xVal + radius > super.getPanelWidth() || where == 1) {  //include getWidth() so we bounce off on the right edge
            xDirection = 0; //negative;
            xVal -= velocity;
            if (yDirection == 0)
                yVal += velocity;
            else
                yVal -= velocity;

        } else if (xVal - radius < 0 || where == 3){
            xVal += velocity;
            xDirection = 1; //positive
            if (yDirection == 0)
                yVal += velocity;
            else
                yVal -= velocity;

        } else if (yVal - radius < 0||where == 4) {
            yDirection = 1;
            yVal += velocity;
            if (xDirection == 0)
                xVal -= velocity;
            else
                xVal += velocity;
        } else if (yVal - radius > 450) {
            yVal = 400;
            xVal = 350;
            xDirection = 0;
            yDirection = 1;
            UserPanel.subtractLife();
        } else if (where == 2){
            yDirection = 0;
            yVal -= velocity;
            if(xDirection == 0)
                xVal -= velocity;
            else
                xVal += velocity;
        }else{
            if (xDirection == 1)
                xVal += velocity;
            else
                xVal -= velocity;

            if (yDirection == 0)
                yVal += velocity;
            else
                yVal += velocity;

        }
        super.setX(xVal);
        super.setY(yVal);
    }

    public void move(boolean hitBrick, boolean hitBar, int where, Bar b){
        int xVal = getX();
        int yVal = getY();
        if(hitBrick){
            move(where);
        }
        else if(hitBar){
            yDirection = 0;
            yVal -= velocity;
            if(xDirection == 0)
                xVal -= Math.abs(super.getX() - (b.getX() + 100));

            else
                xVal += Math.abs(super.getX() - (b.getX() + 100));
            super.setX(xVal);
            super.setY(yVal);
            //move(2);

        }
        else
            move(0);
    }

}
