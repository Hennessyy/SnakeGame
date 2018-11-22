import java.awt.*;

public class Menu {


    //Creating main menu which will contain 3 buttons - Play, Highscore and Quit
    //Also wish to include some background music .  - REF https://www.youtube.com/watch?v=FZWX5WoGW00&t=247s


    public Rectangle playButton = new Rectangle(315,150,150,80);
    public Rectangle highScoresButton = new Rectangle(240,300,310,80);
    public Rectangle quitButton = new Rectangle(315,450,150,80);


    public void render(Graphics graphics){




        Font fnt = new Font("arial",Font.BOLD,70);
        graphics.setFont(fnt);
        graphics.setColor(Color.BLACK);
        graphics.drawString("S N A K E", 220,100);

        Font fnt1 = new Font("arial",Font.BOLD,50);
        graphics.setFont(fnt1);
        graphics.drawString("Play", playButton.x + 20,playButton.y+55);
        graphics.drawString("Highscores", highScoresButton.x + 20,highScoresButton.y+55);
        graphics.drawString("Quit", quitButton.x + 20,quitButton.y+55);


        Graphics2D g2D = (Graphics2D) graphics;

        g2D.draw(playButton);
        g2D.draw(highScoresButton);
        g2D.draw(quitButton);
    }



}
