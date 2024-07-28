
/**
 * Write a description of class complexCraft here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class complexCraft extends quickCraft
{
    
    public complexCraft(InventoryMenu i)
    {
        super(i);
        recipes.setText("<HTML>spaceship = 1 engine + 5 metal<BR>jetpack = 1 engine + 1 magic potion<BR>jetsword = 1 sword + 1 jetpack<BR>possessed axe = 1 sword + 1 life jar<BR></HTML>");
        setTitle("Grafting menu");
        
        canGraft = 1;
        
    }
    
    
    
}
    

