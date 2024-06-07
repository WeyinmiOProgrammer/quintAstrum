
/**
 * Write a description of class quickCraft here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class quickCraft extends JFrame
{
    InventoryMenu im;
    JTextField enter;
    JButton confirm;
    JButton cancel;
    JLabel recipes;
    public quickCraft(InventoryMenu i)
    {
        setLayout(new GridLayout(4,1, 2, 1));
        recipes = new JLabel("<HTML>sword = 1 wood + 2 metal<BR>axe = 1 wood + 1 metal<BR>engine = 2 wood + 1 oil<BR>spaceship = 1 engine + 5 metal<BR>jetpack = 1 engine + 1 magic potion<BR>key = 3 key shard</HTML>");
        add(recipes);
        im = i;
        enter = new JTextField(20);
        add(enter);
        confirm = new JButton("Craft!");
        add(confirm);
        cancel = new JButton("Cancel");
        add(cancel);
        
        event k = new event();
        confirm.addActionListener(k);
        cancel.addActionListener(k);
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
case "sword":
   x  = im.howMuch(4);
   if (x >= 2)
   {
      x = im.howMuch(3);
      if (x >= 1)
      {
         
         im.removeFromInv(3);
         im.removeFromInv(4);
         im.removeFromInv(4);
         im.addToInv(5);
         dispose();
      }
      else
      {
         enter.setText("Not enough wood");
      }
   }
   else
   {
      enter.setText("Not enough scrap metal");
   }
break;
case "axe":
   x  = im.howMuch(4);
   if (x >= 1)
   {
      x = im.howMuch(3);
      if (x >= 1)
      {
         
         im.removeFromInv(3);
         im.removeFromInv(4);
         im.addToInv(7);
         dispose();
      }
      else
      {
         enter.setText("Not enough wood");
      }
   }
   else
   {
      enter.setText("Not enough metal");
   }
break;
case "engine":
   x  = im.howMuch(13);
   if (x >= 1)
   {
      x = im.howMuch(3);
      if (x >= 2)
      {
         
         im.removeFromInv(13);
         im.removeFromInv(3);
         im.removeFromInv(3);
         im.addToInv(6);
         dispose();
      }
      else
      {
         enter.setText("Not enough wood");
      }
   }
   else
   {
      enter.setText("Not enough oil");
   }
break;
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
case "key":
   x  = im.howMuch(15);
   if (x >= 3)
   {
      
               
         im.removeFromInv(15);
         im.removeFromInv(15);
         im.removeFromInv(15);
         im.addToInv(14);
         dispose();
      
   }
   else
   {
      enter.setText("Not enough key shards");
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
