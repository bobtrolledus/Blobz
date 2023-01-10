import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Belts here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Belts extends Machines
{
    private boolean spawner = false, real = false;
    
    public Belts()
    {
        getImage().scale(45, 45);
        real = true;
    }
    
    public Belts(boolean spawner)
    {
        getImage().scale(45, 45);
        this.spawner = spawner;
    }
        
    /**
     * Act - do whatever the Belts wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mouse = Greenfoot.getMouseInfo();
        if(!real)
        {
            if(spawner && Greenfoot.mouseClicked(this))
            {
                Belts mouseBelt = new Belts(false);
                getWorld().addObject(mouseBelt, mouse.getX(), mouse.getY());
            }
            
            if(!spawner)
            {
                followMouse();
                gridSnap();
                place(Belts.class);
                if(Greenfoot.isKeyDown("escape"))
                {
                    getWorld().removeObject(block);
                    getWorld().removeObject(this);
                }
            }
        }
    }
}
