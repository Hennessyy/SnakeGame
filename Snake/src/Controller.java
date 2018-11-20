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
        food = new ArrayList<Food>();

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

        //Trying to generate food to the board. -- Nothing working..

        if(foods.size()==0){

            int locX;
            int locY;

            for (int i = 0;i<1; i++)
            {
                locX = (int)(Math.random()*80);
                locY = (int)(Math.random()*80);
                f = new Food(locX,locY,10);
                foods.add(f);

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

    }

    // A - Left Turn
    //D - Right Turn
    //W - Up
    //S- Down


    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();


        if(key == KeyEvent.VK_D ){
            up = false;
            down= false;
            left= false;
            right= true;
        }

        if(key == KeyEvent.VK_A){
            up =false;
            down=false;
            left=true;
            right=false;
        }

        if(key == KeyEvent.VK_W){
            up =true;
            down=false;
            left=false;
            right=false;
        }

        if(key == KeyEvent.VK_S){
            up =false;
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
