import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrows here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Arrows extends UtilsBlocks
{
    /**
     * Act - do whatever the Arrows wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Utils.getMouse() != null)
        {
            if(Utils.getMouseX() < 200 || Utils.getMouseX() > 1000)
            {
                getWorld().removeObject(this);
            }    
        }
    }
}
