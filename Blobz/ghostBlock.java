import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ghostBlock here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class ghostBlock extends Utils
{
    private int xCoord, yCoord;
    private int lastRotation;
    private boolean addedArrows = false;    
    Arrows arrow1 = new Arrows();
    Arrows arrow2 = new Arrows();
    public ghostBlock()
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
    }
    
    public void act()
    {
        updateArrowImage();
        if(!addedArrows)
        {
            lastRotation = Utils.getDirection();
            spawnArrows(lastRotation);
            addedArrows = true;
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
                    arrow1.setRotation(90);
                    getWorld().addObject(arrow2, getX(), getY() + Utils.gridSize);    
                    arrow2.setRotation(90);
                    break;
                case 1:
                    getWorld().addObject(arrow1, getX() - Utils.gridSize, getY());
                    arrow1.setRotation(180);
                    getWorld().addObject(arrow2, getX() + Utils.gridSize, getY());    
                    arrow2.setRotation(180);
                    break;
                case 2:
                    getWorld().addObject(arrow1, getX(), getY() - Utils.gridSize);
                    arrow1.setRotation(-90);
                    getWorld().addObject(arrow2, getX(), getY() + Utils.gridSize);    
                    arrow2.setRotation(-90);
                    break;
                case 3:
                    getWorld().addObject(arrow1, getX() - Utils.gridSize, getY());
                    arrow1.setRotation(0);
                    getWorld().addObject(arrow2, getX() + Utils.gridSize, getY());    
                    arrow2.setRotation(0);
                    break;
            }
    }
    
    public void updateArrowImage()
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
