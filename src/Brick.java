import java.awt.*;
import java.awt.geom.Line2D;

public class Brick extends MyRectangle {
    private int direction, velocity, timesHit;
    public Brick(int x, int y) {
        super(x, y, 60, 30);
        direction = 0;
        velocity = 3;
    }

    public int wasHit(){
        timesHit++;
        return timesHit;
    }

    public int getTimesHit(){
        return timesHit;
    }

    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(super.getColor());
        if(timesHit == 0) {
            // Translates circle's center to rectangle's origin for drawing.
            if (super.isFilled())
                g.fillRect(super.getX() - super.getWidth() / 2, super.getY() - super.getHeight() / 2, super.getWidth(), super.getHeight());
        }
        else if(timesHit == 1){
            // Translates circle's center to rectangle's origin for drawing.
            if (super.isFilled()) {
                g.fillRect(super.getX() - super.getWidth() / 2, super.getY() - super.getHeight() / 2, super.getWidth(), super.getHeight());

                g.setColor(Color.BLACK);
                g.draw(new Line2D.Double(super.getX() + 17.5, super.getY() - 15, super.getX() + 21.5, super.getY() - 2));
                g.draw(new Line2D.Double(super.getX() + 20.5, super.getY() - 6, super.getX() + 15.5, super.getY() - 3.8));
                g.draw(new Line2D.Double(super.getX() + 15.5, super.getY() - 3.8, super.getX() + 16.5, super.getY() +.1));
                g.draw(new Line2D.Double(super.getX() + 16.5, super.getY() + .1, super.getX()+12, super.getY() +1.6));
                g.draw(new Line2D.Double(super.getX() + 21.5, super.getY() - 2 , super.getX()+19.5, super.getY() + .1));
                g.draw(new Line2D.Double(super.getX() + 19.5, super.getY() + .1, super.getX()+20, super.getY() + 4.91));
                g.draw(new Line2D.Double(super.getX() + 18, super.getY() + 6.5, super.getX()+20, super.getY() + 4.91));
                g.draw(new Line2D.Double(super.getX() + 19.5, super.getY() + .1, super.getX()+23.5, super.getY() + 1.64));
                g.draw(new Line2D.Double(super.getX() + 23.5, super.getY() + 1.64, super.getX() + 22.5, super.getY() + 3.82));
                g.draw(new Line2D.Double(super.getX() + 22.5, super.getY() + 3.82, super.getX() + 26, super.getY() + 3.82));
                g.draw(new Line2D.Double(super.getX() + 26, super.getY() + 3.82, super.getX() + 22.5, super.getY() + 7.1));



                g.draw(new Line2D.Double(super.getX() - 18.5, super.getY() + 15, super.getX()-15, super.getY() + 6));
                g.draw(new Line2D.Double(super.getX() - 16.5, super.getY() + 11.4, super.getX()-22, super.getY() + 8.73));
                g.draw(new Line2D.Double(super.getX() - 20.5, super.getY() + 9.82, super.getX()-16, super.getY() + 3.82));
                g.draw(new Line2D.Double(super.getX() - 17, super.getY() + 5.54, super.getX()-19.5, super.getY() + 2.73));
                g.draw(new Line2D.Double(super.getX() - 24.5, super.getY() + 10.4, super.getX()-22, super.getY() + 8.73));
                g.draw(new Line2D.Double(super.getX() - 24.5, super.getY() + 10.4, super.getX()-26.5, super.getY() + 6.5));
                g.draw(new Line2D.Double(super.getX() - 25, super.getY() + 8.18, super.getX()-23.5, super.getY() + 2.18));


            }
            /*else
                g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
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
