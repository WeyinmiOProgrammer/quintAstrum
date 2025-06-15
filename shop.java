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
    int iReceive, uReceive, iTimes, uTimes;
    int charid;
    Display di;
    Player pl;
    public shop(ImageIcon icon,String name, InventoryMenu menu, int give, int take, int amountToGive, int amountToTake, int cha, Player pla, Display dis)
    {
        di = dis;
        pl = pla;
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
        iTimes = amountToGive;
        uTimes = amountToTake;
        charid = cha;
        event e = new event();
        trade.addActionListener(e);
        talk.addActionListener(e);
        leave.addActionListener(e);

    }

    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String option = e.getActionCommand();
            DialogueB no;
            if (option.equals("LEAVE"))
            {
                dispose();
            }
            else if (option.equals("TRADE"))
            {
                if (im.searchInvFor(iReceive))
                {
                    if (im.howMuch(iReceive) >= iTimes) {
                        for (int i = 0; i < uTimes; i++) {
                            im.addToInv(uReceive);
                        }
                        for (int i = 0; i < iTimes; i++)
                        {
                            im.removeFromInv(iReceive);
                        }
                        no = new DialogueB(charid,"Good trading with you ", 0,im,pl,di);
                        no.setVisible(true);
                        no.pack();
                        no.setLocationRelativeTo(null);
                    }
                    else {
                        no = new DialogueB(charid,"You don't have enough to trade with ", 0,im,pl,di);
                        no.setVisible(true);
                        no.pack();
                        no.setLocationRelativeTo(null);
                    }
                }
                else
                {
                    no = new DialogueB(charid,"You don't have what I'm looking for ", 0,im,pl,di);
                    no.setVisible(true);
                    no.pack();
                    no.setLocationRelativeTo(null);
                }
            }
            else if (option.equals("TALK"))
            {
                //animate talk cutscenes for shops, its high effort but I really don't mind
                //its quicker than using other means
                cutscene talking = new cutscene(charid);
                talking.setVisible(true);
                talking.pack();
                talking.setLocationRelativeTo(null);

            }

        }}
}
