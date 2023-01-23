import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Balancer here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class Balancer extends WideMachines
{
    private boolean outputSide = false, inputSide = false;
    private int spawnX1Coord, spawnY1Coord, spawnX2Coord, spawnY2Coord, inputXCoord, inputYCoord;
    public Balancer()
    {
        setImage("images/Machines/balancer.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
    }
    
    public Balancer(boolean spawner)
    {
        setImage("images/Machines/balancer.png");
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
            if(!spawned && spawner && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("4")))
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
            if(isDeletedWide())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    public boolean checkDeselectKey()
    {
        if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8"))
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
        }
    }
    
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
    
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getBalancerDelay())
        {
            if(!outputSide && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shapes.class).size() < 1)
            {
                getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, false), spawnX1Coord, spawnY1Coord);
                outputShape = null;
                outputSide = true;
                occupied = false;
                timer.mark();
            }
            else if(outputSide && getWorld().getObjectsAt(spawnX2Coord, spawnY2Coord, Shapes.class).size() < 1)
            {
                getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, false), spawnX2Coord, spawnY2Coord);
                outputShape = null;
                outputSide = false;
                occupied = false;
                timer.mark();
            }
        }
    }
    
    protected void addedToWorld(World world)
    {
        checkMirrored();
        timer.mark();
        if(real)
        {
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
                    inputXCoord = getX() + (Utils.gridSize / 2);
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
