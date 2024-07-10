
/**
 * Write a description of class DialogBox here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
public class DialogueB extends JFrame
{
    ImageIcon charIcon;
    JLabel iconLabel, name, dialog;
    JButton close;
    int chara, id;
    int result = 0;
    InventoryMenu in;
    Player p;
    Display d;
    public DialogueB(int i, String txt, int ID, InventoryMenu inv, Player pl, Display di)
    {
        setLayout(new GridLayout(1,5,10,1));
        
        chara = i;
        id = ID;
        in = inv;
        p = pl;
        d = di;

        charIcon = new ImageIcon(getClass().getResource("Yic.png"));
        name = new JLabel("Dleg");
        iconLabel = new JLabel(charIcon);
          
        add(iconLabel);
        add(name);
        
        dialog = new JLabel(txt);
        add(dialog);
      
        close = new JButton("close");
        add(close);

        event e = new event();
        close.addActionListener(e);
       
        charSpec();
    }
    
    public int getResult()
    {
        return result;
    }
    public void charSpec()
    {
        switch(chara)
        {
            case 2: 
                charIcon = new ImageIcon(getClass().getResource("NPC.png"));
               name.setText("bridgeman");
                iconLabel.setIcon(charIcon);
                break;
            case 3: 
                charIcon = new ImageIcon(getClass().getResource("Ric.png"));
               name.setText("Geruo");
                iconLabel.setIcon(charIcon);
                break;
            case 0:
                name.setText("");
                iconLabel.setIcon(null);
                break;
            case 4: 
                charIcon = new ImageIcon(getClass().getResource("Oic.png"));
               name.setText("Enroga");
                iconLabel.setIcon(charIcon);
                break;
            case 5: 
                charIcon = new ImageIcon(getClass().getResource("Koldec.png"));
               name.setText("Koldec");
                iconLabel.setIcon(charIcon);
                break;
            case 8:
                charIcon = new ImageIcon(getClass().getResource("DBoss.png"));
                name.setText("Uandar");
                iconLabel.setIcon(charIcon);
                break;
            case 99:
                charIcon = new ImageIcon(getClass().getResource("tutorialguy.png"));
                name.setText("excerpt from Extraterrestial Travel");
                iconLabel.setIcon(charIcon);
                break;
        }
    }
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
             String optionCheck = e.getActionCommand();
             if (optionCheck.equals("close"))
             {
                 switch(id)
                 {
                     case 1:
                         DialogueA d2 = new DialogueA(3,"Do you have any scrap metal?", 2,in,p,d);
                         d2.setVisible(true);
                         d2.pack();
                         d2.setLocationRelativeTo(null);
                         break;
                         
                     case 4:
                         DialogueA d3 = new DialogueA(8,"Leave this place immediately.",6,in,p,d);
                         d3.setVisible(true);
                         d3.pack();
                         d3.setLocationRelativeTo(null);
                         break;
                    
                    
                    case 2:
                        Enemy e2 = new Enemy(70,0,4,3,1,70,0, 1);
                        e2.diffSpike(d.getDif());
                         BattleBox b2 = new BattleBox(p, e2, in,d);
                         b2.setVisible(true);
                         b2.setSize(900,720);
                         b2.setLocationRelativeTo(null);
                         
                         break;
                         
                    case 3:
                        Enemy e3 = new Enemy(45,0,3,1,6,45,0, 6);
                        e3.diffSpike(d.getDif());
                         BattleBox b3 = new BattleBox(p, e3, in,d);
                         b3.setVisible(true);
                         b3.setSize(900,720);
                         b3.setLocationRelativeTo(null);
                         
                         break;
                        }
                 dispose();
                }
                
            
        }
    }
    
    public int sendResult()
    {
        
        System.out.println(result);
        return result;
    }
}
