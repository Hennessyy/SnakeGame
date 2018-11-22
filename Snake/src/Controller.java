import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Controller extends JPanel implements Runnable, KeyListener {

    //game loop attributes
    private Thread thread;
    private boolean running=true;
    //direction attributes.
    private boolean up = false;
    private boolean  down = false;
    private boolean left = false;
    private boolean right  = false;


    //Arraylist for the snake.
    private Snake s;
    private ArrayList<Snake> snake;

    //Arraylist for the food.
    private Food f;
    private ArrayList<Food>foods;

    //x and y cords for where the head of the snake will appear.
    private int xLocation=10;
    private int yLocation=10;
    //size the snake will be at the beginning of the game.
    private int length =3;

    private int ticks = 0;






    public Controller(){

        setFocusable(true);

        addKeyListener(this);

        snake = new ArrayList<Snake>();
        foods = new ArrayList<Food>();

        start();

    }


    // REF - https://www.youtube.com/watch?v=nhGbFcIW7bc - where I learned about game start and stop methods & threads.


    public synchronized void start(){
        running = true;
        thread = new Thread(this,"Game Loop");
        thread.start();

    }

    //synchronized header makes sure only one thread can be executed at a time.
    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        }catch (InterruptedException interrupt){
            interrupt.printStackTrace();
        }

    }

    //tick method is responsible for updating the game. - Now working!

    public void tick(){
        System.out.println("running");
        //If there is nothing in the snake arraylist we create a new snake.
        if (snake.size()==0){
            s = new Snake(xLocation,yLocation,10);
            snake.add(s);
        }

        //Trying to generate food to the board. -- Now fixed! - Had void header in Food

        if(foods.size()==0){
            //creating a random location for the new piece of food.
                int locX = (int) (Math.random() * 60);
                int locY = (int) (Math.random() * 60);

                f = new Food(locX,locY,10);

                foods.add(f);
        }

        for (int j=0;j<foods.size();j++){
            //if the food is eaten it is removed from the array and the length of the snake is increased.
            if(xLocation == foods.get(j).getLocationX() && yLocation == foods.get(j).getLocationY()){
                length++;
                foods.remove(j);
                j--;


            }
        }



        ticks++;
        // ticks are responsible for speed of snake.
        if(ticks > 9000){
            //changing the coordinates of the snake
            if(right) xLocation++;
            if(left) xLocation--;
            if(up) yLocation--;
            if(down) yLocation++;

            ticks = 0;

            //adds one piece to the snake and removes the piece in the previous spot.
            s = new Snake(xLocation,yLocation,10);
            snake.add(s);


            if(snake.size() > length){
                snake.remove(0);

            }


        }
    }




    public void paint(Graphics grap){
        grap.clearRect(0,0,getWidth(),getHeight());
        grap.setColor(Color.BLACK);


        for(int i=0; i<getWidth()/10;i++){
            grap.drawLine(i*10,0,i*10,getHeight());
        }

        for(int i=0;i<getHeight()/10;i++){
            grap.drawLine(0,i*10,getWidth(),i*10);
        }

        for(int i=0;i<snake.size();i++){
            snake.get(i).draw(grap);
        }


        for (int i=0;i<foods.size();i++){
            foods.get(i).draw(grap);
        }


    }

    // A - Left Turn
    //D - Right Turn
    //W - Up
    //S- Down





    //I found out how to stop snake from turning back in its current direction from this source - https://stackoverflow.com/questions/31552958/snake-game-how-to-stop-the-game-when-the-snake-eat-itself

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();


        if(key == KeyEvent.VK_D && left == false){
            up = false;
            down= false;
            right= true;
        }

        if(key == KeyEvent.VK_A && right == false){
            up =false;
            down=false;
            left=true;
        }

        if(key == KeyEvent.VK_W && down == false){
            up =true;
            left=false;
            right=false;
        }

        if(key == KeyEvent.VK_S && up == false){
            down=true;
            left=false;
            right=false;
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void run() {
        while(running){
            tick();
            repaint();
        }

    }












}
