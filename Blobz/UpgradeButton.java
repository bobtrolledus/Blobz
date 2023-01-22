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
        
    }
    
    public void act() {
        //add edge case for max button level
        upgrade();
        refreshLevel();
        redraw();
    }
    
    public void upgrade() {
        if (clicked) {
            if (upgradeLvl < 8) {
                if (upgradeType.equals("crs")) {
                    Utils.increaseCRSlevel();
                } else if (upgradeType.equals("bd")) {
                    Utils.increaseBDlevel();
                } else if (upgradeType.equals("paint")) {
                    Utils.increasePAINTlevel();
                } else if (upgradeType.equals("extract")) {
                    Utils.increaseEXTRACTlevel();
                }
            }
        }
    }
    
    public void refreshLevel() {
        if (upgradeType.equals("crs")) {
            upgradeLvl = Utils.getCRSlevel();
        } else if (upgradeType.equals("bd")) {
            upgradeLvl = Utils.getBDlevel();
        } else if (upgradeType.equals("paint")) {
            upgradeLvl = Utils.getPAINTlevel();
        } else if (upgradeType.equals("extract")) {
            upgradeLvl = Utils.getEXTRACTlevel();
        }
        moneyReq = (int) Math.round((Math.pow(2, upgradeLvl) * 25 + 60) / 100) * 100;
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(193,183,255)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(Color.WHITE);
        setImage(background);
        if (upgradeLvl < 8) {
            Button.drawCenteredText(background, "LVL: " + upgradeLvl + " -> " + (upgradeLvl + 1), width/2, height/2 + size/3 - size);
            Button.drawCenteredText(background, "$" + moneyReq, width/2, height/2 + size/3 + size);
        } else {
            Button.drawCenteredText(background, "LVL: MAX (8)", width/2, height/2 + size/3);
        }
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(220,210,255)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(Color.CYAN);
        setImage(background);
        if (upgradeLvl < 8) {
            Button.drawCenteredText(background, "LVL: " + upgradeLvl + " -> " + (upgradeLvl + 1), width/2, height/2 + size/3 - size);
            Button.drawCenteredText(background, "$" + moneyReq, width/2, height/2 + size/3 + size);
        } else {
            Button.drawCenteredText(background, "LVL: MAX (8)", width/2, height/2 + size/3);
        }
    }
}
