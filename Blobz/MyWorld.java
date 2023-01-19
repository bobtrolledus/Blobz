import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Font comicFontMid = new Font ("Comic Sans MS", true, false, 24);
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        setPaintOrder(Shapes.class, Belts.class, ghostBlock.class);
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
        // spawns left side bar UI stuff
        int width = 200;
        int height = 800; 
        int offset = 27;
        getBackground().setFont(comicFontMid);
        getBackground().setColor(Color.GRAY);

        Button.drawCenteredText (getBackground(), "belt", width / 2, (int) ((height / 9) * 1 - offset));       
        addObject(new Belts(true), width / 2, (int) ((height / 9) * 1)); // tool 1:
        Button.drawCenteredText (getBackground(), "cutter", width / 2, (int) ((height / 9) * 2 - offset));       
        addObject(new Cutter(true), width / 2, (int) ((height / 9) * 2)); // tool 2:
        Button.drawCenteredText (getBackground(), "extractor", width / 2, (int) ((height / 9) * 3 - offset)); 
        addObject(new Extractor(true), width / 2, (int) ((height / 9) * 3)); // tool 3:
        Button.drawCenteredText (getBackground(), "balancer", width / 2, (int) ((height / 9) * 4 - offset)); 
        addObject(new Balancer(true), width / 2, (int) ((height / 9) * 4)); // tool 5:
        Button.drawCenteredText (getBackground(), "rotate cw", width / 2, (int) ((height / 9) * 5 - offset)); 
        addObject(new RotateRight(true), width / 2, (int) ((height / 9) * 5)); // tool 6:
        Button.drawCenteredText (getBackground(), "rotate ccw", width / 2, (int) ((height / 9) * 6 - offset)); 
        addObject(new RotateLeft(true), width / 2, (int) ((height / 9) * 6)); // tool 7:
        Button.drawCenteredText (getBackground(), "painter", width / 2, (int) ((height / 9) * 7 - offset)); 
        addObject(new Belts(true), width / 2, (int) ((height / 9) * 7)); // tool 8:
        Button.drawCenteredText (getBackground(), "stacker", width / 2, (int) ((height / 9) * 8 - offset)); 
        addObject(new Stacker(true), width / 2, (int) ((height / 9) * 8)); // tool 9:
        
        
        //Button.drawCenteredText (getBackground(), "money: ", width / 2, (int) ((height / 9) * 1 - offset));  
        addObject(new Utils(), 0, 0);

        addObject(new Hub(), 600,400);
        addObject(new Label("Level " + Utils.getLevel(), 20), 600,400);

    }

    public void delete()
    {
        if(Utils.getMouse() != null)
        {
            int gridPositionX = (int) (Utils.getMouseX() - 200) /  Utils.gridSize;
            int gridPositionY = (int) Utils.getMouseY() /  Utils.gridSize;
            int buttonNumber = Utils.getMouseButton();
            if(buttonNumber == 3)
            {
                if(Utils.getSpace(gridPositionY, gridPositionX) != null)
                {
                    if(Utils.getSpace(gridPositionY, gridPositionX).getClass() == Belts.class)
                    {
                        Belts temp = (Belts) Utils.getSpace(gridPositionY, gridPositionX);
                        temp.deletePoints();
                    }
                    if(Utils.getSpace(gridPositionY, gridPositionX).getClass() == Balancer.class || Utils.getSpace(gridPositionY, gridPositionX).getClass() == Cutter.class || Utils.getSpace(gridPositionY, gridPositionX).getClass() == Stacker.class)
                    {
                        WideMachines temp = (WideMachines) Utils.getSpace(gridPositionY, gridPositionX);
                        temp.delete();
                    }
                    removeObjects(getObjectsAt((gridPositionX * Utils.gridSize) + 220, (gridPositionY * Utils.gridSize) + 20, Machines.class));
                    Utils.fillSpace(gridPositionY, gridPositionX, null);
                }
            }    
        }
    }

}
