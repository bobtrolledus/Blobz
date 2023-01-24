import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shapes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shapes extends Material
{
    public FollowPoint guide;
    private int q, x, y, layer, colour, scale;
    protected GreenfootImage red, blue, yellow, orange, purple, green;
    private boolean firstRun = true;
    
    public Shapes(int q, FollowPoint guide, int colour, int scale)
    {
        this.q = q;
        this.guide = guide;
        this.colour = colour;
        this.scale = scale;
    }
    
    public void act()
    {
        if(guide.getWorld() == null)
        {
            getWorld().removeObject(this);
        }
        else
        {
            move();
        }
    }
    
    public void setRotation (int q) {
        if(q == 1 || q == 5){
            getImage().rotate(0);
        } else if (q == 2 || q == 6){
            getImage().rotate(90);
        } else if (q == 3 || q == 7){
            getImage().rotate(180);
        } else if (q == 4 || q == 8){
            getImage().rotate(270);
        }
    }
    
    public void move(){
        if(q == 1 || q == 2 || q == 5 || q == 6){
            x = guide.getX() + (this.getImage().getWidth() / 2);
        } else {
            x = guide.getX() - (this.getImage().getWidth() / 2);
        }
            
        if(q == 1 || q == 4 || q == 5 || q == 8){
            y = guide.getY() - (this.getImage().getHeight() / 2);
        } else {
            y = guide.getY() + (this.getImage().getHeight() / 2);
        }
        
        this.setLocation(x, y);
    }
    
    public void setColour(int colour){
        switch(colour){
            case -1:
                break;
            case 1:
                setImage(red);
                break;
            case 2:
                setImage(yellow);
                break;
            case 3:
                setImage(orange);
                break;
            case 4:
                setImage(blue);
                break;
            case 5:
                setImage(purple);
                break;
            case 6:
                setImage(green);
                break;
        }
    }
}
