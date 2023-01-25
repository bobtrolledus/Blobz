import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Material here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Material extends Actor
{
    protected FollowPoint guide;
    
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
    
    public void move(){
        this.setLocation(guide.getX(), guide.getY());
    }
}
