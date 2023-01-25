import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An abstract class dealing with the spawning and removing of shapes and colours.
 * 
 * @author Andy 
 * @version Jan 24, 2023
 */
public abstract class Material extends Actor
{
    protected FollowPoint guide;
    protected int colour;
    
    public Material(FollowPoint guide){
        this.guide = guide;
    }
    
    public void act()
    {
        if(guide.getWorld() == null)
        {
            getWorld().removeObject(this);
        }
        else
        {
            move();
        }
    }
    
    protected abstract void move();
}
