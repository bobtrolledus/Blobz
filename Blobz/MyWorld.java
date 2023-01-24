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
    private Label levelLabel, itemLabel;
    private static Scanner fileScan;
    private static Scanner scan;
    private static ArrayList<Integer> lines;
    private Color lightGray = new Color(228, 228, 226);
    private Color gray = new Color(171, 171, 171);
    private Color yellow = new Color(255, 255, 186);
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
        prepare();
    }

    public void act()
    {        
        delete();
        levelLabel.setValue(Utils.getLevel() + 1);
        itemLabel.setValue(Utils.getTotalTargetShapes() + " / 250");
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
        addObject(new Utils(), 0, 0);

        addObject(new Hub(), 600,400);
        levelLabel = new Label(Utils.getLevel() + 1, 50);
        itemLabel = new Label(Utils.getTotalTargetShapes() + " / 250", 30);
        addObject(levelLabel, 656, 339);
        addObject(itemLabel, 600, 460);
        levelLabel.setLineColor(yellow);
        levelLabel.setFillColor(yellow);
        itemLabel.setLineColor(yellow);
        itemLabel.setFillColor(yellow);
        
        
        int rightButtonOffset = 20;
        int rightLabelOffset = 40;
        getBackground().setFont(comicFontSmoll);
        addObject(new UpgradeButton("crs"), 1200 - width / 2, (int) ((height / 5) * 1) + rightButtonOffset); 
        addObject(new UpgradeButton("bd"), 1200 - width / 2, (int) ((height / 5) * 2) + rightButtonOffset); 
        addObject(new UpgradeButton("paint"), 1200 - width / 2, (int) ((height / 5) * 3) + rightButtonOffset); 
        addObject(new UpgradeButton("extract"), 1200 - width / 2, (int) ((height / 5) * 4) + rightButtonOffset); 

        addObject(new Deposits("red"), 260, 20);
        addObject(new Deposits("blue"), 300, 20);
        addObject(new Deposits("yellow"), 340, 20);
        addObject(new Deposits("circle"), 380, 20);
        addObject(new Deposits("square"), 420, 20);
        addObject(new Deposits("blue"), 460, 20);
        
        addObject(new Deposits("red"), 220, 100);
        addObject(new Deposits("blue"), 220, 140);
        addObject(new Deposits("yellow"), 220, 180);
        addObject(new Deposits("circle"), 220, 220);
        addObject(new Deposits("square"), 220, 260);
        addObject(new Deposits("star"), 220, 300);
        
        addObject(new Deposits("red"), 980, 100);
        addObject(new Deposits("blue"), 980, 140);
        addObject(new Deposits("yellow"), 980, 180);
        addObject(new Deposits("circle"), 980, 220);
        addObject(new Deposits("square"), 980, 260);
        addObject(new Deposits("star"), 980, 300);
        
        addObject(new Deposits("red"), 940, 780);
        addObject(new Deposits("blue"), 900, 780);
        addObject(new Deposits("yellow"), 860, 780);
        addObject(new Deposits("circle"), 820, 780);
        addObject(new Deposits("square"), 780, 780);
        addObject(new Deposits("star"), 740, 780);
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
                        } else if(Utils.getSpaceMachine(gridPositionY, gridPositionX).getClass() != Hub.class) {
                            NarrowMachines temp = (NarrowMachines) Utils.getSpaceMachine(gridPositionY, gridPositionX);
                            temp.deleteNarrow();
                            tempMachine.deleteShapes();
                        }
                    }
                }
            }    
        }
    }

    public void read()
    {
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
                int a = fileScan.nextInt();
                lines.add(a);
            }
            catch(NoSuchElementException e)
            {
                moreLines = false;
            }
        }
        Utils.setLevel(lines.get(0));
        Utils.setMap(lines.get(1));
        Utils.setUpgrade(lines.get(2));
        Utils.setMoney(lines.get(3));
        lines.clear();
    }
}
