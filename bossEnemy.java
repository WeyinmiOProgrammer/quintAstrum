
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
  {"How DARE you destroy my vantage point!","Taste my venomous rage!","You will NOT OVERPOWER ME","Stay away!","Stop!","STOP IT NOW!","CEASE THIS NONSENSE AT ONCE!"},
{"I told you to leave.","These are orders from my emperor","Mine and soon to be yours","Stop fighting this","You need to stay here","It's nice on this planet","There's pufferfish for everyone","Please just admit defeat","Please stop fighting","You don't have to fight this"},
{"Why are you still fighting this...","It's for the good of the empire","I can't tell you why","I just can't!","...",".....","...........","Look, I'll level with you","In about three minute, a laser will fire","This whole planet's getting destroyed","There's nothing you can do","Don't fight it","You can't stop this","Nothing you do will stop this"}};
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
  case 9:
      dialogChoice = 2;
      break;
    case 10:
        dialogChoice = 3;
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