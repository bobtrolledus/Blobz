import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Belts here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Belts extends NarrowMachines
{
    private RotationPoint point1, point2;
    
    public Belts()
    {
        setImage("images/Machines/ConveyorBelt.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public Belts(boolean spawner)
    {
        setImage("images/Machines/ConveyorBelt.png");
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
            if(!spawned && spawner && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("1")))
            {
                Belts mouseBelt = new Belts(false);
                getWorld().addObject(mouseBelt, Utils.getMouseX(), Utils.getMouseY());
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
            if(isDeletedNarrow())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    public boolean checkDeselectKey()
    {
        if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8"))
        {
            return true;
        } else {
            return false;
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
