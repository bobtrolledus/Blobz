import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square extends Shapes
{
    public Square (int q, FollowPoint guide, int colour, int scale){
        super(q, guide, colour, scale);
        
        setImage("Shapes/Square/grey.png");
        
        red = new GreenfootImage("Shapes/Square/red.png");
        blue = new GreenfootImage("Shapes/Square/blue.png");
        yellow = new GreenfootImage("Shapes/Square/yellow.png");
        orange = new GreenfootImage("Shapes/Square/orange.png");
        purple = new GreenfootImage("Shapes/Square/purple.png");
        green = new GreenfootImage("Shapes/Square/green.png");
        
        getImage().scale(scale, scale);
        setColour(colour);
        setRotation(q);
    }
}
