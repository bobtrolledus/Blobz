import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Uses arraylist manipulation to rotate shapes counter clockwise
 * 
 * @author Anson 
 * @version Jan 24, 2023
 */
public class RotateLeft extends NarrowMachines
{
    private boolean occupied = false, grabbedShape = false;
    private int direction;
    private int inputXCoord, inputYCoord, spawnXCoord, spawnYCoord;

    public RotateLeft()
    {
        setImage("images/Machines/rotateLeft.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        for(int i = 0; i < 8; i++)
        {
            outputShape.add(0);
        }
        for(int i = 0; i < 8; i++)
        {
            outputColour.add(0);
        } 
        real = true;
        placeSound = new GreenfootSound("ccw.wav");
        placeSound.setVolume(90);
        setEffect(placeSound);
    }
    
    public RotateLeft(boolean spawner)
    {
        setImage("images/Machines/rotateLeft.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        this.spawner = spawner;
        placeSound = new GreenfootSound("ccw.wav");
        placeSound.setVolume(90);
        setEffect(placeSound);
    }
    
    /**
     * Act - do whatever the Extractor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //Checks if machine is a placed machine
        if(!real)
        {
            //Creates ghost block
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("6")))
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
            //Updates ghost block position based on mouse and checks if ghost block is clicked
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
                //Checks if machine has been deselected
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
        //Checks if a machine is a placed machine
        if(real)
        {
            if(!occupied)
            {
                getShape();
                getColour();
            }

            if(grabbedShape)
            {
                spawnShape();
            }
            if(isDeletedNarrow())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    /**
     * Returns boolean true if another hotkey is pressed
     * 
     * @return boolean
     */
    public boolean checkDeselectKey()
    {
        if(Utils.getMouse() != null)
        {
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8"))
            {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Gets shape arraylist from inputted follow point and edits
     */
    public void getShape()
    {       
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
            value = tempPoint.getValue();
            int i = 3, j =0;
            while(i != j)
            {
              int temp = outputShape.get(i);
              outputShape.set(i, outputShape.get(j));
              outputShape.set(j, temp);
              i--;
            }
            i = outputShape.size() - 1;
            j = 4;
            while(i != j)
            {
              int temp = outputShape.get(i);
              outputShape.set(i, outputShape.get(j));
              outputShape.set(j, temp);
              i--;
            }
        }
    }
    
    /**
     * Gets colour arraylist of follow point and edits 
     */
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
              int temp = outputColour.get(i);
              outputColour.set(i, outputColour.get(j));
              outputColour.set(j, temp);
              i--;
            }
            i = outputShape.size() - 1;
            j = 4;
            while(i != j)
            {
              int temp = outputColour.get(i);
              outputColour.set(i, outputColour.get(j));
              outputColour.set(j, temp);
              i--;
            }
            occupied = true;
            grabbedShape = true;
        }
    }
    
    /**
     * Spawns in shape with edited colour and shape arraylists with delay
     */
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getRotationDelay() && getWorld().getObjectsAt(spawnXCoord, spawnYCoord, Shapes.class).size() < 1) 
        {
            value = value * 2;
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, -1, value), spawnXCoord, spawnYCoord);
            outputShape.clear();
            outputColour.clear();
            playEffect();
            for(int i = 0; i < 8; i++)
            {
                outputShape.add(0);
            }
            for(int i = 0; i < 8; i++)
            {
                outputColour.add(0);
            } 
            timer.mark();
            occupied = false;
            grabbedShape = false;
        }
    }
    
    /**
     * Sets input and output coordinates, image direction and spawn direction
     */
    protected void addedToWorld(World world)
    {
        timer.mark();
        if(real)
        {
            playPlace();
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
