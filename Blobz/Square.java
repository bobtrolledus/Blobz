import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square extends Shapes
{
    public Square (int q, FollowPoint guide, int layer, int colour){
        super(q, guide, layer, colour);
        layerScale();
        setRotation(q);
    }
}
