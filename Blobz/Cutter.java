import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Uses arraylist manipulation to cut shapes vertically in half
 * 
 * @author Anson 
 * @version Jan 24, 2023
 */
public class Cutter extends WideMachines
{
    private int inputXCoord, inputYCoord, spawnX1Coord, spawnY1Coord, spawnX2Coord, spawnY2Coord;
    private ArrayList<Integer> cut1 = new ArrayList<Integer>(8);
    private ArrayList<Integer> cut2 = new ArrayList<Integer>(8);
    private ArrayList<Integer> cutColour1 = new ArrayList<Integer>(8);
    private ArrayList<Integer> cutColour2 = new ArrayList<Integer>(8);  
    public Cutter()
    {
        setImage("images/Machines/cutter.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        for(int i = 0; i < 8; i++)
            {
                outputShape.add(0);
            }
        for(int i = 0; i < 8; i++)
        {
            outputColour.add(0);
        }
        for(int i = 0; i < 8; i++)
        {
            cut1.add(0);
        }
        for(int i = 0; i < 8; i++)
        {
            cut2.add(0);
        }
        for(int i = 0; i < 8; i++)
        {
            cutColour1.add(0);
        }
        for(int i = 0; i < 8; i++)
        {
            cutColour2.add(0);
        }
        real = true;
        placeSound = new GreenfootSound("cutter.wav");
        placeSound.setVolume(90);
        setEffect(placeSound);
    }
    
    public Cutter(boolean spawner)
    {
        setImage("images/Machines/cutter.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        this.spawner = spawner;
        placeSound = new GreenfootSound("cutter.wav");
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
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("2")))
            {
                Cutter mouseCutter = new Cutter(false);
                getWorld().addObject(mouseCutter, Utils.getMouseX(), Utils.getMouseY());
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
                followMouse(2);
                gridSnap(this.getImage(), 2);
                updateRotation();
                place(Cutter.class, 2);
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
                if(getWorld().getObjectsAt(inputXCoord, inputYCoord, Shape.class).size() > 0)
                {
                    getShape();
                    getColour();
                }
            }
            
            if(outputShape != null)
            {
                spawnShape();
            }
            
            if(isDeletedWide())
            {
                getWorld().removeObject(this);
            }
            
            placeSound.setVolume(90 * Utils.soundLevel());
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
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8") || Greenfoot.isKeyDown("9"))
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
            for(int i = 0; i < cut1.size(); i++)
            {
                cut1.set(i, -1);
                cut2.set(i, -1);
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
            for(int i = 0; i < cutColour1.size(); i++)
            {
                cutColour1.set(i, -1);
                cutColour2.set(i, -1);
            }
            occupied = true;
        }
    }
    
    /**
     * Spawns in shape with edited colour and shape arraylists with delay
     */
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getCutterDelay() && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shape.class).size() < 1 && getWorld().getObjectsAt(spawnX2Coord, spawnY2Coord, Shape.class).size() < 1)
        {
            cut1.set(2, outputShape.get(2));
            cut1.set(3, outputShape.get(3));
            cut1.set(6, outputShape.get(6));
            cut1.set(7, outputShape.get(7));
            cut2.set(0, outputShape.get(0));
            cut2.set(1, outputShape.get(1));
            cut2.set(4, outputShape.get(4));
            cut2.set(5, outputShape.get(5));
            cutColour1.set(2, outputColour.get(2));
            cutColour1.set(3, outputColour.get(3));
            cutColour1.set(6, outputColour.get(6));
            cutColour1.set(7, outputColour.get(7));
            cutColour2.set(0, outputColour.get(0));
            cutColour2.set(1, outputColour.get(1));
            cutColour2.set(4, outputColour.get(4));
            cutColour2.set(5, outputColour.get(5));
            value = value * 1.2;
            getWorld().addObject(new ShapeGenerator(cut1, cutColour1, direction, -1, value), spawnX1Coord, spawnY1Coord);
            getWorld().addObject(new ShapeGenerator(cut2, cutColour2, direction, -1, value), spawnX2Coord, spawnY2Coord);
            outputShape.clear();
            outputColour.clear();
            cut1.clear();
            cut2.clear();
            cutColour1.clear();
            cutColour2.clear();
            for(int i = 0; i < 8; i++)
            {
                outputShape.add(0);
            }
            for(int i = 0; i < 8; i++)
            {
                outputColour.add(0);
            }
            for(int i = 0; i < 8; i++)
            {
                cut1.add(0);
            }
            for(int i = 0; i < 8; i++)
            {
                cut2.add(0);
            }
            for(int i = 0; i < 8; i++)
            {
                cutColour1.add(0);
            }
            for(int i = 0; i < 8; i++)
            {
                cutColour2.add(0);
            }
            outputShape = null;
            occupied = false;
            playEffect();
            timer.mark();
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
                    inputXCoord = getX() + ((Utils.gridSize / 2) * mirrored);
                    inputYCoord = getY() - (Utils.gridSize / 2);
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() + (Utils.gridSize / 2);
                    setDirection(0);
                    setRotation(180);
                    break;
                case 1:
                    inputXCoord = getX() + (Utils.gridSize / 2) - 1;
                    inputYCoord = getY() + ((Utils.gridSize / 2) * mirrored);
                    spawnX1Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY1Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    spawnX2Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY2Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    setDirection(1);
                    setRotation(-90);
                    break;
                case 2:
                    inputXCoord = getX() - ((Utils.gridSize / 2) * mirrored);
                    inputYCoord = getY() + (Utils.gridSize / 2) - 1;
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() - (Utils.gridSize / 2) - 1;
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() - (Utils.gridSize / 2) - 1;
                    setDirection(2);
                    setRotation(0);
                    break;
                case 3:
                    inputXCoord = getX() - (Utils.gridSize / 2);
                    inputYCoord = getY() - ((Utils.gridSize / 2) * mirrored);
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    setDirection(3);
                    setRotation(90);
                    break;
            }
        }
    }
}
