
/**
 * Write a description of class cutscene here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class cutscene extends JFrame
{
    ImageIcon i = new ImageIcon(getClass().getResource("nicetry.gif"));
    JLabel l;
    int id = 0;
    public cutscene(int id)
    {
        setLayout(new GridLayout(1,1,1,1));
        this.id = id;
        if (id != 0)
        {
            setImg();
        }
        l = new JLabel(i);
        add(l);
    }

    public cutscene()
    {
        setLayout(new GridLayout(1,1,1,1));
        l = new JLabel(i);
        add(l);
    }

    public void setImg()
    {
        switch(id)
        {
            case 14:
                i = new ImageIcon(getClass().getResource("treechshop.gif"));
                break;
            case 16:
                i = new ImageIcon(getClass().getResource("podhogshop.gif"));
                break;
            default:
                break;
        }
    }
}
