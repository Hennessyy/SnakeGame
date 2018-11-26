
import java.awt.*;



public class Snake{

    /*Snake attributes -Snakes head will not increase but the body will - Snake needs to be able to turn left,right,up and down initially these will all be false
    as the snake will not be moving, later I will have to implement some type of way to detect keystroke and based off the keystrokes direction the snake will have
    to move in the specific direction.
     */

    /**
     *Snake construtor method which takes in
     * 3 parameters and assigns them to the x and y location
     * and the height and width of the snake.
     *
     * @param xLocation
     * @param yLocation
     * @param tileSize
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

    /**
     * Draw method which uses graphics to draw the
     * the snake to the board.
     *
     *
     * @param grap
     */

    public void draw(Graphics grap){

        grap.fillRect(xLocation*wd+2,yLocation*ht+2,wd-8,ht-8);
        grap.setColor(Color.BLUE);
        grap.fillRect(xLocation*wd+2,yLocation*ht+2,wd-4,ht-4);
    }


    //mutator methods

    /**
     *Mutator method to assign the xLocation to the snake object
     *
     * @param xLocation
     */

    public void setxCord(int xLocation) {
        this.xLocation = xLocation;
    }

    /**
     * Mutator method to assign the yLocation to the snake object
     *
     *
     * @param yLocation
     */

    public void setyCord(int yLocation) {
        this.yLocation = yLocation;
    }


    /**
     *Mutator method to assign the height to the snake object
     *
     * @param ht
     */

    public void setHt(int ht) {
        this.ht = ht;
    }


    /**
     *Mutator method to assign the width to the snake object
     *
     * @param wd
     */

    public void setWd(int wd) {
        this.wd = wd;
    }




    //accessor methods


    /**
     *Accessor method to assess the xLocation to the snake object
     *
     */
    public int getxLocation() {
        return xLocation;
    }

    /**
     * Accessor method to assess the yLocation to the snake object
     *
     *
     */

    public int getyLocation() {
        return yLocation;
    }

    /**
     *Accessor method to assess the height to the snake object
     *
     */
    public int getHt() {
        return ht;
    }

    /**
     *Accessor method to assess the width to the snake object
     *
     */

    public int getWd() {
        return wd;
    }
}
