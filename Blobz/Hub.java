import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
/**
 * Write a description of class Hub here.
 *
 * @author Anson/Eric
 * @version (a version number or a date)
 */
public class Hub extends Actor
{
    private int[] outputShape = new int[8];
    private int[] outputColour = new int[8];
    /**
     * Act - do whatever the Hub wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Hub()
    {
        setImage("images/hub.png");
        getImage().scale(Utils.gridSize * 4 + 20, Utils.gridSize * 4 + 20);
    }

    public void act()
    {
        collecting();
        updateImage();
    }

    public void collecting()
    {
        for(FollowPoint point : getIntersectingObjects(FollowPoint.class))
        {
            if(Arrays.equals(point.getShape(), Utils.getTargetShape()))
            {
                if(!point.checkIfLabel())
                {
                    Utils.addTargetShape();
                }
            }
            if(!point.checkIfLabel())
            {
                getWorld().removeObject(point);
            }
        }
    }
    
    public void setTargetShape()
    {
        outputShape = Utils.getTargetShape();
        outputColour = Utils.getTargetShapeColour();
    }
    
    public void updateImage()
    {
        if(Utils.getTargetShape() != outputShape)
        {
            setTargetShape();
            FollowPoint tempPoint = getWorld().getObjectsAt(600, 420, FollowPoint.class).get(0);
            getWorld().removeObject(tempPoint);
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, 1, true), 600, 420);
        }
    }
    
    protected void addedToWorld(World world)
    {
        setTargetShape();
        getWorld().addObject(new ShapeGenerator(outputShape, outputColour, 1, true), 600, 420);
    }
}
