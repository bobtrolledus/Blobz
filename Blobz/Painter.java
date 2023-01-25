import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import greenfoot.GreenfootSound;

/**
 * Uses arraylist manipualtion to change colour of shapes
 * 
 * @author Anson
 * @version Jan 24, 2023
 */
public class Painter extends WideMachines
{
    private int spawnX1Coord, spawnY1Coord, inputX1Coord, inputY1Coord, inputX2Coord, inputY2Coord;
    private int colour;
    private boolean grabbedShape, grabbedColour;
    public Painter()
    {
        setImage("images/Machines/painter.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
        placeSound = new GreenfootSound("painter.wav");
        placeSound.setVolume(80);
        setEffect(placeSound);
    }
    
    public Painter(boolean spawner)
    {
        setImage("images/Machines/painter.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        this.spawner = spawner;
        placeSound = new GreenfootSound("painter.wav");
        placeSound.setVolume(80);
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
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("7")))
            {
                Painter mousePainter = new Painter(false);
                getWorld().addObject(mousePainter, Utils.getMouseX(), Utils.getMouseY());
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
                gridSnap(this.getImage(), 2);
                updateRotation();
                place(Painter.class, 2);
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
                if(!grabbedShape)
                {
                    getShape();
                    getColourShape();
                }
                if(!grabbedColour)
                {
                    getColourPigment();
                }
            }

            if(grabbedShape && grabbedColour)
            {
                spawnShape();
            }
            if(isDeletedWide())
            {
                getWorld().removeObject(this);
            }
            
            placeSound.setVolume(80 * Utils.soundLevel());
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
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("8") || Greenfoot.isKeyDown("9"))
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
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
            value = tempPoint.getValue();
            grabbedShape = true;
        }
    }
    
    /**
     * Gets colour arraylist of follow point from shape
     */
    public void getColourShape()
    {
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            outputColour = tempPoint.getColour();
            getWorld().removeObject(tempPoint);
        }
    }
    
    public void getColourPigment()
    {
        if(getWorld().getObjectsAt(inputX2Coord, inputY2Coord, Colours.class).size() > 0)
        {
            Colours temp = (Colours) getWorld().getObjectsAt(inputX2Coord, inputY2Coord, Colours.class).get(0);
            if(temp.getX() == inputX2Coord && temp.getY() == inputY2Coord && outputColour != null)
            {
                FollowPoint tempPoint = getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).get(0);
                colour = tempPoint.getRawColour();
                for(int i = 0; i < outputColour.size(); i++)
                {
                    if(outputColour.get(i) == -1 && outputShape.get(i) != -1)
                    {
                        outputColour.set(i, colour);
                    }
                    else if(outputShape.get(i) != -1 && outputColour.get(i) != colour && (outputColour.get(i) != 3 && outputColour.get(i) != 5 && outputColour.get(i) != 6))
                    {
                        outputColour.set(i, outputColour.get(i) + colour);
                    }
                }
                grabbedColour = true;
                getWorld().removeObject(tempPoint);
            }
        }
    }
    
    /**
     * Spawns in shape with edited colour and shape arraylists with delay
     */
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getPainterDelay() && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shape.class).size() < 1)
        {
            value++;
            value = value * 1.2;
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, -1, value), spawnX1Coord, spawnY1Coord);
            outputShape = null; 
            outputColour = null;
            grabbedShape = false;
            grabbedColour = false;
            timer.mark();
            occupied = false;
            playEffect();
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
            playPlace();
            switch (Utils.getDirection())
            {
                case 0:
                    spawnX1Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() - (Utils.gridSize / 2);
                    inputX2Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    inputY2Coord = getY() - (Utils.gridSize / 2);
                    setDirection(0);
                    setRotation(180);
                    break;
                case 1:
                    spawnX1Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY1Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    inputX1Coord = getX() + (Utils.gridSize / 2) - 1;
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord; 
                    inputY2Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    setDirection(1);
                    setRotation(-90);
                    break;
                case 2:
                    spawnX1Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() - (Utils.gridSize / 2) - 1;
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() + (Utils.gridSize / 2) - 1;
                    inputX2Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    inputY2Coord = getY() + (Utils.gridSize / 2) - 1;
                    setDirection(2);
                    setRotation(0);
                    break;
                case 3:
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    inputX1Coord = getX() - (Utils.gridSize / 2);
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord;
                    inputY2Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    setDirection(3);
                    setRotation(90);
                    break;
            }
        }
    }
}
