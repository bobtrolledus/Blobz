import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shapes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shapes extends Material
{
    private FollowPoint guide;
    private int q, x, y;
    
    public Shapes(Boolean isLabel, int q, FollowPoint guide)
    {
        super(isLabel, q);
        this.q = q;
        this.guide = guide;
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
    
    public void act(){
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
}
