import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class BoardGUI {

    private final int BoardH = 750;
    private final int BoardW =750;
    private int gameScore = 0;

    public BoardGUI(){
        JFrame jFrame = new JFrame();
        jFrame.setSize(getBoardH(),getBoardW());
        jFrame.setResizable(false);
        jFrame.setTitle("Snake");
        jFrame.setBackground(Color.black);
        jFrame.setVisible(true);
        // Set location where jframe appears - https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        jFrame.setLocationRelativeTo(null);
        Snake snake = new Snake();
        jFrame.add(snake);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }



    //accessor methods


    public int getBoardH() {
        return BoardH;
    }

    public int getBoardW() {
        return BoardW;
    }


}

