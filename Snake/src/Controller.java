import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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



    //attribute for gamescore


    private int gamescore=0;


    //sound effect for snake eating food -  https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java -- Didn't work for me

   /* String bip = "C:\\Users\\Leon\\Desktop\\projects\\Snake\\assets\\munch.mp3";
    Media hit = new Media(new File(bip).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(hit);*/










    public Controller() {




        menu = new Menu();




        setFocusable(true);


        addMouseListener(new MouseInput());


        addKeyListener(this);




        snake = new ArrayList<Snake>();


        foods = new ArrayList<Food>();




        start();



    }




    //JB added code below to check to see whether the snake's head collides with some part of the snakes body
    //if it does return true and exit immediately, with a suitable message


    public boolean snakeCollidesWithItself()

    {

        Snake snakeHead = snake.get(0);



        for(int i=1;i<snake.size();i++)

            if(snakeHead.getxLocation()==snake.get(i).getxLocation() && snakeHead.getyLocation()==snake.get(i).getyLocation())

            {

                System.out.println("Collision has occurred!!!");

                return true;

            }




        return false;

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


                   // mediaPlayer.play();

                    gamescore=gamescore+10;

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


            if (ticks > 10) {


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




            //Code Added by JB - if the length of the snake exceeds 7 (too short to collide otherwise plus


            //it causes issues at snake creation time if you omit this test) and the snake head collides with


            //snake body, put out a suitable message and stop game thread (you might want something else to happen


            //at this point Leon)




            if(length>3 && snakeCollidesWithItself())


            {


                System.out.println("The snake has collided with itself!!! Game Over!!!");

                thread.stop();


            }

        }

    }



    public void paintComponent(Graphics grap) { //modified by JB from paint() to paintComponent() to override the JPanel paintComponent()


        //paint() is usually only overridden by top-level containers in Java such as JFrame, JDialog etc


        //but it is very unusual to have to draw directly onto these containers - the call to repaint()


        //in the loop will call the paint() method of the JFrame which in turn calls paintComponent() on the


        //JPanel holding your game drawings





        super.paintComponent(grap); //added by JB to paint background of panel in advance of other drawing on panel




        if(State == STATE.CONTROLLER){




            grap.fillRect(0, 0, getWidth(), getHeight());


            grap.setColor(Color.BLACK);





            for (int i = 0; i < snake.size(); i++) {

                snake.get(i).draw(grap);

            }



            for (int i = 0; i < foods.size(); i++) {

                foods.get(i).draw(grap);

            }


            grap.setColor(Color.WHITE);
            grap.drawString("Score : "+gamescore,10,10);


        }




        else


            //menu.render(getGraphics()); //moved by JB from tick() to paintComponent() as it is to do with drawing


            menu.render(grap); //Code modified by JB to get the menu rendering successfully



    }




    // REF -  https://www.youtube.com/watch?v=nhGbFcIW7bc - where I learned about game start and stop methods & threads.






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




    //I found out how to stop snake from turning back in its current direction from this source -  https://stackoverflow.com/questions/31552958/snake-game-how-to-stop-the-game-when-the-snake-eat-itself




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




            //JB added code here to ensure the game update/painting thread gets put to sleep


            //for a while so the CPU gets a break (its in constant use otherwise eating up the CPU time)


            //this sleep value can be reduced if you like and then you can tweak the test condition in the


            //tick() method to get a snake speed you are happy with




            try


            {


                Thread.sleep(10);


            }


            catch(InterruptedException ie)


            {


                System.out.println("Game thread was interrupted!");


                ie.printStackTrace();


            }


        }



    }



}
