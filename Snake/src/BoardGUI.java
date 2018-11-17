import javax.swing.*;
import java.awt.*;

public class BoardGUI {

    private final int BoardH = 750;
    private final int BoardW =750;
    private int gameScore = 0;

    public BoardGUI(){
        JFrame jFrame = new JFrame();
        jFrame.setSize(getBoardH(),getBoardW());
        jFrame.setResizable(false);
        jFrame.setTitle("Snake");
        //background colour not working?
        jFrame.setBackground(Color.black);
        jFrame.setVisible(true);
        // Set location where jframe appears - https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Snake snake = new Snake();
        jFrame.add(snake);
    }



    //accessor methods


    public int getBoardH() {
        return BoardH;
    }

    public int getBoardW() {
        return BoardW;
    }
}

