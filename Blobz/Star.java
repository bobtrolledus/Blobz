import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Star here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Star extends Shapes
{
    public Star (int q, FollowPoint guide, int layer, int colour){
        super(q, guide, layer, colour);
        
        setImage("Shapes/Star/grey.png");
        
        red = new GreenfootImage("Shapes/Star/red.png");
        blue = new GreenfootImage("Shapes/Star/blue.png");
        yellow = new GreenfootImage("Shapes/Star/yellow.png");
        orange = new GreenfootImage("Shapes/Star/orange.png");
        purple = new GreenfootImage("Shapes/Star/purple.png");
        green = new GreenfootImage("Shapes/Star/green.png");
        
        setColour(colour);
        layerScale();
        setRotation(q);
    }
}
