import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Class deals with colour specific movement, image setting, and scaling.
 * 
 * @author Andy 
 * @version Jan 24, 2023
 */
public class Colours extends Material
{
    public Colours(int colour, FollowPoint guide){
        super(guide);
        
        if(colour == 1){
            setImage("Redblob.png");
        } else if (colour == 2){
            setImage("Yellowblob.png");
        } else if (colour == 4){
            setImage("Blueblob.png");
        }
        
        //if the object is a label, make it smaller than an actual object that can be manipulated
        if (guide.checkIfLabel()){
            getImage().scale(26, 26);
        } else {
            getImage().scale(34, 34);
        }
    }
    
    /**
     * Move method for colours
     */
    public void move(){
        this.setLocation(guide.getX(), guide.getY());
    }
}
