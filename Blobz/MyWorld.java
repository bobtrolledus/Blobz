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
    private Font comicFontMid = new Font ("Comic Sans MS", true, false, 24);
    
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
        // spawns left side bar UI stuff
        int width = 200;
        int height = 800; 
        int offset = 27;
        getBackground().setFont(comicFontMid);
        getBackground().setColor(Color.GRAY);
        
        Button.drawCenteredText (getBackground(), "belt", width / 2, (int) ((height / 9.5) * 1 - offset));       
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 1)); // tool 1:
        Button.drawCenteredText (getBackground(), "cutter", width / 2, (int) ((height / 9.5) * 2 - offset));       
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 2)); // tool 2:
        Button.drawCenteredText (getBackground(), "extractor", width / 2, (int) ((height / 9.5) * 3 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 3)); // tool 3:
        Button.drawCenteredText (getBackground(), "tunnel", width / 2, (int) ((height / 9.5) * 4 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 4)); // tool 4:
        Button.drawCenteredText (getBackground(), "balancer", width / 2, (int) ((height / 9.5) * 5 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 5)); // tool 5:
        Button.drawCenteredText (getBackground(), "rotate cw", width / 2, (int) ((height / 9.5) * 6 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 6)); // tool 6:
        Button.drawCenteredText (getBackground(), "rotate ccw", width / 2, (int) ((height / 9.5) * 7 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 7)); // tool 7:
        Button.drawCenteredText (getBackground(), "painter", width / 2, (int) ((height / 9.5) * 8 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 8)); // tool 8:
        Button.drawCenteredText (getBackground(), "stacker", width / 2, (int) ((height / 9.5) * 9 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9.5) * 9)); // tool 9:
        
        
        
        addObject(new Utils(), 0, 0);
    }
    
    public void delete()
    {
        if(mouse != null)
        {
            int gridPositionX = (int) (mouse.getX() - 200) / 40;
            int gridPositionY = (int) mouse.getY() / 40;
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