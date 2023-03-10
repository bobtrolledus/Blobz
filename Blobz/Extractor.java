import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Machine to spawn in shapes or colours depending on deposit which this machine is placed on
 * 
 * @author Anson
 * @version Jan 24, 2023
 */
public class Extractor extends NarrowMachines
{
    private int spawnXCoord, spawnYCoord, colour;
    private boolean isColour;
    private FollowPoint point;
    
    public Extractor()
    {
        setImage("images/Machines/extractor.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
        placeSound = new GreenfootSound("extractor.wav");
        placeSound.setVolume(85);
        setEffect(placeSound);
    }
    
    public Extractor(boolean spawner)
    {
        setImage("images/Machines/extractor.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        this.spawner = spawner;
        placeSound = new GreenfootSound("extractor.wav");
        placeSound.setVolume(85);
        placeSound = new GreenfootSound("extractor.wav");
        placeSound.setVolume(85);
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
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("3")))
            {
                Extractor mouseExtractor = new Extractor(false);
                getWorld().addObject(mouseExtractor, Utils.getMouseX(), Utils.getMouseY());
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
                place(Extractor.class, 1);
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
        if(real) {
            if(!occupied)
            {
                getShape();
            }
            
            if(outputShape != null || colour != -1)
            {
                spawnShape();
            }
            
            if(isDeletedNarrow())
            {
                getWorld().removeObject(this);
            }
            
            placeSound.setVolume(85 * Utils.soundLevel());
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
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8") || Greenfoot.isKeyDown("9"))
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
     * Gets current shape arraylist and shapeColour arraylist from deposit
     */
    public void getShape()
    {
        if(getWorld().getObjectsAt(getX(), getY(), Deposits.class).size() > 0)
        {
            Deposits temp = (Deposits) getWorld().getObjectsAt(getX(), getY(), Deposits.class).get(0);
            if(temp.isColourDeposit())
            {
                colour = temp.getRawColour();
                isColour = true;
            }
            else if (!temp.isColourDeposit())
            {
                isColour = false;
                outputShape = temp.getDepositShape();
                outputColour = temp.getDepositShapeColour();
            }  
        }
        occupied = true;
    }
    
    /**
     * Spawns in shape or colour with delay
     */
    public void spawnShape()
    {
        point = (FollowPoint)getOneIntersectingObject(FollowPoint.class);
        
        if(timer.millisElapsed() > Utils.getExtractorDelay() && getObjectsInRange(25, FollowPoint.class).size() < 2)
        {
            playEffect();
            if(isColour)
            {
                getWorld().addObject(new ShapeGenerator(colour, direction, -1), spawnXCoord, spawnYCoord);
                timer.mark();
            }
            if(!isColour)
            {
                getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, -1, 1), spawnXCoord, spawnYCoord);
                timer.mark();
            }
            outputShape = null;
            colour = -1;
            occupied = false;
        }
    }
    
    /**
     * Sets coordinates for inputs, outputs and directions
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
                    spawnXCoord = getX();
                    spawnYCoord = getY() + 20;
                    direction = 0;
                    setRotation(180);
                    break;
                case 1:
                    spawnXCoord = getX() - 21;
                    spawnYCoord = getY();
                    direction = 1;
                    setRotation(-90);
                    break;
                case 2:
                    spawnXCoord = getX();
                    spawnYCoord = getY() - 21;
                    direction = 2;
                    setRotation(0);
                    break;
                case 3:
                    spawnXCoord = getX() + 20;
                    spawnYCoord = getY();
                    direction = 3;
                    setRotation(90);
                    break;
            }
        }
    }
}
