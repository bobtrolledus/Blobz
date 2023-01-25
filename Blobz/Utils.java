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
 * Class for all global variables
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Utils extends Actor
{
    private static Actor[][] machineMap;
    private static Deposits[][] depositMap;
    private static int rotation;
    private static int arrowXCoord, arrowYCoord;
    private static MouseInfo mouse = Greenfoot.getMouseInfo();
    private static int cutterDelay;
    private static int rotationDelay;
    private static int stackingDelay;
    private static int balancerDelay;
    private static int paintingDelay;
    private static int extractorDelay;
    
    private static int time, timeM;

    public static int gridSize = 40;
    private static int level;
    private static Scanner fileScan;
    private static Scanner scan;
    private static boolean mapChange;
    private static double money;
    private static boolean userApprovedLevelChange;
    private static int soundLevel;
    private static int crsUpgradeLevel;
    private static int bdUpgradeLevel;
    private static int paintUpgradeLevel;
    private static int extractUpgradeLevel;
    private static int totalTargetShapes;
    private static String key;
    private static ArrayList<Integer> list;
    private static boolean mirrored;
    public static Integer[][][] targetShapes = {
        {
            {1, 1, 1, 1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1}
        },
        {
            {2, 2, 2, 2, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1}
        },
        {
            {-1, -1, 1, 1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1}
        },
        {
            {2, 2, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1}
        },
        {
            {1, -1, -1, 1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1}
        },
        {
            {-1, 2, 2, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1}
        },
        {
            {3, 3, 3, 3, -1, -1, -1, -1},
            {2, 2, 2, 2, -1, -1, -1, -1}
        },
        {
            {1, 1, 1, 1, -1, -1, -1, -1},
            {3, 3, 3, 3, -1, -1, -1, -1}
        },
        {
            {3, 3, -1, -1, -1, -1, -1, -1},
            {4, 4, -1, -1, -1, -1, -1, -1}
        },
        {
            {-1, -1, 2, -1, -1, -1, -1, -1},
            {-1, -1, 1, -1, -1, -1, -1, -1}
        },
        {
            {2, 2, 2, 2, 1, 1, 1, 1},
            {-1, -1, -1, -1, -1, -1, -1, -1}
        },
        {
            {-1, 1, 1, -1, -1, 1, 1, -1},
            {-1, 6, 6, -1, -1, 1, 1, -1}
        },
        {
            {1, 2, 2, 1, 2, 1, 1, 2},
            {2, 4, 4, 2, 5, 1, 1, 5}
        },
        {
            {1, 3, 3, 1, 3, 1, 1, 3},
            {5, 6, 6, 5, 3, 3, 3, 3}
        },
        {
            {1, 2, 1, 2, 1, 2, 1, 2},
            {-1, 4, -1, 4, 2, 5, 2, 5}
        },
        {
            {3, 1, 2, 1, 1, 3, 2, 3},
            {5, 6, 5, 6, 6, 5, 3, 5}
        }
    };
    
    private static GreenfootSound upgrade = new GreenfootSound("upgrade.wav"), levelUp = new GreenfootSound("levelUp.wav");
    
    public Utils()
    {
        list = new ArrayList<Integer>();
        machineMap = new Actor[20][20];
        depositMap = new Deposits[20][20];
        cutterDelay = 2500;
        rotationDelay = 2000;
        stackingDelay= 4000;
        balancerDelay = 2000;
        paintingDelay = 4000;
        extractorDelay = 3000;
        mirrored = false;
        rotation = 0;
        money = 0;
        mapChange = false;
        userApprovedLevelChange = false;
        soundLevel = 1;
        totalTargetShapes = 0;
        getImage().scale(10, 10);
        crsUpgradeLevel = 1;
        bdUpgradeLevel = 1;
        paintUpgradeLevel = 1;
        extractUpgradeLevel = 1;
        level = 0;
    }

    public void act()
    {
        key = Greenfoot.getKey();
        mouse = Greenfoot.getMouseInfo();
        if(totalTargetShapes >= 5) {
            if (userApprovedLevelChange) {
                increaseLevel();
                totalTargetShapes = 0;
                if ((level == 4 || level == 8 || level == 12)) {
                    mapChange = true;
                }
                userApprovedLevelChange = false;
            }
        } else {
            mapChange = false;
        }
    }

    
    public static int soundLevel() {
        return soundLevel;
    }
    
    public static void turnOnSound() {
        soundLevel = 1;
    }
    
    public static void turnOffSound() {
        soundLevel = 0;
    }
    
    public static void approveLevelChange() {
        System.out.println("approval method ran");
        userApprovedLevelChange = true;
    }
    
    public static String getLastKey()
    {
        return key;
    }
    
    public static void changeMirrored()
    {
        if(!mirrored)
        {
            mirrored = true;
        } else {
            mirrored = false;
        }
    }
    
    public static boolean getMirrored()
    {
        return mirrored;
    }
    
    public static void addTargetShape()
    {
        totalTargetShapes++;
    }
    
    public static int getTotalTargetShapes()
    {
        return totalTargetShapes;
    }
    
    public static Integer[] getTargetShape()
    {
        return targetShapes[level][0];
    }
    
    public static Integer[] getTargetShapeColour()
    {
        return targetShapes[level][1];
    }

    public static int getExtractorDelay()
    {
        return extractorDelay;
    }

    public static void setExtractorDelay(int delay)
    {
        extractorDelay = delay;
    }

    public static int getBalancerDelay()
    {
        return balancerDelay;
    }

    public static void setBalancerDelay(int delay)
    {
        balancerDelay = delay;
    }
    
    public static int getCutterDelay()
    {
        return balancerDelay;
    }

    public static void setCutterDelay(int delay)
    {
        cutterDelay = delay;
    }
    
    public static int getRotationDelay()
    {
        return rotationDelay;
    }

    public static void setRotationDelay(int delay)
    {
        rotationDelay = delay;
    }
    
    public static int getPainterDelay()
    {
        return paintingDelay;
    }

    public static void setPainterDelay(int delay)
    {
        paintingDelay = delay;
    }
    
    public static int getStackingDelay()
    {
        return stackingDelay;
    }

    public static void setstackingDelay(int delay)
    {
        stackingDelay = delay;
    }

    public static int getMouseX()
    {
        return mouse.getX();
    }

    public static int getMouseY()
    {
        return mouse.getY();
    }

    public static int getMouseButton()
    {
        return mouse.getButton();
    }

    public static MouseInfo getMouse()
    {
        return mouse;
    }

    public static boolean spaceIsEmpty(int x, int y)
    {
        if(machineMap[x][y] == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void addRotation()
    {
        rotation++;
        if(rotation == 4)
        {
            rotation = 0;
        }
    }

    public static int getDirection()
    {
        return rotation;
    }
    
    public static Actor[][] getMachineArray()
    {
        return machineMap;
    }

    public static void fillSpaceMachine(int x, int y, Actor object)
    {
        machineMap[x][y] = object;
    }

    public static Actor getSpaceMachine(int x, int y)
    {
        return machineMap[x][y];
    }
    
    public static void fillSpaceDeposit(int x, int y, Deposits object)
    {
        depositMap[x][y] = object;
    }

    public static Actor getSpaceDeposit(int x, int y)
    {
        return depositMap[x][y];
    }

    public static int getLevel()
    {
        return level;
    }

    public static void increaseLevel()
    {
        levelUp.play();
        level++;
    }

    public static void decreaseLevel()
    {
        level--;
    }
    
    public static int getCRSlevel() {
        return crsUpgradeLevel;
    }
    
    public static void setCRSlevel(int x) {
        crsUpgradeLevel = x;
    }
    
    public static void increaseCRSlevel() {
        crsUpgradeLevel++;
        cutterDelay -= 2500/9;
        rotationDelay -= 2000/9;
        stackingDelay -= 4000/9;
        upgrade.play();
    }
    
    public static int getBDlevel() {
        return bdUpgradeLevel;
    }
    
    public static void setBDlevel(int x) {
        bdUpgradeLevel = x;
    }
    
    public static void increaseBDlevel() {
        bdUpgradeLevel++;
        balancerDelay -= 2000/9;
        upgrade.play();
    }
    
    public static int getPAINTlevel() {
        return paintUpgradeLevel;
    }
    
    public static void setPAINTlevel(int x) {
        paintUpgradeLevel = x;
    }
    
    public static void increasePAINTlevel() {
        paintUpgradeLevel++;
        paintingDelay -= 4000/9;
        upgrade.play();
    }
    
    public static int getEXTRACTlevel() {
        return extractUpgradeLevel;
    }
    
    public static void setEXTRACTlevel(int x) {
        extractUpgradeLevel = x;
    }
    
    public static void increaseEXTRACTlevel() {
        extractUpgradeLevel++;
        extractorDelay -= 3000/9;
        upgrade.play();
    }
    
    public static void save()
    {
        try{
            list.add(timeM);
            list.add(time);
            list.add(level);
            list.add(crsUpgradeLevel);
            list.add(bdUpgradeLevel);
            list.add(paintUpgradeLevel);
            list.add(extractUpgradeLevel);
            list.add((int)money);
            FileWriter out = new FileWriter("save.txt",false);
            PrintWriter output = new PrintWriter(out);
            System.out.println("saved data: " + list);
            output.println(timeM);
            output.println(time);
            output.println(level);
            output.println(crsUpgradeLevel);
            output.println(bdUpgradeLevel);
            output.println(paintUpgradeLevel);
            output.println(extractUpgradeLevel);
            output.println((int)money);
            out.close();
            output.close();
            list.clear();
        }
        catch(IOException e)
        {
            System.out.println("Exception" +  e);
        }
    }
    
    public static void setLevel(int x)
    {
        level = x;
    }
        
    public static boolean mapChange() {
        return mapChange;
    }
    
    public static void setTime(int x)
    {
        time = x;
    }

    public static void setTimeM(int x)
    {
        timeM = x;
    }

    public static int getTime()
    {
        return time;
    }

    public static int getTimeM()
    {
        return timeM;
    }
    
    public static void setUpgrade(int x)
    {
        //upgradeLevel = x;
    }
    
    public static int getMoney() {
        return (int) money;
    }
    
    public static void spendMoney(double x) {
        money -= x;
    }
    
    public static void setMoney(double x)
    {
        money = x;
    }
    
    public static void addMoney(double x){
        money += x;
    }
}
