import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Circle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Circle extends Shapes
{   
    public Circle (int q, FollowPoint guide, int layer, int colour){
        super(q, guide, layer, colour);
        
        setImage("Shapes/Circle/grey.png");
        
        red = new GreenfootImage("Shapes/Circle/red.png");
        blue = new GreenfootImage("Shapes/Circle/blue.png");
        yellow = new GreenfootImage("Shapes/Circle/yellow.png");
        orange = new GreenfootImage("Shapes/Circle/orange.png");
        purple = new GreenfootImage("Shapes/Circle/purple.png");
        green = new GreenfootImage("Shapes/Circle/green.png");
        
        setColour(colour);
        layerScale();
        setRotation(q);
    }
}
