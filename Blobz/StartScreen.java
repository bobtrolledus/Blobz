import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class StartScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        
        
        addObject(new StartButton(455, 80, "Play Game (Enter)", 30), background.getWidth()/2, background.getHeight()*5/8);
        addObject(new StartButton(455, 80, "Load game", 30), background.getWidth()/2, background.getHeight()*5/8 + 120);
    }

    public void act() {
        checkMouse();
        if (clickedPlay || Greenfoot.isKeyDown("enter")) {
            click.play();
            Greenfoot.setWorld(new InstructionsMenu());
        } else if(clickLoadgame)
        {
            click.play();
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
    public void checkMouse() {
        StartButton playButton = (StartButton) getObjectsAt(background.getWidth()/2, background.getHeight()*5/8, StartButton.class).get(0);
        clickedPlay = playButton.getClicked();
        StartButton loadButton = (StartButton) getObjectsAt(background.getWidth()/2, background.getHeight()*5/8 + 120, StartButton.class).get(0);
        clickLoadgame = loadButton.getClicked();
    }
}