import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * used in title screen to create a clickable button
 * (borrows code from Mr. Cohen's Button class to draw centered text)
 * 
 * @author yuebai 
 * @version 11/24/2022
 */
public class StartButton extends Button {
    private GreenfootImage background;
    private Font comicFont;
    private boolean clicked = false;
    private int width;
    private int height;
    
    
    public StartButton(int width, int height, String string, int size) {
        this.width = width;
        this.height = height;
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        // draws gray background
        background = new GreenfootImage(width, height);
        background.setColor(new Color(193,183,255)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(Color.WHITE);
        setImage(background);
        Button.drawCenteredText(background, string, width/2, height/2 + size/3);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            clicked = true;
        }
    }
    
    public boolean getClicked() {
        return clicked;
    }
}