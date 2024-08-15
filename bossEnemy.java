
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
{"Why are you still fighting this...","It's for the good of the empire","I can't tell you why","I just can't!","...",".....","...........","Look, I'll level with you","In about three minute, a laser will fire","This whole planet's getting destroyed","There's nothing you can do","Don't fight it","You can't stop this","Nothing you do will stop this"},
{"Almost ready to fire","Firing in 10","9","8","7","6","5","4","321","BOOM"},
{"Huh, what's going on?","What are you -crk-ing doing?","You're starting to annoy me","You shouldn't even be here. This was our underground sanctuary.","How'd you even get down here?","I specifically dug myself under layers of rock for protection","Wait, you don't mean...","A weapon? That can destroy planets?","Am I all that remains?","Am I the sole survivor","ribbit","ribbit","ribbitribbitribbitribbit","I'M NOT GOING TO LET YOU DEFEAT ME!","I AM THE REMNANT!","I AM THE ELDER FRODOGGER!","THE FLAME THAT WILL NOT BE PUT OUT!","YOU WILL NOT PUT ME OUT!","YOU WILL NOT STOP ME!","-CRK-! YOU HIT HARD!","BUT YOU WILL FIND..","MY FIRE..","BURNS BRIGHTER THAN YOURS.","YOU WILL NOT TAKE THIS ANCIENT THRONE","You will not take...","...our legacy","You will not take...","...","...","is this a losing battle?","a joke of a last stand","...","I guess I'd rather go down fighting a foe like you","...rather than be obliterated by that weapon you speak of","You're a dedicated fighter","I respect that in opponents.","not that I've had that many myself","We folk live a peaceful life","It's nice to have a place to call home","Home","That's what you're looking for, right?","I mean","You're not supposed to be here","...","...","ribbit","...","ribbit","why don't you just run away from here?","just leave and don't come back","We have no beef with eachother","You have nothing to gain","...","I guess that's not why we're fighting","...","...","..."},
{"Put em up","<HTML>What, you think just cause I'm pink and <BR>round and small that I'm weak</HTML>","Man, I'm not a fool","<HTML>I'm not gonna charge into a fight<BR> against some 10 armed unit <BR>if I didn't know I was stronger</HTML>","Now focus on the battle","-","-","-"}};
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
    case 11:
        dialogChoice = 4;
        break;
    case 14:
        dialogChoice = 5;
        break;
    case 18:
        dialogChoice = 6;
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