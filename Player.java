
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    int level;
    int xp;
    int maxXP;
    int id;
    public Player(int h, int m, int a, int d, int s, int mh, int mm, int l, int x, int mx, int i)
    {
        super(h, m, a, d, s, mh, mm);
        level = l;
        xp =x;
        maxXP = mx;
        id = i;
    }
    
    public int getLv()
    {
        return level;
    }
    
    public void setLv(int l)
    {
        level = l;
    }public int getXP()
    {
        return xp;
    }
    
    public void setXP(int x)
    {
        xp = x;
    }public int getmXP()
    {
        return maxXP;
    }
    
    public void setmXP(int mx)
    {
        maxXP = mx;
    }
    
    public int getID()
    {
        return id;
    }
    
    //getters and setters go here
    
    //attack, guard
    
}
