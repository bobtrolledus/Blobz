import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *  A class that creates semi-transparent images of a machine when hovering over the map
 * 
 * @author Anson 
 * @version Jan 24, 2023
 */
public class ghostBlock extends UtilsBlocks
{
    private int xCoord, yCoord;
    private int lastRotation;
    private boolean addedArrows = false;    
    Arrows arrow1 = new Arrows();
    Arrows arrow2 = new Arrows();
    
    public ghostBlock(GreenfootImage image, int xSize)
    {
        setImage(image);
        getImage().scale(Utils.gridSize * xSize, Utils.gridSize);
        getImage().setTransparency(150);
    }
    
    public void act()
    {
        updateRotationImage();
        if(!addedArrows)
        {
            lastRotation = Utils.getDirection();
            spawnArrows(lastRotation);
            updateImage(lastRotation);
            addedArrows = true;
        }
    }
    
    /**
     * Rotates ghost image
     *  
     * @param direction Direction that the ghost image should face
     */
    public void updateImage(int direction)
    {
        switch (direction)
        {
            case 0:
                setRotation(180);
                break;
            case 1:
                setRotation(-90);
                break;
            case 2:
                setRotation(0);
                break;
            case 3:
                setRotation(90);
                break;
        }
    }
    
    protected void addedToWorld(World world)
    {
        spawnArrows(Utils.getDirection());
    }
    
    /**
     * Spawns arrows depending on direction that the machine is facing
     * 
     * @param direction Direction that the arrows should face
     */
    public void spawnArrows(int direction)
    {
        switch (direction)
        {
            case 0:
                getWorld().addObject(arrow1, getX(), getY() - Utils.gridSize);
                arrow1.setRotation(180);
                getWorld().addObject(arrow2, getX(), getY() + Utils.gridSize);    
                arrow2.setRotation(180);
                break;
            case 1:
                getWorld().addObject(arrow1, getX() - Utils.gridSize, getY());
                arrow1.setRotation(-90);
                getWorld().addObject(arrow2, getX() + Utils.gridSize, getY());    
                arrow2.setRotation(-90);
                break;
            case 2:
                getWorld().addObject(arrow1, getX(), getY() - Utils.gridSize);
                arrow1.setRotation(0);
                getWorld().addObject(arrow2, getX(), getY() + Utils.gridSize);    
                arrow2.setRotation(0);
                break;
            case 3:
                getWorld().addObject(arrow1, getX() - Utils.gridSize, getY());
                arrow1.setRotation(90);
                getWorld().addObject(arrow2, getX() + Utils.gridSize, getY());    
                arrow2.setRotation(90);
                break;
        }
    }
    
    /**
     * Updates ghost image when iamge is rotated
     */
    public void updateRotationImage()
    {
        if(Utils.getDirection() != lastRotation && Utils.getMouse() != null)
        {
            if(Utils.getMouseX() > 200 && Utils.getMouseX() < 1000)
            {
                for(Arrows arrow : getWorld().getObjects(Arrows.class))
                {
                    getWorld().removeObject(arrow);
                }
            }
            addedArrows = false;
        }
    }
    
    /**
     * Sets x coordinate of the ghost block
     * @param coord Coordinate
     */
    public void setXGridCoord(int coord)
    {
        xCoord = coord;
    }
    
    /**
     * Sets y coordinate of the ghost block
     * @param coord Coordinate
     */
    public void setYGridCoord(int coord)
    {
        yCoord = coord;
    }
    
    /**
     * Gets x coordinate of the ghost block
     * @return xCoord Coordinate 
     */
    public int getXCoord()
    {
        return xCoord;
    }
    
    /**
     * Gets y coordinate of the ghost block 
     * @return yCoord Coordinate
     */
    public int getYCoord()
    {
        return yCoord;
    }
}