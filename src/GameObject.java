import java.awt.*;

public abstract class GameObject {

    private int centerX, centerY;
    private Color color;
    private boolean filled;
    private static int panelWidth = 600; //All enemies will share this information
    Color[] colorList = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW,
            Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN};

    public GameObject(int x, int y) {
        centerX = x;
        centerY = y;
        filled = true;
        changeColor();
    }

    public static void setPanelWidth(int w) {
        panelWidth = w;
    }
    public boolean isFilled(){
        return filled;
    }
    public int getX(){
        return centerX;
    }

    public void addToX(int x){
        centerX += x;
    }
    public void setX(int x) {
        centerX = x;
    }
    public void setY(int y){
        centerY = y;
    }
    public void setXY(int x, int y) {
        centerX = x;
        centerY = y;
    }
    public int getY(){
        return centerY;
    }

    public Color getColor(){
        return color;
    }
    private void changeColor() {
        int index =0;
        for(int i = 0; i < colorList.length; i++) {
            if (colorList[i] == color)
                index = i;
        }
        color = colorList[index+1];
    }
    public Color returnNewColor(){
        int index =0;
        for(int i = 0; i < colorList.length; i++) {
            if (colorList[i] == color)
                index = i;
        }
        return colorList[index+1];
    }

    public int getPanelWidth(){
        return panelWidth;
    }

    public abstract void move();
    public abstract void draw(Graphics g);

}
