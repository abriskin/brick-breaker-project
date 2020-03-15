import java.awt.*;

public class Bar extends Rectangle{
    private int direction, velocity;
 // move left and move right methods needed
    public Bar(int x, int y, int rounds) {
        super(x, y, 100, 20);
        direction = 0;
        velocity = 0;
    }

    public void draw(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
        if (super.isFilled())
            g.fillRect(super.getX() - super.getWidth()/2, super.getY() - super.getHeight()/2, super.getWidth(), super.getHeight());
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
        g.setColor(oldColor);
    }

    ////????????????????Am I doing a move? Not doing a move?
   public void move(){
        int xVal = super.getX();
        if(xVal + super.getWidth() > super.getPanelWidth()){  //include getWidth() so we bounce off on the right edge
            direction=0; //negative;
            xVal-=velocity;
        }
        else if(xVal - super.getWidth() < 0){

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
        super.setX(xVal);
    }

    public void moveToMouse(int x) {
        super.setX(x);
    }

    public void  moveRight(){
        super.addToX(20);
    }

    public void moveLeft(){
        super.addToX(-20);
    }
}
