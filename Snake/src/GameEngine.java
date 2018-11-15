import javax.swing.*;
import java.awt.*;

public class GameEngine {
    public static void main(String[] args) {


        JFrame jFrame = new JFrame();
        jFrame.setSize(750,750);
        //background colour is not displaying?
        jFrame.setBackground(Color.black);
        jFrame.setResizable(false);
        jFrame.setTitle("Snake");
        jFrame.setVisible(true);
        // Set location where jframe appears - https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }
}
