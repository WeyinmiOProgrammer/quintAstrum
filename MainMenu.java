
/**
 * Write a description of class worldTraversal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class MainMenu extends JFrame
{
    JButton start, setDifficulty, quit;
   
    JLabel title;
    int difficulty = 0;
    ImageIcon titleImage;
   
    public MainMenu()
    {
       
        setLayout(new GridLayout(4,1,5,1));
        //title = new JLabel("Quintastrum");
        //add(title);
        titleImage = new ImageIcon(getClass().getResource("titlescr.png"));
        Image img = titleImage.getImage();
        Image newImg = img.getScaledInstance(420, 160,  java.awt.Image.SCALE_SMOOTH);
        titleImage = new ImageIcon(newImg);
        title = new JLabel(titleImage);
        add(title);
        start = new JButton("Start game");
        add(start);
        setDifficulty = new JButton("Difficulty: Easy");
        add(setDifficulty);
        quit = new JButton("Close");
        add(quit);
       
        //this makes the buttons have a function
       
        event x = new event();
        start.addActionListener(x);
        setDifficulty.addActionListener(x);
        quit.addActionListener(x);
    }
   
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent x)
        {
            String optionCheck = x.getActionCommand();
           
            if (optionCheck.equals("Start game"))
            {
                //opens the game and closes the menu
                //changed to make a new instance of mapDisplay
                mainGame z = new mainGame(difficulty);
                z.setVisible(true);
               
                //testing if this method works
                z.setSize(900,720);
                z.setTitle("QUINTASTRUM");
                z.setLocationRelativeTo(null);
                
                
                
               
                dispose();
                
                
                
               
            }
            else if(optionCheck.equals("Close"))
            {
                //closes the menu - using system, so JVM stops
               
                System.exit(0);
               
            }
            else if (optionCheck.equals("Difficulty: Easy"))
            {
                difficulty = 1;
                setDifficulty.setText("Difficulty: Medium");
            }
            else if (optionCheck.equals("Difficulty: Medium"))
            {
                difficulty = 2;
                setDifficulty.setText("Difficulty: Hard");
            }
            else if (optionCheck.equals("Difficulty: Hard"))
            {
                difficulty = 3;
                setDifficulty.setText("Difficulty: Impossible");
            }
            else if (optionCheck.equals("Difficulty: Impossible"))
            {
                difficulty = -3;
                setDifficulty.setText("Difficulty: Chill");
            }
            else if (optionCheck.equals("Difficulty: Chill"))
            {
                difficulty = 0;
                setDifficulty.setText("Difficulty: Easy");
            }
           
        }
    }
   
    public static void main (String [] args)
    {
        MainMenu a = new MainMenu();
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);
       
        //these were added to refine the 1st version of the gui
        a.setSize(500,500);
        //using pack seems to get a better title image
        a.pack();
        a.setLocationRelativeTo(null);
        a.setTitle("Quintastrum");
        
    }

   
   
}    


