
/**
 * Write a description of class Display here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends JPanel implements KeyListener
{
    //MOVED MAP CLASS DATA TO DISPLAY CLASS
    /*
     first sub-array is meant to store all possible exits for reference
     in the order [entrance, exit 1, exit 2]
     
     the other sub-arrays will be loaded to form a map
     
     I highly doiubt this will work
     
     chests will probably contain random items, not preset (makes it easi8er)
     
     might mix chests with the resources like wood and metal
     */
   
   
    // 0 - free space
    // 1 - wall
    // 2 - wood - becomes free space after interaction
    // 3 - harmful substance
    // 4 - exit 1
    // 5 - exit 2 (optional)
    // 8 - entrance
    // 6 - boss enemy (optional)
    // 7 - player start position
    // 9 - scrap metal - becomes free space after interaction
    // 10 - chest? becomes free space after interaction - contains potions
    // 11 - more background
    // 12 - oil
    // 13 - spaceship place
    // 14 - key - becomes free space after interaction
    // 15 - key door - becomes free space after interaction
    // 16 - key shard - becomes free space after interaction - can be crafted into key
   
    /*
     The maximum size of any map should be 30x30
     */
   
   
   
   
   
   
   
    //map one - 15x8
   public static int[][] i = {{0,2},
                 {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                 {1,1,1,2,0,0,0,0,0,0,0,2,1,1,1},
                 {1,1,0,0,0,0,0,9,0,0,0,0,0,1,1},
                 {1,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                 {1,1,0,0,0,6,0,7,0,0,0,0,0,1,1},
                 {1,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                 {1,1,1,2,0,0,0,0,0,0,0,2,1,1,1},
                 {1,1,1,1,1,1,4,4,4,1,1,1,1,1,1}
         
                };
               
   public static int[][] ii = {{1,3,4},
                  {1,1,1,1,8,8,1,1,1,1},
                  {1,1,0,0,7,0,0,0,1,1},
                  {1,1,1,1,1,1,0,0,1,1},
                  {1,0,0,0,0,0,0,0,1,1},
                  {1,0,1,1,1,1,1,0,0,1},
                  {1,0,0,4,1,1,1,1,0,1},
                  {1,1,1,1,1,1,1,1,5,1},
                  {1,1,1,1,1,1,1,1,1,1}
                 
                };
                
    public static int[][] iii = {{2,5},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,8,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
                   {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,9,1,1},
                   {1,1,0,1,1,1,0,0,0,0,0,1,1,1,1,0,1,1,1,1},
                   {1,1,0,1,1,1,0,1,1,1,4,1,1,1,1,0,0,1,1,1},
                   {1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1},
                   {1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,0,1,1,1},
                   {1,1,0,1,1,1,10,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
                   {1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
                   {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                   
    public static int[][] iv = {{2,0},
                  {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                  {1,1,0,0,0,0,1,8,1,0,0,0,0,1},
                  {1,1,0,0,0,0,1,7,1,0,0,0,0,1},
                  {1,1,0,0,0,0,0,0,0,0,0,0,0,1},
                  {11,11,11,11,11,11,11,0,0,0,0,0,0,1},
                  {11,11,0,0,0,0,11,11,11,11,0,0,0,1},
                  {1,1,0,0,0,0,0,11,11,0,0,0,0,1},
                  {1,1,0,0,0,0,11,11,0,0,0,0,0,1},
                  {1,1,0,0,0,0,11,11,0,0,0,0,0,1},
                  {1,1,0,0,0,0,0,11,11,0,0,0,0,1},
                  {1,4,0,0,0,0,0,0,11,0,0,0,6,1},
                  {1,1,1,1,1,1,1,1,11,11,11,1,1,1}};
                  
    public static int[][] zero = {{4},
                  {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                  {1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                  {1,0,0,9,0,0,0,9,0,0,0,0,1,1},
                  {1,0,0,0,12,0,0,0,12,0,0,1,1,1},
                  {1,0,0,0,0,0,0,0,0,0,6,7,8,1},
                  {1,0,0,0,12,0,0,0,12,0,0,1,1,1},
                  {1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                  {1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                  
    public static int[][] v = {{3,6},
                  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                  {1,1,1,1,0,1,1,8,1,1,0,1,1,1,1},
                  {1,1,1,0,0,0,1,7,1,0,0,0,1,1,1},
                  {1,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                  {1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
                  {1,1,1,1,0,0,0,6,0,0,0,1,1,1,1},
                  {1,1,1,1,1,0,0,0,0,0,1,1,1,1,1},
                  {1,1,1,1,1,1,0,0,0,1,1,1,1,1,1},
                  {1,1,1,1,1,1,1,0,1,1,1,1,1,1,1},
                  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                  
    public static int[][] vi = {{5,7},
                  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                  {1,0,0,0,0,0,0,8,0,0,0,0,0,0,1},
                  {1,0,0,0,0,0,0,7,0,0,0,0,0,0,1},
                  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                  {1,0,0,0,0,0,0,4,0,0,0,0,0,0,1},
                  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                  
     public static int[][] vii = {{6,8},
                 {1,1,1,1,1,1,1,1,1,1,1,1,11,11,11},
                 {1,1,1,1,0,0,0,0,0,0,0,2,1,11,11},
                 {1,1,0,0,0,0,0,0,0,0,0,0,0,1,11},
                 {1,1,0,0,0,0,0,8,0,0,0,0,0,1,11},
                 {1,1,0,0,0,6,0,7,0,0,0,0,0,1,1},
                 {11,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                 {11,1,1,2,0,0,0,0,0,0,0,2,1,1,1},
                 {11,11,1,1,1,1,1,4,1,1,1,1,1,1,1}
         
                };
    public static int[][]viii = {{7,9},
                 {11,11,11,1,1,1,1,1,1,1,1,1,11,11},
                 {11,11,1,8,1,1,1,4,4,1,1,11,11,11},
                 {11,1,0,0,7,1,1,0,0,1,1,11,11,11},
                 {1,0,0,0,0,0,0,0,0,0,0,1,11,11},
                 {1,1,1,1,1,1,1,1,1,1,1,1,1,11}};
                 
     public static int [][] ix = {{8,10,11},
                    {01,04,01,11,11,11,11,11,11,11},
                    {01,00,01,11,11,11,11,11,11,11},
                    {01,00,01,00,00,00,00,15,05,11},
                    {01,00,00,00,11,11,11,11,11,11},
                    {01,01,01,00,11,11,11,00,11,11},
                    {01,01,01,00,11,11,00,14,00,11},
                    {01,00,00,00,11,11,11,00,11,11},
                    {01,00,01,11,11,11,11,00,11,11},
                    {01,00,01,11,11,11,11,00,01,11},
                    {01,00,00,00,00,00,07,00,01,11},
                    {01,01,01,01,01,01,8,01,01,01}};
     static int path = 0;

public static int[][] x = {{9,12},
                    {01,01,01,01,01,01,01,01,01},
                    {01,01,00,00,00,01,01,01,01},
                    {01,01,00,01,00,00,00,00,04},
                    {01,00,00,01,01,01,01,01,01},
                    {01,00,01,01,01,01,01,01,01},
                    {01,00,00,00,00,00,00,01,01},
                    {01,01,07,01,01,01,00,14,01},
                    {01,01,8,01,01,01,01,01,01}};

public static int[][] xii = {{10,13},
                    {01,01,01,01,01,04,01},
                    {01,00,00,00,00,00,01},
                    {01,01,00,00,00,00,01},
                    {8,07,00,00,01,15,01},
                    {01,01,00,00,01,10,01},
                    {01,01,01,01,01,01,01}};

public static int[][] xi = {{9,13},
             {01,04,01,01,01,01,01},
             {01,00,00,00,00,16,01},
             {01,01,01,06,01,01,01},
             {01,00,00,00,00,00,01},
             {8,07,00,00,00,00,01},
             {01,01,01,01,01,01,01}};

public static int[][] xiii = {{11,14},
                {11,11,11,11,01,04,01,11,11,11,11},
                {11,11,11,11,11,06,11,11,11,11,11},
                {01,01,01,01,00,07,00,01,01,01,01},
                {01,01,01,01,8,01,8,01,01,01,01}};
                
public static int[][] xiv = {{13,15},
                {01,01,01,01,01,01,01,01,01,01,01,01},
                {04,00,00,00,00,00,00,00,00,00,00,01},
                {01,01,01,01,01,01,01,01,01,01,00,01},
                {01,02,00,00,00,00,00,00,00,00,00,01},
                {01,00,01,01,01,01,01,01,01,01,01,01},
                {01,12,00,00,00,00,00,00,00,00,00,01},
                {01,01,01,01,01,01,01,01,01,01,00,01},
                {01,10,00,00,00,00,00,00,00,00,00,01},
                {01,00,01,01,01,01,01,01,01,01,01,01},
                {01,9,00,00,00,00,00,00,00,00,00,01},
                {01,01,01,01,01,01,01,01,01,01,00,01},
                {01,00,00,00,00,00,00,00,00,00,00,01},
                {01,02,01,01,01,01,01,01,01,01,01,01},
                {01,00,00,00,00,00,00,00,00,00,07,01},
                {01,01,01,01,01,01,01,01,01,01,8,01}};

public static int[][] xv = {{14,16,-1},
                {01,01,01,01,01,01,01,01,01,01},
                {01,00,00,00,00,00,00,00,07,8},
                {01,00,01,00,01,00,00,01,00,01},
                {01,00,00,00,00,00,00,00,00,01},
                {01,01,00,01,00,01,00,01,00,01},
                {01,04,00,01,15,01,00,01,16,01},
                {01,01,01,01,05,01,01,01,01,01}};
                
public static int[][] minusone = {{15,17},
               {01,01,01,01,8,01,01,01,01},
               {01,00,00,00,00,00,00,00,01},
               {01,00,00,00,00,00,00,00,01},
               {01,00,00,00,13,00,00,00,01},
               {01,00,00,00,00,00,00,00,01},
               {01,01,01,01,8,01,01,01,01}};
                
public static int[][] xvi = {{15,17},
               {01,01,01,01,01,01,01,01,01},
               {11,01,00,00,00,00,00,01,01},
               {11,11,01,01,01,01,01,11,11},
               {11,11,04,06,00,16,00,07,8},
               {11,11,01,01,01,01,01,11,11},
               {11,01,00,00,00,00,00,01,01},
               {01,01,01,01,01,01,01,01,01}
};

public static int[][] xvii = {{16,18},
              {01,01,01,01,01,01,01,01},
              {01,00,00,00,00,00,06,01},
              {01,04,01,07,8,00,00,01},
              {01,00,00,00,00,00,00,01},
              {01,01,01,01,01,01,01,01}};
              
public static int[][] xviii = {{17,19},
            {01,01,01,01,01,01,01,01},
            {01,00,00,07,8,01,01,01},
            {01,00,00,00,00,01,01,01},
            {01,01,01,01,01,01,01,01}};
    //variables relating to movement
    int charX = 7;
    int charY = 5;
    //int inventoryOpen = 0;
    InventoryMenu I = new InventoryMenu();
    
     
    //extra color constants
    Color WOOD = new Color(66,41,1);
    Color HKIT = new Color(24,242,126);
    Color CAVE = new Color(8,79,10);
    
    //player
    Player myChar = new Player(50,10,7,2,2,50,10,1,0,4,4);
               
    int currentSection = 1;
    //relating to random encounter
    int stepsLeft = 30;
    Random R = new Random();
    int Dif;
    public Display(int diff)
    {
        //changed background to black
        setOpaque(true);
        setBackground(Color.BLACK);
        addKeyListener(this);
         //creates new inventory menu
        InventoryMenu I = new InventoryMenu();
        
        //starts invisible
        I.setVisible(false);
       
        
        I.setSize(1020,1020);
        I.setLocationRelativeTo(null);
        Dif = diff;
    }
    public void revivePlayer()
    {
        myChar.heal(10*myChar.getLv());
        myChar.recover(5*myChar.getLv());
    }
    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }
    
    public int[][] findMap(int curr)
    {
        switch(curr)
        {
            case -1:
                return minusone;
                
            case 0:
                return zero;
                
            case 1:
                return i;
                
            case 2:
                return ii;
                
            case 3:
                return iii;
                
            case 4:
                return iv;
                
            case 5:
                return v;
                
            case 6:
                return vi;
                
            case 7:
                return vii;
                
            case 8:
                return viii;
                
            case 9:
                return ix;
                
            case 10:
                return x;
                
            case 11:
                return xi;
               
            case 12:
                return xii;
             
            case 13:
                return xiii;
              
            case 14:
                return xiv;
             case 15:
                return xv;
             case 16:
                return xvi;
            case 17:
                return xvii;
            case 18:
                return xviii;
            
        }
        return zero;
    }
    
    public void collisionCheck(int[][] arr)
    {
       
       
        if (arr[charY][charX] == 4)
        {
            //retrieves data for first exit
            currentSection = arr[0][1];
            calculatePosition(currentSection);
           
        }
        else if (arr[charY][charX] == 5)
        {
            //retrieves data for second exit
            currentSection = arr[0][2];
            calculatePosition(currentSection);
        }
        else if (arr[charY][charX] == 8)
        {
            //retrieves data for entrance
            currentSection = arr[0][0];
            calculatePosition(currentSection);
        }
       
       
       
       
       
       
    }
   
   
    public void editArea(int g)
    {
        int gx = 0;
        int gy = 0;
        if (g == 1)
        {
            gx = v[1].length;
            gy = v.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {
                    if (v[y][x] == 6)
                    {
                        v[y][x] = 13;
                        repaint();
                    }
                }}
            
        }
        else if (g == 2)
        {
            gx = findMap(currentSection)[1].length;
            gy = findMap(currentSection).length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {
                    if (findMap(currentSection)[y][x] == 13)
                    {
                        findMap(currentSection)[y][x] = 4;
                        repaint();
                    }
                }}
            
        }
        else if (g == 3)
        {
            gx = v[1].length;
            gy = v.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {
                    if (v[y][x] == 6)
                    {
                        v[y][x] = 4;
                        repaint();
                    }
                }}
            
        }
        else if (g == 4)
        {
            gx = iv[1].length;
            //gy = iv.length;
            
           
            
                for (int x = 0; x < gx; x++)
                {
                    
                    if (iv[7][x] == 11)
                    {
                        iv[7][x] = 0;
                        repaint();
                    }
                    if (iv[11][x] == 6)
                    {
                        iv[11][x] = 0;
                        repaint();
                    }
                }
            
        }
        else if (g ==5)
        {
            gx = zero[1].length;
            gy = zero.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {
                    if (zero[y][x] == 6)
                    {
                        zero[y][x] = 0;
                        repaint();
                    }
                }}
            
        }
        else if (g == 6)
        {
            gx = xi[1].length;
            gy = xi.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {
                    if (xi[y][x] == 6)
                    {
                        xi[y][x] = 0;
                        repaint();
                    }
                }}
            
        }
        else if (g == 7)
        {
            gx = xiii[1].length;
            gy = xiii.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {

                    if (xiii[y][x] == 6)
                    {
                        xiii[y][x] = 0;
                        repaint();
                    }
                }}
            
        }
        
        else if (g == 8)
        {
            gx = xvi[1].length;
            gy = xvi.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {
                    if (xvi[y][x] == 7)
                    {
                        xvi[y][x] = 0;
                        repaint();
                    }
                    if (xvi[y][x] == 6)
                    {
                        xvi[y][x] = 7;
                        repaint();
                    }
                }
            }
            
        }
        else if (g == 9)
        //destroying blue planet or green planet
        {
            boolean defeatedLaser = true;
            gx = xvii[1].length;
            gy = xvii.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {

                    if (xvii[y][x] == 6)
                    {
                        defeatedLaser = false;
                    }
                }}
            if (!defeatedLaser)
            {
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {

                    if (xvii[y][x] == 8)
                    {
                        xvii[y][x] = 0;
                        repaint();
                    }
                }}
            }
            else
            {
                gx = xi[1].length;
                gy = xi.length;
                for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {

                    if (xi[y][x] == 8)
                    {
                        xi[y][x] = 0;
                        repaint();
                    }
                }}
            }
            }
            
            else if (g == 10)
            {
                //general "get rid of character onscreen command
                gx = findMap(currentSection)[1].length;
                gy = findMap(currentSection).length;
                for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {

                    if (findMap(currentSection)[y][x] == 6)
                    {
                        findMap(currentSection)[y][x] = 0;
                        repaint();
                    }
                }}
            }
            
        }
    
   
    public void calculatePosition(int c)
    {
        findPlayerPos((findMap(c)));
        //for path splitting
        if (c == 12)
        {
            xiii[0][0] = 12;
        }
        if (c == 11)
        {
            xiii[0][0] = 11;
        }

       
    }
   
    public void findPlayerPos(int[][] arr)
    {
        int yLen = arr.length;
        int xLen = arr[1].length;
        //added for tested
        //System.out.println("START");
        for (int y = 1; y < yLen; y++)
        {
            // fixed issue with not checking 1st x value
            for (int x = 0; x < xLen; x++)
            {
               
               
                if (arr[y][x] == 7)
                {
                    charY = y;
                    charX = x;
                    repaint();
                }
            }
           
           
        }
        //added for testing
       // System.out.println("END");
    }
   
    public void keyPressed(KeyEvent e) 
    {
        
        int z = e.getKeyCode();
        //System.out.println(z);
       
        if (z == KeyEvent.VK_Q)
        {
            //if (inventoryOpen == 0)
            //{
                //inventoryOpen = 1;
                I.openInvMenu();
                I.setSize(1020,1020);
                I.setLocationRelativeTo(null);
            //}
            //else
            //{
                //inventoryOpen = 0;
                //I.closeInvMenu();
            //}
             
           
        }
        else if (z == KeyEvent.VK_W)
        {
            quickCraft Q = new quickCraft(I);
       
            Q.setVisible(true);
            Q.setSize(1020,1020);
            Q.setLocationRelativeTo(null);
        }
        else if (z == KeyEvent.VK_E)
{
    playerDetails P = new playerDetails(myChar);
    P.setVisible(true);
    P.setSize(500,500);
    P.setLocationRelativeTo(null);
}
        else if (z == KeyEvent.VK_RIGHT || z == KeyEvent.VK_LEFT || z == KeyEvent.VK_UP || z == KeyEvent.VK_DOWN)
        {
        //add controls here
        findMap(currentSection)[charY][charX] = 0;

        int tempX = charX;
        int tempY = charY;
       
       
       
        if (z == KeyEvent.VK_RIGHT)
        {
            charX++;
           
           
        }
        else if (z == KeyEvent.VK_LEFT)
        {
            charX--;
           
        }
        else if (z == KeyEvent.VK_UP)
        {
            charY--;
           
        }
        else if (z == KeyEvent.VK_DOWN)
        {
            charY++;
        }
       
        movementCheck(findMap(currentSection), tempX, tempY);
        

        repaint();
        try{
        Thread.sleep(45);}
        catch(Exception f)
        {
            System.out.println("error");
        }
    }
    
   
   
    }
    public void keyReleased(KeyEvent e)
    {
       
       
    }
    public void keyTyped(KeyEvent e)
    {
       
    }
   
    public void movementCheck(int[][] arr, int tX,int tY)
    {
                //sets the position of the player in the previous map, to their position before movement
                //if they are travelling between maps
                if ( arr[charY][charX] == 8 || arr[charY][charX] == 4 || arr[charY][charX] == 5)
                {
                    arr[tY][tX] = 7;
                    collisionCheck(arr);
                }
               
               
               
               
                //keeps the player in position when interacting with a wall 
                else if (arr[charY][charX] == 1 || arr[charY][charX] == 11)
                {
                    charX = tX;
                    charY = tY;
                    arr[charY][charX] = 7;
                }
                //moves the player to a free space
                else if (arr[charY][charX] == 0)
                {
                    arr[charY][charX] = 7;
                    stepsLeft--;
                    if (stepsLeft < 1)
                    {
                        int BattleNow = R.nextInt(19);
                        if (BattleNow % 3 == 0)
                        {
                            if (currentSection == 3)
                            {
                             Enemy e1 = new Enemy(30,0,3,1,1,30,0, 2);
                             e1.diffSpike(Dif);
                             BattleBox b = new BattleBox(myChar, e1, I, this);
                             b.setVisible(true);
                             b.setSize(900,720);
                             b.setLocationRelativeTo(null);
                             stepsLeft = R.nextInt(10)+30;
                            }
                            if (currentSection == 8)
                            {
                             Enemy e1 = new Enemy(45,7,7,3,4,45,7, 3);
                             e1.diffSpike(Dif);
                             BattleBox b = new BattleBox(myChar, e1, I, this);
                             b.setVisible(true);
                             b.setSize(900,720);
                             b.setLocationRelativeTo(null);
                             stepsLeft = R.nextInt(10)+30;
                            }
                            if (currentSection == 10)
                            {
                             Enemy e1 = new Enemy(30,0,20,1,myChar.getSD(),30,3, 5);
                             e1.diffSpike(Dif);
                             BattleBox b = new BattleBox(myChar, e1, I, this);
                             b.setVisible(true);
                             b.setSize(900,720);
                             b.setLocationRelativeTo(null);
                             stepsLeft = R.nextInt(10)+30;
                            }
                            if (currentSection == 14)
                            {
                                Enemy e1;
                                if (R.nextInt(2) == 1)
                                {
                             e1 = new Enemy(30,0,20,1,myChar.getSD(),30,3, 5);
                            }
                            else
                            {
                                e1 = new Enemy(45,7,7,3,4,45,7, 3);
                            }
                             e1.diffSpike(Dif);
                             BattleBox b = new BattleBox(myChar, e1, I, this);
                             b.setVisible(true);
                             b.setSize(900,720);
                             b.setLocationRelativeTo(null);
                             stepsLeft = R.nextInt(15)+30;
                            }
                            
                        }
                        
                    }
                }
                
                //interacting with a tree/wood? 
                else if (arr[charY][charX] == 2)
                {
                    boolean collect = I.addToInv(3);
                    if (collect == true)
                    {
                        arr[charY][charX] = 7;
                    }
                }
                
                //interacting with scrap metal
                else if (arr[charY][charX] == 9)
                {
                    boolean collect = I.addToInv(4);
                    if (collect == true)
                    {
                        arr[charY][charX] = 7;
                    }
                }
                //interacting with oil
                else if (arr[charY][charX] == 12)
                {
                    boolean collect = I.addToInv(13);
                    if (collect == true)
                    {
                        arr[charY][charX] = 7;
                    }
                }
                //interacting with key
                else if (arr[charY][charX] == 14)
                {
                    boolean collect = I.addToInv(14);
                    if (collect == true)
                    {
                        arr[charY][charX] = 7;
                    }
                }
                //interacting with key shard
                else if (arr[charY][charX] == 16)
                {
                    boolean collect = I.addToInv(15);
                    if (collect == true)
                    {
                        arr[charY][charX] = 7;
                    }
                }
                //interacting with keydoor
                else if (arr[charY][charX] == 15)
                {
                    
                    if (I.searchInvFor(14))
                    {
                        arr[charY][charX] = 7;
                        I.removeFromInv(14);
                    }
                    else
                    {
                        DialogueB d4 = new DialogueB(1,"I need a key to open this door", 0,I,myChar,this);
                         d4.setVisible(true);
                         d4.pack();
                         d4.setLocationRelativeTo(null);
                          charX = tX;
                          charY = tY;
                           arr[charY][charX] = 7;
                    }
                }
                //interacting with health kit
                else if (arr[charY][charX] == 10)
                {
                    for (int j = 0; j < 4; j++)
                    {
                    boolean collect = I.addToInv(1);
                    if (collect == true)
                    {
                        arr[charY][charX] = 7;
                    }}
                     for (int k = 0; k < 4; k++)
                    {
                    boolean collect = I.addToInv(2);
                    if (collect == true)
                    {
                        arr[charY][charX] = 7;
                    }}
                }
                
                else if (arr[charY][charX] == 3)
                {
                    //left blank for now - may go unused
                }
                
                else if (arr[charY][charX] == 6)
                {
                    charX = tX;
                    charY = tY;
                    arr[charY][charX] = 7;
                    if (currentSection == 5)
                    {
                        DialogueB d = new DialogueB(1,"I'm trying to make a rocket to get off this planet", 1, I, myChar, this);
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
                        
                        
                    
                    }
                    else if (currentSection == 1)
                    {
                        DialogueA d1 = new DialogueA(1,"Should I fight this guy?", 1, I, myChar, this);
                        d1.setVisible(true);
                        d1.pack();
                        d1.setLocationRelativeTo(null);
                    }
                    else if (currentSection == 7)
                    {
                        Enemy ed = new Enemy(45,7,7,3,4,45,7, 3);
                        ed.diffSpike(Dif);
                             BattleBox b = new BattleBox(myChar, ed, I, this);
                             b.setVisible(true);
                             b.setSize(900,720);
                             b.setLocationRelativeTo(null);
                    }
                    else if (currentSection == 4)
                    {
                        DialogueA d = new DialogueA(2,"<HTML>I could probably build a bridge across<BR> if you have 4 pieces of wood.<BR>Do you?</HTML>", 4, I, myChar, this);
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
                        
                        
                    
                    }
                    else if (currentSection == 0)
                    {
                        DialogueA d1 = new DialogueA(4,"You want that stuff? Then fight me.", 5, I, myChar, this);
                        d1.setVisible(true);
                        d1.pack();
                        d1.setLocationRelativeTo(null);
                    }
                    else if (currentSection == 11)
                    {
                        DialogueB d = new DialogueB(5,"INTRUDERS! YOU'LL GET WHAT'S COMING TO YOU!", 3, I, myChar, this);
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
                        
                        
                    
                    }
                    else if (currentSection == 16)
                    {
                        DialogueB d = new DialogueB(8,"HALT! This shuttle will NOT be leaving this planet!",4,I,myChar,this);
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
                    }
                    else if (currentSection == 13)
                    {
                        bossEnemy ed = new bossEnemy(60,10,8,2,3,60,10, 7,myChar,I,this);
                        ed.diffSpike(Dif);
                        BattleBox b = new BattleBox(myChar, ed, I, this);
                        b.setVisible(true);
                        b.setSize(900,720);
                        b.setLocationRelativeTo(null);
                    }
                    else if (currentSection == 17)
                    {
                        bossEnemy ed = new bossEnemy(100,0,8,2,4,100,0, 11,myChar,I,this);
                        ed.diffSpike(Dif);
                        BattleBox b = new BattleBox(myChar, ed, I, this);
                        b.setVisible(true);
                        b.setSize(900,720);
                        b.setLocationRelativeTo(null);
                    }
                }
                else if (arr[charY][charX] == 13)
                {
                    charX = tX;
                    charY = tY;
                    arr[charY][charX] = 7;
                    //if you have a spaceship
                    boolean gotEnoughMetal = I.removeFromInv(11);
                    if (gotEnoughMetal)
                    {
                        editArea(2);
                    }
                    else
                    {
                        //if you have the items to make a spaceship
                    if (I.howMuch(4) >= 5)
                    {
                    for (int w = 0; w < 5; w++)
                    {
                        gotEnoughMetal = I.removeFromInv(4);
                    }
                }
                    gotEnoughMetal = I.removeFromInv(6);
                    if (gotEnoughMetal)
                    {
                       editArea(2); 
                    }
                }
                }
               
            //test
            //System.out.println(charX + "," + charY);
           
           
           
    }
    
    public int getDif()
    {
        return Dif;
    }
   
    @Override
   
     public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
       
        int yLen;
        int xLen;
        int excessX;
        int excessY;
        
           
            yLen = findMap(currentSection).length ;
            xLen = findMap(currentSection)[1].length;
            excessX = (30 - xLen)/2;
            excessY = (24 - yLen)/2;
            for (int y = 1; y < yLen ; y++)
           
            {
                for (int x = 0; x < xLen; x++)
                {
                    switch(findMap(currentSection)[y][x])
                    {
                        case 0:
                            if (currentSection < 6)
                            {
                            g.setColor(Color.GREEN);
                            }
                            if (currentSection == 6)
                            {
                            g.setColor(Color.BLACK);
                            }
                            if (currentSection > 6 && currentSection < 20)
                            {
                            g.setColor(Color.CYAN);
                            }
                            break;
                        case 1:
                            if (currentSection < 6)
                            {
                            g.setColor(Color.DARK_GRAY);
                            }
                            if (currentSection == 6 || currentSection == 17)
                            {
                            g.setColor(Color.BLACK);
                            }
                            if (currentSection > 6 && currentSection < 17)
                            {
                            g.setColor(Color.BLUE);
                            }
                            break;
                        case 2:
                            //changed block colour
                            g.setColor(WOOD);
                            break;
                        case 8:
                            if (currentSection < 6)
                            {
                            g.setColor(Color.GREEN.darker());
                            }
                             if (currentSection == 6)
                            {
                            g.setColor(Color.GREEN);
                            }
                             if (currentSection > 6 && currentSection < 17)
                            {
                            g.setColor(Color.CYAN.darker());
                            }
                             if (currentSection == 17)
                            {
                            g.setColor(Color.BLUE);
                            }
                            if (currentSection == 7 || currentSection == 18)
                            {
                                g.setColor(Color.WHITE);
                            }

                            break;
                        case 4:
                             if (currentSection < 6)
                            {
                            g.setColor(Color.GREEN.darker());
                            }
                             if (currentSection == 6)
                            {
                            g.setColor(Color.BLUE);
                            }
                             if (currentSection > 6 && currentSection < 17)
                            {
                            g.setColor(Color.CYAN.darker());
                            }
                            if (currentSection == 17)
                            {
                            g.setColor(Color.PINK);
                            }
                            if (currentSection == 5 || currentSection == 16 || currentSection == -1)
                            {
                                g.setColor(Color.WHITE);
                            }

                            break;
                        
                        case 5:
                             if (currentSection < 6)
                            {
                            g.setColor(Color.GREEN.darker());
                            }
                             if (currentSection == 6)
                            {
                            g.setColor(Color.GREEN);
                            }
                             if (currentSection > 6 && currentSection < 20)
                            {
                            g.setColor(Color.CYAN.darker());
                            }
                            break;
                        case 7:
                            g.setColor(Color.RED);
                            if (currentSection == 6 || currentSection == 17)
                            {
                                g.setColor(Color.WHITE);
                            }
                            break;
                        case 9:
                            g.setColor(Color.LIGHT_GRAY);
                            break;
                        case 6:
                            if (currentSection == 0)
                            {
                            g.setColor(Color.ORANGE.darker().darker());
                            }
                            if (currentSection == 1)
                            {
                            g.setColor(Color.BLUE);
                            }
                            if (currentSection == 5)
                            {
                            g.setColor(Color.YELLOW);
                            }
                            if (currentSection == 4)
                            {
                            g.setColor(Color.RED.darker().darker());
                            }
                            if (currentSection == 7)
                            {
                            g.setColor(Color.GREEN.darker());
                            }
                            if (currentSection == 11)
                            {
                            g.setColor(Color.MAGENTA);
                            }
                            if (currentSection == 13)
                            {
                            g.setColor(Color.CYAN.darker().darker().darker());
                            }
                            if (currentSection == 16)
                            {
                            g.setColor(Color.GREEN.brighter().brighter().brighter());
                            }
                            if (currentSection == 17)
                            {
                            g.setColor(Color.CYAN.darker());
                            }
                        
                            break;
                        case 12:
                            g.setColor(Color.ORANGE.darker().darker().darker().darker());
                            break;
                        case 11:
                            g.setColor(Color.BLACK);
                            break;
                         case 13:
                            g.setColor(Color.DARK_GRAY.darker());
                            break;
                        case 14:
                            g.setColor(Color.YELLOW);
                            break;
                        case 15:
                            g.setColor(Color.GRAY);
                            break;
                        case 16:
                            g.setColor(Color.YELLOW.darker());
                            break;
                        case 10:
                            //changed block colour
                            g.setColor(HKIT);
                            break;
                   
                       
                       
                    }
                    g.drawRect(((x+ excessX) * 30),((y-1+excessY)*30), 30, 30);
                    g.fillRect(((x+ excessX) * 30),((y-1+excessY)*30), 30, 30);
                    
                }
            }
            
            
    }
   
}
   

