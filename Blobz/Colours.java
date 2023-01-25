import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Colours here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colours extends Material
{
    protected int colour, label;
    
    public Colours(FollowPoint guide){
        super(guide);
        this.label = label;
        
        if (label == 1){
            getImage().scale(26, 26);
        } else {
            getImage().scale(34, 34);
        }
    }
}
