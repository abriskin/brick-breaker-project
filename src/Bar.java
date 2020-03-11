import java.awt.*;

public class Bar extends Rectangle{
    private int direction, velocity;
 // move left and move right methods needed
    public Bar(int x, int y) {
        super();
    }

    public void draw(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(super.color);
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
    ////????????????????Am I doing a move? Not doing a move?
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

    public void moveToMouse(int x, int y) {
    }

    public void  moveRight(){

    }

    public void moveLeft(){

    }
}
