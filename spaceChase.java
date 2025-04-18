import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class spaceChase extends JFrame
{
    int id; //determines what happens after the flight is completed and Destination details

    int theirHP; //dependent on aircraft, planets have incredibly high HP
    int distFromDest;
    int speedOfDest = 0; //if destination is a planet = 0, otherwise determines how much an aircraft moves during a turn

    int yourHP; //depends on what you're flying with
    int currentSpeed; //partly dependent on your craft but more based on
    int yourShip;
    JButton left, right, centre, blast;
    JLabel distanceAway;
    ImageIcon target, you, obst;
    Display dis;
    Player pla;
    InventoryMenu inv;
    public spaceChase(int id, int yourSh, Display d, Player p, InventoryMenu i)
    {
      yourShip = yourSh;
      this.id = id;
      dis = d;
      pla = p;
      inv = i;

      setLayout(new GridLayout(5,3,1,1));

      setup();
      
    }

    public void setup()
    {

        theirHP = 999999;
        distFromDest = 50;
        speedOfDest = 0;
        switch(id)
        {
            case 1:
            case 2:
            case 3:
                distFromDest = 100;
                break;
            case 4:
                distFromDest = 60;
                theirHP = 50;
                speedOfDest = 10;
                break;

            default:

                break;
        }
    }
}
