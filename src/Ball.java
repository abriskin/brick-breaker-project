import java.awt.*;

public class Ball extends GameObject {
    private int radius;
    private int velocity, xDirection, yDirection;

    public Ball(int x, int y) {
        super(x, y);
        radius = 20; // Note, the radius is actually a diameter
        xDirection = 0;
        yDirection = 1;
        velocity = 3;
    }

    public void reset() {
        setXY(300, 400);
        velocity = 3;
        xDirection = 0;
        yDirection = 0;
    }

    public void draw(Graphics g) { // need to draw them in the right coordinates
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
        g.fillOval(super.getX() - radius / 2, super.getY() - radius / 2, radius, radius);
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
    }

    public int getRadius() {
        return radius/2;
    }

    //returns true if ball hit floor, false otherwise
    public boolean move(int where) {
        boolean hitFloor = false;
        int xVal = super.getX();
        int yVal = super.getY();
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
            hitFloor = true;
        } else if (where == 2){
            yDirection = 0;
            yVal -= velocity;
            if(xDirection == 0)
                xVal -= velocity;
            else
                xVal += velocity;
        } else {
            if (xDirection == 1)
                xVal += velocity;
            else
                xVal -= velocity;

            if (yDirection == 0)
                yVal -= velocity;
            else
                yVal += velocity;

        }
        super.setX(xVal);
        super.setY(yVal);
        return hitFloor;
    }

    public boolean move(boolean hitBrick, boolean hitBar, int where, Bar b){
        int xVal = super.getX();
        int yVal = super.getY(); // TODO: change it back to getX and getY
        if (hitBrick) {
            return move(where);
        }
        else if (hitBar){
            yDirection = 0;
            yVal -= velocity;
            if(xDirection == 0)
                xVal -= Math.abs(super.getX() - (b.getX() + 50));
            else
                xVal += Math.abs(super.getX() - (b.getX() + 50));
            super.setX(xVal);
            super.setY(yVal);
            return false;
        }
        else
            return move(0);
    }

}
