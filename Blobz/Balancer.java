import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Machine to split 1 stream of materials into 2
 * 
 * @author Anson 
 * @version Jan 24, 2023
 */
public class Balancer extends WideMachines
{
    private boolean outputSide = false, inputSide = false, isRawColour;
    private int spawnX1Coord, spawnY1Coord, spawnX2Coord, spawnY2Coord, inputXCoord, inputYCoord, colour;
    public Balancer()
    {
        setImage("images/Machines/balancer.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
        placeSound = new GreenfootSound("balancer.wav");
        placeSound.setVolume(90);
        setEffect(placeSound);
    }
    
    public Balancer(boolean spawner)
    {
        setImage("images/Machines/balancer.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        this.spawner = spawner;
        placeSound = new GreenfootSound("balancer.wav");
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
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("4")))
            {
                Balancer mouseBalancer = new Balancer(false);
                getWorld().addObject(mouseBalancer, Utils.getMouseX(), Utils.getMouseY());
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
                place(Balancer.class, 2);
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
                if(!isRawColour)
                {
                    getColour();
                }
            }
            
            spawnShape();
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
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8") || Greenfoot.isKeyDown("9"))
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
     * Gets shape arraylist at input coordinates 
     */
    public void getShape()
    {  
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            if(getWorld().getObjectsAt(inputXCoord, inputYCoord, Colours.class).size() > 0)
            {
                FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
                colour = tempPoint.getRawColour();
                getWorld().removeObject(tempPoint);
                isRawColour = true;
            }
            if(getWorld().getObjectsAt(inputXCoord, inputYCoord, Shapes.class).size() > 0)
            {
                FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
                outputShape = tempPoint.getShape();
                direction = tempPoint.getRotation();
                isRawColour = false;
            }
        }
    }
    
    /**
     * Gets colour arrylist at input coordinates
     */
    public void getColour()
    {
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputColour = tempPoint.getColour();
            getWorld().removeObject(tempPoint);
            occupied = true;
        }
    }
    
    /**
     * Spawns shape at either output according to boolean
     */
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getBalancerDelay())
        {
            if(!outputSide && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shapes.class).size() < 1)
            {
                if(isRawColour)
                {
                    getWorld().addObject(new ShapeGenerator(colour, direction, -1), spawnX1Coord, spawnY1Coord);
                }
                if(!isRawColour && outputShape != null)
                {
                    getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, -1), spawnX1Coord, spawnY1Coord);
                }
                outputShape = null;
                outputSide = true;
                occupied = false;
                playEffect();
                timer.mark();
            }
            else if(outputSide && getWorld().getObjectsAt(spawnX2Coord, spawnY2Coord, Shapes.class).size() < 1)
            {
                if(isRawColour)
                {
                    getWorld().addObject(new ShapeGenerator(colour, direction, -1), spawnX2Coord, spawnY2Coord);
                }
                if(!isRawColour && outputShape != null)
                {
                    getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, -1), spawnX2Coord, spawnY2Coord);
                }
                outputShape = null;
                outputSide = false;
                occupied = false;
                playEffect();
                timer.mark();
            }
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
                    spawnX1Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    spawnX2Coord = getX() + ((Utils.gridSize / 2) * mirrored);
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
                    spawnX1Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() - (Utils.gridSize / 2) - 1;
                    spawnX2Coord = getX() + ((Utils.gridSize / 2) * mirrored);
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
