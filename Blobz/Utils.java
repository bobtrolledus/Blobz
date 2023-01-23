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
    private static Actor[][] machineMap;
    private static Deposits[][] depositMap;
    private static int rotation;
    private static int arrowXCoord, arrowYCoord;
    private static MouseInfo mouse = Greenfoot.getMouseInfo();
    private static int extractorDelay = 1000;
    private static int balancerDelay = 1000;
    private static int cutterDelay = 1000;
    private static int rotationDelay = 1000;
    private static int paintingDelay = 1000;
    private static int stackingDelay = 1000;

    public static int gridSize = 40;
    private static int level;
    private static Scanner fileScan;
    private static Scanner scan;
    private static int mapNumber;
    private static int money;
    private static int crsUpgradeLevel;
    private static int bdUpgradeLevel;
    private static int paintUpgradeLevel;
    private static int extractUpgradeLevel;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private static int totalTargetShapes;
    private static String key;
    private static ArrayList<Integer> list;
    private static boolean mirrored;
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
    
=======

    private static ArrayList<Integer> list;

    public static int[][][] targetShapes = {
=======

    private static ArrayList<Integer> list;

    public static int[][][] targetShapes = {
>>>>>>> Stashed changes
=======

    private static ArrayList<Integer> list;

    public static int[][][] targetShapes = {
>>>>>>> Stashed changes
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

    private static int time;
    private static int timeM;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public Utils()
    {
        list = new ArrayList<Integer>();
        machineMap = new Actor[20][20];
        depositMap = new Deposits[20][20];
        mirrored = false;
        rotation = 0;
        money = 0;
        mapNumber = 0;
        totalTargetShapes = 0;
        getImage().scale(10, 10);
        crsUpgradeLevel = 1;
        bdUpgradeLevel = 1;
        paintUpgradeLevel = 1;
        extractUpgradeLevel = 1;
        level = 7;
    }

    public void act()
    {
        key = Greenfoot.getKey();
        mouse = Greenfoot.getMouseInfo();
        if(totalTargetShapes == 5)
        {
            totalTargetShapes = 0;
            level++;
        }
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
    
    public static int[] getTargetShape()
    {
        return targetShapes[level][0];
    }
    
    public static int[] getTargetShapeColour()
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
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
            list.add(timeM);
            list.add(time);
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
    
    public static void setCrsUpgradeLevel(int x)
    {
        crsUpgradeLevel = x;
    }

    public static void setBdUpgradeLevel(int x)
    {
        bdUpgradeLevel =x;
    }

    public static void setPaintUpgradeLevel(int x)
    {
        paintUpgradeLevel = x;
    }

    public static void setExtractUpgradeLevel(int x)
    {
        extractUpgradeLevel = x;
    }

    public static int getCrsUpgradeLevel()
    {
        return crsUpgradeLevel;
    }

    public static int getBdUpgradeLevel()
    {
        return bdUpgradeLevel;
    }

    public static int getPaintUpgradeLevel()
    {
        return paintUpgradeLevel;
    }

    public static int getExtractUpgradeLevel()
    {
        return extractUpgradeLevel;
    }

    
}
