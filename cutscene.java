
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
    public cutscene()
    {
        setLayout(new GridLayout(1,1,1,1));
        l = new JLabel(i);
        add(l);
    }
}
