
/**
 * Write a description of class Battle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;
import java.util.ArrayList;
public class BattleBox extends JFrame
{
    Player player;
    Enemy enemy;
    InventoryMenu items;
    JButton attack, skills, useItems, defend;
    //labels for player HP and MP, enemy HP, Player and enemy icons, 2 blank labels used to visually display
    JLabel hp, mp, ehp, pcon, econ, blank, blank2;
    ImageIcon pla, ene;
    int turn = 1;
    int playerDefending = 0;
    Random rd = new Random();
    int startDef;
    int enemyCharged = 0;
    int guardCooldown = 0;
    
    ImageIcon shield = new ImageIcon(getClass().getResource("Shield.png"));
    ImageIcon hit = new ImageIcon(getClass().getResource("ppunch.png"));
    ImageIcon ypunch = new ImageIcon(getClass().getResource("epunch.png"));
    ImageIcon anger = new ImageIcon(getClass().getResource("yangry.png"));
    ImageIcon chItem = new ImageIcon(getClass().getResource("type item.png"));
    ImageIcon empty = new ImageIcon(getClass().getResource("blankitem.png"));
    ImageIcon range = new ImageIcon(getClass().getResource("outOfRange.png"));
    ImageIcon notInt = new ImageIcon(getClass().getResource("notNum.png"));
    ImageIcon gameOver = new ImageIcon(getClass().getResource("newerGOscreen.png"));
    ImageIcon enemyDefeat1 = new ImageIcon(getClass().getResource("Ydefeat.png"));
    ImageIcon playerDefeat = new ImageIcon(getClass().getResource("Rdefeat.png"));

    ImageIcon blueEnemy = new ImageIcon(getClass().getResource("Bic.png"));
    ImageIcon enemyDefeat2 = new ImageIcon(getClass().getResource("Bdefeat.png"));
    ImageIcon blueFire = new ImageIcon(getClass().getResource("Bfire.png"));
    ImageIcon bCharge = new ImageIcon(getClass().getResource("Bcharged.png"));
    
    ImageIcon dolph = new ImageIcon(getClass().getResource("Gic.png"));
    ImageIcon dolphBoost = new ImageIcon(getClass().getResource("Ghigh.png"));
    ImageIcon dolphDef = new ImageIcon(getClass().getResource("Gdefeat.png"));
    ImageIcon dPuff = new ImageIcon(getClass().getResource("Gpuff.png"));
    ImageIcon gHit = new ImageIcon(getClass().getResource("hit.png"));
    //determines whether the player is choosing an item or a skill
    int choosing = 0;
    //used to submit a choice
    JTextField enterChoice;
    JButton submit, exit;
    
    //represents skills numerically - (id of skill, MP cost, type of skill [offensive?, healing? etc.], numerical data)
   int[][] skillsList = {{1,5,1,14},{2,2,2,8},{3,8,1,20},{4,0,3,8},{5,5,4,4},{6},{7},{8},{9},{10,8,1,24},
   {11,10,1,40},{12,2,5,0},{13,5,1,40} };
   String[] skillNames = {"","Forceful thrust","Self-care","Ten-tackle","Energy drain","Weaken",
    "","","","","Lethal chop","Sword song","Parry","Exhaust flame"};
    ArrayList <Integer> availableSkills = new ArrayList<Integer>();
    
    //needed for tag team
    int turns = 0; 
   
   Display d;
   
    //also needed for tag team
    BattleBox b2;
    DialogueB defeatMsg;
    DialogueB lvlUpMsg;
    Player p2;
    
    //needed to make speed an important stat
    int playerRepeats;
    int enemyRepeats;
    public BattleBox(Player p, Enemy e, InventoryMenu i, Display di)
    {
        player = p;
        enemy = e;
        items = i;
        startDef = p.getDF();
        d = di;
        playerRepeats = Math.round(p.getSD()/e.getSD());
        enemyRepeats = Math.round(e.getSD()/p.getSD());
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        attack = new JButton("ATTACK");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(attack, c);
        
        skills = new JButton("SKILLS");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        add(skills, c);
        
        useItems = new JButton("ITEMS");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        add(useItems, c);
        
        defend = new JButton("GUARD");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        add(defend, c);
        
        pla = new ImageIcon(getClass().getResource("Ric.png"));
        pcon = new JLabel(pla);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(pcon, c);
        
        //player blank
        blank = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        
        add(blank, c);
        
        //enemy blank
        blank2 = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        
        add(blank2, c);
        
        ene = new ImageIcon(getClass().getResource("Yic.png"));
        econ = new JLabel(ene);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        
        add(econ, c);
        
        hp = new JLabel((p.getHP())+"/"+(p.getmHP()));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        
        add(hp, c);
        
        ehp = new JLabel((e.getHP())+"/"+(e.getmHP()));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;
        
        add(ehp, c);
        
        mp = new JLabel((p.getMP())+"/"+(p.getmMP()));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        
        add(mp, c);
        
        enterChoice = new JTextField();
        enterChoice.setVisible(false);
         c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        
        add(enterChoice, c);
        
        
        submit = new JButton("submit");
        submit.setVisible(false);
         c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 5;
        
        add(submit, c);
        
        exit = new JButton("exit");
        exit.setVisible(false);
         c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 6;
        
        add(exit, c);
        
        
        
        event e2 = new event();
        attack.addActionListener(e2);
        skills.addActionListener(e2);
        useItems.addActionListener(e2);
        defend.addActionListener(e2);
        submit.addActionListener(e2);
        exit.addActionListener(e2);
        
        enemyIconSetup();
        skillSetup1(player.getLv());
    }
    
    public void skillSetup1(int lv)
    {
        if (player.getID() == 4){
        availableSkills.add(0);
        availableSkills.add(1);
        if (lv >= 2)
        {
            availableSkills.add(2);
            availableSkills.add(3);
        }
        if (lv >= 3)
        {
            availableSkills.add(4);
            
        }
    } 
    if (player.getID() == 3)
    {
        skills.setText("REV UP");
        pla = new ImageIcon(getClass().getResource("Yic.png"));
        shield = new ImageIcon(getClass().getResource("ydef.png"));
        playerDefeat = new ImageIcon(getClass().getResource("yangry.png"));
        pcon.setIcon(pla);
        hit = new ImageIcon(getClass().getResource("dpunch.png"));;
    }
    if (player.getID() == 5)
    {
        skills.setText("TECHNIQUE");
        attack.setVisible(false);
        pla = new ImageIcon(getClass().getResource("Koldec.png"));
        shield = new ImageIcon(getClass().getResource("ydef.png"));
        playerDefeat = new ImageIcon(getClass().getResource("oilFire.png"));
        pcon.setIcon(pla);
        hit = new ImageIcon(getClass().getResource("dpunch.png"));;
    }
    if (player.getID() == 6)
    {
        skills.setText("BAD HABITS");
        pla = new ImageIcon(getClass().getResource("Yic.png"));
        shield = new ImageIcon(getClass().getResource("ydef.png"));
        playerDefeat = new ImageIcon(getClass().getResource("yangry.png"));
        pcon.setIcon(pla);
        hit = new ImageIcon(getClass().getResource("gHit.png"));;
    }
    }
    
    public void enemyIconSetup()
    {
        switch(enemy.getID())
        {
            case 2:
                econ.setIcon(blueEnemy);
                break;
            case 3:
                econ.setIcon(dolphBoost);
                break;
            case 4:
                ene = new ImageIcon(getClass().getResource("Oic.png"));
                anger = new ImageIcon(getClass().getResource("Ocharged.png"));
                econ.setIcon(ene);
                break;
            case 5:
                ene = new ImageIcon(getClass().getResource("Pcharge.png"));
                anger = new ImageIcon(getClass().getResource("Pic.png"));
                ypunch = new ImageIcon(getClass().getResource("Pshot.png"));
                econ.setIcon(ene);
                break;
            case 6:
                ene = new ImageIcon(getClass().getResource("KBoss.png"));
                anger = new ImageIcon(getClass().getResource("axThr.png"));
                ypunch = new ImageIcon(getClass().getResource("oilFire.png"));
                econ.setIcon(ene);
                break;
            case 7:
              
                ene = new ImageIcon(getClass().getResource("genSh.png"));
              
                econ.setIcon(ene);
                break;
            case 8:
                ene = new ImageIcon(getClass().getResource("gen.png"));
                anger = new ImageIcon(getClass().getResource("genA.png"));
                ypunch = new ImageIcon(getClass().getResource("acid.png"));
                econ.setIcon(ene);
                break;
            case 9:
                ene = new ImageIcon(getClass().getResource("DBossph1.png"));
                ypunch = new ImageIcon(getClass().getResource("hugepuff.png"));
                
                econ.setIcon(ene);
                break;
            case 10:
                ene = new ImageIcon(getClass().getResource("DBoss.png"));
                ypunch = new ImageIcon(getClass().getResource("hugepuff.png"));
                enemyDefeat1 = new ImageIcon(getClass().getResource("DBossDown.png"));
                econ.setIcon(ene);
                break;
            case 11:
                ene = new ImageIcon(getClass().getResource("deathLaser.png"));
                econ.setIcon(ene);
                break;
        }
    }
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e1)
        {
            String optionCheck = e1.getActionCommand();
            switch(optionCheck)
            {
                case "leave":
                    d.revivePlayer();
                    dispose();
                    break;
                case "exit":
                    //items as reward
                    int nextPhase = 0;
                    boolean collect = false;
                    switch(enemy.getID())
                    {
                         
                        case 1:
                            for (int j = 0; j < 4; j++)
                            {
                              collect = items.addToInv(4);
                              
                    
                        }
                        collect = items.addToInv(6);
                        d.editArea(1);
                        player.setXP(player.getXP()+10);
                        
                        break;
                         case 2:
                            for (int j = 0; j < 2; j++)
                            {
                              collect = items.addToInv(3);
                    
                        }
                        player.setXP(player.getXP()+2);
                        break;
                         case 3:
                            for (int j = 0; j < 3; j++)
                            {
                              collect = items.addToInv(2);
                    
                        }
                        if (enemy.getMP() > 4)
                        {
                            collect = items.addToInv(16);
                        }
                        player.setXP(player.getXP()+18);
                        break;
                        
                        case 4:
                            
                        d.editArea(5);
                        player.setXP(player.getXP()+20);
                        
                        break;
                        
                        case 5:
                            for (int j = 0; j < 2; j++)
                            {
                              collect = items.addToInv(4);
                    
                        }
                        player.setXP(player.getXP()+25);
                        break;
                        
                        case 6:
                           
                        collect = items.addToInv(51);
                        d.editArea(6);
                        player.setXP(player.getXP()+40);
                        break;
                        
                        case 7:
                            bossEnemy ph = new bossEnemy(30,4,8,2,5,60,10, 8,player,items,d);
                            b2 = new BattleBox(player,ph,items,d);
                            b2.setVisible(true);
                            b2.setSize(900,720);
                            b2.setLocationRelativeTo(null);
                            nextPhase = 1;
                            dispose();
                            break;
                        case 8:
                            player.setXP(player.getXP()+50);
                            d.editArea(7);
                            break;
                        case 9:
                            bossEnemy d2 = new bossEnemy(120,17,12,4,6,120,17,10,player,items,d);
                            b2 = new BattleBox(player,d2,items,d);
                            b2.setVisible(true);
                            b2.setSize(900,720);
                            b2.setLocationRelativeTo(null);
                            nextPhase = 1;
                            dispose();
                            break;
                        case 10:
                            d.editArea(8);
                            DialogueA da = new DialogueA(8,"<HTML>Leave me! <BR>I'd rather be gone with this planet<BR> than betray the emperor!</HTML>",7,items,player,d);
                            da.pack();
                            da.setLocationRelativeTo(null);
                            da.setVisible(true);
                            for (int j = 0; j < 20; j++)
                            {
                              collect = items.addToInv(16);
                    
                        }
                            break;
                        case 11:
                            d.editArea(10);
                            //that took guts...aliens have those, right?
                            player.setXP(player.getXP()+80);
                            break;

                }
                
                //level up
                if (player.getXP() >= player.getmXP())
                {
                    
                    player.setXP(player.getXP()-player.getmXP());
                    if (player.getmXP() < 256 && player.getID() == 4)
                    {
                    player.setmXP(player.getmXP() * player.getmXP());
                }
                else
                {
                    player.setmXP(player.getmXP()*2);
                }
                    player.setmHP(player.getmHP()+player.getLv());
                    player.setmMP(player.getmMP()+player.getLv());
                    player.setAT(player.getAT()+player.getLv());
                    player.setDF(player.getDF()+player.getLv());
                    player.setSD(player.getSD()+player.getLv());
                    player.setHP(player.getmHP());
                    player.setMP(player.getmMP());
                    player.setLv(player.getLv()+ 1);
                    
                }
                if (player.getID() != 4 && nextPhase == 0)
                {
                    items.addToInv((player.getID()*10)+(player.getLv()-1));
                    for (int w = 40; w < 50; w++)
                    {
                    items.removeFromInv(w);
                }
                }
                    dispose();
                    break;
                
                //handles player attack
                case "ATTACK":
                  if (turn == 1 && choosing == 0)
                  {
                      if (player.getID()== 3)
                      {
                          player.setAT(player.getAT()+(player.getMP()*3));
                        }
                  player.attack(enemy);
                  if (player.getID()== 3)
                      {
                          player.setAT(player.getAT()-(player.getMP()*3));
                          player.setMP(0);
                          pcon.setIcon(pla);
                        }
                  ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
                  mp.setText((player.getMP())+"/"+(player.getmMP()));
                 
                try{
                   blank.setIcon(hit);
                  Thread.sleep(500);
                  
                  
                }
                catch(Exception e)
                {
                    System.out.println("error");
                    
                }
                //switches to enemy turn
                turn = 2;
                enemyTurn();
                
                }
                  break;
                
                //handles charging attacks
                case "REV UP":
                    if (turn == 1 && choosing == 0)
                  {
                    player.recover(1);
                    pcon.setIcon(playerDefeat);
                    mp.setText((player.getMP())+"/"+(player.getmMP()));
                    turn = 2;
                    enemyTurn();
                }
                    break;
                //handles skill attacks - uses MP
                case "SKILLS":
                   
                   if (turn == 1 && choosing == 0)
                   {
                       //add weapon specific skills
                       if (items.searchInvFor(7))
                       {
                           availableSkills.add(9);
                        }
                        if (items.searchInvFor(5))
                       {
                           availableSkills.add(10);
                           availableSkills.add(11);
                        }
                        if (items.searchInvFor(12))
                       {
                           availableSkills.add(12);
                        }
                       blank.setIcon(null);
                       String skillsDisplay = "<HTML> "+skillsList[0][0] + ":" + skillNames[1] + " - " + skillsList[0][1] +"MP";
                       int leng = availableSkills.size();
                       leng = leng;
                       String tempString = "";
                       for (int z = 1; z < leng; z++)
                       {
                           tempString = skillsList[availableSkills.get(z)][0] + ":" + skillNames[(skillsList[availableSkills.get(z)][0])] + " - " + skillsList[availableSkills.get(z)][1] +"MP";
                           skillsDisplay = skillsDisplay+" <BR> "+tempString;
                        }
                       skillsDisplay = skillsDisplay +" </HTML>";
                   
                       blank.setText(skillsDisplay);
                       choosing = 2;
                       enterChoice.setVisible(true);
                       submit.setVisible(true);
                       
                    }
                
                break;
                
                //handles items 
                case "ITEMS":
                   if (turn == 1 && choosing == 0)
                   {
                       //shows the item menu
                       items.setVisible(true);
                       items.setSize(300,150);
                       items.setLocationRelativeTo(null);
                       //tells programm that an item is being chosen
                       choosing = 1;
                       blank.setIcon(chItem);
                       //sets it visible
                       enterChoice.setVisible(true);
                       submit.setVisible(true);
                       
                    }
                
                break;
                
                //handles submissions
                case "submit":
                    if(choosing == 1)
                    { 
                        try
                        {
                            int itemNo = (Integer.parseInt(enterChoice.getText()));
                            //-1 allows the player to go back from items (e.g. if they have no items
                            //in their inventory to use or if they want to use a different option)
                            if (itemNo == -1)
                            {
                                choosing = 0;
                                submit.setVisible(false);
                                enterChoice.setVisible(false);
                                items.setVisible(false);
                                blank.setIcon(null);
                                blank.setText(null);
                            }
                            else if(itemNo > 20 || itemNo < 1)
                            {
                                blank.setIcon(range);
                            }
                            else
                            {
                            //checks for the correct item
                            int itemToUse = items.peekItem(itemNo - 1);
                            if (itemToUse == 0)
                            {
                                //if item is empty, does nothing
                                blank.setIcon(empty);
                                
                            }
                            
                            else if (itemToUse > 29 && turns < 4)
                                {
                                    
                                        //prevents player from immediately tagteaming
                                        blank.setIcon(null);
                                        blank.setText("Wait "+ (4-turns)+ " turns before switching out");
                                    
                                }
                            
                            
                            else
                            {
                                items.removeFromInv(itemToUse);
                                //to prevent really stupid glitches
                                //this did not prevent those stupid glitches - keep looking
                                playerRepeats = 0;
                                System.out.println("Test 1 - item correct");
                                //System.out.println("item id:"+itemToUse);
                                if (player.getID() == 5)
                                {
                                    player.attack(enemy);
                                }
                                switch(itemToUse)
                                {
                                    case 1:
                                        player.heal(10);
                                        hp.setText((player.getHP())+"/"+(player.getmHP()));
                                        break;
                                        
                                    case 2:
                                        player.recover(10);
                                        mp.setText((player.getMP())+"/"+(player.getmMP()));
                                        break;
                                    case 3:
                                        enemy.heal(-2);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 4:
                                        enemy.heal(-4);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 5:
                                        enemy.heal(-15);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 6:
                                        enemy.heal(-30);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 7:
                                        enemy.heal(-15);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 8:
                                        enemy.heal(-2);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 11:
                                        enemy.heal(-80);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 12:
                                        enemy.heal(-40);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        break;
                                    case 13:
                                        if (player.getID() == 5)
                                        {
                                        enemy.heal(-20);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                    }
                                    else
                                    {
                                        enemy.setSD(enemy.getSD()-2);
                                    }
                                        break;   
                                    case 16:
                                        player.setMP(player.getmMP());
                                        //this is permanent for Geruo
                                        player.setSD(player.getSD()-rd.nextInt(1));
                                        player.heal(-1*rd.nextInt(2));
                                        
                                    //Dleg
                                    case 30:
                                        p2 = new Player(70,0,4,3,1,70,1,1,0,2,3);
                                        break;
                                        
                                    case 31:
                                        p2 = new Player(72,0,7,6,2,72,2,2,0,4,3);
                                        break;
                                    case 32:
                                        p2 = new Player(76,0,9,8,2,76,3,3,0,8,3);
                                        break;
                                        
                                    case 33:
                                        p2 = new Player(82,0,7,6,2,82,4,4,0,16,3);
                                        break;
                                    case 34:
                                        p2 = new Player(88,0,9,6,3,88,5,5,0,32,3);
                                        break;
                                    case 35:
                                        p2 = new Player(94,0,11,8,3,94,6,6,0,64,3);
                                        break;
                                    //Geruo
                                    case 40:
                                        p2 = new Player(50,10,7,2,2,50,10,1,0,4,4);
                                        break;
                                    case 41:
                                        p2 = new Player(51,11,8,3,3,51,11,2,0,16,4);
                                        break;
                                    case 42:
                                        p2 = new Player(53,13,10,5,5,53,13,3,0,256,4); 
                                        break;
                                    case 43:
                                        p2 = new Player(56,16,13,8,8,56,13,4,0,65536,4);
                                        break;
                                    //Keldoc
                                    case 50:
                                        p2 = new Player(45,0,3,1,6,45,0,1,0,4,5);
                                        break;
                                    case 51:
                                        p2 = new Player(47,0,5,2,8,47,0,2,0,8,5);
                                        break;
                                    case 52:
                                        p2 = new Player(50,0,7,4,10,50,0,3,0,32,5);
                                        break;
                                        //needs to be at the end of this set
                                    default:
                                        items.addToInv(itemToUse);
                                        break;
                                }
                                //additional code if player tags out
                                if (itemToUse > 29)
                                {
                                    //stores player details, so that they can be rebooted as required
                                    System.out.println(itemToUse);
                                    System.out.println("Test 2 - tagged in");
                                    b2 = new BattleBox(p2, enemy, items, d);
                                    b2.setVisible(true);
                                    b2.setSize(900,720);
                                    b2.setLocationRelativeTo(null);
                                    items.addToInv((player.getID()*10)+(player.getLv()-1)); 
                                    dispose();
                                      
                                    
                                }
                                //enemy turn if item actually works
                                blank.setIcon(null);
                                blank.setText(null);
                                choosing = 0;
                                submit.setVisible(false);
                                enterChoice.setVisible(false);
                                items.setVisible(false);
                                
                                //if player tags out, the enemy shouldn't be able to kill them 
                                // this will cause a funny but annoying glitch
                                //therefore...
                                if (itemToUse < 30)
                                {
                                    
                                    turn = 2;
                                enemyTurn();
                                }
                                
                            }
                        }
                        }
                        catch(Exception ex)
                        {
                            blank.setIcon(notInt);

                        }
                        
                        
                        
                    }
                    else if (choosing == 2)
                    {
                       String skillzDisplay = "<HTML> "+skillsList[0][0] + ":" + skillNames[1] + " - " + skillsList[0][1] +"MP";
                       //System.out.println(skillzDisplay);
                       int leng = availableSkills.size();
                       leng = leng;
                       String tempZtring = "";
                       for (int z = 1; z < leng; z++)
                       {
                           tempZtring = skillsList[availableSkills.get(z)][0] + ":" + skillNames[(skillsList[availableSkills.get(z)][0])] + " - " + skillsList[availableSkills.get(z)][1] +"MP";
                           
                           skillzDisplay = skillzDisplay+" <BR> "+tempZtring;
                           //System.out.println(skillzDisplay);
                        }
                        skillzDisplay = skillzDisplay +" </HTML>";
                        try{
                        int skillNo = (Integer.parseInt(enterChoice.getText()));
                       
                        if (skillNo == -1)
                            {
                                choosing = 0;
                                submit.setVisible(false);
                                enterChoice.setVisible(false);
                                blank.setText(null);
                                for (int u = 0; u < availableSkills.size(); u++)
                                {
                                    if(availableSkills.get(u)>=9)
                                    {
                                        availableSkills.remove(u);
                                    }
                                }
                            }
                            else if(skillNo < 1)
                            {
                                
                                    blank.setText("<HTML>you entered a number out of range<BR>"+skillzDisplay + "</HTML>");
                            }
                            else
                            {
                                //checks if the skill is one in the list
                                boolean skillFound = false;
                                for (int i = 0; i < availableSkills.size(); i++)
                                {
                                    //System.out.println(availableSkills.get(i));
                                    //System.out.println(skillsList[availableSkills.get(i)][0]);
                                    if (skillsList[availableSkills.get(i)][0] == skillNo && skillFound == false)
                                    {
                                        skillFound = true;
                                        //checks if player has enough MP
                                        if (player.getMP() >= skillsList[availableSkills.get(i)][1])
                                        {
                                            player.setMP((player.getMP()-skillsList[availableSkills.get(i)][1]));
                                            mp.setText((player.getMP())+"/"+(player.getmMP()));
                                        switch(skillsList[availableSkills.get(i)][2])
                                        {
                                            //aggressive skills
                                            case 1:
                                                int temp = enemy.getHP();
                                                temp = temp - skillsList[availableSkills.get(i)][3];
                                                enemy.setHP(temp);
                                                ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
                                                break;
                                            
                                                //healing skills
                                            case 2:
                                                player.heal(skillsList[availableSkills.get(i)][3]);
                                                hp.setText((player.getHP())+"/"+(player.getmHP()));
                                                break;
                                                //restore
                                            case 3:
                                                for (int q =0; q < (skillsList[availableSkills.get(i)][3]); q++)
                                                {
                                                    if (enemy.getMP() > 0)
                                                    {
                                                        enemy.recover(-1);
                                                        player.recover(1);
                                                    }
                                                }
                                                mp.setText((player.getMP())+"/"+(player.getmMP()));
                                                
                                                break;
                                                
                                        }
                                        submit.setVisible(false);
                                        enterChoice.setVisible(false);
                                        blank.setText(null);
                                        choosing = 0;
                                        turn = 2;
                                        enemyTurn();
                                        //to remove weapon specific skills
                                        for (int u = 0; u < availableSkills.size(); u++)
                                {
                                    if(availableSkills.get(u)>=9)
                                    {
                                        availableSkills.remove(u);
                                    }
                                }
                                    }else
                                    {
                                         String infoText = blank.getText();
                                        blank.setText("<HTML>not enough MP for that move - select something else<BR>"+skillzDisplay + "</HTML>");
                                        
                                    }
                                    }
                                    
                                }
                                
                                if (skillFound == false)
                                {
                                    String infoText = blank.getText();
                                    blank.setText("<HTML>it appears the skill you selected isn't in the list<BR>"+skillzDisplay + "</HTML>");
                                }
                            }
                        }
                        catch(Exception ex)
                        {
                            String infoText = blank.getText();
                            blank.setText("<HTML>Enter a number<BR>"+skillzDisplay + "</HTML>");
                        }
                    }
                    
                    break;
                
                //handles guarding
                //guarding increases the player defense for 1 turn
                case "GUARD":
                  if(turn == 1 && choosing == 0 && guardCooldown == 0)
                  {
                      int tempDef = player.getDF();
                      tempDef += 5;
                      player.setDF(tempDef);
                      playerDefending = 1;
                      
                      //visual confirmation of it working 
                      blank.setIcon(shield);
                      turn = 2;
                      guardCooldown = 3;
                      //to prevent players from attacking behind their shields like cowards
                      playerRepeats = 0;
                      enemyTurn();
                      player.setDF(tempDef);
                      playerDefending = 0;
                      player.setDF(startDef);
                      
                    }
                
                break;
            }
        }
    }
    
    public void enemyTurn()
    {
        playerRepeats--;
        if (playerRepeats > 0)
        {
            
            turn = 1;
        }
        else
        {
        turns++;
        if (enemy.getHP() > 0)
        {
        int id = enemy.getID();
        int move = rd.nextInt(5);
        
        try{
                  Thread.sleep(500);
                }
                catch(Exception e)
                {
                    System.out.println("error");
                }
        switch(id)
        {
            //for 
            case 1:
                if(move < 3 || (move >= 3 && enemyCharged == 1))
                {
                   //normal attack
                   enemy.attack(player);
                   blank2.setIcon(ypunch);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   enemy.setAT(4);
                   if (enemyCharged == 1)
                   {
                   enemyCharged = 0;
                }
                   econ.setIcon(ene);
                    
                }
                else if(move >= 3 && enemyCharged != 1)
                {
                    //temporary attack boost
                    enemy.setAT(7);
                    econ.setIcon(anger);
                    enemyCharged = 1;
                    blank2.setIcon(null);
                }
                break;  
                
                 case 2:
                if(move < 3 || (move >= 3 && enemyCharged == 1))
                {
                   //normal attack
                   enemy.attack(player);
                   blank2.setIcon(blueFire);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   enemy.setAT(3);
                   if (enemyCharged == 1)
                   {
                   enemyCharged = 0;
                }
                   econ.setIcon(blueEnemy);
                    
                }
                else if(move >= 3 && enemyCharged != 1)
                {
                    //temporary attack boost
                    enemy.setAT(5);
                    econ.setIcon(bCharge);
                    enemyCharged = 1;
                    blank2.setIcon(null);
                }
                break;  
                
              case 3:
                if(move < 3 || (move >= 3 && enemyCharged == 1))
                {
                   //normal attack
                   enemy.attack(player);
                   blank2.setIcon(gHit);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   enemy.recover(-1);
                   econ.setIcon(dolph);
                   if (enemy.getMP() < 1)
                   {
                   enemyCharged = 0;
                   econ.setIcon(dolphDef);
                }
                }
                   
                    
                
                else if(move >= 3 && enemyCharged != 1)
                {
                    //temporary attack boost
                    enemy.recover(rd.nextInt(7));
                    player.recover(-2);
                    mp.setText((player.getMP())+"/" + (player.getmMP()));
                    econ.setIcon(dolphBoost);
                    blank2.setIcon(dPuff);
                    enemyCharged = 1;
                    
                }
                break;  
               
                case 4:
                if(move < 3 || (move >= 3 && enemyCharged == 1))
                {
                   //normal attack
                   enemy.attack(player);
                   blank2.setIcon(new ImageIcon(getClass().getResource("1ic.png")));
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   enemy.setAT(5);
                   if (enemyCharged == 1)
                   {
                   enemyCharged = 0;
                }
                   econ.setIcon(ene);
                    
                }
                else if(move == 3 && enemyCharged != 1 && enemy.getMP() >= 3)
                {
                    //temporary attack boost
                    enemy.setAT(10);
                    econ.setIcon(anger);
                    enemyCharged = 1;
                    enemy.recover(-3);
                    blank2.setIcon(null);
                }
                else 
                {
                    enemy.recover(4);
                    econ.setIcon(new ImageIcon(getClass().getResource("Orec.png")));
                }
                break;  
                
                case 5:
                if(enemy.getMP() >= 3)
                {
                   //normal attack
                   enemy.attack(player);
                   econ.setIcon(ene);
                   blank2.setIcon(ypunch);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   enemy.setAT(5);
                   
                   
                    
                }
                
                else 
                {
                    enemy.recover(1);
                    enemy.setSD(player.getSD());
                    econ.setIcon(ene);
                    if (enemy.getMP()>= 3)
                    {
                        econ.setIcon(anger);
                    }
                }
                break;  
                
                 case 6:
                if(move <= 3)
                {
                   //normal attack
                   enemy.attack(player);
                   player.heal(-15);
                   blank2.setIcon(anger);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   
                   
                  
                    
                }
                else 
                {
                   
                   player.heal(-20);
                   blank2.setIcon(ypunch);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                }
                
                break;  
                
                case 7:
                    enemy.nextTurn();
                    player.heal(-move);
                    hp.setText((player.getHP())+"/" + (player.getmHP()));
                    break;
                case 8:
                    enemy.nextTurn();
                    if (move < 3)
                    {
                        if (enemy.getHP() < (enemy.getmHP()/2))
                        {
                            enemy.attack(player);
                            blank2.setIcon(gHit);
                            hp.setText((player.getHP())+"/" + (player.getmHP()));
                        }
                        else
                        {
                            blank2.setIcon(null);
                        }
                        econ.setIcon(ene);
                    }
                    else
                    {
                        enemy.setAT(enemy.getAT()+2);
                        enemy.attack(player);
                        player.recover(-1);
                        econ.setIcon(anger);
                        blank2.setIcon(ypunch);
                        hp.setText((player.getHP())+"/" + (player.getmHP()));
                        mp.setText((player.getMP())+"/" + (player.getmMP()));
                        enemy.setAT(enemy.getAT()-2);
                    }
                    break;
                case 9:
                    enemy.nextTurn();
                    if (move < 2)
                    {
                        blank2.setIcon(null);
                        enemy.recover(2);
                    }
                    else if (enemy.getMP() > 3)
                    {
                        enemy.attack(player);
                        hp.setText((player.getHP())+"/"+(player.getmHP()));
                        blank2.setIcon(gHit);
                        enemy.recover(-4);
                        
                    }
                    else
                    {
                        blank2.setIcon(ypunch);
                        enemy.recover(enemy.getmHP());
                        enemy.setSD(enemy.getSD()-1);
                        enemy.setAT(enemy.getAT()-1);
                        enemy.heal(-2);
                        //cause drugs are bad
                        player.recover(-4);
                        mp.setText((player.getMP())+"/"+(player.getmMP()));
                    }
                    break;
                case 10:
                    enemy.nextTurn();
                    if (move < 3 && enemy.getMP() > 1)
                    {
                        enemy.attack(player);
                        hp.setText((player.getHP())+"/"+(player.getmHP()));
                        blank2.setIcon(gHit);
                        enemy.recover(-2);
                    }
                    else if (enemy.getMP() > 0)
                    {
                        enemy.recover(-1);
                        enemy.setSD(enemy.getSD() + 1);
                    }
                    else
                    {
                        enemy.recover(enemy.getmMP());
                        blank2.setIcon(ypunch); 
                        
                        enemy.setAT(enemy.getAT()-1);
                        enemy.heal(-2);
                        //cause drugs are bad
                        player.recover(-4);
                        mp.setText((player.getMP())+"/"+(player.getmMP()));
                    }
                    break;
                case 11:
                    enemy.nextTurn();
                    if (turns >= 10)
                    {
                        d.editArea(9);
                        d.editArea(10);
                        dispose();
                    }
                    break;
        }
        if (player.getHP() > 0)
        {
            enemyRepeats--;
            if (enemyRepeats <= 0)
            {
        turn = 1;
        playerRepeats = Math.round(player.getSD()/enemy.getSD());
        enemyRepeats =  Math.round(enemy.getSD()/player.getSD());
        if(guardCooldown > 0)
        {
            
            guardCooldown--;
            defend.setText("turns left:"+guardCooldown);
            if (guardCooldown == 0)
            {
                defend.setText("GUARD");
            }
        }
    }
    else
    {
        enemyTurn();
    }
        }else
        {
            
            boolean checkForFriends = false;
            int count = 30;
            while (!checkForFriends && count < 99)
            {
                checkForFriends = items.searchInvFor(count);
                
                if (!checkForFriends)
                {
                count++;
            }
            }
            if (checkForFriends)
            {
                items.removeFromInv(count);
                switch(count)
                {
                    //Dleg
                                    case 30:
                                        p2 = new Player(70,0,4,3,1,70,1,1,0,2,3);
                                        break;
                                        
                                    case 31:
                                        p2 = new Player(72,0,7,6,2,72,2,2,0,4,3);
                                        break;
                                    case 32:
                                        p2 = new Player(76,0,9,8,2,76,3,3,0,8,3);
                                        break;
                                        
                                    case 33:
                                        p2 = new Player(82,0,7,6,2,82,4,4,0,16,3);
                                        break;
                                    case 34:
                                        p2 = new Player(88,0,9,6,3,88,5,5,0,32,3);
                                        break;
                                    case 35:
                                        p2 = new Player(94,0,11,8,3,94,6,6,0,64,3);
                                        break;
                                    //Geruo
                                    case 40:
                                        p2 = new Player(50,10,7,2,2,50,10,1,0,4,4);
                                        break;
                                    case 41:
                                        p2 = new Player(51,11,8,3,3,51,11,2,0,16,4);
                                        break;
                                    case 42:
                                        p2 = new Player(53,13,10,5,5,53,13,3,0,256,4); 
                                        break;
                                    case 43:
                                        p2 = new Player(56,16,13,8,8,56,13,4,0,65536,4);
                                        break;
                                    //Keldoc
                                    case 50:
                                        p2 = new Player(45,0,3,1,6,45,0,1,0,4,5);
                                        break;
                                    case 51:
                                        p2 = new Player(47,0,5,2,8,47,0,2,0,8,5);
                                        break;
                                    case 52:
                                        p2 = new Player(50,0,7,4,10,50,0,3,0,32,5);
                                        break;        
                }
                //needs to be at the end of this set
                                        b2 = new BattleBox(p2, enemy, items, d);
                                        b2.setVisible(true);
                                        b2.setSize(900,720);
                                        b2.setLocationRelativeTo(null);
                                        dispose();
                                        
            }
            else
            {
        pcon.setVisible(false);
        econ.setVisible(false);
        blank.setVisible(false);
        blank2.setVisible(false);
        attack.setText(null);
        attack.setIcon(gameOver);
        attack.setText("leave");
        skills.setVisible(false);
        useItems.setVisible(false);
        defend.setVisible(false);
        hp.setText(null);
        ehp.setText(null);
        mp.setText(null);
    }
       }
    }
    else
    {
        switch(enemy.getID())
        { case 1:
            econ.setIcon(enemyDefeat1);
            defeatMsg = new DialogueB(1,"Metal hoarder.",0,items, player,d);
            break;
        case 2:
            econ.setIcon(enemyDefeat2);
            defeatMsg = new DialogueB(0,"I croaked",0,items, player,d);
            break;
        case 3:
            econ.setIcon(dolphDef);
            defeatMsg = new DialogueB(0,"I aint do nuttin",0,items, player,d);
            break;
        case 4:
            econ.setIcon(new ImageIcon(getClass().getResource("Odefeat.png")));
            defeatMsg = new DialogueB(4,"The loot is yours. ",0,items, player,d);
                break;
        case 5:
            econ.setIcon(ene);
            defeatMsg = new DialogueB(0,"It's over",0,items, player,d);
            break;
        case 6:
            defeatMsg = new DialogueB(5,"FORGIVE ME, SUPERIOR BEING",0,items,player,d);
            break;
    case 7:
            defeatMsg = new DialogueB(0,"WAIT! WHAT'S GOING ON!",0,items,player,d);
            break;
        case 8:
            defeatMsg = new DialogueB(0,"<HTML>(Start charging it. My situation's compromised).<BR>Suffer, fools. Perish and suffer. </HTML>",0,items,player,d);
            break;
        case 9:
            defeatMsg = new DialogueB(8,"It is foolish to persist",0,items,player,d);
            break;
        case 10:
            defeatMsg = new DialogueB(8,"It's... not too late for you to escape",0,items,player,d);
            break;
        case 11:
            defeatMsg = new DialogueB(0,"You just made a big mistake",0,items,player,d);
            break;
    }
    defeatMsg.pack();
    defeatMsg.setLocationRelativeTo(null);
    defeatMsg.setVisible(true);
        attack.setVisible(false);
        skills.setVisible(false);
        useItems.setVisible(false);
        defend.setVisible(false);
        exit.setVisible(true);
    }
}
}
}
