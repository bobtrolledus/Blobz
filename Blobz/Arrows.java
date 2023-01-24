import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Arrow image to display which direction the machine is facing
 * 
 * @author Anson
 * @version Jan 24, 2023
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
