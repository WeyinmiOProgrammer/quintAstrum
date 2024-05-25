
/**
 * Write a description of class Enemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    int id;
    public Enemy(int h, int m, int a, int d, int s, int mh, int mm, int i)
    {
        super(h, m, a, d, s, mh, mm);
        id = i;
    }
    
    public int getID()
    {
        return id;
    }
    
    public void setID(int i)
    {
        id = i;
    }
    
    public void nextTurn()
    {}
   
}
