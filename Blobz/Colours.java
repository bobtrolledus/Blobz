import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Colours here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colours extends Material
{
    public FollowPoint guide;
    protected int colour, label;
    private boolean firstRun = true;
    
    public Colours(int label, FollowPoint guide){
        this.label = label;
        this.guide = guide;
        if (label == 1){
            getImage().scale(26, 26);
        } else {
            getImage().scale(38, 38);
        }
    }
    
    public void act() {
        if(label == -1)
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
