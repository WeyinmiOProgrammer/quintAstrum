
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
//import java.util.concurrent.*;
//import javafx.concurrent.*;
public class BattleBox extends JFrame
{
    Player player;
    Enemy enemy;
    InventoryMenu items;
    JButton attack, skills, useItems, defend, flee;
    //labels for player HP and MP, enemy HP, Player and enemy icons, 2 blank labels used to visually display
    JLabel hp, mp, ehp, emp, pcon, econ, blank, blank2, stat, estat;
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
    ImageIcon gameOver = new ImageIcon(getClass().getResource("youdiescreen.png"));
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
    
    //represents skills numerically - for normal party members (Geruo, Parmesian,Keldoc)
    //(id of skill, MP cost, type of skill [offensive?, healing? etc.], numerical data)
   int[][] skillsList = {{1,5,1,14},{2,2,2,8},{3,8,1,20},{4,0,3,8},{5,5,4,4},{6},{7},{8},{9},{10,8,1,24},
   {11,10,1,40},{12,2,5,0},{13,13,1,34},
   {14,4,4,1},{15,4,4,2},{16,2,4,3},{17,2,4,4},{18,10,6,80},
   {19,0,6,20},{20,0,6,30},{21,0,5,1},{22,0,8,1}};
   String[] skillNames = {"","Forceful thrust","Self-care","Ten-tackle","Energy drain","Weaken",
    "","","","","Lethal chop","Sword song","Parry","Exhaust flame","Talk it out","Demoralise",
    "Tough love","Comfort","Teleportation Madness","Half a Mobius Strip","Throw of Fate ",
    "Karma toss","Taunt"};
    ArrayList <Integer> availableSkills = new ArrayList<Integer>();
    //for party members with item based skills, there is a different list (Secreco,Uuander)
    //(id of skill, required item, item num,type of skill [offensive, healing,buffing etc.], numerical data)
    int[][] techList = {{1,16,5,9,1},{2,16,1,9,2},{3,13,1,6,10},{4,13,2,7,1}};
    String[] techNames = {"","Big bakeout","Light reconnaisance ","Inferno","Phoenix's rebirth"};
    //if you're wondering, Dleg doesn't learn any cool new skills
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
    
    //needed to make the title
    String[] charNames = {"","","","Dleg","Geruo","Keldoc","Uandar","Some dork","Par","..."};
    String [] eneNames = {"","Dleg","Froddoger","Podhog","Enroga","Lipsauge","Keldoc","General Fodriquod","Fallen General Fodriquod","Supreme General Uandar","Reborn General Uandar","Death Cannon","Survivor","Casualty","The Colossal CROAKER","Treech","Govic","Oeleh","Uadevah","Limesloy","Strilnoz"};
    
    //needed for weapon sprites
    ImageIcon wepspr;
    
    //needed for animations - not that they work
    /*
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    Task <Void> ask = new Task<Void>()
    {
        @Override protected Void call() throws Exception {
            endAnim(1);
            blank.setIcon(null);
            return null;
    }
    };
    Task <Void> ask2 = new Task<Void>()
    {
        @Override protected Void call() throws Exception {
            endAnim(2);
            blank2.setIcon(null);
            return null;
    }
    };
    Task <Void> ask3 = new Task<Void>()
    {
        @Override protected Void call() throws Exception {
            endAnim(3);
            return null;
    }
    };
    Task <Void> ask4 = new Task<Void>()
    {
        @Override protected Void call() throws Exception {
            endAnim(4);
            return null;
    }
    };
    */
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
        
        emp = new JLabel((e.getMP())+"/"+(e.getmMP()));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 3;
        
        add(emp, c);
        
        stat = new JLabel(p.getStatus());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        
        add(stat, c);
        
        estat = new JLabel(e.getStatus());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 4;
        
        add(estat, c);
        
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
        
        flee = new JButton("FLEE");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 5;
        add(flee, c);
        
        event e2 = new event();
        attack.addActionListener(e2);
        skills.addActionListener(e2);
        useItems.addActionListener(e2);
        defend.addActionListener(e2);
        submit.addActionListener(e2);
        exit.addActionListener(e2);
        flee.addActionListener(e2);
        
        enemyIconSetup();
        skillSetup1(player.getLv());
        this.setTitle(charNames[p.getID()]+" ====== vs ====== "+eneNames[e.getID()]);
    }
    
    public void screenreset()
    {
        hp.setText((player.getHP())+"/" + (player.getmHP()));
        ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
        mp.setText((player.getMP())+"/"+(player.getmMP()));
        emp.setText((enemy.getMP())+"/"+(enemy.getmMP()));
        stat.setText(player.getStatus());
        estat.setText(enemy.getStatus());
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
        hit = new ImageIcon(getClass().getResource("dpunch.png"));
    }
    if (player.getID() == 5)
    {
        skills.setText("TECHNIQUE");
        attack.setVisible(false);
        pla = new ImageIcon(getClass().getResource("Koldec.png"));
        shield = new ImageIcon(getClass().getResource("hide.png"));
        playerDefeat = new ImageIcon(getClass().getResource("oilFire.png"));
        pcon.setIcon(pla);
        hit = new ImageIcon(getClass().getResource("dpunch.png"));
         if (lv >= 2)
        {
            availableSkills.add(21);
            
        }
    }
    if (player.getID() == 6)
    {
        skills.setText("ARTS");
        attack.setText("HIT - 2MP");
        pla = new ImageIcon(getClass().getResource("Dbossshamed.png"));
        shield = new ImageIcon(getClass().getResource("hide.png"));
        
        pcon.setIcon(pla);
        hit = new ImageIcon(getClass().getResource("hit.png"));
        availableSkills.add(0);
        availableSkills.add(1);
    }
    if (player.getID() == 8)
    {
        skills.setText("LOVE");
        attack.setText("HUG");
        pla = new ImageIcon(getClass().getResource("plant2.png"));
        shield = new ImageIcon(getClass().getResource("hide.png"));
        playerDefeat = new ImageIcon(getClass().getResource("plantwoo.png"));
        pcon.setIcon(pla);
        hit = new ImageIcon(getClass().getResource("plant.png"));
         availableSkills.add(13);
          availableSkills.add(16);
          if (lv >= 2)
        {
            availableSkills.add(17);
            
        }
         if (lv >= 4)
        {
            availableSkills.add(14);
            
            availableSkills.add(15);
        }
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
                //enemy.setStatus("Reborn",1);
                econ.setIcon(ene);
                break;
            case 11:
                ene = new ImageIcon(getClass().getResource("deathLaser.png"));
                econ.setIcon(ene);
                break;
            case 12:
                ene = new ImageIcon(getClass().getResource("surv2.png"));
                anger = new ImageIcon(getClass().getResource("survivor.png"));
                ypunch = new ImageIcon(getClass().getResource("strongerfire.png"));
                enemyDefeat1 = new ImageIcon(getClass().getResource("notsurv.png"));
                econ.setIcon(ene);
                break;
            case 13:
                ene = new ImageIcon(getClass().getResource("casu.png"));
                
                ypunch = new ImageIcon(getClass().getResource("hit.png"));
                enemyDefeat1 = new ImageIcon(getClass().getResource("casu2.png"));
                econ.setIcon(ene);
                enemy.setStatus("Charred",10);
                break;
            case 14:
                ene = new ImageIcon(getClass().getResource("madfrog.png"));
                ypunch = new ImageIcon(getClass().getResource("hellfire.png"));
                enemyDefeat1 = new ImageIcon(getClass().getResource("madfrog2.png"));
                econ.setIcon(ene);
                break;
            case 15:
                ene = new ImageIcon(getClass().getResource("treech.png"));
                ypunch = gHit;
                enemyDefeat1 = gHit;
                econ.setIcon(ene);
                break;
            case 16:
                ene = new ImageIcon(getClass().getResource("Govic.png"));
                ypunch = new ImageIcon(getClass().getResource("hit.png"));
                enemyDefeat1 = new ImageIcon(getClass().getResource("Govic.png"));
                econ.setIcon(ene);
                break;
            case 17:
                ene = new ImageIcon(getClass().getResource("hippie.png"));
                ypunch = new ImageIcon(getClass().getResource("hit.png"));
                econ.setIcon(ene);
                break;
            case 18:
                ene = new ImageIcon(getClass().getResource("awww.png"));
                anger = new ImageIcon(getClass().getResource("arghhh.png"));
                ypunch = new ImageIcon(getClass().getResource("swThr.png"));
                econ.setIcon(ene);
                break;
            case 19:
                ene = new ImageIcon(getClass().getResource("slimeguy.png"));
                ypunch = new ImageIcon(getClass().getResource("wet.png"));
                anger = new ImageIcon(getClass().getResource("slimedry.png"));
                econ.setIcon(ene);
                break;
             case 20:
                ene = new ImageIcon(getClass().getResource("nuz.png"));
                ypunch = new ImageIcon(getClass().getResource("acid.png"));
                econ.setIcon(ene);
                break;
        }
    }
    
    
    public void endAnim(int o)
    {
        switch(o)
        {
            //for physical attacks
            case 1:
                blank.setIcon(null);
                break;
            case 2:
                blank2.setIcon(null);
                break;
            //for projectile attacks
            case 3:
                blank.setIcon(blank2.getIcon());
                blank2.setIcon(null);
                break;
            case 4:
                blank2.setIcon(blank.getIcon());
                blank.setIcon(null);
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
                    items.dropItemsOnDef(d.getDif());
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
                        
                        
                            collect = items.addToInv(8);
                        
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
                            ph.setStatus("Grounded",3);
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
                            d2.setStatus("Reborn",2);
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
                        case 12:
                            for (int j = 0; j < 2; j++)
                            {
                              collect = items.addToInv(8);
                    
                        }
                        
                        
                            
                        
                        player.setXP(player.getXP()+20);
                        break;
                        case 13:
                            for (int j = 0; j < 1; j++)
                            {
                              collect = items.addToInv(3);
                    
                        }
                        
                        
                           
                        
                        player.setXP(player.getXP()+2);
                        break;
                        case 14:
                            for (int j = 0; j < 5; j++)
                            {
                              collect = items.addToInv(8);
                    
                        }
                        
                        
                            collect = items.addToInv(8);
                        d.editArea(10);
                        player.setXP(player.getXP()+100);
                        break;
                        case 15:
                            for (int j = 0; j < 3; j++)
                            {
                              collect = items.addToInv(3);
                    
                        }
                        
                        
                            
                        
                        player.setXP(player.getXP()+2);
                        break;
                        
                        case 16:
                            d.uanFinal();
                            player.setXP(player.getXP()+50);
                        break;
                        
                        case 17:
                            for (int j = 0; j < 2; j++)
                            {
                              collect = items.addToInv(22);
                    
                        }
                
                        player.setXP(player.getXP()+35);
                        break;
                        
                        case 18:
                            for (int j = 0; j < 1; j++)
                            {
                              collect = items.addToInv(5);
                    
                        }
                        d.editArea(10);
                        player.setXP(player.getXP()+50);
                        break;
                        
                        case 19:
                            for (int j = 0; j < 2; j++)
                            {
                              collect = items.addToInv(23);
                    
                        }
                
                        player.setXP(player.getXP()+32);
                        break;
                        
                        case 20:
                            for (int j = 0; j < 2; j++)
                            {
                              collect = items.addToInv(13);
                    
                        }
                
                        player.setXP(player.getXP()+30);
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
                    if (player.getLv() < 10)
                    {
                    player.setLv(player.getLv()+ 1);
                }
                    
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
                  if (turn == 1 && choosing == 0 && !enemy.getStatus().equals("Flying"))
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
                 
                
                   blank.setIcon(hit);
                   //executorService.schedule(ask, 2, TimeUnit.SECONDS);
                //switches to enemy turn
                if (enemy.getHP()< 1)
                
                {
                    playerRepeats = 0;
                }
                turn = 2;
                enemyTurn();
                
                }
                  break;
                
                case "HIT - 2MP":
                     if (turn == 1 && choosing == 0 && player.getMP() > 1 && !enemy.getStatus().equals("Flying"))
                  {
                      
                  player.attack(enemy);
                  player.setMP(player.getMP()-2);
                  ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
                  mp.setText((player.getMP())+"/"+(player.getmMP()));
                 
                
                   blank.setIcon(hit);
                  //executorService.schedule(ask, 2, TimeUnit.SECONDS);
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
                    if (!(player.getStatus().equals("Oily")))
                    {
                    statReset(player);
                    player.setStatus("Oily",3);
                    player.setSD(player.getSD()+3);
                    playerRepeats = Math.round(player.getSD()/enemy.getSD()) - 1;
                    enemyRepeats =  Math.round(enemy.getSD()/player.getSD());
                }
                    turn = 2;
                    enemyTurn();
                }
                    break;
                case "HUG":
                    if (turn == 1 & choosing  == 0)
                    {
                        enemy.heal(rd.nextInt(3));
                        player.recover(rd.nextInt(5));
                        turn = 2;
                        enemyTurn();
                        pcon.setIcon(playerDefeat);
                    }
                    break;
                case "FLEE":
                    if (turn == 1 && choosing == 0)
                    {
                        if ((player.getSD()/enemy.getSD()) > 2)
                        {
                            if (enemy.getID() == 11)
                            {
                                d.editArea(9);
                                d.editArea(10);
                           }
                           if (enemy.getID() == 16)
                           {
                            d.uanFinal();
                        }
                        if (player.getID() != 4)
                {
                    items.addToInv((player.getID()*10)+(player.getLv()-1));
                    for (int w = 40; w < 50; w++)
                    {
                    items.removeFromInv(w);
                }
                }
                            dispose();
                            
                            
                        }
                        else if (enemy.getHP()< enemy.getmHP()/4)
                        {
                            if (enemy.getID() == 11)
                            {
                                d.editArea(9);
                                d.editArea(10);
                           }
                           if (enemy.getID() == 16)
                           {
                            d.uanFinal();
                        }
                        if (player.getID() != 4)
                {
                    items.addToInv((player.getID()*10)+(player.getLv()-1));
                    for (int w = 40; w < 50; w++)
                    {
                    items.removeFromInv(w);
                }
                }
                            dispose();
                        }
                        else
                        {
                            if (rd.nextInt(4)==2)
                            {
                                if (enemy.getID() == 11)
                            {
                                d.editArea(9);
                                d.editArea(10);
                           }
                           if (enemy.getID() == 16)
                           {
                            d.uanFinal();
                        }
                        if (player.getID() != 4)
                {
                    items.addToInv((player.getID()*10)+(player.getLv()-1));
                    for (int w = 40; w < 50; w++)
                    {
                    items.removeFromInv(w);
                }
                }
                                dispose();
                            }
                            else
                            {
                                turn = 2;
                                enemyTurn();
                                DialogueB fail = new DialogueB(0,"Failed to flee",0,items,player,d);
                                fail.pack();
                                fail.setLocationRelativeTo(null);
                                fail.setVisible(true);
                            }
                        }
                    }
                    break;
                //handles skill attacks - uses MP
                case "SKILLS":
                case "TECHNIQUE":   
                case "LOVE":
                   if (turn == 1 && choosing == 0 && availableSkills.size() != 0 && !enemy.getStatus().equals("Flying"))
                   {
                       //add weapon specific skills
                       if (items.searchInvFor(7) && player.getID() == 4)
                       {
                           availableSkills.add(9);
                        }
                        if (items.searchInvFor(5)  && player.getID() == 4)
                       {
                           availableSkills.add(10);
                           availableSkills.add(11);
                        }
                        if (items.searchInvFor(12)  && player.getID() == 4)
                       {
                           availableSkills.add(12);
                        }
                        if (items.searchInvFor(17) && player.getID() == 5)
                       {
                           availableSkills.add(19);
                           availableSkills.add(20);
                        }
                         if (items.searchInvFor(18) && player.getID() == 5)
                       {
                           availableSkills.add(18);
                           
                        }
                       blank.setIcon(null);
                       String skillsDisplay = "<HTML>";
                       int leng = availableSkills.size();
                       leng = leng;
                       String tempString = "";
                       for (int z = 0; z < leng; z++)
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
                case "TRICKS":
                case "ARTS":
                
                   
                   if (turn == 1 && choosing == 0 && availableSkills.size() != 0)
                   {
                       
                       
                       blank.setIcon(null);
                       String skillsDisplay = "<HTML> ";
                       int leng = availableSkills.size();
                       leng = leng;
                       String tempString = "";
                       for (int z = 0; z < leng; z++)
                       {
                           tempString = techList[availableSkills.get(z)][0] + ":" + techNames[(techList[availableSkills.get(z)][0])] + " - " + techList[availableSkills.get(z)][2]+" " +items.valToItem(techList[availableSkills.get(z)][1]) ;
                           skillsDisplay = skillsDisplay+" <BR> "+tempString;
                        }
                       skillsDisplay = skillsDisplay +" </HTML>";
                   
                       blank.setText(skillsDisplay);
                       choosing = 3;
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
                                //System.out.println("Test 1 - item correct");
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
                                        if (!(enemy.getID()==17))
                                        {
                                        enemy.heal(-2);
                                        if (enemy.getStatus().equals("Oily"))
                                        {
                                            enemy.heal(-10);
                                        }
                                        if (enemy.getID()==15)
                                        {
                                            enemy.setHP(1);
                                        }
                                        enemy.setStatus("Burning",3);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                    }
                                    else
                                    {
                                        enemy.heal(5);
                                        ehp.setText((enemy.getHP())+"/"+(enemy.getmHP()));
                                        DialogueB chi = new DialogueB (0,"This battle was missin some spice",0,items,player,d);
                                            chi.setVisible(true);
                                            chi.pack();
                                            chi.setLocationRelativeTo(null);
                                    }
                                    
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
                                        
                                        if (!(enemy.getStatus().equals("Burning"))&&!(enemy.getStatus().equals("Oily"))){
                                            statReset(enemy);
                                            enemy.setStatus("Oily",3);
                                            enemy.setSD(enemy.getSD()+3);
                                            
                                            enemyRepeats =  Math.round(enemy.getSD()/player.getSD());
                                    }else
                                    {
                                        if ((enemy.getStatus().equals("Burning"))){
                                        enemy.heal(-10);
                                    }
                                    }
                                    }
                                        break;   
                                    case 16:
                                        player.setMP(player.getmMP());
                                        //this is permanent for Geruo - shouldn't have played around
                                        player.setSD(player.getSD()-rd.nextInt(2));
                                        player.heal(-1*rd.nextInt(2));
                                        //can be used to tame dolphs and lions
                                        if (enemy.getID()==3 || enemy.getID()==17)
                                        {
                                            DialogueB chi = new DialogueB (0,"Far out, dude. Far. Out.",0,items,player,d);
                                            chi.setVisible(true);
                                            chi.pack();
                                            chi.setLocationRelativeTo(null);
                                            dispose();
                                        }
                                        break;
                                    case 23:
                                        if (!player.getStatus().equals("Wet"))
                                        {
                                           statReset(player);
                                            player.setStatus("Wet",3);
                                            player.setSD(enemy.getSD()-3);
                                        }
                                        if (!enemy.getStatus().equals("Wet"))
                                        {
                                        statReset(enemy);
                                            enemy.setStatus("Wet",3);
                                            enemy.setSD(enemy.getSD()-3);
                                            if (enemy.getID()==19)
                                            {
                                                DialogueB chi2 = new DialogueB (0,"That's the nicest thing anyone's done for me",0,items,player,d);
                                            chi2.setVisible(true);
                                            chi2.pack();
                                            chi2.setLocationRelativeTo(null);
                                            dispose();
                                            }
                                            
                                            enemyRepeats =  Math.round(enemy.getSD()/player.getSD());
                                        }
                                        break;
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
                                        
                                    case 41:
                                    case 42:   
                                    case 43:
                                    case 44:
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                        p2 = d.currentGeruo();
                                        p2.setHP(p2.getmHP());
                                        p2.setMP(p2.getmMP());
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
                                    //Uuandar
                                    case 60:
                                        p2 = new Player(120,17,12,4,6,120,17,1,0,4,6);
                                        break;
                                    case 61:
                                        p2 = new Player(110,14,14,4,5,110,14,2,0,64,6);
                                        break;
                                    case 62:
                                        p2 = new Player(100,12,14,4,4,100,12,3,0,128,6);
                                        break;
                                        //needs to be at the end of this set
                                    //Parmas
                                    case 80:
                                        p2 = new Player(60,20,3,5,9,60,20,1,0,100,8);
                                        break;
                                    case 81:
                                        p2 = new Player(62,22,3,3,6,62,22,2,0,20,8);
                                        break;
                                    case 82:
                                        p2 = new Player(64,24,3,3,7,64,24,2,0,20,8);
                                        break;
                                    default:
                                        items.addToInv(itemToUse);
                                        break;
                                }
                                //additional code if player tags out
                                if (itemToUse > 29)
                                {
                                    //stores player details, so that they can be rebooted as required
                                    //System.out.println(itemToUse);
                                    //System.out.println("Test 2 - tagged in");
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
                       String skillzDisplay = "<HTML> ";
                       //System.out.println(skillzDisplay);
                       int leng = availableSkills.size();
                       leng = leng;
                       String tempZtring = "";
                       for (int z = 0; z < leng; z++)
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
                                    if((availableSkills.get(u)>=9 && availableSkills.get(u)< 13)||(availableSkills.get(u)>=18 && availableSkills.get(u)< 21))
                                  
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
                                                if (skillNo < 6)
                                                {
                                                    wepspr = new ImageIcon(getClass().getResource("ppunch.png"));
                                                    blank.setIcon(wepspr);
                                                }
                                                else if (skillNo == 11)
                                                {
                                                    wepspr = new ImageIcon(getClass().getResource("sword.png"));
                                                    blank.setIcon(wepspr);
                                                }
                                                else if (skillNo == 10)
                                                {
                                                    wepspr = new ImageIcon(getClass().getResource("axe.png"));
                                                    blank.setIcon(wepspr);
                                                }
                                                else if (skillNo == 13)
                                                {
                                                    wepspr = new ImageIcon(getClass().getResource("jet.png"));
                                                    blank.setIcon(wepspr);
                                                    statReset(enemy);
                                                    enemy.setStatus("Burning",3);
                                                }
                                                //executorService.schedule(ask, 2, TimeUnit.SECONDS);
                                                break;
                                            
                                                //healing skills
                                            case 2:
                                                player.heal(skillsList[availableSkills.get(i)][3]);
                                                hp.setText((player.getHP())+"/"+(player.getmHP()));
                                                break;
                                                //drain
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
                                                //weaken aspect
                                            case 4:
                                                if (skillsList[availableSkills.get(i)][3] == 4)
                                                {
                                                enemy.setAT(enemy.getAT()-2);
                                                if (enemy.getAT() < 1)
                                                {
                                                    enemy.setAT(1);
                                                }
                                                if (player.getID()==8)
                                                {
                                                    pcon.setIcon(playerDefeat);
                                                }
                                            }
                                            if (skillsList[availableSkills.get(i)][3] == 3)
                                                {
                                                enemy.setDF(enemy.getDF()-2);
                                                if (enemy.getDF() < 1)
                                                {
                                                    enemy.setDF(1);
                                                }
                                            }
                                            
                                            if (skillsList[availableSkills.get(i)][3] == 2)
                                                {
                                                enemy.setMP(enemy.getMP()-2);
                                                if (enemy.getMP() < 1)
                                                {
                                                    enemy.setMP(1);
                                                }
                                                 if (player.getID()==8)
                                                {
                                                    pcon.setIcon(hit);
                                                }
                                            }
                                            if (skillsList[availableSkills.get(i)][3] == 1)
                                                {
                                                enemy.setSD(enemy.getSD()-2);
                                                if (enemy.getSD() < 1)
                                                {
                                                    enemy.setSD(1);
                                                }
                                                 if (player.getID()==8)
                                                {
                                                    pcon.setIcon(pla);
                                                }
                                            }
                                            
                                                break;
                                                //parrying attacks
                                            case 5:
                                                wepspr = new ImageIcon(getClass().getResource("sword.png"));
                                                blank.setIcon(wepspr);
                                                playerRepeats = 0;
                                                enemyRepeats = enemyRepeats - 1;
                                                enemy.attack(enemy);
                                                ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
   
                                                break;
                                            //random damage/gamble
                                            case 6:
                                                blank.setIcon(gHit);
                                                enemy.setHP(enemy.getHP()- rd.nextInt(skillsList[availableSkills.get(i)][3]));
                                                player.setHP(player.getHP()-((rd.nextInt(skillsList[availableSkills.get(i)][3]))/2));
                                                ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
   
                                                break;
                                            //cloning/phoenixing
                                            case 7:
                                                items.addToInv((player.getID()*10)+(player.getLv()-1));
                                                break;
                                                
                                            //taunting - angers the enemy (extra attack dmg)
                                            //gives player a speed boost
                                            case 8:
                                                enemy.setAT(enemy.getAT()+2);
                                                player.setSD(player.getSD()+1);
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
                                    if((availableSkills.get(u)>=9 && availableSkills.get(u)< 13)||(availableSkills.get(u)>=18 && availableSkills.get(u)< 21))
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
                    else if (choosing == 3)
                    {
                       String skillzDisplay = "<HTML> ";
                       //System.out.println(skillzDisplay);
                       int leng = availableSkills.size();
                       leng = leng;
                       String tempZtring = "";
                       for (int z = 0; z < leng; z++)
                       {
                           tempZtring = techList[availableSkills.get(z)][0] + ":" + techNames[(techList[availableSkills.get(z)][0])] + " - " + techList[availableSkills.get(z)][2]+" " +items.valToItem(techList[availableSkills.get(z)][1]) ;
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
                                    if (techList[availableSkills.get(i)][0] == skillNo && skillFound == false)
                                    {
                                        skillFound = true;
                                        //checks if player has enough items
                                        if (items.howMuch(techList[availableSkills.get(i)][1]) >= techList[availableSkills.get(i)][2])
                                        {
                                           for (int k = 0; k < techList[availableSkills.get(i)][2];k++)
                                           {
                                               items.removeFromInv(techList[availableSkills.get(i)][1]);
                                            }
                                        switch(techList[availableSkills.get(i)][3])
                                        {
                                            //aggressive skills
                                            case 1:
                                                int temp = enemy.getHP();
                                                temp = temp - techList[availableSkills.get(i)][4];
                                                enemy.setHP(temp);
                                                ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
                                                break;
                                            
                                                //healing skills
                                            case 2:
                                                player.heal(techList[availableSkills.get(i)][4]);
                                                hp.setText((player.getHP())+"/"+(player.getmHP()));
                                                break;
                                                //drain
                                            case 3:
                                                for (int q =0; q < (techList[availableSkills.get(i)][4]); q++)
                                                {
                                                    if (enemy.getMP() > 0)
                                                    {
                                                        enemy.recover(-1);
                                                        player.recover(1);
                                                    }
                                                }
                                                mp.setText((player.getMP())+"/"+(player.getmMP()));
                                                
                                                break;
                                                //weaken aspect
                                            case 4:
                                                if (techList[availableSkills.get(i)][4] == 4)
                                                {
                                                enemy.setAT(enemy.getAT()-2);
                                                if (enemy.getAT() < 1)
                                                {
                                                    enemy.setAT(1);
                                                }
                                            }
                                            if (techList[availableSkills.get(i)][4] == 3)
                                                {
                                                enemy.setDF(enemy.getDF()-2);
                                                if (enemy.getDF() < 1)
                                                {
                                                    enemy.setDF(1);
                                                }
                                            }
                                            
                                            if (techList[availableSkills.get(i)][4] == 2)
                                                {
                                                enemy.setMP(enemy.getMP()-2);
                                                if (enemy.getMP() < 1)
                                                {
                                                    enemy.setMP(1);
                                                }
                                            }
                                            if (techList[availableSkills.get(i)][4] == 1)
                                                {
                                                enemy.setSD(enemy.getSD()-2);
                                                if (enemy.getSD() < 1)
                                                {
                                                    enemy.setSD(1);
                                                }
                                            }
                                            
                                                break;
                                                //parrying attacks
                                            case 5:
                                                playerRepeats = 0;
                                                enemyRepeats = enemyRepeats - 1;
                                                enemy.attack(enemy);
                                                ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
   
                                                break;
                                            //random damage/gamble
                                            case 6:
                                                enemy.setHP(enemy.getHP()- rd.nextInt(techList[availableSkills.get(i)][4]));
                                                player.setHP(player.getHP()-((rd.nextInt(techList[availableSkills.get(i)][4]))/2));
                                                ehp.setText((enemy.getHP())+"/" + (enemy.getmHP()));
   
                                                break;
                                            //cloning/phoenixing
                                            case 7:
                                                items.addToInv((player.getID()*10)+(player.getLv()-1));
                                                break;
                                                
                                            //taunting - angers the enemy (extra attack dmg)
                                            //gives player a speed boost
                                            case 8:
                                                enemy.setAT(enemy.getAT()+2);
                                                player.setSD(player.getSD()+1);
                                                break;
                                                
                                            //Uandar specific skills
                                            case 9:
                                                if (techList[availableSkills.get(i)][4] == 2)
                                                {
                                                   player.recover(rd.nextInt(5));
                                            }
                                            
                                            if (techList[availableSkills.get(i)][4] == 1){
                                                player.recover(player.getmMP());
                                                
                        
                                                player.setAT(player.getAT()-1);
                                                player.heal(-2);
                                                //cause drugs are bad
                                                 enemy.recover(-4);
                                            }
                                                break;
                                                
                                        }
                                        submit.setVisible(false);
                                        enterChoice.setVisible(false);
                                        blank.setText(null);
                                        choosing = 0;
                                        turn = 2;
                                        enemyTurn();
                                        
                                        
                                    }else
                                    {
                                         String infoText = blank.getText();
                                        blank.setText("<HTML>not enough"+  items.valToItem(techList[availableSkills.get(i)][1]) +"for that move - select something else<BR>"+skillzDisplay + "</HTML>");
                                        
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
    
    public void statReset(Actor a)
    {
        if (a.getStatus().equals("Oily"))
                {
                    a.setSD(player.getSD()-3);
                }
                 if (a.getStatus().equals("Wet"))
                {
                    a.setSD(player.getSD()+3);
                }
                if (a.getStatus().equals("Buff"))
                {
                    a.setAT(player.getAT()-4);
                }
                if (a.getStatus().equals("Stricken"))
                {
                   a.setSD(player.getSD()+2);
                     a.setDF(player.getDF()+2);
                }
    }
    
    public void enemyTurn()
    {
        playerRepeats--;
       
        screenreset();
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
        
        
        switch(id)
        {
            //Dleg
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
                   //executorService.schedule(ask2, 2, TimeUnit.SECONDS);
                    
                }
                else if(move >= 3 && enemyCharged != 1)
                {
                    //temporary attack boost
                    enemy.setAT(7);
                    if (!enemy.getStatus().equals("Oily"))
                    {
                    statReset(enemy);
                    enemy.setStatus("Oily",3);
                    enemy.setSD(enemy.getSD()+3);
                    
                    enemyRepeats =  Math.round(enemy.getSD()/player.getSD());
                    }
                    econ.setIcon(anger);
                    enemyCharged = 1;
                    blank2.setIcon(null);
                }
                break;  
                //froddoger
                 case 2:
                if(move < 3 || (move >= 3 && enemyCharged == 1))
                {
                   //normal attack
                   enemy.attack(player);
                   blank2.setIcon(blueFire);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   enemy.setAT(3);
                   if (player.getStatus().equals("Oily"))
                   {
                       player.heal(-2);
                    }
                    
                    if (rd.nextInt(3)==2 && !(player.getStatus().equals("Wet")))
                    {
                        statReset(player);
                        player.setStatus("Burning",3);
                    }
                   if (enemyCharged == 1)
                   {
                   enemyCharged = 0;
                }
                   econ.setIcon(blueEnemy);
                   //executorService.schedule(ask2, 2, TimeUnit.SECONDS);
                    
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
                //Podhog - cause a group of dolphins is a pod and they're apperently called sea swine in some places
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
                //executorService.schedule(ask2, 2, TimeUnit.SECONDS);
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
                //Enroga
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
                //Lipsauge
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
                    blank2.setIcon(null);
                    if (enemy.getMP()>= 3)
                    {
                        econ.setIcon(anger);
                    }
                }
                break;  
                //Keldoc
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
                //General Fodriquod
                case 7:
                    //enemy.nextTurn();
                    player.heal(-move);
                    hp.setText((player.getHP())+"/" + (player.getmHP()));
                    break;
                case 8:
                    //enemy.nextTurn();
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
                    //General Uandar
                case 9:
                    //enemy.nextTurn();
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
                    //enemy.nextTurn();
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
                    //death cannon
                case 11:
                    enemy.setSD(player.getSD());
                    //enemy.nextTurn();
                    if (turns >= 10)
                    {
                        statReset(player);
                        player.setStatus("Charred",10);
                        d.editArea(9);
                        d.editArea(10);
                        dispose();
                    }
                    break;
                    //survivor
                    case 12:
                if(move < 2 || (move >= 2 && enemyCharged == 3))
                {
                   //normal attack
                   enemy.attack(player);
                   blank2.setIcon(ypunch);
                   hp.setText((player.getHP())+"/" + (player.getmHP()));
                   if (player.getStatus().equals("Oily"))
                   {
                       player.heal(-2);
                    }
                    
                    if (rd.nextInt(2)==1 && !(player.getStatus().equals("Wet")))
                    {
                        statReset(player);
                        player.setStatus("Burning",3);
                    }
                   if (enemyCharged == 3)
                   {
                   enemyCharged = 0;
                   enemy.setAT(enemy.getAT()-3);
                }
                   econ.setIcon(anger);
                    
                }
                else if(move >= 4 && enemyCharged != 3)
                {
                    //temporary attack boost
                    
                    econ.setIcon(ene);
                    enemyCharged += 1;
                    blank2.setIcon(null);
                    if (enemyCharged == 3)
                    {
                        econ.setIcon(anger);
                        enemy.setAT(enemy.getAT()+3);
                        player.recover(-3);
                        hp.setText((player.getHP())+"/" + (player.getmHP()));
                    }
                }
                else
                {
                    econ.setIcon(ene);
                    blank2.setIcon(gHit);
                }
                break;  
                //casualty
                case 13:
                    if (move > 3)
                    {
                        enemy.attack(player);
                       econ.setIcon(ene);
                       blank2.setIcon(ypunch);
                       hp.setText((player.getHP())+"/" + (player.getmHP()));
                    }
                    else
                    {
                        blank2.setIcon(null);
                    }
                    break;
                //croaker
                case 14:
                    if (enemy.getHP() > 750)
                    {
                        if (move < 2)
                        {
                            blank2.setIcon(gHit);
                            enemy.setAT(enemy.getAT()/2);
                            enemy.attack(player);
                            enemy.setAT(enemy.getAT()*2);
                            hp.setText((player.getHP())+"/" + (player.getmHP()));
                        }
                        else
                        {
                            blank2.setIcon(null);
                            if (!enemy.getStatus().equals("Burning") && !enemy.getStatus().equals("Oily"))
                            {
                                statReset(enemy);
                                enemy.setStatus("Oily",3);
                                enemy.setSD(enemy.getSD()+3);
                            }
                            enemy.recover(1);
                        }
                        
                    }
                    else if (enemy.getHP() > 500)
                    {
                        if (enemy.getMP()>= 10)
                        {
                            enemy.attack(player);
                            if (!player.getStatus().equals("Wet"))
                            {
                                statReset(player);
                                player.setStatus("Burning",3);
                            }
                            blank2.setIcon(ypunch);
                            enemy.recover(-10);
                        }
                        else if (move > 2)
                        {
                            blank2.setIcon(gHit);
                            enemy.setAT(enemy.getAT()/2);
                            enemy.attack(player);
                            enemy.setAT(enemy.getAT()*2);
                            hp.setText((player.getHP())+"/" + (player.getmHP()));
                            enemy.recover(1);
                        }
                        else
                        {
                            blank2.setIcon(null);
                            enemy.recover(4);
                            statReset(enemy);
                            enemy.setStatus("Burning",3);
                        }
                    }
                    else if (enemy.getHP() > 250)
                    {
                        if (enemy.getMP()>= 10)
                        {
                            enemy.attack(player);
                            if (!player.getStatus().equals("Wet"))
                            {
                                statReset(player);
                                player.setStatus("Burning",3);
                            }
                            blank2.setIcon(ypunch);
                            enemy.recover(-10);
                        }
                        else if (move > 2)
                        {
                            blank2.setIcon(gHit);
                            enemy.setAT(enemy.getAT()/2);
                            enemy.attack(player);
                            enemy.setAT(enemy.getAT()*2);
                            hp.setText((player.getHP())+"/" + (player.getmHP()));
                            enemy.recover(1);
                        }
                        else
                        {
                            blank2.setIcon(null);
                            enemy.recover(8);
                            statReset(enemy);
                            enemy.setAT(enemy.getAT()+rd.nextInt(2));
                            enemy.setStatus("Charred",5);
                        }
                        
                    }
                    else
                    {
                        enemy.setAT(enemy.getAT()-1);
                        blank2.setIcon(gHit);
                            enemy.setAT(enemy.getAT()/2);
                            enemy.attack(player);
                            enemy.setAT(enemy.getAT()*2);
                            hp.setText((player.getHP())+"/" + (player.getmHP()));
                            enemy.recover(1);
                    }
                    break;
                //treech
                case 15:
                    if (move > 2)
                    {
                        enemy.attack(player);
                       econ.setIcon(ene);
                       blank2.setIcon(ypunch);
                       hp.setText((player.getHP())+"/" + (player.getmHP()));
                    }
                    else
                    {
                        blank2.setIcon(null);
                    }
                    break;
                //Govic
                case 16:
                    if (move > 1 || enemy.getStatus().equals("Flying"))
                    {
                        enemy.attack(player);
                       econ.setIcon(ene);
                       blank2.setIcon(ypunch);
                       hp.setText((player.getHP())+"/" + (player.getmHP()));
                    }
                    else
                    {
                        blank2.setIcon(null);
                        statReset(enemy);
                        enemy.setStatus("Flying",1);
                    }
                    break;
                //Oeleh
                case 17:
                    if (move > 2)
                    {
                        enemy.attack(player);
                        blank2.setIcon(gHit);
                        hp.setText((player.getHP())+"/" + (player.getmHP()));
                    }
                    else
                    {
                        player.recover(-3);
                        blank2.setIcon(null);
                        if (!player.getStatus().equals("Wet"))
                        {
                            statReset(player);
                            player.setStatus("Charred",5);
                        }
                    }
                    break;
                //Uadevah
                case 18:
                    econ.setIcon(anger);
                    player.heal(-15);
                    blank2.setIcon(ypunch);
                    hp.setText((player.getHP())+"/" + (player.getmHP()));
                    break;
                //Limesloy
                case 19:
                    if (enemy.getStatus().equals("Wet"))
                    {
                        enemy.attack(player);
                        econ.setIcon(ene);
                        blank2.setIcon(ypunch);
                        if (rd.nextInt(3)==1 && !player.getStatus().equals("Wet"))
                        {
                            statReset(player);
                            player.setStatus("Wet",3);
                            player.setSD(player.getSD()-3);
                        }
                        hp.setText((player.getHP())+"/" + (player.getmHP()));
                        enemy.recover(-1);
                    }
                    else
                    {
                        econ.setIcon(anger);
                        enemy.recover(1);
                        if (enemy.getMP()>=3)
                        {
                            statReset(enemy);
                            enemy.setStatus("Wet",3);
                            enemy.setSD(enemy.getSD()-3);
                            econ.setIcon(ene);
                        }
                        
                    }
                    break;
                //Strilnoz
                case 20:
                    if (enemy.getMP() > 0)
                    {
                        if (move >= 2)
                        {
                            enemy.attack(player);
                        
                            blank2.setIcon(ypunch);
                            if (rd.nextInt(3)==1)
                            {
                                statReset(player);
                                player.setStatus("Sick",10);
                                
                            }
                            
                        }
                        else
                            {
                                blank2.setIcon(null);
                                enemy.recover(rd.nextInt(3));
                            }
                    }
                    else
                    {
                        player.heal(-10);
                        player.setStatus("Sick",10);
                        if (player.getID() != 4)
                {
                    items.addToInv((player.getID()*10)+(player.getLv()-1));
                    for (int w = 40; w < 50; w++)
                    {
                    items.removeFromInv(w);
                }
                }
                        this.dispose();
                        enemyRepeats = 0;
                    }
                    break;
        }
        if (player.getHP() > 0)
        {
            enemyRepeats--;
            if (enemyRepeats <= 0)
            {
                enemy.nextTurn();
                if (!(player.getStatus().equals("Normal")))
        {
            player.statusFX();
            player.durDown();
            if (player.getDur()<= 0)
            {
                
                statReset(player);
                player.setStatus("Normal",0);
            }
        }
        if (!(enemy.getStatus().equals("Normal")))
        {
            enemy.statusFX();
            enemy.durDown();
            if (enemy.getDur()<= 0)
            {
                statReset(enemy);
                enemy.setStatus("Normal",0);
            }
        }
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
        
        screenreset();
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
                if (count == 60 || count == 61)
                {
                    checkForFriends = false;
                }
                if (!checkForFriends)
                {
                count++;
            }
            }
            if (checkForFriends )
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
                                        
                                    case 41:
                                    case 42:   
                                    case 43:
                                    case 44:
                                    case 45:
                                    case 46:
                                    case 47:
                                    case 48:
                                    case 49:
                                        p2 = d.currentGeruo();
                                        p2.setHP(p2.getmHP());
                                        p2.setMP(p2.getmMP());
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
                                    // Low lvl Uandar will not help you if the rest of your team dies
                                    case 62:
                                        p2 = new Player(100,12,14,4,4,100,12,3,0,128,6);
                                        break;
                                    case 80:
                                        p2 = new Player(60,20,3,5,9,60,20,1,0,100,8);
                                        break;
                                    case 81:
                                        p2 = new Player(62,22,3,3,6,62,22,2,0,20,8);
                                        break;
                                    case 82:
                                        p2 = new Player(64,24,3,3,7,64,24,2,0,20,8);
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
        flee.setVisible(false);
        hp.setText(null);
        ehp.setText(null);
        mp.setText(null);
        if (items.searchInvFor(60) || items.searchInvFor(61))
        {
            defeatMsg = new DialogueB(8,"You aren't worth saving. Neither was I.",0,items, player,d);
            defeatMsg.pack();
            defeatMsg.setLocationRelativeTo(null);
            defeatMsg.setVisible(true);
        }
        if (enemy.getID() == 16)
                           {
                            d.uanFinal();
                        }
    }
       }
    }
    else
    {
        defeatMsg = new DialogueB(0,"...",0,items, player,d);
        econ.setIcon(gHit);
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
            defeatMsg = new DialogueB(0,"I'm outta here.",0,items, player,d);
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
            econ.setIcon(enemyDefeat1);
            defeatMsg = new DialogueB(8,"It's... not too late for you to escape",0,items,player,d);
            break;
        case 11:
            defeatMsg = new DialogueB(0,"You just made a big mistake",0,items,player,d);
            break;
        case 12:
            econ.setIcon(enemyDefeat1);
            defeatMsg = new DialogueB(0,"You. You shouldn't even be here.",0,items,player,d);
            break;
        case 13:
            econ.setIcon(enemyDefeat1);
            defeatMsg = new DialogueB(0,"...",0,items,player,d);
            break;
        case 14:
            econ.setIcon(enemyDefeat1);
            defeatMsg = new DialogueB(0,"<HTML>The glory of the longest burning flame<BR> becomes nothing, hidden beneath bushel</HTML>",0,items,player,d);
            break;
        case 15:
            econ.setIcon(enemyDefeat1);
            defeatMsg = new DialogueB(0,"This isn't the last you'll see of me",0,items,player,d);
            break;
        case 16:
            econ.setIcon(enemyDefeat1);
            defeatMsg = new DialogueB(0,"Forget this, I'm outta here",0,items,player,d);
            break;
    }
    defeatMsg.pack();
    defeatMsg.setLocationRelativeTo(null);
    defeatMsg.setVisible(true);
        attack.setVisible(false);
        skills.setVisible(false);
        useItems.setVisible(false);
        defend.setVisible(false);
        flee.setVisible(false);
        exit.setVisible(true);
    }
}
}
}
