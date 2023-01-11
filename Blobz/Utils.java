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
    private static Actor[][] map = new Actor[20][20];
    
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
    
    public static void fillSpace(int x, int y, Machines object)
    {
        map[x][y] = object;
    }
}
