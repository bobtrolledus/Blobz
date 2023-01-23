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
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Font comicFontMid = new Font ("Comic Sans MS", true, false, 24);
    private Font comicFontSmoll = new Font ("Comic Sans MS", true, false, 20);
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
        getBackground().setColor(lightGray);
        getBackground().fill();
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
        String key = Greenfoot.getKey();
        levelLabel.setValue(Utils.getLevel() + 1);
        itemLabel.setValue(Utils.getTotalTargetShapes() + " / 250");
        if(key != null)
        {
            if(key.equals("r"))
            {
                Utils.addRotation();
            }
            if(key.equals("m"))
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

        Button.drawCenteredText (getBackground(), "belt", width / 2, (int) ((height / 9) * 1 - offset));       
        addObject(new Belts(true), width / 2, (int) ((height / 9) * 1)); // tool 1:
        Button.drawCenteredText (getBackground(), "cutter", width / 2, (int) ((height / 9) * 2 - offset));       
        addObject(new Cutter(true), width / 2, (int) ((height / 9) * 2)); // tool 2:
        Button.drawCenteredText (getBackground(), "extractor", width / 2, (int) ((height / 9) * 3 - offset)); 
        addObject(new Extractor(true), width / 2, (int) ((height / 9) * 3)); // tool 3:
        Button.drawCenteredText (getBackground(), "balancer", width / 2, (int) ((height / 9) * 4 - offset)); 
        addObject(new Balancer(true), width / 2, (int) ((height / 9) * 4)); // tool 5:
        Button.drawCenteredText (getBackground(), "rotate cw", width / 2, (int) ((height / 9) * 5 - offset)); 
        addObject(new RotateRight(true), width / 2, (int) ((height / 9) * 5)); // tool 6:
        Button.drawCenteredText (getBackground(), "rotate ccw", width / 2, (int) ((height / 9) * 6 - offset)); 
        addObject(new RotateLeft(true), width / 2, (int) ((height / 9) * 6)); // tool 7:
        Button.drawCenteredText (getBackground(), "painter", width / 2, (int) ((height / 9) * 7 - offset)); 
        addObject(new Painter(true), width / 2, (int) ((height / 9) * 7)); // tool 8:
        Button.drawCenteredText (getBackground(), "stacker", width / 2, (int) ((height / 9) * 8 - offset)); 
        addObject(new Stacker(true), width / 2, (int) ((height / 9) * 8)); // tool 9:

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
        Button.drawCenteredText (getBackground(), "upgrades:", 1200 - width / 2, (int) ((height / 5) * 1 - rightLabelOffset - 40));
        getBackground().setFont(comicFontSmoll);
        Button.drawCenteredText (getBackground(), "cut/rotate/stack", 1200 - width / 2, (int) ((height / 5) * 1 - rightLabelOffset)); 
        addObject(new UpgradeButton("crs"), 1200 - width / 2, (int) ((height / 5) * 1) + rightButtonOffset); 
        Button.drawCenteredText (getBackground(), "belts/distributors", 1200 - width / 2, (int) ((height / 5) * 2 - rightLabelOffset)); 
        addObject(new UpgradeButton("bd"), 1200 - width / 2, (int) ((height / 5) * 2) + rightButtonOffset); 
        Button.drawCenteredText (getBackground(), "painting speed", 1200 - width / 2, (int) ((height / 5) * 3 - rightLabelOffset)); 
        addObject(new UpgradeButton("paint"), 1200 - width / 2, (int) ((height / 5) * 3) + rightButtonOffset); 
        Button.drawCenteredText (getBackground(), "extraction speed", 1200 - width / 2, (int) ((height / 5) * 4 - rightLabelOffset)); 
        addObject(new UpgradeButton("extract"), 1200 - width / 2, (int) ((height / 5) * 4) + rightButtonOffset); 

        addObject(new Deposits("red"), 220, 20);
        addObject(new Deposits("blue"), 40 + 220, 20);
        addObject(new Deposits("yellow"), 2 * 40 + 220, 20);
        addObject(new Deposits("circle"), 220, 60);
        addObject(new Deposits("square"), 40 + 220, 60);
        addObject(new Deposits("blue"), 2 * 40 + 220, 60);
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
                    } else {
                        NarrowMachines temp = (NarrowMachines) Utils.getSpaceMachine(gridPositionY, gridPositionX);
                        temp.deleteNarrow();
                        tempMachine.deleteShapes();
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
