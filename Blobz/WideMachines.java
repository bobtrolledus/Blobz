import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WideMachines here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public abstract class WideMachines extends Machines
{
    private int gridSpaceX1, gridSpaceX2, gridSpaceY1, gridSpaceY2;
    public int mirrored;
    public void deleteShapesWide()
    {
        for(FollowPoint point : getObjectsInRange(50, FollowPoint.class))
        {
            if(!point.checkIfLabel())
            {
                getWorld().removeObject(point);
            }
        }
    }
    
    public boolean isDeletedWide()
    {
        getGridSpace();
        if(Utils.getSpaceMachine(gridSpaceY1, gridSpaceX1) == null || Utils.getSpaceMachine(gridSpaceY2, gridSpaceX2) == null)
        {
            return true;
        } else {
            return false;
        }
    }
    
    public void deleteWide()
    {
        getGridSpace();
        Utils.fillSpaceMachine(gridSpaceY1, gridSpaceX1, null);
        Utils.fillSpaceMachine(gridSpaceY2, gridSpaceX2, null);
    }
    
    public void checkMirrored()
    {
        if(Utils.getMirrored())
        {
            mirrored = -1;
        } else {
            mirrored = 1;
        }
    }
    
    public void getGridSpace()
    {
        switch(direction)
        {
            case 0:
                gridSpaceX1 = (int) (getX() - (Utils.gridSize / 2) - 200) / Utils.gridSize;
                gridSpaceX2 = (int) (getX() + (Utils.gridSize / 2) - 200) / Utils.gridSize;
                gridSpaceY1 = (int) getY() / Utils.gridSize;
                gridSpaceY2 = (int) getY() / Utils.gridSize;
                break;
            case 1:
                gridSpaceX1 = (int) (getX() - 200) / Utils.gridSize;
                gridSpaceX2 = (int) (getX() - 200) / Utils.gridSize;
                gridSpaceY1 = (int) (getY() - (Utils.gridSize / 2)) / Utils.gridSize;
                gridSpaceY2 = (int) (getY() + (Utils.gridSize / 2)) / Utils.gridSize;
                break;
            case 2:
                gridSpaceX1 = (int) (getX() - (Utils.gridSize / 2) - 200) / Utils.gridSize;
                gridSpaceX2 = (int) (getX() + (Utils.gridSize / 2) - 200) / Utils.gridSize;
                gridSpaceY1 = (int) getY() / Utils.gridSize;
                gridSpaceY2 = (int) getY() / Utils.gridSize;
                break;
            case 3:
                gridSpaceX1 = (int) (getX() - 200) / Utils.gridSize;
                gridSpaceX2 = (int) (getX() - 200) / Utils.gridSize;
                gridSpaceY1 = (int) (getY() - (Utils.gridSize / 2)) / Utils.gridSize;
                gridSpaceY2 = (int) (getY() + (Utils.gridSize / 2)) / Utils.gridSize;
                break;
        }
    }
}
