import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
/**
 * Write a description of class RotateRight here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class RotateRight extends NarrowMachines
{
    private boolean occupied = false;
    private int direction;
    private int inputXCoord, inputYCoord, spawnXCoord, spawnYCoord;
    public RotateRight()
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public RotateRight(boolean spawner)
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
        this.spawner = spawner;
    }
    
    /**
     * Act - do whatever the Extractor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!real)
        {
            if(spawner && Greenfoot.mouseClicked(this))
            {
                RotateRight mouseRotateRight = new RotateRight(false);
                getWorld().addObject(mouseRotateRight, Utils.getMouseX(), Utils.getMouseY());
            }
            
            if(!spawner)
            {
                followMouse(1);
                gridSnap(this.getImage(), 1);
                updateRotation();
                place(RotateRight.class, 1);
                if(!updatedImage)
                {
                    lastRotation = Utils.getDirection();
                    updateImage(lastRotation);
                    updatedImage = true;
                }
                if(Greenfoot.isKeyDown("escape"))
                {
                    if(Utils.getMouseX() > 200 && Utils.getMouseY() < 1000)
                    {
                        for(Arrows arrow : getWorld().getObjects(Arrows.class))
                        {
                            getWorld().removeObject(arrow);
                        }
                    }
                    getWorld().removeObject(block);
                    getWorld().removeObject(this);
                }
            }
        }
        if(real)
        {
            if(!occupied)
            {
                getShape();
                getColour();
            }

            if(outputShape != null)
            {
                spawnShape();
            }
        }
    }
    
    public void getShape()
    {       
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
            int i = 0, j = 3;
            while(i != j)
            {
              int temp = outputShape[i];
              outputShape[i] = outputShape[j];
              outputShape[j] = temp;
              i++;
            }
            i = 4;
            j = outputShape.length - 1;
            while(i != j)
            {
              int temp = outputShape[i];
              outputShape[i] = outputShape[j];
              outputShape[j] = temp;
              i++;
            }
        }
    }
    
    public void getColour()
    {
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputColour = tempPoint.getColour();
            getWorld().removeObject(tempPoint);
            int i = 0, j = 3;
            while(i != j)
            {
              int temp = outputColour[i];
              outputColour[i] = outputColour[j];
              outputColour[j] = temp;
              i++;
            }
            i = 4;
            j = outputColour.length - 1;
            while(i != j)
            {
              int temp = outputColour[i];
              outputColour[i] = outputColour[j];
              outputColour[j] = temp;
              i++;
            }
            occupied = true;
        }
    }
    
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getRotationDelay() && getWorld().getObjectsAt(spawnXCoord, spawnYCoord, Shapes.class).size() < 1) 
        {
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, false), spawnXCoord, spawnYCoord);
            outputShape = null; 
            timer.mark();
            occupied = false;
        }
    }
    
    protected void addedToWorld(World world)
    {
        timer.mark();
        if(real)
        {
            switch (Utils.getDirection())
            {
                case 0:
                    inputXCoord = getX();
                    inputYCoord = getY() - (Utils.gridSize / 2);
                    spawnXCoord = getX();
                    spawnYCoord = getY() + (Utils.gridSize / 2);
                    direction = 0;
                    setRotation(180);
                    break;
                case 1:
                    inputXCoord = getX() + (Utils.gridSize / 2) - 1;
                    inputYCoord = getY();
                    spawnXCoord = getX() - (Utils.gridSize / 2) - 1;
                    spawnYCoord = getY();
                    direction = 1;
                    setRotation(-90);
                    break;
                case 2:
                    inputXCoord = getX();
                    inputYCoord = getY() + (Utils.gridSize / 2) - 1;
                    spawnXCoord = getX();
                    spawnYCoord = getY() - (Utils.gridSize / 2) - 1;
                    direction = 2;
                    setRotation(0);
                    
                    break;
                case 3:
                    inputXCoord = getX() - (Utils.gridSize / 2);
                    inputYCoord = getY();
                    spawnXCoord = getX() + (Utils.gridSize / 2);
                    spawnYCoord = getY();
                    direction = 3;
                    setRotation(90);
                    break;
            }
        }
    }
}
