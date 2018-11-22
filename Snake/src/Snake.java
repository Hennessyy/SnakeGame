
import java.awt.*;



public class Snake{

    /*Snake attributes -Snakes head will not increase but the body will - Snake needs to be able to turn left,right,up and down initially these will all be false
    as the snake will not be moving, later I will have to implement some type of way to detect keystroke and based off the keystrokes direction the snake will have
    to move in the specific direction.
     */

    public Snake(int xLocation,int yLocation,int tileSize){
        this.xLocation = xLocation;
        this.yLocation =yLocation;
        ht=tileSize;
        wd=tileSize;

    }

    private int xLocation;
    private int yLocation;
    private int wd;
    private int ht;

    public void draw(Graphics grap){

        grap.fillRect(xLocation*wd+2,yLocation*ht+2,wd-8,ht-8);
        grap.setColor(Color.BLUE);
        grap.fillRect(xLocation*wd+2,yLocation*ht+2,wd-4,ht-4);
    }


    //mutator methods

    public void setxCord(int xLocation) {
        this.xLocation = xLocation;
    }

    public void setyCord(int yLocation) {
        this.yLocation = yLocation;
    }


    public void setHt(int ht) {
        this.ht = ht;
    }


    public void setWd(int wd) {
        this.wd = wd;
    }

    //accessor methods
    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public int getHt() {
        return ht;
    }

    public int getWd() {
        return wd;
    }
}
