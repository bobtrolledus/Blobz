import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

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
    private static int balancerDelay = 500;
    
    public static int gridSize = 40;
    private static int level;
    
    public static int[][][] targetShapes = {
                                            {
                                                {1, 1, 1, 1, -1, -1, -1, -1},
                                            {-1, -1, -1, -1, -1, -1, -1, -1}}},
                                            {{1, 1, 1, 1, -1, -1, -1, -1},
                                            {-1, -1, -1, -1, -1, -1, -1, -1};
    public Utils()
    {
        map = new Actor[20][20];
        rotation = 0;
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


}
