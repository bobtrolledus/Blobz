import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MoneyDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoneyDisplay extends UI
{
    private GreenfootImage background;
    private Font comicFont;
    private int width = 100;
    private int height = 50;
    private int size = 20;
    public MoneyDisplay() {
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        background = new GreenfootImage(width, height);
    }
    
    /**
     * Act - do whatever the MoneyDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        background.setColor(new Color(141, 141, 141)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(new Color(255, 251, 170));
        setImage(background);
        Button.drawCenteredText(background, "" + Utils.getMoney(), width/2, height/2 + size/3);
    }
}
