import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsMenu here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class InstructionsMenu extends World
{
    private GreenfootImage[] images = new GreenfootImage[12];
    private Label continueLabel = new Label("Space to Continue", 40);
    private Color yellow = new Color(255, 255, 186);
    private int index = 0;
    /**
     * Constructor for objects of class InstructionsMenu.
     * 
     */
    public InstructionsMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        for(int i = 0; i < images.length; i++)
        {
            images[i] = new GreenfootImage("images/Instructions/instruction " + i + ".png");
            images[i].scale(1200, 800);
        }
        setBackground(images[index]);
        addObject(new Utils(), 0, 0);
        addObject(continueLabel, 600, 770);
        continueLabel.setLineColor(yellow);
        continueLabel.setFillColor(yellow);
    }
    
    public void act()
    {
        if(Utils.getLastKey() != null)
        {
            if(Utils.getLastKey().equals("space"))
            {
                index++;
                if(index == 12)
                {
                    Greenfoot.setWorld(new MyWorld());
                } else {
                    setBackground(images[index]);
                }
            }
        }
    }
}
