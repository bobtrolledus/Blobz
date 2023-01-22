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
 * Write a description of class Utils here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Utils extends Actor
{
    private static Actor[][] map;
    private static int rotation;
    private static int arrowXCoord, arrowYCoord;
    private static MouseInfo mouse = Greenfoot.getMouseInfo();
    private static int extractorDelay = 1000;
    private static int balancerDelay = 1000;
    private static int cutterDelay = 1000;
    private static int rotationDelay = 1000;
    private static int paintingDelay = 1000;

    public static int gridSize = 40;
    private static int level;
    private static Scanner fileScan;
    private static Scanner scan;
    private static int mapNumber = 0;
    private static int money = 0;
    private static int crsUpgradeLevel;
    private static int bdUpgradeLevel;
    private static int paintUpgradeLevel;
    private static int extractUpgradeLevel;
    
    private static ArrayList<Integer> list;
    
    public static int[][][] targetShapes = {
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
            {-1, -1, 1, -1, -1, -1, -1, -1},
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
            {-1, 2, -1, 2, 1, 2, 1, 2},
            {-1, 4, -1, 4, 2, 5, 2, 5}
        },
        {
            {3, -1, 2, -1, 2, 1, 3, 1},
            {5, -1, 3, -1, 4, 6, 2, 6}
        }
    };
                                            
                                                
                                        
                                            
    
    public Utils()
    {
        list = new ArrayList<Integer>();
        map = new Actor[20][20];
        rotation = 0;
        getImage().scale(10, 10);
        crsUpgradeLevel = 1;
        bdUpgradeLevel = 1;
        paintUpgradeLevel = 1;
        extractUpgradeLevel = 1;
        level = 1;
    }

    public void act()
    {
        mouse = Greenfoot.getMouseInfo();
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
        if(map[x][y] == null)
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

    public static void fillSpace(int x, int y, Machines object)
    {
        map[x][y] = object;
    }

    public static Actor getSpace(int x, int y)
    {
        return map[x][y];
    }

    public static int getLevel()
    {
        return level;
    }

    public static void increaseLevel()
    {
        
        level++;
    }

    public static void decreaseLevel()
    {
        level--;
    }
    
    public static int getCRSlevel() {
        return crsUpgradeLevel;
    }
    
    public static void increaseCRSlevel() {
        crsUpgradeLevel++;
    }
    
    public static int getBDlevel() {
        return bdUpgradeLevel;
    }
    
    public static void increaseBDlevel() {
        bdUpgradeLevel++;
    }
    
    public static int getPAINTlevel() {
        return paintUpgradeLevel;
    }
    
    public static void increasePAINTlevel() {
        paintUpgradeLevel++;
    }
    
    public static int getEXTRACTlevel() {
        return extractUpgradeLevel;
    }
    
    public static void increaseEXTRACTlevel() {
        extractUpgradeLevel++;
    }
    
    private static void save()
    {
        
        try{
            list.add(level);
            list.add(mapNumber);
            list.add(crsUpgradeLevel);
            list.add(bdUpgradeLevel);
            list.add(paintUpgradeLevel);
            list.add(extractUpgradeLevel);
            list.add(money);
            FileWriter out = new FileWriter("save.txt",false);
            PrintWriter output = new PrintWriter(out);
            output.println(list);
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
    
    public static void setMap(int x)
    {
        mapNumber = x;
    }
    
    public static void setUpgrade(int x)
    {
        //upgradeLevel = x;
    }
    
    public static void setMoney(int x)
    {
        money = x;
    }
    
    
}
