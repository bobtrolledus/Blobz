import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Machines here.
 * 
 * @author Anson  
 * @version (a version number or a date)
 */
public abstract class Machines extends Actor
{
    ghostBlock block = new ghostBlock();
    int gridPositionX, gridPositionY;  
    
    public void followMouse()
    {
        if(Utils.getMouse() != null)
        {
            setLocation(Utils.getMouseX() + getImage().getWidth() / 2 - Utils.gridSize / 2, Utils.getMouseY() + getImage().getHeight() / 2 - Utils.gridSize / 2);
        } 
    }
    
    public void gridSnap()
    {
        if(Utils.getMouse() != null)
        {
            if(Utils.getMouseX() > 200 && Utils.getMouseX() < 1000)
            {
                gridPositionX = (int) (Utils.getMouseX() - 200) / Utils.gridSize;
                gridPositionY = (int) Utils.getMouseY() / Utils.gridSize;
                
                if(getWorld().getObjects(ghostBlock.class).isEmpty() == true)
                {
                    getWorld().addObject(block, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                }
                
                if(gridPositionX != block.getXCoord() || gridPositionY != block.getYCoord())
                {
                    for(Arrows arrow : getWorld().getObjects(Arrows.class))
                    {
                        getWorld().removeObject(arrow);
                    }
                    getWorld().removeObject(block);
                    getWorld().addObject(block, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                }
                
                block.setXGridCoord(gridPositionX);
                block.setYGridCoord(gridPositionY);
            }
            
            if(Utils.getMouseX() < 200 || Utils.getMouseX() > 1000)
            {
                getWorld().removeObject(block);
            }
        }
    }
    
    public void place(Class cls)
    {
        if(Utils.getMouse() != null)
        {
            if(Utils.getMouseX() > 200 && Utils.getMouseX() < 1000)
            {
                int buttonNumber = Utils.getMouseButton();
                if(buttonNumber == 1 && Utils.spaceIsEmpty(gridPositionX, gridPositionY))
                {
                    try{ 
                        Machines temp = (Machines) cls.newInstance();
                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                        Utils.fillSpace(gridPositionX, gridPositionY, temp);
                    }
                    catch(Exception e){
                    }
                }
            }
        }
    }
}
