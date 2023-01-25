import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class saveButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SaveButton extends Button
{
    public SaveButton() {
        this.width = 160;
        this.height = 50;
        this.size = 20;
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        // draws purple background
        background = new GreenfootImage(width, height);
        drawNormalButton();
    }
    
    public void act() {
        redraw();
        if (clicked) {
            Utils.save();
            Greenfoot.setWorld(new StartScreen());
        }
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(141, 141, 141)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(255, 251, 170));
        setImage(background);
        Button.drawCenteredText(background, "save & quit", width/2, height/2 + size/3);
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(171, 171, 171)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(250, 246, 165));
        setImage(background);
        Button.drawCenteredText(background, "save & quit", width/2, height/2 + size/3);
    }
}
