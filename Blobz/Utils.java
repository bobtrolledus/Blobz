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
    private static int balancerDelay = 500;

    public static int gridSize = 40;
    private static int level=0;
    private static Scanner fileScan;
    private static Scanner scan;
    private static int mapNumber=0;
    private static int money=0;
    private static int upgradeLevel = 0;
    private static ArrayList<Integer> list;
    public Utils()
    {
        list = new ArrayList<Integer>();
        map = new Actor[20][20];
        rotation = 0;
        getImage().scale(10, 10);
    }

    public void act()
    {
        mouse = Greenfoot.getMouseInfo();
        save();
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

    private static void save()
    {
        try{
            list.add(level);
            list.add(mapNumber);
            list.add(upgradeLevel);
            list.add(money);
            FileWriter out = new FileWriter("save.txt",false);
            PrintWriter output = new PrintWriter(out);
            output.println(list);
            out.close();
            output.close();
        }
        catch(IOException e)
        {
            System.out.println("Exception" +  e);
        }
    }
}
