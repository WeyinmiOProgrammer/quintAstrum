
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
    
    /*
     first sub-array is meant to store all possible exits for reference
     in the order [entrance, exit 1, exit 2]
     
     the other sub-arrays will be loaded to form a map
     
    
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
    // 10 - chest - becomes free space after interaction - contains potions
    // 11 - more background
    // 12 - oil
    // 13 - spaceship place
    // 14 - key - becomes free space after interaction
    // 15 - key door - becomes free space after interaction
    // 16 - key shard - becomes free space after interaction - can be crafted into key
    // 17 - craft spot - craft more complex weapons
    // 18 - cinematic character you aren't meant to interact with
    /*
     The maximum size of any map should be 30x30
     */
   
   
   
   
   
   
   
    //map one - 15x8 - using the entrance as a quick test door to wherever
   public static int[][] i = {{17,2},
                 {1,1,1,1,1,1,1,8,1,1,1,1,1,1,1},
                 {1,1,1,2,0,0,0,0,0,0,0,2,1,1,1},
                 {1,1,0,0,0,0,0,9,0,0,0,0,0,1,1},
                 {1,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
                 {1,1,0,0,0,6,0,7,0,0,17,0,0,1,1},
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
    public static int[][] minustwo = {{2,5,-3},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1},
                   {1,1,0,0,0,0,0,0,0,1,5,1,0,0,0,0,0,1,1,1},
                   {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,1,1},
                   {1,1,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,1,1,1},
                   {1,1,0,1,1,1,0,1,1,1,4,1,1,1,1,0,0,1,1,1},
                   {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,0,1,1,1,10,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
                   {1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
                   {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                   
    public static int[][] minusthree = {{-2,-4},
         {01,01,01,01,01,04,01,01,01},
         {01,01,01,00,00,00,01,01,01},
         {01,01,01,00,01,00,00,00,01},
         {01,01,01,00,01,01,01,00,01},
         {01,01,01,00,01,01,00,00,01},
         {01,00,00,00,01,01,00,01,01},
         {01,00,01,01,00,00,00,01,01},
         {01,00,00,00,00,07,01,01,01},
         {01,01,01,01,01,8,01,01,01}};
         
    public static int[][] minusfour = {{-3},
        {01,01,01,01,01,01},
        {01,01,00,00,01,01},
        {11,01,00,00,01,11},
        {11,11,06,06,11,11},
        {01,11,00,00,11,01},
        {01,01,00,00,01,01},
        {01,00,07,00,00,01},
        {01,01,8,8,01,01}};
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
               {01,00,00,00,07,00,00,00,01},
               {01,00,00,00,00,00,00,00,01},
               {01,00,00,00,13,00,00,00,01},
               {01,00,00,00,00,00,00,00,01},
               {01,01,01,01,01,01,01,01,01}};
                
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
              {01,04,00,07,8,00,00,01},
              {01,04,04,00,00,00,00,01},
              {01,01,01,01,01,01,01,01}};
              
public static int[][] xviii = {{17,19},
            {01,01,01,01,01,01,01,01},
            {01,00,00,07,8,01,01,01},
            {01,17,00,00,00,01,01,01},
            {01,00,00,00,00,00,00,01},
            {01,01,01,01,01,01,00,01},
            {01,04,00,00,00,00,00,01},
            {01,01,01,01,01,01,01,01}};
            
public static int[][] xix = {{18,20},
{01,01,01,01,01,01,01,01,01,01},
{01,01,00,00,00,00,00,07,8,01},
{01,01,00,01,01,01,01,01,01,01},
{01,00,00,00,00,00,01,01,01,01},
{01,00,00,00,00,00,01,01,01,01},
{01,01,00,01,01,01,01,01,01,01},
{01,01,04,01,01,01,01,01,01,01}};

public static int[][] xx = {{19,21},
{01,8,01,01,01,01,01,01,01,01,01},
{01,07,01,01,01,01,01,01,01,01,01},
{01,00,01,01,00,00,00,01,01,01,01},
{01,00,01,01,00,00,00,00,01,01,01},
{01,00,01,01,00,06,18,00,04,01,01},
{01,00,11,00,00,00,00,00,01,01,01},
{01,01,01,01,01,01,01,01,01,01,01}};


    //variables relating to movement
    int charX = 7;
    int charY = 5;
    //int inventoryOpen = 0;
    InventoryMenu I = new InventoryMenu();
    
    //progress monitoring
    int laserCheck = 0; //checks if the death cannon has been fired
    int uanCheckOne = 0; //checks if uandar is in the party --> he leaves
    int uanCheckTwo = 0; //checks if the player has seen him captured - interaction
    int warCheck = 0; //checks if the player has encountered the warrior
    int xpConst = 0; //used to check if player has resorted to violence
     
    //extra color constants
    Color WOOD = new Color(66,41,1);
    Color HKIT = new Color(24,242,126);
    Color CAVE = new Color(8,79,10);
    
    //player
    Player myChar = new Player(50,10,7,2,2,50,10,1,0,4,4);
     int playerD = 1;          
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
        startMessage();
    }
    public void startMessage()
    {
       DialogueB w = new DialogueB(3,"<HTML>You...crash landed. <BR>"+
                                      "You'll need to find a way out of...<BR>"+
                                      "Wherever this place is<BR>"+
                                      "===========================<BR>"+
                                      "Use the arrow keys to move Geruo<BR>"+
                                      "Collect items by walking into them<BR>"+
                                      "Press Q to see your items<BR>"+
                                      "Press W to craft new ones<BR>"+
                                      "Press E to inspect yourself<BR>"+
                                      "Expect the unexpected"+
                                      "</HTML>",0,I,myChar,this); 
       w.pack();
       w.setLocationRelativeTo(this);
       w.setVisible(true);
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
    public Player currentGeruo()
    {
        return myChar;
    }
    public void uanScene(int state)
    {
        uanCheckTwo = state;
        repaint();
    }
    public int[][] findMap(int curr)
    {
        switch(curr)
        {
            case -4:
                return minusfour;
                
            case -3:
                return minusthree;
            
            case -2:
                return minustwo;
                
            case -1:
                return minusone;
                
            case 0:
                return zero;
                
            case 1:
                return i;
                
            case 2:
                I.addToInv(62);
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
            case 19:
                return xix;
            case 20:
                return xx;
            
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
                        charX = x;
                        charY = y;
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
            //blue planet destruction    
            if (!defeatedLaser)
            {
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {

                    if (xvii[y][x] == 8 || xvii[y][x] == 6)
                    {
                        xvii[y][x] = 0;
                        repaint();
                    }
                }}
                DialogueB destMsg = new DialogueB(0,"Delphini-Porcastella has been destroyed",0,I,myChar,this);
                destMsg.pack();
                destMsg.setVisible(true);
                destMsg.setLocationRelativeTo(null);
            }
            else
            //green planet modification - map 3 replaced by map -2
            {
                v[0][0] = -2;
                DialogueB destMsg = new DialogueB(0,"The weapon was redirected in time",0,I,myChar,this);
                destMsg.pack();
                destMsg.setVisible(true);
                destMsg.setLocationRelativeTo(null);
                
            }
            laserCheck = 1;
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
            
            else if (g == 11)
            {
                gx = xx[1].length;
            gy = xx.length;
            for (int y = 1; y < gy ; y++)
           
            {
                for (int x = 0; x < gx; x++)
                {

                    if (xx[y][x] == 11 || xx[y][x] == 6 || xx[y][x] == 18)
                    {
                       xx[y][x] = 0;
                    }
                }}
                repaint();
            }
            
        }
    
    public void uanFinal()
    {
        DialogueB part = new DialogueB(8,"<HTML>You've saved me twice now...<BR>I'm...grateful that you did. Thank you.</HTML>",0,I,myChar,this);
        switch(uanCheckTwo)
        {
            
            case 0:
                I.addToInv(uanCheckOne+1);
                break;
            case 2:
                I.addToInv(uanCheckOne);
                break;
            case 3:
                part = new DialogueB(8,"...it's too late for me. Get out of this place while you can.",0,I,myChar,this);
                break;
            case 4:
                part = new DialogueB(3,"I should've acted faster...NO! He made his choices.",0,I,myChar,this);
                break;
                
        }
        part.setVisible(true);
        part.pack();
        part.setLocationRelativeTo(null);
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
        if (c == -1)
        {
            xvii[0][0] = -1;
        }
        if (c == 16)
        {
            xvii[0][0] = 16;
        }
        if (c == 18 && laserCheck == 0)
        {
            editArea(9);
        }
        if (c == 18 && uanCheckOne == 0) 
        {
            boolean x = false;
            int z = 0;
            for (int w = 60; w < 70; w++)
            {
                if (I.searchInvFor(w))
                {
                x = true;
                z= w;
            }
                
            }
            if (x)
            {
                I.removeFromInv(z);
                uanCheckOne = z;
                DialogueB leaving = new DialogueB(8,"You shouldn't have saved me. I'm not staying.",0,I,myChar,this);
                leaving.setVisible(true);
                
                leaving.pack();
                leaving.setLocationRelativeTo(null);
            }
            else
            {
                uanCheckOne = 1;
            }
        }
        if (c == 20 && uanCheckOne == 1)
        {
            uanCheckTwo = 5;
            editArea(11);
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
                I.setSize(900,720);
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
            Q.setSize(900,720);
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
            playerD = 1;
           
           
        }
        else if (z == KeyEvent.VK_LEFT)
        {
            charX--;
            playerD = 2;
        }
        else if (z == KeyEvent.VK_UP)
        {
            charY--;
            playerD = 3;
        }
        else if (z == KeyEvent.VK_DOWN)
        {
            charY++;
            playerD = 4;
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
                    if (arr[charY][charX] ==11 && currentSection == 20)
                    {
                         DialogueB d4 = new DialogueB(9,"You had strict instructions ", 20,I,myChar,this);
                         d4.setVisible(true);
                         d4.pack();
                         d4.setLocationRelativeTo(null);
                    }
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
                            if (currentSection == 2)
                            {
                             Enemy e1 = new Enemy(20,0,6,2,1,20,0, 15);
                             e1.diffSpike(Dif);
                             BattleBox b = new BattleBox(myChar, e1, I, this);
                             b.setVisible(true);
                             b.setSize(900,720);
                             b.setLocationRelativeTo(null);
                             stepsLeft = R.nextInt(10)+30;
                            }
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
                            if (currentSection == -3)
                            {
                                Enemy e1;
                                if (R.nextInt(3) == 1)
                                {
                             e1 = new Enemy(40,0,8,2,8,40,0, 13);
                            }
                            else
                            {
                                e1 = new Enemy(87,10,15,3,5,87,10, 12);
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
                        DialogueB d4 = new DialogueB(3,"I need a key to open this door", 0,I,myChar,this);
                         d4.setVisible(true);
                         d4.pack();
                         d4.setLocationRelativeTo(null);
                          charX = tX;
                          charY = tY;
                           arr[charY][charX] = 7;
                    }
                }
                //interacting with craft spot
                else if (arr[charY][charX] == 17)
                {
                    complexCraft Q = new complexCraft(I);
       
                    Q.setVisible(true);
                    Q.setSize(900,720);
                     Q.setLocationRelativeTo(null);
                     charX = tX;
                          charY = tY;
                           arr[charY][charX] = 7;
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
                        DialogueB d = new DialogueB(99,"<HTML>important to behave approapriately <BR>"+
                                                              "when at another planet. Many inhab<BR>"+
                                                              "-itants communicate in ways that may<BR>"+
                                                              "be dangerous to your species. It is<BR>"+
                                                              "best to respond in kind and ATTACK.<BR>"+
                                                              "Be wary, not all species are able to<BR>"+
                                                              "survive such rough interactions and<BR>"+
                                                              "it is perfectly respectable to avoid<BR>"+
                                                              "combat altogether and FLEE, should they<BR>"+
                                                              "allow it.<BR>"+
                                                              "Moving on, it is always important to carry</HTML>",0,I,myChar,this); 
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
                        
                    }
                    else if (currentSection == 7)
                    {
                        DialogueB d = new DialogueB(99,"<HTML>that you shouldn't hold onto all souvenirs<BR>"+
                                                              "but there are limits to what you can take<BR>"+
                                                              "home with you. Wouldn't a picture be better?<BR>"+
                                                              "<BR>"+
                                                              "On local customs:<BR>"+
                                                              "Some species seem to partake of some actions<BR>"+
                                                              "or ''arts'' that can be quite harmful, even for <BR>"+
                                                              "their own species. Don't join them. They've dug<BR>"+
                                                              "their own hole. There may be some benefits to <BR>"+
                                                              "their practices. But *I* would never be found<BR>"+
                                                              "partaking of something so clearly detrimental to</HTML>",0,I,myChar,this); 
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
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
                        if (laserCheck == 0)
                        {
                        DialogueB d = new DialogueB(8,"HALT! This shuttle will NOT be leaving this planet!",4,I,myChar,this);
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
                    }
                    else
                    {
                        DialogueB d = new DialogueB(8,"<HTML>You found another takeoff zone?<BR>And destroyed the laser?<BR>HOW?</HTML>",0,I,myChar,this);
                        d.setVisible(true);
                        d.pack();
                        d.setLocationRelativeTo(null);
                    }
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
                    else if (currentSection == -4)
                    {
                        bossEnemy ed = new bossEnemy(1000,0,30,4,4,1000,4, 14,myChar,I,this);
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
        ImageIcon block = new ImageIcon(getClass().getResource("MAPbg.png"));
        int type = 0;
        
           
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
                            type = 0;
                            if (currentSection < 6)
                            {
                            g.setColor(Color.GREEN);
                            if (currentSection <-1)
                            {g.setColor(Color.GREEN.darker().darker());}
                            }
                            if (currentSection == 6 || currentSection == 17)
                            {
                            g.setColor(Color.BLACK);
                            }
                            if ((currentSection > 6 && currentSection < 17) || currentSection == -1)
                            {
                            g.setColor(Color.CYAN);
                            }
                            if (currentSection > 17 && currentSection < 25)
                            {
                            g.setColor(Color.PINK);
                            }
                            break;
                        case 1:
                            type = 0;
                            if (currentSection < 6)
                            {
                            g.setColor(Color.DARK_GRAY);
                            }
                            if (currentSection == 6 || currentSection == 17)
                            {
                            //g.setColor(Color.BLACK);
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPbg.png"));
                            }
                            if ((currentSection > 6 && currentSection < 17) || currentSection == -1)
                            {
                            g.setColor(Color.BLUE);
                            }
                            if (currentSection > 17 && currentSection < 25)
                            {
                            g.setColor(Color.MAGENTA.darker());
                            }
                            break;
                        case 2:
                            //changed block colour
                            //g.setColor(WOOD);
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPstick.png"));
                            break;
                        case 8:
                            type = 0;
                            if (currentSection < 6)
                            {
                            g.setColor(Color.GREEN.darker());
                            }
                             if (currentSection == 6)
                            {
                            g.setColor(Color.GREEN);
                            }
                             if ((currentSection > 6 && currentSection < 17)||currentSection == -1)
                            {
                            g.setColor(Color.CYAN.darker());
                            }
                             if (currentSection == 17)
                            {
                            g.setColor(Color.BLUE);
                            }
                            if (currentSection == 7 || currentSection == 18)
                            {
                                type = 1;
                               block = new ImageIcon(getClass().getResource("MAPship.png"));
                            }
                            if (currentSection > 18 && currentSection < 25)
                            {
                            g.setColor(Color.PINK.darker());
                            }

                            break;
                        case 4:
                            type = 0;
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
                                type = 1;
                               block = new ImageIcon(getClass().getResource("MAPship.png"));
                            }
                             if (currentSection > 17 && currentSection < 25)
                            {
                            g.setColor(Color.PINK.darker());
                            }

                            break;
                        
                        case 5:
                            type = 0;
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
                            if (currentSection > 17 && currentSection < 25)
                            {
                            g.setColor(Color.PINK.darker());
                            }
                            break;
                        case 7:
                            type =1;
                            
                            if (currentSection == 6 || currentSection == 17)
                            {
                                switch(playerD)
                                {
                                    case 1:
                                        block = new ImageIcon(getClass().getResource("MAPshiR.png"));
                                        break;
                                     case 2:
                                        block = new ImageIcon(getClass().getResource("MAPshiL.png"));
                                        break;   
                                    case 3:
                                        block = new ImageIcon(getClass().getResource("MAPshiU.png"));
                                        break;
                                    case 4:
                                        block = new ImageIcon(getClass().getResource("MAPshiD.png"));
                                        break;
                                }
                            }
                            else
                            {
                                switch(playerD)
                                {
                                    case 1:
                                        block = new ImageIcon(getClass().getResource("MAPgerR.png"));
                                        break;
                                     case 2:
                                        block = new ImageIcon(getClass().getResource("MAPgerL.png"));
                                        break;   
                                    case 3:
                                        block = new ImageIcon(getClass().getResource("MAPgerU.png"));
                                        break;
                                    case 4:
                                        block = new ImageIcon(getClass().getResource("MAPgerD.png"));
                                        break;
                                }
                            }
                            break;
                        case 9:
                            //g.setColor(Color.LIGHT_GRAY);
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPscrap.png"));
                            break;
                        case 6:
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPbg.png"));
                            if (currentSection == 0)
                            {
                            block = new ImageIcon(getClass().getResource("MAPora.png"));
                            }
                            if (currentSection == 1)
                            {
                            block = new ImageIcon(getClass().getResource("MAPpage.png"));
                            }
                            if (currentSection == 5)
                            {
                            block = new ImageIcon(getClass().getResource("MAPdle.png"));
                            }
                            if (currentSection == 4)
                            {
                            block = new ImageIcon(getClass().getResource("MAPbrid.png"));
                            }
                            if (currentSection == 7)
                            {
                            block = new ImageIcon(getClass().getResource("MAPpage.png"));
                            }
                            if (currentSection == 11)
                            {
                            block = new ImageIcon(getClass().getResource("MAPkel.png"));
                            }
                            if (currentSection == 13)
                            {
                            block = new ImageIcon(getClass().getResource("MAPbos.png"));
                            }
                            if (currentSection == 16)
                            {
                            block = new ImageIcon(getClass().getResource("MAPuua.png"));
                            }
                            if (currentSection == 17)
                            {
                            block = new ImageIcon(getClass().getResource("MAPwep.png"));
                            }
                            if (currentSection == 20)
                            {
                                if (uanCheckTwo == 0)
                                {
                            block = new ImageIcon(getClass().getResource("MAPdrama1.png"));
                        }
                        else if (uanCheckTwo == 2)
                                {
                            block = new ImageIcon(getClass().getResource("MAPdrama2.png"));
                        }
                        else if (uanCheckTwo == 3)
                                {
                            block = new ImageIcon(getClass().getResource("MAPdrama3.png"));
                        }
                            }
                        
                            break;
                        case 18:
                            if (currentSection == 20)
                            {
                            block = new ImageIcon(getClass().getResource("MAPclow.png"));
                            }
                        
                            break;
                        case 12:
                            //g.setColor(Color.ORANGE.darker().darker().darker().darker());
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPoil.png"));
                            break;
                        case 11:
                            //g.setColor(Color.BLACK);
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPbg.png"));
                            break;
                         case 13:
                            //g.setColor(Color.DARK_GRAY.darker());
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPshipspot.png"));
                            break;
                        case 14:
                            //g.setColor(Color.YELLOW);
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPkey.png"));
                            break;
                        case 15:
                            //g.setColor(Color.GRAY);
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPkeyhole.png"));
                            break;
                        case 16:
                            //g.setColor(Color.YELLOW.darker());
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPkeyshard.png"));
                            break;
                        case 17:
                            
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPcraf.png"));
                            break;
                        case 10:
                            
                            //g.setColor(HKIT);
                            type = 1;
                            block = new ImageIcon(getClass().getResource("MAPbox.png"));
                            break;
                            
                   
                       
                       
                    }
                    if (type == 0)
                    {
                    g.drawRect(((x+ excessX) * 30),((y-1+excessY)*30), 30, 30);
                    g.fillRect(((x+ excessX) * 30),((y-1+excessY)*30), 30, 30);
                }
                else
                {
                    block.paintIcon(this,g,((x+ excessX) * 30),((y-1+excessY)*30));
                }
                    
                }
            }
            
            
    }
   
}
   

