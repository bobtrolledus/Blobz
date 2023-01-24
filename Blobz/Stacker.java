import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Stacker here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Stacker extends WideMachines
{
    private int spawnX1Coord, spawnY1Coord, inputX1Coord, inputX2Coord, inputY1Coord, inputY2Coord;
    private ArrayList<Integer> shapeID1, shapeID2, colourID1, colourID2;
    private boolean grabbedShape1, grabbedShape2;
    public Stacker()
    {
        setImage("images/Machines/stacker.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        for(int i = 0; i < 8; i++)
        {
            outputShape.add(-1);
        }
        for(int i = 0; i < 8; i++)
        {
            outputColour.add(-1);
        }
        real = true;
    }
    
    public Stacker(boolean spawner)
    {
        setImage("images/Machines/stacker.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
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
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("8")))
            {
                Stacker mouseStacker = new Stacker(false);
                getWorld().addObject(mouseStacker, Utils.getMouseX(), Utils.getMouseY());
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
                followMouse(2);
                gridSnap(this.getImage(), 2);
                updateRotation();
                place(Stacker.class, 2);
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
                if(!grabbedShape1 || !grabbedShape2)
                {
                    getShape();   
                }
            }

            if(grabbedShape1 && grabbedShape2)
            {
                spawnShape();
            }
            if(isDeletedWide())
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
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("9"))
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
     * Gets shapeID and colourID arraylists from inputted follow points and concatenates them together
     */
    public void getShape()
    {
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            shapeID1 = tempPoint.getShape();
            if((shapeID1.get(4) != -1 || shapeID1.get(5) != -1 || shapeID1.get(6) != -1 || shapeID1.get(7) != -1))
            {
                shapeID1 = null;
            } else {
                if(!grabbedShape1)
                {
                    colourID1 = tempPoint.getColour();
                    grabbedShape1 = true;
                    getWorld().removeObject(tempPoint);
                }
            }
        }
        if(getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).get(0);
            shapeID2 = tempPoint.getShape();
            if((shapeID2.get(4) != -1 || shapeID2.get(5) != -1 || shapeID2.get(6) != -1 || shapeID2.get(7) != -1))
            {
                shapeID2 = null;
            } else {
                if(!grabbedShape2)
                {
                    colourID2 = tempPoint.getColour();
                    grabbedShape2 = true;
                    getWorld().removeObject(tempPoint);
                }
            }
        }
        if(grabbedShape1 && grabbedShape2 && shapeID2 != null && colourID2 != null)
        {
            for(int i = 0; i < 4; i++)
            {
                outputShape.set(i, shapeID1.get(i));
                outputColour.set(i, colourID1.get(i));
            }
            for(int i = 4; i < 8;  i++)
            {
                if(outputShape.get(i - 4) == -1)
                {
                    outputShape.set(i - 4, shapeID2.get(i - 4));
                    outputColour.set(i - 4, colourID2.get(i - 4));
                } else {
                    outputShape.set(i, shapeID2.get(i - 4));
                    outputColour.set(i, colourID2.get(i - 4));
                }
            }
            occupied = true;
        }
    }
    
    /**
     * Spawns in shape with edited colour and shape arraylists with delay
     */
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getStackingDelay() && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shapes.class).size() < 1)
        {
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, -1), spawnX1Coord, spawnY1Coord);
            shapeID1 = null;
            shapeID2 = null;
            colourID1 = null;
            colourID2 = null;
            outputShape.clear();
            outputColour.clear();
            for(int i = 0; i < 8; i++)
            {
                outputShape.add(-1);
            }
            for(int i = 0; i < 8; i++)
            {
                outputColour.add(-1);
            }
            grabbedShape1 = false;
            grabbedShape2 = false;
            timer.mark();
            occupied = false;
        }
    }
    
    /**
     * Sets input and output coordinates, image direction and spawn direction
     */
    protected void addedToWorld(World world)
    {
        checkMirrored();
        timer.mark();
        if(real)
        {
            switch (Utils.getDirection())
            {
                case 0:
                    spawnX1Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() - (Utils.gridSize / 2);
                    inputX2Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    inputY2Coord = getY() - (Utils.gridSize / 2);
                    setDirection(0);
                    setRotation(180);
                    break;
                case 1:
                    spawnX1Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY1Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    inputX1Coord = getX() + (Utils.gridSize / 2) - 1;
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord;
                    inputY2Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    setDirection(1);
                    setRotation(-90);
                    break;
                case 2:
                    spawnX1Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() - (Utils.gridSize / 2) - 1;
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() + (Utils.gridSize / 2) - 1;
                    inputX2Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    inputY2Coord = getY() + (Utils.gridSize / 2) - 1;
                    setDirection(2);
                    setRotation(0);
                    break;
                case 3:
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    inputX1Coord = getX() - (Utils.gridSize / 2);
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord;
                    inputY2Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    setDirection(3);
                    setRotation(90);
                    break;
            }
        }
    }
}
