import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Extractor here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Extractor extends Machines
{
    private boolean spawner = false, real = false, updatedImage = false;
    private int lastRotation;
    private int spawnXCoord, spawnYCoord;
    private SimpleTimer timer = new SimpleTimer();
    private Shapes shape;
    
    private int[] corners = {1, 2, 3, 2, -1, -1, -1, -1};
    
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
                followMouse();
                gridSnap(this.getImage());
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
                place(Extractor.class);
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
        
        if (real) {
            spawnShape();
        }
    }
    
    public void updateRotation()
    {
        if(Utils.getDirection() != lastRotation)
        {
            updatedImage = false;
        }
    }
    
    public void getShape()
    {
        
    }
    
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getExtractorDelay())
        {
            getWorld().addObject(new ShapeGenerator(corners, this), spawnXCoord, spawnYCoord);
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
                    spawnYCoord = getY() + 20;
                    setRotation(180);
                    break;
                case 1:
                    spawnXCoord = getX() - 20;
                    spawnYCoord = getY();
                    setRotation(-90);
                    break;
                case 2:
                    spawnXCoord = getX();
                    spawnYCoord = getY() - 20;
                    setRotation(0);
                    break;
                case 3:
                    spawnXCoord = getX() + 20;
                    spawnYCoord = getY() - 20;
                    setRotation(90);
                    break;
            }
        }
    }
    
    public int getSpawnXCoord(){
        return spawnXCoord;
    }
    
    public int getSpawnYCoord(){
        return spawnYCoord;
    }
}
