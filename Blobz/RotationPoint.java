import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A point that is spawned in by the belt for followpoints to change direction
 * 
 * @author Anson 
 * @version Jan 24, 2023
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
