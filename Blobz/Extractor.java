import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Extractor here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Extractor extends NarrowMachines
{
    private int spawnXCoord, spawnYCoord;
    
    private int[] corners = {3, -1, 2, -1, 2, 1, 3, 1};
    private int[] colours = {5, -1, 3, -1, 4, 6, 2, 6};
    
    private FollowPoint point;
    
    public Extractor()
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public Extractor(boolean spawner)
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
                Extractor mouseExtractor = new Extractor(false);
                getWorld().addObject(mouseExtractor, Utils.getMouseX(), Utils.getMouseY());
            }
            
            if(!spawner)
            {
                followMouse(1);
                gridSnap(this.getImage(), 1);
                updateRotation();
                /*
                if(Utils.getSpace(gridPositionX, gridPositionY) != null)
                {
                    if(Utils.getSpace(gridPositionX, gridPositionY).getClass() == Deposits.class)
                    {
                        place(Extractor.class);
                    }
                }
                */
                place(Extractor.class, 1);
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
        
        if(real) {
            spawnShape();
        }
    }
    
    public void getShape()
    {
        
    }
    
    public void spawnShape()
    {
        point = (FollowPoint)getOneIntersectingObject(FollowPoint.class);
        
        if(timer.millisElapsed() > Utils.getExtractorDelay() && point.checkIfLabel())
        {
            getWorld().addObject(new ShapeGenerator(corners, colours, direction, false), spawnXCoord, spawnYCoord);
            timer.mark();
        }
    }
    
    protected void addedToWorld(World world)
    {
        getShape();
        timer.mark();
        if(real)
        {
            switch (Utils.getDirection())
            {
                case 0:
                    spawnXCoord = getX();
                    spawnYCoord = getY() + 21;
                    direction = 0;
                    setRotation(180);
                    break;
                case 1:
                    spawnXCoord = getX() - 20;
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
