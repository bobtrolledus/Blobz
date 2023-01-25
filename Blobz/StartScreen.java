import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Starting screen of the game.
 * 
 * @author Yuebai
 * @version 1/24/2023 
 */
public class StartScreen extends World
{
    private GreenfootImage background;
    private boolean clickedPlay = false, clickLoadgame = false;
    private boolean hover = false;
    private Font comicFontLarge = new Font ("Comic Sans MS", true, false, 77);
    
    private GreenfootSound click = new GreenfootSound("select.wav");
    
    public StartScreen() {    
        super(1200, 800, 1);
        addObject(new Utils(), 0, 0);
        
        background = new GreenfootImage("startscreen.png");
        background.scale(1200, 800);
        setBackground(background);
        
        addObject(new GeneralButton(455, 80, "Play New Game (Enter)", 30), background.getWidth()/2, background.getHeight()*5/8);
        addObject(new GeneralButton(455, 80, "Load game", 30), background.getWidth()/2, background.getHeight()*5/8 + 120);
    }

    public void act() {
        checkMouse();
        if (clickedPlay || Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new InstructionsMenu());
            click.play();
        } else if(clickLoadgame)
        {
            Greenfoot.setWorld(new MyWorld(true));
            click.play();
        }
    }
    
    public void checkMouse() {
        GeneralButton playButton = (GeneralButton) getObjectsAt(background.getWidth()/2, background.getHeight()*5/8, GeneralButton.class).get(0);
        clickedPlay = playButton.getClicked();
        GeneralButton loadButton = (GeneralButton) getObjectsAt(background.getWidth()/2, background.getHeight()*5/8 + 120, GeneralButton.class).get(0);
        clickLoadgame = loadButton.getClicked();
    }
}