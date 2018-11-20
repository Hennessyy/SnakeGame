import java.awt.*;
public class Food {

    /*Creating attributes for where the location of the food will be generated, this will be done using a Math.random
    * function. I will have to take into consideration that if the snake is currently in the position of the newly
    * generated food that it will generate a new food in a different location. - This will involve some type of
    * collision detection.*/

    private int locationX;
    private int  locationY;
    private int wd;
    private int ht;


    //constructor

    public void Food(int locationX,int locationY,int tileSize){
        this.locationX=locationX;
        this.locationY=locationY;
        wd=tileSize;
        ht=tileSize;
    }



    //mutator methods
    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }


    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    //accessor methods
    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    //draw method

    public void draw(Graphics grap){
        grap.setColor(Color.GREEN);
        grap.fillRect(locationX*wd,locationY*ht,wd,ht);

    }




}

