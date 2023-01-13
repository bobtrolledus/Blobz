import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RotateRight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RotateRight extends Machines
{
    private boolean spawner = false, real = false, updatedImage = false;
    private int lastRotation;
    private int spawnXCoord, spawnYCoord;
    private SimpleTimer timer = new SimpleTimer();
    private Shapes shape;
    public RotateRight()
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public RotateRight(boolean spawner)
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
                RotateRight mouseRotateRight = new RotateRight(false);
                getWorld().addObject(mouseRotateRight, Utils.getMouseX(), Utils.getMouseY());
            }
            
            if(!spawner)
            {
                followMouse(1);
                gridSnap(this.getImage(), 1);
                updateRotation();
                place(RotateRight.class, 1);
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
}
