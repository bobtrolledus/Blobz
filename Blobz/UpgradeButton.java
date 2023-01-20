import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpgradeButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeButton extends Button
{
    private String upgradeType;
    private int upgradeLvl = 0;
    private int moneyReq = 100;
    
    
    public UpgradeButton(String upgradeType) {
        this.width = 160;
        this.height = 100;
        this.size = 20;
        this.upgradeType = upgradeType;
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        // draws purple background
        background = new GreenfootImage(width, height);
        drawNormalButton();
    }
    
    public void act() {
        //add edge case for max button level
        mouse();
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(193,183,255)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(Color.WHITE);
        setImage(background);
        Button.drawCenteredText(background, "LVL: " + upgradeLvl + " -> " + (upgradeLvl + 1), width/2, height/2 + size/3 - size);
        Button.drawCenteredText(background, "$" + moneyReq, width/2, height/2 + size/3 + size);
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(220,210,255)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(Color.CYAN);
        setImage(background);
        Button.drawCenteredText(background, "LVL: " + upgradeLvl + " -> " + (upgradeLvl + 1), width/2, height/2 + size/3 - size);
        Button.drawCenteredText(background, "$" + moneyReq, width/2, height/2 + size/3 + size);
    }
    
    public boolean getClicked() {
        return clicked;
    }
}
