import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Snake extends JPanel implements Runnable, KeyListener {

    /*Snake attributes -Snakes head will not increase but the body will - Snake needs to be able to turn left,right,up and down initially these will all be false
    as the snake will not be moving, later I will have to implement some type of way to detect keystroke and based off the keystrokes direction the snake will have
    to move in the specific direction.
     */

    private int[] snakelength = new int[100];
    private int snakehead = 1;
    private boolean up = false;
    private boolean  down = false;
    private boolean left = false;
    private boolean right  = false;

    //game loop
    private Thread thread;
    private boolean running;



    //no arg constructor
    public Snake(){

        start();

    }


    // REF - https://www.youtube.com/watch?v=nhGbFcIW7bc - where I learned about game start and stop methods & threads.


    public synchronized void start(){
        running = true;
        thread = new Thread();
        thread.start();

    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        }catch (InterruptedException interrupt){
            interrupt.printStackTrace();
        }

    }

    public void tick(){

    }


    //Background colour now working for game.

    public void paint(Graphics grap){
        grap.clearRect(0,0,getHeight(),getWidth());
        grap.setColor(Color.BLACK);
        grap.fillRect(0,0,getHeight(),getWidth());


    }




    //KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //runnable method

    @Override
    public void run() {


    }



}
