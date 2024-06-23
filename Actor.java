
/**
 * Write a description of class Actor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Actor
{
    //both player and enemy extend the actor class
    int HP, MP, ATK, DEF, SPD, maxHP, maxMP;
    public Actor(int h, int m, int a, int d, int s, int mh, int mm)
    {
        HP = h;
        MP = m;
        ATK = a;
        DEF = d;
        SPD = s;
        maxHP = mh;
        maxMP  = mm;
    }
    
    public void setHP(int h)
    {
        HP = h;
    }
    public int getHP()
    {
        return HP;
    }
    public void setMP(int m)
    {
        MP = m;
    }
    public int getMP()
    {
        return MP;
    }
    public void setmHP(int mh)
    {
        maxHP = mh;
    }
    public int getmHP()
    {
        return maxHP;
    }
    public void setmMP(int mm)
    {
        maxMP = mm;
    }
    public int getmMP()
    {
        return maxMP;
    }
    public void setAT(int a)
    {
        ATK = a;
    }
    public int getAT()
    {
        return ATK;
    }
    public void setDF(int d)
    {
        DEF = d;
    }
    public int getDF()
    {
        return DEF;
    }
    public void setSD(int s)
    {
        SPD = s;
    }
    public int getSD()
    {
        return SPD;
    }
    
    public void diffSpike(int d)
    {
        HP += d;
        MP += d;
        ATK += d;
        DEF += d;
        SPD += d;
        
        maxHP += d;
        maxMP += d;
        if (HP < 1)
        {
            HP = 1;
        }
        if (MP < 1)
        {
            MP = 1;
        }
        if (SPD < 1)
        {
            SPD = 1;
        }
        if (maxHP < 1)
        {
            maxHP = 1;
        }
        if (maxHP < 1)
        {
            maxHP = 1;
        }
    }
    public void attack(Actor a)
    {
        int ehp = a.getHP();
        int edf = a.getDF();
        int dmg = (ATK-edf);
        if (dmg < 0)
        {
            dmg = 0;
        }
        ehp = ehp - dmg;
        a.setHP(ehp);
    }
    
    public void heal(int i)
    {
        setHP(HP + i);
        if (HP > maxHP)
        {
            setHP(maxHP);
        }
        if (HP < 0)
        {
            setHP(0);
        }
        
    }
    public void recover(int i)
    {
        setMP(MP + i);
        if (MP > maxMP)
        {
            setMP(maxMP);
        }
        if (MP < 0)
        {
            setMP(0);
        }
    }
}
