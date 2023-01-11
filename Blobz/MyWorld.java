import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    MouseInfo mouse = Greenfoot.getMouseInfo();

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1300, 900, 1); 
        setPaintOrder(Belts.class, ghostBlock.class);
        Color lightGray = new Color(228, 228, 226);
        Color gray = new Color(171, 171, 171);
        getBackground().setColor(lightGray);
        getBackground().fill();
        getBackground().setColor(gray);        
        for(int x = 0; x <= 900; x++){
            if(x % 45 == 0){
                    getBackground().drawLine(x + 200, 0, x + 200, 1100);
            }
        }
        for(int x = 0; x < 1100; x++){
            if(x % 45 == 0){
                    getBackground().drawLine(200, x, 1100, x);
            }
        }
        prepare();
    }
    
    public void act()
    {
        mouse = Greenfoot.getMouseInfo();
        delete();
        String key = Greenfoot.getKey();
        if(key != null)
        {
            if(key.equals("r"))
            {
                Utils.addRotation();
            }
        }
    }
    
    public void prepare()
    {
        addObject(new Belts(true), 100, 100);
        addObject(new shape(), 245, 68);
        addObject(new Utils(), 0, 0);
    }
    
    public void delete()
    {
        if(mouse != null)
        {
            int gridPositionX = (int) (mouse.getX() - 200) / 45;
            int gridPositionY = (int) mouse.getY() / 45;
            int buttonNumber = mouse.getButton();
            if(buttonNumber == 3)
            {
                if(!Utils.spaceIsEmpty(gridPositionX, gridPositionY))
                {
                    removeObjects(getObjectsAt(mouse.getX(), mouse.getY(), Machines.class));
                    Utils.fillSpace(gridPositionX, gridPositionY, null);
                }
            }    
        }
    }
}
