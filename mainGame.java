
/**
 * Write a description of class MapDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainGame extends JFrame
{
    //sets which layout is displayed
    static int cardID = 1;
   
   
    Display d;
    
   
    public mainGame(int dif)
    {
       JPanel d  = new Display(dif);  
     
      add(d);
      
      
     
     



    }
   
    public void showGame()
    {
        this.setVisible(true);
    }
   
   
    public void hideGame()
    {
        this.setVisible(false);
    }
   
    /*
    public void keyPressed(KeyEvent e)
    {
        int x = e.getKeyCode();
        System.out.println(x);
        if (x == KeyEvent.VK_Q)
        {
           
            if (cardID == 1)
            {
                cardID = 2;
                c.show(cPane, "menuP");
                System.out.println("switch to menu");
            }
           
            else if (cardID == 2)
            {
                cardID = 1;
                c.show(cPane, "mainP");
                System.out.println("switch to main");
            }
        }
       
    }
    public void keyTyped(KeyEvent e)
    {}
    public void keyReleased(KeyEvent e)
    {}
   */  
 
}