import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Machines here.
 * 
 * @author Anson  
 * @version (a version number or a date)
 */
public abstract class Machines extends Actor
{
    ghostBlock block;
    public boolean spawner = false, real = false, updatedImage = false, occupied = false, spawned = false, lastMirror = false;
    public int gridPositionX, gridPositionY;  
    public int direction, lastRotation; 
    public Shapes shape;
    public SimpleTimer timer = new SimpleTimer();

    public void followMouse(int xSize)
    {
        if(Utils.getMouse() != null)
        {
            if(xSize > 1 && (Utils.getDirection() == 1 || Utils.getDirection() == 3))
            {
                setLocation(Utils.getMouseX() + getImage().getWidth() / 2 - Utils.gridSize, Utils.getMouseY() + getImage().getHeight() / 2 - Utils.gridSize);
            } else {
                setLocation(Utils.getMouseX() + getImage().getWidth() / 2 - Utils.gridSize / 2, Utils.getMouseY() + getImage().getHeight() / 2 - Utils.gridSize / 2);
            }
        } 
    }
    
    public void gridSnap(GreenfootImage image, int xSize)
    {
        if(Utils.getMouse() != null)
        {
            if(Utils.getMouseX() > 200 && Utils.getMouseX() < 1000)
            {
                gridPositionX = (int) (Utils.getMouseX() - 200) / Utils.gridSize;
                gridPositionY = (int) (Utils.getMouseY()) / Utils.gridSize;
                if(xSize > 1 && (Utils.getDirection() == 1 || Utils.getDirection() == 3))
                {
                    if(getWorld().getObjects(ghostBlock.class).isEmpty() == true)
                    {
                        block = new ghostBlock(image, xSize);
                        getWorld().addObject(block, (gridPositionX * Utils.gridSize) + 220, (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2) - 20);
                    }
                    
                    if(gridPositionX != block.getXCoord() || gridPositionY != block.getYCoord())
                    {
                        for(Arrows arrow : getWorld().getObjects(Arrows.class))
                        {
                            getWorld().removeObject(arrow);
                        }
                        getWorld().removeObject(block);
                        getWorld().addObject(block, (gridPositionX * Utils.gridSize) + 220, (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2) - 20);
                    }
                } else {
                    if(getWorld().getObjects(ghostBlock.class).isEmpty() == true)
                    {
                        block = new ghostBlock(image, xSize);
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
            }
            if(Utils.getMirrored() != lastMirror && xSize > 1 && block != null)
            {
                block.getImage().mirrorHorizontally();
                lastMirror = Utils.getMirrored();
            }
            if(Utils.getMouseX() < 200 || Utils.getMouseX() > 1000)
            {
                getWorld().removeObject(block);
            }
        }
    }
    
    public void place(Class cls, int xSize)
    {
        if(Utils.getMouse() != null)
        {
            if(Utils.getMouseX() > 200 && Utils.getMouseX() < 1000)
            {
                int buttonNumber = Utils.getMouseButton();
                if(buttonNumber == 1 && Utils.getSpaceMachine(gridPositionY, gridPositionX) == null)
                {
                    if(xSize > 1)
                    {
                        if((Utils.getDirection() == 0 && Utils.getSpaceMachine(gridPositionY, gridPositionX + 1) == null) || (Utils.getDirection() == 1 && Utils.getSpaceMachine(gridPositionY - 1, gridPositionX) == null) || (Utils.getDirection() == 2 && Utils.getSpaceMachine(gridPositionY, gridPositionX - 1) == null) || (Utils.getDirection() == 3 && Utils.getSpaceMachine(gridPositionY - 1, gridPositionX) == null))
                        {
                            try{ 
                                Machines temp = (Machines) cls.newInstance();
                                Utils.fillSpaceMachine(gridPositionY, gridPositionX, temp);
                                switch(Utils.getDirection())
                                {
                                    case 0:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                                        Utils.fillSpaceMachine(gridPositionY, gridPositionX + 1, temp);
                                        break;
                                    case 1:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (180 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2 - 20));
                                        Utils.fillSpaceMachine(gridPositionY - 1, gridPositionX, temp);
                                        break;
                                    case 2:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                                        Utils.fillSpaceMachine(gridPositionY, gridPositionX + 1, temp);
                                        break;
                                    case 3:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (180 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2 - 20));
                                        Utils.fillSpaceMachine(gridPositionY - 1, gridPositionX, temp);
                                        break;
                                }
                                if(Utils.getMirrored())
                                {
                                    temp.getImage().mirrorHorizontally();
                                }
                            }
                                catch(Exception e){
                            }
                        }
                    } else if(cls == Extractor.class){
                        if(Utils.getSpaceDeposit(gridPositionY, gridPositionX) != null)
                        {
                            try{
                                Machines temp = (Machines) cls.newInstance();
                                getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                                Utils.fillSpaceMachine(gridPositionY, gridPositionX, temp);
                            }
                                catch(Exception e){        
                            }
                        }
                    } else {
                        try{ 
                            Machines temp = (Machines) cls.newInstance();
                            getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                            Utils.fillSpaceMachine(gridPositionY, gridPositionX, temp);
                        }
                            catch(Exception e){
                        }
                    }
                }
            }
        }
    }
    
    public void deleteShapes()
    {
        for(FollowPoint point : getObjectsInRange(21, FollowPoint.class))
        {
            if(!point.checkIfLabel())
            {
                getWorld().removeObject(point);
            }
        }
    }
    
    public void updateRotation()
    {
        if(Utils.getDirection() != lastRotation)
        {
            updatedImage = false;
        }
    }
    
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    public boolean isOccupied()
    {
        return occupied;
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
}
