import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ghostBlock here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class ghostBlock extends UtilsBlocks
{
    private int xCoord, yCoord;
    private int lastRotation;
    private boolean addedArrows = false;    
    Arrows arrow1 = new Arrows();
    Arrows arrow2 = new Arrows();
    
    public ghostBlock(GreenfootImage image)
    {
        setImage(image);
        getImage().scale(Utils.gridSize, Utils.gridSize);
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
    
    public void updateRotationImage()
    {
        if(Utils.getDirection() != lastRotation)
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
    
    public void setXGridCoord(int coord)
    {
        xCoord = coord;
    }
    
    public void setYGridCoord(int coord)
    {
        yCoord = coord;
    }
    
    public int getXCoord()
    {
        return xCoord;
    }
    
    public int getYCoord()
    {
        return yCoord;
    }
}
