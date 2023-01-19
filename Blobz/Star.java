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
        layerScale();
        setRotation(q);
    }
}
