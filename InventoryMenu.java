
/**
 * Write a description of class InventoryMenu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
public class InventoryMenu extends JFrame
{
    JPanel p;
    //this array will hold the items in the list
    //each cell of the array contains an the value for an item (e.g. 2 - a health potion)
    //and the amount of that item that the player has (e.g. 10 health potions)
    int[][] items = new int[20][2];
    //this array contains the actual item names
    String[] itemNames = {"Empty","🍶Health potion", "🍵Magic potion", "📏Stick", "⚙Scrap metal", "⚔Sword",
                          "⛽Engine", "⚒Axe", "🔥Fire powder", "☕Coffee", "❧Bullet", "🚀Spaceship", "⛽Jetpack","🛢Oil","🔑Key","🔑Key piece",
                        "🐡Porcpuff","❥Boomerang","💔Broken boomerang","🗡Dark sword","🗡Jetsword",
                    "💵Imperial Currency","💵Delgg","","","",
                "","","","",
            "👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg",
            "👽Geruo","👽Geruo","👽Geruo","👽Geruo","👽Geruo","👽Geruo","👽Geruo","👽Geruo","👽Geruo","👽Geruo",
            "👽Koldec","👽Koldec","👽Koldec","👽Koldec","👽Koldec","👽Koldec","👽Koldec","👽Koldec","👽Koldec","👽Koldec",
            "👽Uandar","👽Uandar","👽Uandar","👽Uandar","👽Uandar","👽Uandar","👽Uandar","👽Uandar","👽Uandar","👽Uandar",
            "Oriinmi","Oriinmi","Oriinmi","Oriinmi","Oriinmi","Oriinmi","Oriinmi","Oriinmi","Oriinmi","Oriinmi",
            "🌹Parmesian","🥀?Permas","🥀Permasi","🌹Permasio","🌷Permasionne","🌸Permanne","💮Permanen","💮Permanente","🏵️Impermanente","⚜️Impare",
            "👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg","👽Dleg"
        };
   
    //5 labels will be used to display 5 items at a time
    JLabel one;
    JLabel two;
    JLabel thr;
    JLabel fou;
    JLabel fiv;
   
    //2 buttons will allow the user to move through the JFrame
    JButton forward;
    JButton backward;
    JButton exit;
    
    int page = 0;
   
   
    public InventoryMenu()
    {
       
        setLayout(new GridLayout(5,2, 5, 1));
       
        //adds labels
        one  = new JLabel("1 - empty : 0");
        add(one);
        two  = new JLabel("2 - empty : 0");
        add(two);
        thr  = new JLabel("3 - empty : 0");
        add(thr);
        fou  = new JLabel("4 - empty : 0");
        add(fou);
        fiv  = new JLabel("5 - empty : 0");
        add(fiv);
        exit = new JButton("❌");
        add(exit);
        forward = new JButton("🢀");
        add(forward);
        backward = new JButton("🢂");
        add(backward);
        
        event z = new event();
        exit.addActionListener(z);
        forward.addActionListener(z);
        backward.addActionListener(z);
       
    }
   
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent z)
        {
            String optionCheck = z.getActionCommand();
            if (optionCheck.equals("❌"))
            {
                closeInvMenu();
            }
            else if(optionCheck.equals("🢂"))
            {
                if (page < 3)
                {
                    page = page + 1;
                }
                displayItems();
            }
            
            else if (optionCheck.equals("🢀"))
            {
                if(page > 0)
                {
                    page = page - 1;
                }
                displayItems();
            }
            
        }
    }
    
    public void displayItems()
    {
        one.setText(((page*5)+1)+ " - " + (valToItem(items[(page*5)][0]) + " : " + (items[(page*5)][1])));
        two.setText(((page*5)+2)+ " - " + (valToItem(items[(page*5)+1][0]) + " : " + (items[(page*5)+1][1])));
        thr.setText(((page*5)+3)+ " - " + (valToItem(items[(page*5)+2][0]) + " : " + (items[(page*5)+2][1])));
        fou.setText(((page*5)+4)+ " - " + (valToItem(items[(page*5)+3][0]) + " : " + (items[(page*5)+3][1])));
        fiv.setText(((page*5)+5)+ " - " + (valToItem(items[(page*5)+4][0]) + " : " + (items[(page*5)+4][1])));
}
    
    public String valToItem(int i)
    {
        String ite = itemNames[i];
        return ite;
    }
    
    
    public void openInvMenu()
    {
       
        setVisible(true);
    }
    
    public void closeInvMenu()
    {
        setVisible(false);
    }
   
    
    public boolean searchInvFor(int item)
    {
    boolean itemInList = false;
    for (int i = 0; i < 19; i++)
        {
            //System.out.println(items[i][0]);
            if (items[i][0] == item)
            {
                itemInList = true;
                
            }
        }
        return itemInList;
}
    public boolean addToInv(int item)
    {
        boolean itemInList = false;
        boolean spaceInInv = false;
        for (int i = 0; i < 19; i++)
        {
            //System.out.println(items[i][0]);
            if (items[i][0] == item)
            {
                itemInList = true;
                items[i][1] = items[i][1] + 1;
                spaceInInv = true;
            }
        }
       
        
       
        if (itemInList == false)
        {
            for (int i = 0; i < 19; i++)
        {
            //System.out.println(items[i][0]);
            if (items[i][0] == 0 && spaceInInv == false)
            {
                items[i][0] = item;
               
                items[i][1] = 1;
                spaceInInv = true;
            }
        }
           
        }
        
        displayItems();
       
        return spaceInInv;
    }
    
    public int peekItem(int i)
    {
        int x = items[i][0];
        return x;
    }
    public int howMuch(int item)
    {
        int amount = 0;
        boolean itemInList = false;
        
        for (int i = 0; i < 19; i++)
        {
            //System.out.println(items[i][0]);
            if (items[i][0] == item)
            {
                itemInList = true;
                amount = items[i][1];
                
            }
        }
 
       return amount;
  }
    public boolean removeFromInv(int itemID)
    {
        boolean itemInList = false;
        
        for (int i = 0; i < 19; i++)
        {
            //System.out.println(items[i][0]);
            if (itemInList == false)
            {
            if (items[i][0] == itemID)
            {
                itemInList = true;
                //reduces the number of that specific item you have
                items[i][1] = items[i][1] - 1;
                if (items[i][1] == 0)
                {
                    //sets item to empty if there are none left
                    items[i][0] = 0;
                }
                //
                
            }
        }
        }
       
        
       
        
           
        
        
        displayItems();
        return itemInList;
    }
   
   
}