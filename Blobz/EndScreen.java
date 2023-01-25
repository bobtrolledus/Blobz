import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class EndScreen extends World
{
    private GreenfootImage background;
    private boolean clickedPlay = false, clickLoadgame = false;
    private boolean hover = false;
    private Font comicFontLarge = new Font ("Comic Sans MS", true, false, 77);
    
    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        background = new GreenfootImage("images/End Screen.png");
        background.scale(1200, 800);
        setBackground(background);
        
        addObject(new Utils(), 0, 0);
        addObject(new GeneralButton(400, 100, "Play Again", 40), 350, 600); 
        addObject(new GeneralButton(400, 100, "Start from Load", 40), 850, 600 ); 
    }
    
    public void act() {
        checkMouse();
        if (clickedPlay || Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new InstructionsMenu());
        } else if(clickLoadgame)
        {
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
    public void checkMouse() {
        GeneralButton playButton = (GeneralButton) getObjectsAt(350, 600, GeneralButton.class).get(0);
        clickedPlay = playButton.getClicked();
        GeneralButton loadButton = (GeneralButton) getObjectsAt(850, 600, GeneralButton.class).get(0);
        clickLoadgame = loadButton.getClicked();
    }
}
