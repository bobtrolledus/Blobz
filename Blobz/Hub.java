import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Hub here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hub extends Actor
{
    private static int level;
    private static int obj;
    private boolean isObj;
    private static int x;
    private static int y;
    /**
     * Act - do whatever the Hub wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Hub()
    {
        setImage("images/hub.png");
        getImage().scale(Utils.gridSize*4, Utils.gridSize*4);

    }

    public void act()
    {
        collecting();
        upgrade();
    }

    public void collecting()
    {
        Resources resource = (Resources)getOneIntersectingObject(Resources.class);
        if(resource != null)
        {
            getWorld().removeObject(resource);
            if(isObj)
            {
                obj--;
                
            }

        }
    }

    public void upgrade()
    {
        if(obj == 0)
        {
            Utils.increaseLevel();
        }
    }
}
