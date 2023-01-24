import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RotateRight here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class TrashCan extends NarrowMachines
{
    public TrashCan()
    {
        setImage("images/Machines/trashcan.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public TrashCan(boolean spawner)
    {
        setImage("images/Machines/trashcan.png");
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
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("9")))
            {
                TrashCan mouseRotateLeft = new TrashCan(false);
                getWorld().addObject(mouseRotateLeft, Utils.getMouseX(), Utils.getMouseY());
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
                place(TrashCan.class, 1);
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
            deleteShape();
            if(isDeletedNarrow())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    public void deleteShape()
    {
        for(FollowPoint point : getIntersectingObjects(FollowPoint.class))
        {
            getWorld().removeObject(point);
        }
    }
    
    public boolean checkDeselectKey()
    {
        if(Utils.getMouse() != null)
        {
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8"))
            {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
