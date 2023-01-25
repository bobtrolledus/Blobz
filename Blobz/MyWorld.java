import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * <h1>Factory Simulation Game finished by Group 1.</h1>
 * <p> Group member: Peter Chen, Anson Ho, Andy Li, Eric Zheng</p>
 * <h2>Credit:</h2>
 * 
 * @author (Group 1) 
 * @version Jan 24, 2023
 */
public class MyWorld extends World
{
    private Font comicFontMid = new Font ("Comic Sans MS", true, false, 24);
    private Font comicFontSmoll = new Font ("Comic Sans MS", true, false, 20);
    private GreenfootImage background;
    private Label levelLabel, itemLabel, timeLabel, moneyLabel;
    private static Scanner fileScan;
    private static Scanner scan;
    private static ArrayList<Integer> lines;
    private Color lightGray = new Color(228, 228, 226);
    private Color gray = new Color(171, 171, 171);
    private Color yellow = new Color(255, 255, 186);
    private int gameTimer, gameTimeM, gameTime;
    private GreenfootSound[] delete = new GreenfootSound[20];
    private int deleteIndex = 0;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        setPaintOrder(Label.class, Machines.class, Colours.class, Shapes.class, Hub.class, Belts.class, ghostBlock.class);
        
        background = new GreenfootImage("gamebackground.png");
        background.scale(1200, 800);
        setBackground(background);
        getBackground().setColor(gray);
        
        for(int x = 0; x <= 800; x++){
            if(x % 40 == 0){
                getBackground().drawLine(x + 200, 0, x + 200, 1000);
            }
        }
        for(int x = 0; x < 1000; x++){
            if(x % 40 == 0){
                getBackground().drawLine(200, x, 1000, x);
            }
        }
        addObject(new Utils(), 0, 0);
        prepare();
        addTimeLabel();
        setMap1();
        for(int i = 0; i < 20; i++){
            delete[i] = new GreenfootSound("delete.wav");
        }
    }

    public void act()
    {        
        delete();
        time();
        levelLabel.setValue(Utils.getLevel() + 1);
        itemLabel.setValue(Utils.getTotalTargetShapes() + " / 5");
        moneyLabel.setValue("$" + Utils.getMoney());
        if(Utils.getLastKey() != null)
        {
            if(Utils.getLastKey().equals("r"))
            {
                Utils.addRotation();
            }
            if(Utils.getLastKey().equals("m"))
            {
                Utils.changeMirrored();
            }
        }
        cheat();
        setLevel();
    }

    public void prepare()
    {
        // spawns left side bar UI stuff
        int width = 200;
        int height = 800; 
        int offset = 27;
        getBackground().setFont(comicFontMid);
        getBackground().setColor(Color.GRAY);

        addObject(new Belts(true), width / 2, (int) ((height / 10) * 1)); // tool 1:
        addObject(new Cutter(true), width / 2, (int) ((height / 10) * 2)); // tool 2:
        addObject(new Extractor(true), width / 2, (int) ((height / 10) * 3)); // tool 3:
        addObject(new Balancer(true), width / 2, (int) ((height / 10) * 4)); // tool 5:
        addObject(new RotateRight(true), width / 2, (int) ((height / 10) * 5)); // tool 6:
        addObject(new RotateLeft(true), width / 2, (int) ((height / 10) * 6)); // tool 7:
        addObject(new Painter(true), width / 2, (int) ((height / 10) * 7)); // tool 8:
        addObject(new Stacker(true), width / 2, (int) ((height / 10) * 8)); // tool 9:
        addObject(new TrashCan(true), width / 2, (int) ((height / 10) * 9)); // tool 9:
        //Button.drawCenteredText (getBackground(), "money: ", width / 2, (int) ((height / 9) * 1 - offset));  
        

        addObject(new Hub(), 600,400);
        levelLabel = new Label(Utils.getLevel() + 1, 50);
        itemLabel = new Label(Utils.getTotalTargetShapes() + " / 5", 30);
        moneyLabel = new Label("$" + Utils.getMoney(), 22);
        addObject(levelLabel, 656, 339);
        addObject(itemLabel, 600, 460);
        addObject(moneyLabel, 1150, 25);
        levelLabel.setLineColor(yellow);
        levelLabel.setFillColor(yellow);
        itemLabel.setLineColor(yellow);
        itemLabel.setFillColor(yellow);
        moneyLabel.setLineColor(new Color(171, 171, 171));
        moneyLabel.setFillColor(new Color(171, 171, 171));

        
        
        int rightButtonOffset = 20;
        int rightLabelOffset = 40;
        getBackground().setFont(comicFontMid);
        getBackground().setColor(Color.MAGENTA);
        
        //addObject(new MoneyDisplay(), 1150, 40);
        addObject(new UpgradeButton("crs"), 1200 - width / 2, 150); 
        addObject(new UpgradeButton("bd"), 1200 - width / 2, 280); 
        addObject(new UpgradeButton("paint"), 1200 - width / 2, 410); 
        addObject(new UpgradeButton("extract"), 1200 - width / 2, 540);
        addObject(new NextLevelButton(), 1200 - width / 2, 640);
        addObject(new SoundButton(), 1200 - width / 2, 700);
        addObject(new SaveButton(), 1200 - width / 2, 760);
    }

    public void reset() {
        int width = 200;
        int height = 800; 
        int offset = 27;
        addObject(new Belts(true), width / 2, (int) ((height / 10) * 1)); // tool 1:
        addObject(new Cutter(true), width / 2, (int) ((height / 10) * 2)); // tool 2:
        addObject(new Extractor(true), width / 2, (int) ((height / 10) * 3)); // tool 3:
        addObject(new Balancer(true), width / 2, (int) ((height / 10) * 4)); // tool 5:
        addObject(new RotateRight(true), width / 2, (int) ((height / 10) * 5)); // tool 6:
        addObject(new RotateLeft(true), width / 2, (int) ((height / 10) * 6)); // tool 7:
        addObject(new Painter(true), width / 2, (int) ((height / 10) * 7)); // tool 8:
        addObject(new Stacker(true), width / 2, (int) ((height / 10) * 8)); // tool 9:
        addObject(new TrashCan(true), width / 2, (int) ((height / 10) * 9)); // tool 9:
        ArrayList<Hub> hub = (ArrayList<Hub>) getObjects(Hub.class);
        for (Hub h : hub){
            h.updateImage(); 
        }       
    }
    
    
    public void setLevel() {
        if (Utils.mapChange()) {
            for (Deposits v : (ArrayList<Deposits>) getObjects(Deposits.class)){
                    removeObject(v);
                }
            for (Material m : (ArrayList<Material>) getObjects(Material.class)){
                removeObject(m);
            }
            for (NarrowMachines nm : getObjects(NarrowMachines.class)){
                removeObject(nm);
            }
            for (WideMachines wm : getObjects(WideMachines.class)){
                removeObject(wm);
            }
            for (FollowPoint fp : getObjects(FollowPoint.class)){
                if(!fp.checkIfLabel())
                {
                    removeObject(fp);
                }
            }
            for (ghostBlock gb : getObjects(ghostBlock.class)){
                removeObject(gb);
            }
            for (Arrows a : getObjects(Arrows.class)){
                removeObject(a);
            }
            Actor[][] machineMap = Utils.getMachineArray();
            for (int i = 0; i < machineMap.length; i++)
            {
                for(int j = 0; j < machineMap[i].length; j++)
                {
                    if(machineMap[i][j] != null)
                    {
                        if(machineMap[i][j].getClass() != Hub.class)
                        {
                            Utils.fillSpaceMachine(i, j, null);
                        }
                    }
                }
            }
            
            if (Utils.getLevel() == 4) {
                setMap2();
            } else if (Utils.getLevel() == 8) {
                setMap3();
            } else if (Utils.getLevel() == 12) {
                setMap4();
            } else {
                // CHANGE TO END WORLD
                Greenfoot.setWorld(new EndScreen());
            }
            reset();
        }
    }
    
    public void cheat() {
        if (Utils.getLastKey() != null && Utils.getLastKey().equals("space") && Utils.getLevel() < 15) {
            Utils.increaseLevel();
        }
        if (Utils.getLastKey() != null) {
            // CHANGE LETTERS OR ELSE IT WONT GET REMOVED
            if (Utils.getLastKey().equals("u") || Utils.getLastKey().equals("i") || Utils.getLastKey().equals("o") || Utils.getLastKey().equals("p")) {
                for (Deposits v : (ArrayList<Deposits>) getObjects(Deposits.class)){
                    removeObject(v);
                }
                for (Material m : (ArrayList<Material>) getObjects(Material.class)){
                    removeObject(m);
                }
                for (NarrowMachines nm : getObjects(NarrowMachines.class)){
                    removeObject(nm);
                }
                for (WideMachines wm : getObjects(WideMachines.class)){
                    removeObject(wm);
                }
                for (FollowPoint fp : getObjects(FollowPoint.class)){
                    if(!fp.checkIfLabel())
                    {
                        removeObject(fp);
                    }
                }
                for (ghostBlock gb : getObjects(ghostBlock.class)){
                    removeObject(gb);
                }
                for (Arrows a : getObjects(Arrows.class)){
                    removeObject(a);
                }
                Actor[][] machineMap = Utils.getMachineArray();
                for (int i = 0; i < machineMap.length; i++)
                {
                    for(int j = 0; j < machineMap[i].length; j++)
                    {
                        if(machineMap[i][j] != null)
                        {
                            if(machineMap[i][j].getClass() != Hub.class)
                            {
                                Utils.fillSpaceMachine(i, j, null);
                            }
                        }
                    }
                }
                reset();
            }
            
            if (Utils.getLastKey().equals("u")) {
                // map 1
                setMap1();
            } else if (Utils.getLastKey().equals("i")) {
                // map 2
                setMap2();
            } else if (Utils.getLastKey().equals("o")) {
                // map 3
                setMap3();
            } else if (Utils.getLastKey().equals("p")) {
                // map 4
                setMap4();
            } 
            
        }
    }
    
    public void setMap1() {
        addObject(new Deposits("circle"), 3 * 40 + 220, 4 * 40 + 20);
        addObject(new Deposits("square"), 14 * 40 + 220, 17 * 40 + 20);
        addObject(new Deposits("red"), 17 * 40 + 220, 11 * 40 + 20);
    }
    
    public void setMap2() {
        int[][] eyebrows = {{2,1}, {3,2}, {4,3}, {5,4}, {6,4}, {7,4}, {17,1}, {16,2}, {15,3}, {14,4}, {13,4}, {12,4}};
        for (int i = 0; i < eyebrows.length; i++) {
            addObject(new Deposits("star"), eyebrows[i][0] * 40 + 220, eyebrows[i][1] * 40 + 20);
        }
        int[][] eyes = {{3, 6}, {3, 7}, {3, 8}, {4, 6}, {5, 6}, {5, 7}, {5, 8}, {4, 8}, {16, 6}, {16, 7}, {16, 8}, {15, 6}, {14, 6}, {14, 7}, {14, 8}, {15, 8}};
        for (int i = 0; i < eyes.length; i++) {
            addObject(new Deposits("circle"), eyes[i][0] * 40 + 220, eyes[i][1] * 40 + 20);
        }
        addObject(new Deposits("red"), 4 * 40 + 220, 7 * 40 + 20);
        addObject(new Deposits("red"), 15 * 40 + 220, 7 * 40 + 20);
        addObject(new Deposits("yellow"), 0 * 40 + 220, 19 * 40 + 20);
        addObject(new Deposits("yellow"), 19 * 40 + 220, 19 * 40 + 20);
        int[][] mouth = {{1,12}, {2,11}, {1,13}, {2,14}, {3,15}, {4,16}, {3,11}, {4,12}, {5,13}, {6,14}, 
                        {7,14}, {8,14}, {9,14}, {10,14}, {11,14}, {12,14}, {6,17}, {7,17}, {8,17}, {9,17}, {10,17}, {11,17}, {12,17}, {13,17},
                        {18,12}, {17,11}, {18,13}, {17,14}, {16,15}, {15,16}, {16,11}, {15,12}, {14,13}, {13,14}};
        for (int i = 0; i < mouth.length; i++) {
            addObject(new Deposits("square"), mouth[i][0] * 40 + 220, mouth[i][1] * 40 + 20);
        }
        int[][] mouthInside = {{2,12}, {3,12}, {16,12}, {17,12},
                                {2,13}, {3,13}, {4,13}, {15,13}, {16,13}, {17,13},
                                {3,14}, {4,14}, {5,14}, {14,14}, {15,14}, {16,14},
                                {4,15}, {5,15}, {6,15}, {7,15}, {8,15}, {9,15}, {10,15}, {11,15}, {12,15}, {13,15}, {14,15}, {15,15},
                                {5,16}, {6,16}, {7,16}, {8,16}, {9,16}, {10,16}, {11,16}, {12,16}, {13,16}, {14,16}};
        for (int i = 0; i < mouthInside.length; i++) {
            addObject(new Deposits("red"), mouthInside[i][0] * 40 + 220, mouthInside[i][1] * 40 + 20);
        }                   
    }
    
    public void setMap3() {
        ArrayList<String> depositTypes = new ArrayList<String>();
        depositTypes.add("circle");
        depositTypes.add("square");
        depositTypes.add("star");
        depositTypes.add("blue");
        depositTypes.add("yellow");
        depositTypes.add("red");
        for (int i = 0; i < 6; i++) {
            addObject(new Deposits(depositTypes.get(i%6)), 9 * 40 + 220, i * 40 + 20);  
            addObject(new Deposits(depositTypes.get(i%6)), 10 * 40 + 220, i * 40 + 20); 
            addObject(new Deposits(depositTypes.get(i%6)), 9 * 40 + 220, 780 - i * 40);  
            addObject(new Deposits(depositTypes.get(i%6)), 10 * 40 + 220, 780 - i * 40);  
            addObject(new Deposits(depositTypes.get(i%6)), i * 40 + 220, 9 * 40 + 20);  
            addObject(new Deposits(depositTypes.get(i%6)), i * 40 + 220, 10 * 40 + 20);  
            addObject(new Deposits(depositTypes.get(i%6)), 1200 - 220 - i * 40, 9 * 40 + 20);  
            addObject(new Deposits(depositTypes.get(i%6)), 1200 - 220 - i * 40, 10 * 40 + 20);
        }
    }
    
    public void setMap4() {
        ArrayList<String> depositTypes = new ArrayList<String>();
        depositTypes.add("circle");
        depositTypes.add("square");
        depositTypes.add("star");
        depositTypes.add("blue");
        depositTypes.add("yellow");
        depositTypes.add("red");
        for (int i = 0; i < 18; i++) {
            addObject(new Deposits(depositTypes.get(i%6)), 0 * 40 + 220, i * 40 + 60);
            addObject(new Deposits(depositTypes.get(i%6)), 19 * 40 + 220, i * 40 + 60);
        }
        for (int i = 0; i < 6; i++) {
            addObject(new Deposits(depositTypes.get(i%6)), i * 40 + 220 + 120, 0 * 40 + 20);
            addObject(new Deposits(depositTypes.get(i%6)), i * 40 + 220 + 120, 19 * 40 + 20);  
            addObject(new Deposits(depositTypes.get(i%6)), 1200 - 340 - i * 40, 0 * 40 + 20);
            addObject(new Deposits(depositTypes.get(i%6)), 1200 - 340 - i * 40, 19 * 40 + 20);  
        }
    }

    public void delete()
    {
        if(Utils.getMouse() != null)
        {
            int gridPositionX = (int) (Utils.getMouseX() - 200) /  Utils.gridSize;
            int gridPositionY = (int) Utils.getMouseY() /  Utils.gridSize;
            int buttonNumber = Utils.getMouseButton();
            if(buttonNumber == 3)
            {
                if(gridPositionY < 20 && gridPositionY > -1 && gridPositionX < 20 && gridPositionX > -1)
                {
                    if(Utils.getSpaceMachine(gridPositionY, gridPositionX) != null)
                    {
                        Machines tempMachine = (Machines) Utils.getSpaceMachine(gridPositionY, gridPositionX);
                        if(Utils.getSpaceMachine(gridPositionY, gridPositionX).getClass() == Belts.class)
                        {
                            Belts temp = (Belts) Utils.getSpaceMachine(gridPositionY, gridPositionX);
                            temp.deletePoints();
                        }
                        if(Utils.getSpaceMachine(gridPositionY, gridPositionX).getClass() == Balancer.class || Utils.getSpaceMachine(gridPositionY, gridPositionX).getClass() == Cutter.class || Utils.getSpaceMachine(gridPositionY, gridPositionX).getClass() == Stacker.class || Utils.getSpaceMachine(gridPositionY, gridPositionX).getClass() == Painter.class)
                        {
                            WideMachines temp = (WideMachines) Utils.getSpaceMachine(gridPositionY, gridPositionX);
                            temp.deleteWide();
                            temp.deleteShapesWide();
                            playDelete();
                        } else if(Utils.getSpaceMachine(gridPositionY, gridPositionX).getClass() != Hub.class) {
                            NarrowMachines temp = (NarrowMachines) Utils.getSpaceMachine(gridPositionY, gridPositionX);
                            temp.deleteNarrow();
                            tempMachine.deleteShapes();
                            playDelete();
                        }
                    }
                }
            }    
        }
    }
    /**
     * mehtod to read stats from file then sets the stats in the game to these values.
     */
    public void read()
    {
        System.out.println("reading");
        scan = new Scanner (System.in);
        lines = new ArrayList<Integer>();
        try{
            fileScan = new Scanner (new File("save.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("there is no such file");
            System.exit(1);
        }
        boolean moreLines = true;
        while (moreLines)
        {
            try{
                int a = Integer.parseInt(fileScan.next()); // turns strings into integers
                lines.add(a);
            }
            catch(NoSuchElementException e)
            {
                moreLines = false;
            }
        }
        System.out.println(lines);
        Utils.setLevel(lines.get(0));
        Utils.setMap(lines.get(1));
        Utils.setMoney(lines.get(6));
        //Utils.setCrsUpgradeLevel(lines.get(2));
        //Utils.setBdUpgradeLevel(lines.get(3));
        //Utils.setPaintUpgradeLevel(lines.get(4));
        //Utils.setExtractUpgradeLevel(lines.get(5));
        gameTime = lines.get(8);
        gameTimeM = lines.get(7);
        lines.clear();
    }

    /**
     * method to keep track of game time that has passed, by counting each fps.
     */
    private void time()
    {
        gameTimer = (gameTimer + 1) % 60; // 60 times a second, gametimer is 0 each second(60fps)
        if (gameTimer == 0) {
            gameTime++; // seconds increase
            Utils.setTime(gameTime);
            if(gameTime<10)
            {
                timeLabel.setValue(gameTimeM + ": 0" + gameTime); // to keep 2 digits format
            }
            else if(gameTime>=10)
            {
                timeLabel.setValue(gameTimeM + ": " + gameTime);
            }
            if(gameTime == 59)
            {
                gameTime = 0;
                gameTimeM ++; // minutes increa se
                Utils.setTime(gameTime);
                Utils.setTimeM(gameTimeM);
            }
        }
    }
     /**
      * method to create the time visual in the game
      */   
    public void addTimeLabel()
    {
        timeLabel = new Label(gameTimeM + ": " + gameTime, 30);
        timeLabel.setLineColor(Color.DARK_GRAY);
        timeLabel.setFillColor(Color.DARK_GRAY);
        addObject(timeLabel,600,55);
    }
    
    public void playDelete(){
        delete[deleteIndex].setVolume(90 * Utils.soundLevel());
        delete[deleteIndex].play();
        deleteIndex++;
        if(deleteIndex > delete.length - 1){
            deleteIndex = 0;
        }
    }
}
