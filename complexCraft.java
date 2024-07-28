
/**
 * Write a description of class complexCraft here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class complexCraft extends quickCraft
{
    public complexCraft(InventoryMenu i)
    {
        super(i);
        recipes.setText("<HTML>spaceship = 1 engine + 5 metal<BR>jetpack = 1 engine + 1 magic potion<BR></HTML>");
    }
    
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String option = e.getActionCommand();
            if (option.equals("Craft!"))
            {
                int x = 0;
switch(enter.getText()){

case "spaceship":
   x  = im.howMuch(6);
   if (x >= 1)
   {
      x = im.howMuch(4);
      if (x >= 5)
      {
         
         im.removeFromInv(6);
         im.removeFromInv(4);
         im.removeFromInv(4);
         im.removeFromInv(4);
         im.removeFromInv(4);
         im.addToInv(11);
         dispose();
      }
      else
      {
         enter.setText("Not enough scrap metal");
      }
   }
   else
   {
      enter.setText("No engine");
   }
break;
case "jetpack":
   x  = im.howMuch(6);
   if (x >= 1)
   {
      x = im.howMuch(2);
      if (x >= 2)
      {
         
         im.removeFromInv(6);
         im.removeFromInv(2);
         im.removeFromInv(2);
         im.addToInv(12);
         dispose();
      }
      else
      {
         enter.setText("Not enough magic potion");
      }
   }
   else
   {
      enter.setText("No engine");
   }
break;

            }}
            else if (option.equals("Cancel"))
            {
               dispose(); 
            }
        }
    }
    
}
    

