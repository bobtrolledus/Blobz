import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Colours here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colours extends Material
{
    private boolean isLabel;
    public FollowPoint guide;
    protected int colour;
    private boolean firstRun = true;
    
    public Colours(boolean isLabel, FollowPoint guide){
        this.isLabel = isLabel;
        this.guide = guide;
        if (isLabel){
            getImage().scale(26, 26);
        } else {
            getImage().scale(38, 38);
        }
    }
    
    public void act() {
        if(isLabel)
        {
            if(firstRun){
                firstRun = false;
            }
        }
        if(!isLabel)
        {
            if (guide.getWorld() == null){
                getWorld().removeObject(this);
            } else {
                move();
            }
        }
    }
    
    public void move(){
        this.setLocation(guide.getX(), guide.getY());
    }
}
