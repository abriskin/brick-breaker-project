import java.awt.*;
public class Rectangle extends GameObject{
    private int width, height;
    private static int panelWidth; //All enemies will share this information

    public Rectangle(Color c, int x, int y, int w) {
        super(c, x, y);
        width = w;
        height = w/3;
        //games++;
    }

    public static void setPanelWidth(int w) {
        panelWidth = w;
    }
    public void draw(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
        if (super.getfilled)
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

        else if(xVal -  < 0){

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
