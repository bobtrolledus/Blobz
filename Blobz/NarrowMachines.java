import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NarrowMachines here.
 * 
 * @author Anson 
 * @version (a version number or a date)
 */
public class NarrowMachines extends Machines
{
    int gridSpaceX, gridSpaceY;
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
    
    public void deleteNarrow()
    {
        getGridSpace();
        Utils.fillSpaceMachine(gridSpaceY, gridSpaceX, null);
    }
    
    public void getGridSpace()
    {
        gridSpaceX = (int) (getX() - 200) / Utils.gridSize;
        gridSpaceY = (int) (getY() / Utils.gridSize);
    }
}
