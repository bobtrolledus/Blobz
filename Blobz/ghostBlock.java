import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ghostBlock here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class ghostBlock extends Actor
{
    private int xCoord, yCoord;
    
    public ghostBlock()
    {
        getImage().scale(45, 45);
    }
    
    public void setXGridCoord(int coord)
    {
        xCoord = coord;
    }
    
    public void setYGridCoord(int coord)
    {
        yCoord = coord;
    }
    
    public int getXCoord()
    {
        return xCoord;
    }
    
    public int getYCoord()
    {
        return yCoord;
    }
}
