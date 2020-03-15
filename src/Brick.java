import java.awt.*;

public class Brick extends MyRectangle {
    private int direction, velocity, timesHit;
    public Brick(int x, int y) {
        super(x, y, 60, 30);
        direction = 0;
        velocity = 3;
    }

    public void wasHit(){
        timesHit++;
    }

    public void draw(Graphics g) {
        if(timesHit == 0) {
            Color oldColor = g.getColor();
            // g.setColor(super.getColor());
            g.setColor(super.returnNewColor());
            // Translates circle's center to rectangle's origin for drawing.
            if (super.isFilled())
                g.fillRect(super.getX() - super.getWidth() / 2, super.getY() - super.getHeight() / 2, super.getWidth(), super.getHeight());
            /*else
                g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
            //g.setColor(oldColor);
            g.setColor(super.returnNewColor());
        }
        else if(timesHit == 1){
            Color oldColor = g.getColor();
            // g.setColor(super.getColor());
            g.setColor(super.returnNewColor());
            // Translates circle's center to rectangle's origin for drawing.
            if (super.isFilled()) {
                g.fillRect(super.getX() - super.getWidth() / 2, super.getY() - super.getHeight() / 2, super.getWidth(), super.getHeight());
                g.drawLine(super.getX() - super.getWidth() / 2, super.getY() - super.getHeight() / 2,super.getX() - super.getWidth(), super.getY() - super.getHeight());
                g.drawLine(super.getX(), super.getY(),super.getX() - 30, super.getY() + 40);
                g.drawLine(super.getX() + super.getWidth(), super.getY() + super.getHeight(),super.getX() +60, super.getY() + 25);
            }
            /*else
                g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
            //g.setColor(oldColor);
            g.setColor(super.returnNewColor());
        }
        else{}
    }

/*
    public void move() {
        int xVal = getX();

        if (xVal + super.getWidth() > super.getPanelWidth()) {  //include getWidth() so we bounce off on the right edge
            direction = 0; //negative;
            xVal -= velocity;
        } else if (xVal - super.getWidth() < 0) {

            xVal += velocity;
            direction = 1; //positive
        } else {
            if (direction == 1)
                xVal += velocity;
            else
                xVal -= velocity;
        }
        super.setX(xVal);
    }*/


}
