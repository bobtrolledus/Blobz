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
    private boolean clicked = false;
    private Font comicFontLarge = new Font ("Comic Sans MS", true, false, 77);
    
    public StartScreen() {    
        super(1200, 800, 1);
                
        background = new GreenfootImage("start_screen.png");
        setBackground(background);
        
        background.setFont(comicFontLarge);
        background.setColor(Color.GRAY.darker());
        Button.drawCenteredText (background, "blobz", 600, 171);
        
        addObject(new StartButton(455, 80, "click or press enter to start", 30), background.getWidth()/2, background.getHeight()*5/7);
    }

    public void act() {
        checkClicked();
        if (clicked || Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
    public void checkClicked() {
        ArrayList<StartButton> button = (ArrayList<StartButton>)getObjects(StartButton.class);
        for (StartButton b : button) {
            clicked = b.getClicked();
        }
    }
}

