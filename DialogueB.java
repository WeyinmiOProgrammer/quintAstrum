
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
            case 9:
                charIcon = new ImageIcon(getClass().getResource("honkHONK.png"));
                name.setText("oOo");
                iconLabel.setIcon(charIcon);
                break;
            case 10:
                charIcon = new ImageIcon(getClass().getResource("plant2.png"));
                name.setText("Par");
                iconLabel.setIcon(charIcon);
                break;
            case 11:
                charIcon = new ImageIcon(getClass().getResource("plantshock.png"));
                name.setText("Par");
                iconLabel.setIcon(charIcon);
                break;
            case 12:
                charIcon = new ImageIcon(getClass().getResource("THEROOK.png"));
                name.setText("Nameless");
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
                         
                    case 5:
                        DialogueB sab = new DialogueB(3,"I'm a girl.", 6,in,p,d);
                         sab.setVisible(true);
                         sab.pack();
                         sab.setLocationRelativeTo(null);
                        break;
                    case 6:
                        DialogueB sac = new DialogueB(11,"-", 7,in,p,d);
                         sac.setVisible(true);
                         sac.pack();
                         sac.setLocationRelativeTo(null);
                        break;
                    case 7:
                        DialogueB sad = new DialogueB(10,"<HTML>Look, just try to deal with your<BR>opponents more peacefully.<BR>Find out what they really want<BR>Or falling short of that, just run away.<BR>I'll be watching you very closely.</HTML>", 0,in,p,d);
                         sad.setVisible(true);
                         sad.pack();
                         sad.setLocationRelativeTo(null);
                         d.editArea(10);
                         d.moralityTesting(1);
                        break;
                    
                    case 20:
                         DialogueB ser1 = new DialogueB(9,"Instead you abandoned the mission, hah.", 21,in,p,d);
                         ser1.setVisible(true);
                         ser1.pack();
                         ser1.setLocationRelativeTo(null);
                         break;
                    case 21:
                         DialogueB ser2 = new DialogueB(8,"...", 22,in,p,d);
                         ser2.setVisible(true);
                         ser2.pack();
                         ser2.setLocationRelativeTo(null);
                         break;
                     case 22:
                         DialogueB ser3 = new DialogueB(9,"HAh hah Hah HAh hAH HAH ", 23,in,p,d);
                         ser3.setVisible(true);
                         ser3.pack();
                         ser3.setLocationRelativeTo(null);
                         break; 
                    case 23:
                         DialogueB ser4 = new DialogueB(8,"...", 24,in,p,d);
                         ser4.setVisible(true);
                         ser4.pack();
                         ser4.setLocationRelativeTo(null);
                         break;
                    case 24:
                         DialogueB ser5 = new DialogueB(9,"You all are like instruments, in an orchestra", 25,in,p,d);
                         ser5.setVisible(true);
                         ser5.pack();
                         ser5.setLocationRelativeTo(null);
                         break;
                    case 25:
                         DialogueB ser6 = new DialogueB(9,"Let's say you're a drum", 26,in,p,d);
                         ser6.setVisible(true);
                         ser6.pack();
                         ser6.setLocationRelativeTo(null);
                         break;
                    case 26:
                         DialogueB ser7 = new DialogueB(9,"To make music, you have to", 27,in,p,d);
                         ser7.setVisible(true);
                         ser7.pack();
                         ser7.setLocationRelativeTo(null);
                         break;
                    case 27:
                         d.uanScene(2);
                         DialogueB ser8 = new DialogueB(9,"STRIKE HIM.", 28,in,p,d);
                         ser8.setVisible(true);
                         ser8.pack();
                         ser8.setLocationRelativeTo(null);
                         break;
                     case 28:
                         DialogueA sir = new DialogueA(0,"Interfere?", 29,in,p,d);
                         sir.setVisible(true);
                         sir.pack();
                         sir.setLocationRelativeTo(null);
                         break;
                    case 30:
                         DialogueA sir2 = new DialogueA(0,"INTERFERE?", 31,in,p,d);
                         sir2.setVisible(true);
                         sir2.pack();
                         sir2.setLocationRelativeTo(null);
                         break;
                    case 32:
                         DialogueA sir3 = new DialogueA(0,"AVENGE?", 33,in,p,d);
                         sir3.setVisible(true);
                         sir3.pack();
                         sir3.setLocationRelativeTo(null);
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
