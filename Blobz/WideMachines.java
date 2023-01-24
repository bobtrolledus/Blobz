import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class containing methods for wide machines. Includes shape deletion and coordinate locating methods
 * 
 * @author Anson
 * @version Jan 24, 2023
 */
public abstract class WideMachines extends Machines
{
    private int gridSpaceX1, gridSpaceX2, gridSpaceY1, gridSpaceY2;
    public int mirrored;
    /**
     * Removes all shapes and colours touching machine when deleted
     */
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
    
    /**
     * Checks if machine 2D array contains machine. If not, it deletes itself.
     */
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
    
    /**
     * Edits machine 2D array when wide machine is deleted
     */
    public void deleteWide()
    {
        getGridSpace();
        Utils.fillSpaceMachine(gridSpaceY1, gridSpaceX1, null);
        Utils.fillSpaceMachine(gridSpaceY2, gridSpaceX2, null);
    }

    /**
     * Checks if machine should be placed mirrored
     */
    public void checkMirrored()
    {
        if(Utils.getMirrored())
        {
            mirrored = -1;
        } else {
            mirrored = 1;
        }
    }
    
    /**
     * Gets both grid spaces that wide machine occupies 
     */
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
