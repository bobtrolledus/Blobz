import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RotationPoint here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class RotationPoint extends UtilsBlocks
{
    private int degreeOfMovement;
    
    public RotationPoint(int degree)
    {
        degreeOfMovement = degree;
        getImage().scale(1, 1);
    }
    
    public int getRotation()
    {
        return degreeOfMovement;
    }
}
