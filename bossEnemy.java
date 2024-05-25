
/**
 * Write a description of class bossEnemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
public class bossEnemy extends Enemy
{
  int turnsSince = 0;
  int dialogChoice = 0;
  
  String[][]bossD = {
      {"No one is allowed beyond this point","I am the gate and none shall pass through","You are foolish to try and overpower me","Kneel before the might of the NqUra","Kowtow","Give up","Cease."},
  {"How DARE you destroy my vantage point!","Taste my venomous rage!","You will NOT OVERPOWER ME","Stay away!","Stop!","STOP IT NOW!","CEASE THIS NONSENSE AT ONCE!"}};
  Player P;
  InventoryMenu iM;
  Display D;
  DialogueB speech;
  public bossEnemy(int h, int m, int a, int d, int s, int mh, int mm, int i, Player p, InventoryMenu im, Display de)
 {
  super(h, m, a, d, s, mh, mm, i);
  P = p;
  iM = im;
  D = de;
}
@Override
public void nextTurn()
{
 
  
  switch(id)
  {
case 7:
    dialogChoice = 0;
    break;
  case 8:
    dialogChoice = 1;
    break;
  }
  speech = new DialogueB(0,bossD[dialogChoice][turnsSince],0,iM,P,D);
  speech.setVisible(true);
  speech.pack();
  speech.setLocationRelativeTo(null);
  turnsSince++;
  if (turnsSince > (bossD[dialogChoice].length-1))
  {
      turnsSince-= 3;
    }

}

}