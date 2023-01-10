import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Machines here.
 * 
 * @author Anson  
 * @version (a version number or a date)
 */
public abstract class Machines extends Actor
{
    MouseInfo mouse = Greenfoot.getMouseInfo();
    ghostBlock block = new ghostBlock();
    
    int gridPositionX, gridPositionY;
    
    public void followMouse()
    {
        if(mouse != null)
        {
            setLocation(mouse.getX() + getImage().getWidth() / 2 - 45 / 2, mouse.getY() + getImage().getHeight() / 2 - 45 / 2);
        } 
    }
    
    public void gridSnap()
    {
        if(mouse != null)
        {
            if(mouse.getX() > 200 && mouse.getX() < 1100)
            {
                gridPositionX = (int) (mouse.getX() - 200) / 45;
                gridPositionY = (int) mouse.getY() / 45;
                
                if(getWorld().getObjects(ghostBlock.class).isEmpty() == true)
                {
                    getWorld().addObject(block, (gridPositionX * 45) + (200 + getImage().getWidth() / 2), (gridPositionY * 45) + (getImage().getHeight() / 2));
                }
                
                if(gridPositionX != block.getXCoord() || gridPositionY != block.getYCoord())
                {
                    getWorld().removeObject(block);
                    getWorld().addObject(block, (gridPositionX * 45) + (200 + getImage().getWidth() / 2), (gridPositionY * 45) + (getImage().getHeight() / 2));
                }
                
                block.setXGridCoord(gridPositionX);
                block.setYGridCoord(gridPositionY);
            }
            if(mouse.getX() < 200 || mouse.getX() > 1100)
            {
                getWorld().removeObject(block);
            }
        }
    }
    
    public void place(Class passedClass)
    {
        if(mouse != null)
        {
            if(mouse.getX() > 200 && mouse.getX() < 1100)
            {
                int buttonNumber = mouse.getButton();
                if(buttonNumber == 1 && Utils.spaceIsEmpty(gridPositionX, gridPositionY))
                {
                    try{ 
                        Machines temp = (Machines) passedClass.newInstance();
                        getWorld().addObject(temp, (gridPositionX * 45) + (200 + getImage().getWidth() / 2), (gridPositionY * 45) + (getImage().getHeight() / 2));
                        Utils.fillSpace(gridPositionX, gridPositionY, temp);
                    }
                    catch(Exception e){
                    }
                }
            }
        }
    }
}
