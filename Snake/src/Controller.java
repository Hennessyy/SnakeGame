import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Controller extends JPanel implements Runnable, KeyListener {

    //game loop attributes
    private Thread thread;
    private boolean running = true;
    //direction attributes.
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;


    //Arraylist for the snake.
    private Snake s;
    private ArrayList<Snake> snake;

    //Arraylist for the food.
    private Food f;
    private ArrayList<Food> foods;

    //x and y cords for where the head of the snake will appear.
    private int xLocationn = 37;
    private int yLocationn = 30;
    //size the snake will be at the beginning of the game.
    private int length = 3;

    private int ticks = 0;

    //game states

    public static Menu menu;

    public enum STATE{
    MENU,
    CONTROLLER
    }

    public static STATE State = STATE.MENU;



    public Controller() {

        menu = new Menu();

        setFocusable(true);
        addMouseListener(new MouseInput());
        addKeyListener(this);

        snake = new ArrayList<Snake>();
        foods = new ArrayList<Food>();

        start();

    }

    //tick method is responsible for updating the game. - Now working!

    public void tick() {



        if(State == STATE.CONTROLLER){

            //If there is nothing in the snake arraylist we create a new snake.
            if (snake.size() == 0) {
                s = new Snake(xLocationn, yLocationn, 10);
                snake.add(s);
            }

            //Trying to generate food to the board. -- Now fixed! - Had void header in Food

            if (foods.size() == 0) {
                //creating a random location for the new piece of food.
                int locX = (int) (Math.random() * 60);
                int locY = (int) (Math.random() * 60);

                f = new Food(locX, locY, 10);

                foods.add(f);
            }

            for (int j = 0; j < foods.size(); j++) {
                //if the food is eaten it is removed from the array and the length of the snake is increased.
                if (xLocationn == foods.get(j).getLocationX() && yLocationn == foods.get(j).getLocationY()) {
                    length++;
                    foods.remove(j);
                    j--;


                }
            }

            //checks all 4 sides of the board and if they are over or under the amount it brings the snake out
            //the other side of the board.
            if (xLocationn < 0 || xLocationn > 74 || yLocationn < 0 || yLocationn > 74) {
                if (xLocationn < 0) xLocationn = 74;
                if (xLocationn > 74) xLocationn = 0;
                if (yLocationn < 0) yLocationn = 74;
                if (yLocationn > 74) yLocationn = 0;
            }


            ticks++;
            // ticks are responsible for speed of snake.
            if (ticks > 200000) {
                //changing the coordinates of the snake
                if (right) xLocationn++;
                if (left) xLocationn--;
                if (up) yLocationn--;
                if (down) yLocationn++;

                ticks = 0;

                //adds one piece to the snake and removes the piece in the previous spot.
                s = new Snake(xLocationn, yLocationn, 10);
                snake.add(s);


                if (snake.size() > length) {
                    snake.remove(0);

                }


            }
        }else if(State == STATE.MENU){
            menu.render(getGraphics());
        }
    }

    public void paint(Graphics grap) {

        if(State == STATE.CONTROLLER)

        grap.fillRect(0, 0, getWidth(), getHeight());
        grap.setColor(Color.BLACK);


        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(grap);
        }


        for (int i = 0; i < foods.size(); i++) {
            foods.get(i).draw(grap);
        }


    }

    // REF - https://www.youtube.com/watch?v=nhGbFcIW7bc - where I learned about game start and stop methods & threads.


    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Game Loop");
        thread.start();

    }

    //synchronized header makes sure only one thread can be executed at a time.
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException interrupt) {
            interrupt.printStackTrace();
        }

    }


    // A - Left Turn
    //D - Right Turn
    //W - Up
    //S- Down

    //I found out how to stop snake from turning back in its current direction from this source - https://stackoverflow.com/questions/31552958/snake-game-how-to-stop-the-game-when-the-snake-eat-itself

    @Override
    public void keyPressed(KeyEvent e) {

        if (State == STATE.CONTROLLER) {

            int key = e.getKeyCode();


            if (key == KeyEvent.VK_D && left == false) {
                up = false;
                down = false;
                right = true;
            }

            if (key == KeyEvent.VK_A && right == false) {
                up = false;
                down = false;
                left = true;
            }

            if (key == KeyEvent.VK_W && down == false) {
                up = true;
                left = false;
                right = false;
            }

            if (key == KeyEvent.VK_S && up == false) {
                down = true;
                left = false;
                right = false;
            }

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


            while (running) {

                tick();
                repaint();
                }

    }

}
