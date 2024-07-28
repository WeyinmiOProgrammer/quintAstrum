
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
    int canGraft = 0;
    public quickCraft(InventoryMenu i)
    {
        setLayout(new GridLayout(4,1, 2, 1));
        recipes = new JLabel("<HTML>sword = 1 wood + 2 metal<BR>axe = 1 wood + 1 metal<BR>bow = 3 wood + 1 string<BR>guitar = 1 wood + 6 string<BR>engine = 2 wood + 1 oil<BR>key = 3 key shard</HTML>");
        add(recipes);
        im = i;
        enter = new JTextField(20);
        add(enter);
        confirm = new JButton("Craft!");
        add(confirm);
        cancel = new JButton("Cancel");
        add(cancel);
        setTitle("Crafting menu");
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
case "bow":
   x  = im.howMuch(3);
   if (x >= 3)
   {
      x = im.howMuch(22);
      if (x >= 1)
      {
         
         im.removeFromInv(3);
         im.removeFromInv(3);
         im.removeFromInv(3);
         im.removeFromInv(22);
         im.addToInv(26);
         dispose();
      }
      else
      {
         enter.setText("Not enough strings");
      }
   }
   else
   {
      enter.setText("Not enough wood");
   }
break;
case "guitar":
   x  = im.howMuch(3);
   if (x >= 1)
   {
      x = im.howMuch(22);
      if (x >= 6)
      {
         
         im.removeFromInv(3);
         im.removeFromInv(22);
         im.removeFromInv(22);
         im.removeFromInv(22);
         im.removeFromInv(22);
         im.removeFromInv(22);
         im.removeFromInv(22);
         im.addToInv(27);
         dispose();
      }
      else
      {
         enter.setText("Not enough strings");
      }
   }
   else
   {
      enter.setText("Not enough wood");
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
//restricted items
case "spaceship":
    if (canGraft == 1)
    {
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
}
else
{
    enter.setText("Can't graft. Find a grafting station");
}
break;
case "jetpack":
    if (canGraft == 1)
    {
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
}
else
{
    enter.setText("Can't graft. Find a grafting station.");
}
break;
case "jetsword":
    if (canGraft == 1)
    {
   x  = im.howMuch(5);
   if (x >= 1)
   {
      x = im.howMuch(12);
      if (x >= 1)
      {
         
         im.removeFromInv(5);
         im.removeFromInv(12);
         
         im.addToInv(20);
         dispose();
      }
      else
      {
         enter.setText("No jetpack");
      }
   }
   else
   {
      enter.setText("No sword");
   }
}
else
{
    enter.setText("Can't graft. Find a grafting station.");
}
break;
case "possessed axe":
    if (canGraft == 1)
    {
   x  = im.howMuch(7);
   if (x >= 1)
   {
      x = im.howMuch(100);
      if (x >= 1)
      {
         
         im.removeFromInv(7);
         im.removeFromInv(100);
         
         im.addToInv(24);
         dispose();
      }
      else
      {
         enter.setText("No life jar");
      }
   }
   else
   {
      enter.setText("No axe");
   }
}
else
{
    enter.setText("Can't graft. Find a grafting station.");
}
break;

default:
    enter.setText("You can't craft that");
    break;
            }}
            else if (option.equals("Cancel"))
            {
               dispose(); 
            }
        }
    }
    
}
