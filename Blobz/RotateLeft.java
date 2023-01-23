import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
/**
 * Write a description of class RotateRight here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class RotateLeft extends NarrowMachines
{
    private boolean occupied = false;
    private int direction;
    private int inputXCoord, inputYCoord, spawnXCoord, spawnYCoord;
    public RotateLeft()
    {
        setImage("images/Machines/rotateLeft.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public RotateLeft(boolean spawner)
    {
        setImage("images/Machines/rotateLeft.png");
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
            if(!spawned && spawner && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("6")))
            {
                RotateLeft mouseRotateLeft = new RotateLeft(false);
                getWorld().addObject(mouseRotateLeft, Utils.getMouseX(), Utils.getMouseY());
                spawned = true;
            }
            if(spawner && spawned)
            {
                if(checkDeselectKey())
                {
                    spawned = false;
                }
            }
            
            if(!spawner)
            {
                followMouse(1);
                gridSnap(this.getImage(), 1);
                updateRotation();
                place(RotateLeft.class, 1);
                if(!updatedImage)
                {
                    lastRotation = Utils.getDirection();
                    updateImage(lastRotation);
                    updatedImage = true;
                }
                if(checkDeselectKey())
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
            if(isDeletedNarrow())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    public boolean checkDeselectKey()
    {
        if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8"))
        {
            return true;
        } else {
            return false;
        }
    }
    
    public void getShape()
    {       
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
            int i = 3, j =0;
            while(i != j)
            {
              int temp = outputShape[i];
              outputShape[i] = outputShape[j];
              outputShape[j] = temp;
              i--;
            }
            i = 7;
            j = 4;
            while(i != j)
            {
              int temp = outputShape[i];
              outputShape[i] = outputShape[j];
              outputShape[j] = temp;
              i--;
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
            int i = 3, j = 0;
            while(i != j)
            {
              int temp = outputColour[i];
              outputColour[i] = outputColour[j];
              outputColour[j] = temp;
              i--;
            }
            i = 7;
            j = 4;
            while(i != j)
            {
              int temp = outputColour[i];
              outputColour[i] = outputColour[j];
              outputColour[j] = temp;
              i--;
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
