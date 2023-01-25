import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NextLevelButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
            System.out.println("CLICKED");
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
    
    public void changeLevel() {
        
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
