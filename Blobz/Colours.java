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
    private FollowPoint guide;
    protected int colour;
    private boolean firstRun = true;
    
    public Colours(boolean isLabel){
        this.isLabel = isLabel;
        
        if (isLabel){
            getImage().scale(26, 26);
        } else {
            getImage().scale(38, 38);
        }
    }
    
    public void act() {
        if(firstRun){
            spawnPoint();
            firstRun = false;
        }
        
        if (guide.getWorld() == null){
            getWorld().removeObject(this);
        } else {
            setLocation(guide.getX(), guide.getY());
        }
    }
    
    public int getColour(){
        return colour;
    }
    
    public void spawnPoint(){
        if(isLabel){
            guide = new FollowPoint(0, true);
        } else {
            guide = new FollowPoint(0, false);
        }

        getWorld().addObject(guide, this.getX(), this.getY());
    }
}
