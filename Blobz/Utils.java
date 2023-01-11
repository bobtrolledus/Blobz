import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;

/**
 * Write a description of class Utils here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Utils extends Actor
{
    private static Machines[][] map = new Machines[20][20]; 
    private static int rotation = 0;
    private static int arrowXCoord, arrowYCoord;
    private static MouseInfo mouse = Greenfoot.getMouseInfo();
    
    public static int gridSize = 40;
    
    public void act()
    {
        mouse = Greenfoot.getMouseInfo();
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
}
