import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SoundButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundButton extends Button
{
    private boolean soundStatus = true;
    
    public SoundButton(){
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
        refreshUtil();
    }
    
    public void refreshUtil() {
        if (clicked) {
            if (soundStatus) {
                soundStatus = false;
                Utils.turnOffSound();
            } else {
                soundStatus = true;
                Utils.turnOnSound();
            }
        }
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(141, 141, 141)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(255, 251, 170));
        setImage(background);
        if (soundStatus) {
            Button.drawCenteredText(background, "sound: on", width/2, height/2 + size/3);
        } else {
            Button.drawCenteredText(background, "sound: off", width/2, height/2 + size/3);
        }
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(171, 171, 171)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(250, 246, 165));
        setImage(background);
        if (soundStatus) {
            Button.drawCenteredText(background, "sound: on", width/2, height/2 + size/3);
        } else {
            Button.drawCenteredText(background, "sound: off", width/2, height/2 + size/3);
        }
    }
}
