import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {


    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();



        //Play button cords  -- Working
        if(mx >= 315 && mx <= 465){
            if(my >=150 &&my <= 220){
                Controller.State = Controller.STATE.CONTROLLER;
            }

        }


        //Highscores Button

        if(mx >= 315 && mx <= 465){
            if(my >=300 &&my <= 370){
                JOptionPane.showMessageDialog(null,"HIGHSCORES          SCORE\n\nRANK 1 - PLAYER X  - 2510\n\nRANK 2 - PLAYER Y - 2250\n\nRANK 3 - PLAYER Z - 1990");
            }

        }





        //Quit Button -- Working
        if(mx >= 315 && mx <= 465){
            if(my >=450 &&my <= 530){
                System.exit(0);
            }

        }





    }
    public void mouseClicked(MouseEvent e) {

    }



    public void mouseEntered(MouseEvent e) {

    }



    public void mouseExited(MouseEvent e) {

    }







    public void mouseReleased(MouseEvent e) {

    }
}
