import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * General purpose button for menu screens.
 * (borrows code from Mr. Cohen's Button class to draw centered text)
 * 
 * @author Yuebai 
 * @version 11/24/2022
 */
public class GeneralButton extends Button {
    private String string;
    
    public GeneralButton(int width, int height, String string, int size) {
        this.width = width;
        this.height = height;
        this.string = string;
        this.size = size;
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        background = new GreenfootImage(width, height);
        drawNormalButton();
    }
    
    public void act() {
        redraw();
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(141, 141, 141)); // add normal background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(255, 251, 170));
        setImage(background);
        Button.drawCenteredText(background, string, width/2, height/2 + size/3);
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(171, 171, 171)); // add light background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(250, 246, 165));
        setImage(background);
        Button.drawCenteredText(background, string, width/2, height/2 + size/3);
    }
}