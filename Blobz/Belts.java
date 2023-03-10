import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Machine that creates path for shapes to follow
 * 
 * @author Anson
 * @version Jan 24, 2023
 */
public class Belts extends NarrowMachines
{
    private RotationPoint point1, point2;
    public Belts()
    {
        setImage("images/Machines/ConveyorBelt.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
        placeSound = new GreenfootSound("belt.wav");
        placeSound.setVolume(85);
        for(int i = 0; i < 20; i++){
            place[i] = placeSound;
        }
    }
    
    public Belts(boolean spawner)
    {
        setImage("images/Machines/ConveyorBelt.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        this.spawner = spawner;
        placeSound = new GreenfootSound("belt.wav");
        placeSound.setVolume(50);
        for(int i = 0; i < 20; i++){
            place[i] = placeSound;
        }
    }
       
    /**
     * Act - do whatever the Belts wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //Checks if machine is a placed machine
        if(!real)
        {
            //Creates ghost block
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("1")))
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
            //Updates ghost block position based on mouse and checks if ghost block is clicked
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
                //Checks if machine has been deselected
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
        //Checks if a machine is a placed machine
        if(real)
        {
            if(isDeletedNarrow())
            {
                getWorld().removeObject(this);
            }
            
            placeSound.setVolume(85 * Utils.soundLevel());
        }
    }
    
    /**
     * Returns boolean true if another hotkey is pressed
     * 
     * @return boolean
     */
    public boolean checkDeselectKey()
    {
        if(Utils.getMouse() != null)
        {
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8") || Greenfoot.isKeyDown("9"))
            {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Deletes rotation points of belt to remove pathfinding for shapes
     */
    public void deletePoints()
    {
        getWorld().removeObject(point1);
        getWorld().removeObject(point2);
    }
    
    /**
     * Sets coordinates for rotation points
     */
    protected void addedToWorld(World world)
    {
        if(real)
        {
            playPlace();
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
    
    /**
     * Return boolean true if machine is placed on the map
     * 
     * @return boolean
     */
    public Boolean getReal(){
        return real;
    }
}
