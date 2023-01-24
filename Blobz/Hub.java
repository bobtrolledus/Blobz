import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of class Hub here.
 *
 * @author Anson/Eric/Andy
 * @version (a version number or a date)
 */
public class Hub extends Machines
{
    private ArrayList<Integer> outputShape;
    private ArrayList<Integer> outputColour;
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
            if(point.getShape().equals(Utils.getTargetShape()))
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
    
    public void fillGrid()
    {
        Utils.fillSpaceMachine(8, 8, this);
        Utils.fillSpaceMachine(8, 9, this);
        Utils.fillSpaceMachine(8, 10, this);
        Utils.fillSpaceMachine(8, 11, this);
        Utils.fillSpaceMachine(9, 8, this);
        Utils.fillSpaceMachine(9, 9, this);
        Utils.fillSpaceMachine(9, 10, this);
        Utils.fillSpaceMachine(9, 11, this);
        Utils.fillSpaceMachine(10, 8, this);
        Utils.fillSpaceMachine(10, 9, this);
        Utils.fillSpaceMachine(10, 10, this);
        Utils.fillSpaceMachine(10, 11, this);
        Utils.fillSpaceMachine(11, 8, this);
        Utils.fillSpaceMachine(11, 9, this);
        Utils.fillSpaceMachine(11, 10, this);
        Utils.fillSpaceMachine(11, 11, this);
    }
    
    public void setTargetShape()
    {
        outputShape = new ArrayList<Integer>(Arrays.asList(Utils.getTargetShape()));
        outputColour = new ArrayList<Integer>(Arrays.asList(Utils.getTargetShapeColour()));
    }
    
    public void updateImage()
    {
        if(Arrays.asList(Utils.getTargetShape()) != outputShape)
        {
            setTargetShape();
            FollowPoint tempPoint = getWorld().getObjectsAt(600, 420, FollowPoint.class).get(0);
            getWorld().removeObject(tempPoint);
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, 0, true), 600, 420);
        }
    }
    
    protected void addedToWorld(World world)
    {
        setTargetShape();
        fillGrid();
        getWorld().addObject(new ShapeGenerator(outputShape, outputColour, 0, true), 600, 420);
    }
}
