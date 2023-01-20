import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square extends Shapes
{
    public Square (Boolean isLabel, int q, FollowPoint guide){
        super(isLabel, q, guide);
        getImage().scale(15, 15);
        setRotation(q);
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
}
