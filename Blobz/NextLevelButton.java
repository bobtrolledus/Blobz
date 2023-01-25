import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Locked Button for level advancement.
 * 
 * @author Yuebai
 * @version 1/24/2023
 */
public class NextLevelButton extends Button
{
    public NextLevelButton() {
        this.width = 160;
        this.height = 50;
        this.size = 20;
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        background = new GreenfootImage(width, height);
    }
    
    public void act()
    {
        redraw();
        if (Greenfoot.mouseClicked(this)) {
            Utils.approveLevelChange();
        }
    }
    
    public void redraw() {
        if (Utils.getTotalTargetShapes() >= 5) {
            drawNormalButton();
        } else {
            drawFaintButton();
        }
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(141, 141, 141)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(255, 251, 170));
        setImage(background);
        Button.drawCenteredText(background, "next level", width/2, height/2 + size/3);
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(211, 211, 211)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(255, 251, 170));
        setImage(background);
        Button.drawCenteredText(background, "next level", width/2, height/2 + size/3);
    }
}
