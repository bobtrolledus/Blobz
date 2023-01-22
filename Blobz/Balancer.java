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
    private int spawnX1Coord, spawnY1Coord, spawnX2Coord, spawnY2Coord, inputX1Coord, inputY1Coord, inputX2Coord, inputY2Coord;
    public Balancer()
    {
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
    }
    
    public Balancer(boolean spawner)
    {
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
            if(spawner && Greenfoot.mouseClicked(this))
            {
                Balancer mouseBalancer = new Balancer(false);
                getWorld().addObject(mouseBalancer, Utils.getMouseX(), Utils.getMouseY());
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
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
        }
        if(getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
        }
    }
    
    public void getColour()
    {
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            outputColour = tempPoint.getColour();
            getWorld().removeObject(tempPoint);
            occupied = true;
        }
        if(getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).get(0);
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
            }
            else if(outputSide && getWorld().getObjectsAt(spawnX2Coord, spawnY2Coord, Shapes.class).size() < 1)
            {
                getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, false), spawnX2Coord, spawnY2Coord);
                outputShape = null;
                outputSide = false;
            }
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
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() + (Utils.gridSize / 2);
                    inputX1Coord = spawnX1Coord;
                    inputX2Coord = spawnX2Coord;
                    inputY1Coord = getY() - (Utils.gridSize / 2);
                    inputY2Coord = getY() - (Utils.gridSize / 2);
                    setDirection(0);
                    setRotation(180);
                    break;
                case 1:
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnX2Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() - (Utils.gridSize / 2);
                    spawnY2Coord = getY() + (Utils.gridSize / 2);
                    inputX1Coord = getX() + (Utils.gridSize / 2);
                    inputX2Coord = getX() + (Utils.gridSize / 2);
                    inputY1Coord = spawnY1Coord;
                    inputY2Coord = spawnY2Coord;
                    setDirection(1);
                    setRotation(-90);
                    break;
                case 2:
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnX2Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() - (Utils.gridSize / 2);
                    spawnY2Coord = getY() - (Utils.gridSize / 2);
                    inputX1Coord = spawnX1Coord;
                    inputX2Coord = spawnX2Coord;
                    inputY1Coord = getY() + (Utils.gridSize / 2);
                    inputY2Coord = getY() + (Utils.gridSize / 2);
                    setDirection(2);
                    setRotation(0);
                    break;
                case 3:
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() - (Utils.gridSize / 2);
                    inputX1Coord = getX() - (Utils.gridSize / 2);
                    inputX2Coord = getX() - (Utils.gridSize / 2);
                    inputY1Coord = spawnY1Coord;
                    inputY2Coord = spawnY2Coord;
                    setDirection(3);
                    setRotation(90);
                    break;
            }
        }
    }
}
