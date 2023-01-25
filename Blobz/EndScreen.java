import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author Anson
 * @version 1/24/2023
 */
public class EndScreen extends World
{
    private GreenfootImage background;
    private boolean clickedPlay = false, clickLoadgame = false;
    private boolean hover = false;
    private Font comicFontLarge = new Font ("Comic Sans MS", true, false, 77);
    private Label timeLabel;
    private Color yellow = new Color(255, 255, 186);
    
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
        if(Utils.getTime() < 10)
        {
            timeLabel = new Label(Utils.getTimeM() + ": 0" + Utils.getTime(), 250); // to keep 2 digits format
        }
        else if(Utils.getTime() >= 10)
        {
            timeLabel = new Label(Utils.getTimeM() + ":" + Utils.getTime(), 250);
        }
        timeLabel.setLineColor(yellow);
        timeLabel.setFillColor(yellow);
        addObject(timeLabel,600,400);
    }
    
    public void act() {
        checkMouse();
        if (clickedPlay || Greenfoot.isKeyDown("enter")) {
            Utils.setTime(0);
            Utils.setTimeM(0);
            Greenfoot.setWorld(new InstructionsMenu());
        } else if(clickLoadgame)
        {
            Greenfoot.setWorld(new MyWorld(true));
        }
    }
    
    public void checkMouse() {
        GeneralButton playButton = (GeneralButton) getObjectsAt(350, 600, GeneralButton.class).get(0);
        clickedPlay = playButton.getClicked();
        GeneralButton loadButton = (GeneralButton) getObjectsAt(850, 600, GeneralButton.class).get(0);
        clickLoadgame = loadButton.getClicked();
    }
}
