import java.awt.*;

public class Rectangle extends GameObject{
    private int width, height;

    public Rectangle(int x, int y, int w, int h) {
        super(x, y);
        width = w;
        height = h;
    }
///check for the other screen

    public void draw(Graphics g){
        Color oldColor = g.getColor();
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
        if (super.isFilled())
            g.fillRect(super.getX() - width/2, super.getY() - height/2, width, height);
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
        g.setColor(oldColor);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void move(){
    }

}
