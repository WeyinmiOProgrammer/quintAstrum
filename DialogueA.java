
/**
 * Write a description of class DialogueA here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
public class DialogueA extends JFrame
{
   ImageIcon charIcon;
    JLabel iconLabel, name, dialog;
    JButton confirm, reject;
    int chara, id;
    int result = 0;
    Player p;
    Display d;
    InventoryMenu in;
    public DialogueA(int i, String txt, int ID, InventoryMenu inv, Player pl,Display di)
    {
        setLayout(new GridLayout(1,5,10,1));
        
        chara = i;
        p = pl;
        in = inv;
        id = ID;
        d = di;
         
        charIcon = new ImageIcon(getClass().getResource("Ric.png"));
        name = new JLabel("Geruo");
        iconLabel = new JLabel(charIcon);
     
        add(iconLabel);
        add(name);
        
        dialog = new JLabel(txt);
        add(dialog);
        
      
       confirm = new JButton("Yes");
       reject = new JButton("No");
       add(confirm);
       add(reject);
                //break;
        //}
        
        event e = new event();
       
        confirm.addActionListener(e);
        reject.addActionListener(e);
        charSpec();
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
                charIcon = new ImageIcon(getClass().getResource("Yic.png"));
               name.setText("Dleg");
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
            
        }
    }
    public int getResult()
    {
        return result;
    }
    
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
             String optionCheck = e.getActionCommand();
             if (optionCheck.equals("close"))
             {
                 dispose();
                }
                else if(optionCheck.equals("Yes"))
                {
                    switch (id)
                    {
                        case 1:
                             Enemy e1 = new Enemy(30,0,3,1,1,30,0, 2);
                             e1.diffSpike(d.getDif());
                             BattleBox b = new BattleBox(p, e1, in, d);
                             b.setVisible(true);
                             b.setSize(900,720);
                             b.setLocationRelativeTo(null);
                         
                         break;
                         
                         case 2:
                             if (in.searchInvFor(4)== false)
                             {
                          DialogueB d3 = new DialogueB(1,"You liar, you don't have any!", 2,in,p,d);
                         d3.setVisible(true);
                         d3.pack();
                         d3.setLocationRelativeTo(null);
                        
                        }
                        else
                        {
                        DialogueB d4 = new DialogueB(1,"<HTML>Oh, that's great!<BR> I can make this right away.<BR> Heck, we should stick together</HTML>", 0,in,p,d);
                         d4.setVisible(true);
                         d4.pack();
                         d4.setLocationRelativeTo(null);
                         in.addToInv(30);
                         in.removeFromInv(4);
                         d.editArea(3);
                        }
                         
                         break;
                         
                         case 4:
                             if (in.howMuch(3)<4)
                             {
                          DialogueB d3 = new DialogueB(2,"You need 4 pieces of wood, dumdum.", 0,in,p,d);
                         d3.setVisible(true);
                         d3.pack();
                         d3.setLocationRelativeTo(null);
                        
                        }
                        else
                        {
                        DialogueB d4 = new DialogueB(2,"Ok, I'll make this now", 0,in,p,d);
                         d4.setVisible(true);
                         d4.pack();
                         d4.setLocationRelativeTo(null);
                         
                         for(int i = 0; i < 4; i++)
                         {
                         in.removeFromInv(3);
                        }
                         d.editArea(4);
                        }
                         
                         break;
                         
                         case 5:
                             Enemy e2 = new Enemy(60,10,10,3,2,60,10, 4);
                             e2.diffSpike(d.getDif());
                             BattleBox b2 = new BattleBox(p, e2, in, d);
                             b2.setVisible(true);
                             b2.setSize(900,720);
                             b2.setLocationRelativeTo(null);
                         
                         break;
                         
                         case 7:
                             DialogueB d4 = new DialogueB(8,"<HTML>Take this money<BR>You'll need it where you're going <BR> NOW HURRY!</HTML>", 0,in,p,d);
                         d4.setVisible(true);
                         d4.pack();
                         d4.setLocationRelativeTo(null);
                         in.addToInv(21);
                             break;
                             
                             case 29:
                            d.editArea(11);
                            Enemy bs4 = new Enemy(80,20,10,4,3,80,20, 16);
                             bs4.diffSpike(d.getDif());
                             BattleBox b7 = new BattleBox(p, bs4, in, d);
                             b7.setVisible(true);
                             b7.setSize(900,720);
                             b7.setLocationRelativeTo(null);
                            
                            
                            break;
                             
                            case 31:
                             case 33:
                            d.editArea(11);
                            Enemy bs5 = new Enemy(80,20,10,4,3,80,20, 16);
                             bs5.diffSpike(d.getDif());
                             BattleBox b6 = new BattleBox(p, bs5, in, d);
                             b6.setVisible(true);
                             b6.setSize(900,720);
                             b6.setLocationRelativeTo(null);
                            
                            
                            break;
                    }
                    dispose();
                    
                }
                else if(optionCheck.equals("No"))
                {
                    switch (id)
                    {
                        
                         
                         case 2:
                             
                         DialogueB d2 = new DialogueB(1,"You're lying! I know you have some!", 2,in,p,d);
                         d2.setVisible(true);
                         d2.pack();
                         d2.setLocationRelativeTo(null);
                         break;
                         
                         case 6:
                             bossEnemy e5 = new bossEnemy(120,42,10,6,5,120,42,9,p,in,d);
                             e5.diffSpike(d.getDif());
                             BattleBox b5 = new BattleBox(p,e5,in,d);
                             b5.setVisible(true);
                             b5.setSize(900,720);
                             b5.setLocationRelativeTo(null);
                             
                             break;
                        
                        case 7:
                            in.addToInv(60);
                            break;
                        case 29:
                             d.uanScene(3);
                             DialogueB ser8 = new DialogueB(9,"How about an encore?", 30,in,p,d);
                            ser8.setVisible(true);
                            ser8.pack();
                            ser8.setLocationRelativeTo(null);
                            break;
                        case 31:
                             d.uanScene(4);
                             DialogueB ser9 = new DialogueB(9,"Right, let's get out of here.", 32,in,p,d);
                            ser9.setVisible(true);
                            ser9.pack();
                            ser9.setLocationRelativeTo(null);
                            break;
                        case 33:
                            d.editArea(11);
                            d.uanScene(6);
                            
                            break;
                        case 100:
                            DialogueB nah = new DialogueB(0,"Don't say I didn't warn you.", 0,in,p,d);
                            nah.setVisible(true);
                            nah.pack();
                            nah.setLocationRelativeTo(null);
                            d.editArea(10);
                            break;
                    }
                    
                    dispose();
                }
            
        }
    }
    
   
}
