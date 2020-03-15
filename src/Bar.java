import java.awt.*;

public class Bar extends MyRectangle{
    private int direction, velocity;
 // move left and move right methods needed
    public Bar(int x, int y) {
        super(x, y, 100, 20);
        direction = 0;
        velocity = 0;
    }

    public void draw(Graphics g){
        g.setColor(super.getColor());
        // Translates circle's center to rectangle's origin for drawing.
        if (super.isFilled())
            g.fillRect(super.getX() - super.getWidth()/2, super.getY() - super.getHeight()/2, super.getWidth(), super.getHeight());
        /*else
            g.drawRect(centerX - width/2, centerY - height/2, width, height);*/
    }

    public void reset() {
        setXY(350, 425);
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
