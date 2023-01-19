import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Belts here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Belts extends Machines
{
    private boolean spawner = false, real = false, updatedImage = false;
    private int lastRotation;
    private RotationPoint point1, point2;
    
    public Belts()
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public Belts(boolean spawner)
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
        this.spawner = spawner;
    }
        
    /**
     * Act - do whatever the Belts wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!real)
        {
            if(spawner && Greenfoot.mouseClicked(this))
            {
                Belts mouseBelt = new Belts(false);
                getWorld().addObject(mouseBelt, Utils.getMouseX(), Utils.getMouseY());
            }
            
            if(!spawner)
            {
                followMouse(1);
                gridSnap(this.getImage(), 1);
                updateRotation();
                place(Belts.class, 1);
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
    
    public void deletePoints()
    {
        getWorld().removeObject(point1);
        getWorld().removeObject(point2);
    }
    
    protected void addedToWorld(World world)
    {
        if(real)
        {
            switch (Utils.getDirection())
            {
                case 0:
                    point1 = new RotationPoint(0);
                    point2 = new RotationPoint(0);
                    world.addObject(point1, getX(), getY() - 20);
                    world.addObject(point2, getX(), getY());
                    setRotation(180);
                    break;
                case 1:
                    point1 = new RotationPoint(1);
                    point2 = new RotationPoint(1);
                    world.addObject(point1, getX() + 20, getY());
                    world.addObject(point2, getX(), getY());
                    setRotation(-90);
                    break;
                case 2:
                    point1 = new RotationPoint(2);
                    point2 = new RotationPoint(2);
                    world.addObject(point1, getX(), getY() + 20);
                    world.addObject(point2, getX(), getY());
                    setRotation(0);
                    break;
                case 3:
                    point1 = new RotationPoint(3);
                    point2 = new RotationPoint(3);
                    world.addObject(point1, getX() - 20, getY());
                    world.addObject(point2, getX(), getY());
                    setRotation(90);
                    break;
            }
        }
    }
    
    public Boolean getReal(){
        return real;
    }
}
