import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class EndScreen extends World
{
    private GreenfootImage image;
    private Color yellow = new Color(255, 255, 186);
    private int index = 0;
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        image = new GreenfootImage("images/End Screen.png");
        image.scale(1200, 800);
        setBackground(image);
        
        addObject(new Utils(), 0, 0);
        addObject(new StartButton(400, 100, "Play Again", 40), 350, 600); 
        addObject(new StartButton(400, 100, "Start from Load", 40), 850, 600 ); 
    }
    
    public void act()
    {
        
    }
}
