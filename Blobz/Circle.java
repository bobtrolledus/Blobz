import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Circle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Circle extends Shapes
{
    public Circle (Boolean isLabel, int q, FollowPoint guide){
        super(isLabel, q, guide);
        getImage().scale(15, 15);
        setRotation(q);
    }
}
