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
    private boolean hover = false;
    private int width;
    private int height;
    private String string;
    private int size;
    
    public StartButton(int width, int height, String string, int size) {
        this.width = width;
        this.height = height;
        this.string = string;
        this.size = size;
        comicFont = new Font ("Comic Sans MS", true, false, size);
    
        // draws purple background
        background = new GreenfootImage(width, height);
        drawNormalButton();
    }
    
    public void act() {
        if (Utils.getMouse() != null) {
            if (Utils.getMouseX() > getX() - width/2 && Utils.getMouseX() < getX() + width/2 && Utils.getMouseY() > getY() - height/2 && Utils.getMouseY() < getY() + height/2) {
                hover = true;
                drawFaintButton();
            } else {
                hover = false;
                drawNormalButton();
            }
            if (Greenfoot.mouseClicked(this)) {
                clicked = true;
            } 
        }
    }
    
    public void drawNormalButton() {
        background.setColor(new Color(193,183,255)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(Color.WHITE);
        setImage(background);
        Button.drawCenteredText(background, string, width/2, height/2 + size/3);
    }
    
    public void drawFaintButton() {
        background.setColor(new Color(220,210,255)); // add background colour
        background.fillRect(0, 0, width, height); // fill Background    
        background.setFont(comicFont);
        background.setColor(Color.CYAN);
        setImage(background);
        Button.drawCenteredText(background, string, width/2, height/2 + size/3);
    }
    
    public boolean getHover() {
        return hover;
    }
    
    public boolean getClicked() {
        return clicked;
    }
}