import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class containing methods for small machines
 * 
 * @author Anson 
 * @version Jan 24, 2023
 */
public class NarrowMachines extends Machines
{
    int gridSpaceX, gridSpaceY;
    /**
     * Checks if machine 2D array is empty at current location. If true, it deletes itself.
     */
    public boolean isDeletedNarrow()
    {
        getGridSpace();
        if(Utils.getSpaceMachine(gridSpaceY, gridSpaceX) == null)
        {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Updates machine 2D array when machine is deleted
     */
    public void deleteNarrow()
    {
        getGridSpace();
        Utils.fillSpaceMachine(gridSpaceY, gridSpaceX, null);
    }
    
    /**
     * Gets current grid location
     */
    public void getGridSpace()
    {
        gridSpaceX = (int) (getX() - 200) / Utils.gridSize;
        gridSpaceY = (int) (getY() / Utils.gridSize);
    }
}
