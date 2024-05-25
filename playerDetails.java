
/**
 * Write a description of class playerDetails here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class playerDetails extends JFrame
{
    Player p;
    JLabel hp, mp, at, df, sd, exp, lvl, name, icon;
    JButton close;
    ImageIcon you = new ImageIcon(getClass().getResource("Ric.png"));
    public playerDetails(Player pl)
    {  
        setLayout(new GridLayout(10,1,10,1));
        p = pl;
        name = new JLabel("Geruo Lvl "+p.getLv());
        add(name);
        icon = new JLabel(you);
        add(icon);
        hp = new JLabel("HP:"+p.getHP()+"/"+p.getmHP());
        add(hp);
        mp = new JLabel("MP:"+p.getMP()+"/"+p.getmMP());
        add(mp);
        at = new JLabel("AT:"+p.getAT());
        add(at);
        df = new JLabel("DF:"+p.getDF());
        add(df); 
        sd = new JLabel("SD:"+p.getSD());
        add(sd);
        exp = new JLabel("EXP:"+p.getXP()+"/"+p.getmXP());
        add(exp);
        close = new JButton("CLOSE");
        add(close);
        event v = new event();
        close.addActionListener(v);
    
        
    }

    public class event implements ActionListener
    {
       public void actionPerformed(ActionEvent e)
       {
           dispose();
       } 
    }
}