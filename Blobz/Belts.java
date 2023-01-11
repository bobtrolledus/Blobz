import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Belts here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Belts extends Machines
{
    private boolean spawner = false, real = false, foundResource = false;
    private int inputXCoord, inputYCoord, outputXCoord, outputYCoord;
    private Resources assignedResource;
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
                followMouse();
                gridSnap();
                place(Belts.class);
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
}
