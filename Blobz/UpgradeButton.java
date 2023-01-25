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
    private int moneyReq;
    
    public UpgradeButton(String upgradeType) {
        this.width = 160;
        this.height = 80;
        this.size = 20;
        this.upgradeType = upgradeType;
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        background = new GreenfootImage(width, height);
        
    }
    
    public void act() {
        //add edge case for max button level
        upgrade();
        refreshLevel();
        redraw();
    }
    
    public void upgrade() {
        if (clicked && Utils.getMoney() > moneyReq) {
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
            Utils.spendMoney(moneyReq);
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
        moneyReq = (int) Math.round((Math.pow(2, upgradeLvl) * 7 + 11) / 10) * 10;
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(141, 141, 141)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(255, 251, 170));
        setImage(background);
        if (upgradeLvl < 8) {
            Button.drawCenteredText(background, "LVL: " + upgradeLvl + " -> " + (upgradeLvl + 1), width/2, height/2 + size/3 - size);
            Button.drawCenteredText(background, "$" + moneyReq, width/2, height/2 + size/3 + size);
        } else {
            Button.drawCenteredText(background, "LVL: MAX (8)", width/2, height/2 + size/3);
        }
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(171, 171, 171)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(250, 246, 165));
        setImage(background);
        if (upgradeLvl < 8) {
            Button.drawCenteredText(background, "LVL: " + upgradeLvl + " -> " + (upgradeLvl + 1), width/2, height/2 + size/3 - size);
            Button.drawCenteredText(background, "$" + moneyReq, width/2, height/2 + size/3 + size);
        } else {
            Button.drawCenteredText(background, "LVL: MAX (8)", width/2, height/2 + size/3);
        }
    }
}
