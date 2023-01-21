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
    private int q, x, y, layer, colour;
    protected GreenfootImage red, blue, yellow, orange, purple, green, grey;
    
    public Shapes(int q, FollowPoint guide, int layer, int colour)
    {
        this.q = q;
        this.guide = guide;
        this.layer = layer;
        this.colour = colour;
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
        switch(q){
            case 1:
                getImage().rotate(0);
                break;
            case 2:
                getImage().rotate(90);
                break;
            case 3:
                getImage().rotate(180);
                break;
            case 4:
                getImage().rotate(270);
                break;
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
    
    public void layerScale(){
        if (layer == 1){
            getImage().scale(19, 19);
        } else {
            getImage().scale(13, 13);
        }
    }
    
    public void setColour(int colour){
        switch(colour){
            case -1:
                setImage(grey);
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
