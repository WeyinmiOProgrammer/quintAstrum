import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class shop extends JFrame {
    ImageIcon i;
    JLabel faceGoesHere;
    JButton trade;
    JButton talk;
    JButton leave;
    InventoryMenu im;
    JLabel keeper;
    int iReceive, uReceive;
    public shop(ImageIcon icon,String name, InventoryMenu menu, int give, int take)
    {
        im = menu;
        i = icon;
        setLayout(new GridLayout(5,1,2,3));
        faceGoesHere = new JLabel(icon);
        add(faceGoesHere);
        keeper = new JLabel(name);
        add(keeper);
        trade = new JButton("TRADE");
        add(trade);
        talk = new JButton("TALK");
        add(talk);
        leave = new JButton("LEAVE");
        add(leave);

        iReceive = give;
        uReceive = take;

        event e = new event();
        trade.addActionListener(e);
        talk.addActionListener(e);
        leave.addActionListener(e);

    }

    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String option = e.getActionCommand();
            if (option.equals("LEAVE"))
            {
                dispose();
            }
            else if (option.equals("TRADE"))
            {
                if (im.searchInvFor(iReceive))
                {
                    im.addToInv(uReceive);
                }
                else
                {
                    //message
                }
            }
        }}
}
