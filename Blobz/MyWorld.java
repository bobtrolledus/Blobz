import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        setPaintOrder(Belts.class, ghostBlock.class);
        Color lightGray = new Color(228, 228, 226);
        Color gray = new Color(171, 171, 171);
        getBackground().setColor(lightGray);
        getBackground().fill();
        getBackground().setColor(gray);        
        for(int x = 0; x <= 800; x++){
            if(x % 40 == 0){
                    getBackground().drawLine(x + 200, 0, x + 200, 1000);
            }
        }
        for(int x = 0; x < 1000; x++){
            if(x % 40 == 0){
                    getBackground().drawLine(200, x, 1000, x);
            }
        }
        prepare();
    }
    
    public void act()
    {        
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
        addObject(new Extractor(true), 100, 180);
        addObject(new Utils(), 0, 0);
    }
    
    public void delete()
    {
        if(Utils.getMouse() != null)
        {
            int gridPositionX = (int) (Utils.getMouseX() - 200) / 40;
            int gridPositionY = (int) Utils.getMouseY() / 40;
            int buttonNumber = Utils.getMouseButton();
            if(buttonNumber == 3)
            {
                if(!Utils.spaceIsEmpty(gridPositionX, gridPositionY))
                {
                    if(Utils.getSpace(gridPositionX, gridPositionY).getClass() == Belts.class)
                    {
                        Belts temp = (Belts) this.getObjectsAt((gridPositionX * Utils.gridSize) + 220, (gridPositionY * Utils.gridSize) + 20, Belts.class).get(0);
                        temp.deletePoints();
                    }
                    removeObjects(getObjectsAt((gridPositionX * Utils.gridSize) + 220, (gridPositionY * Utils.gridSize) + 20, Machines.class));
                    Utils.fillSpace(gridPositionX, gridPositionY, null);
                }
            }    
        }
    }
}
