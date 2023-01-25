import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent class that deals with the placement, image, and movement of each corner in the "blob".
 * 
 * @author Andy
 * @version Jan 24, 2023
 */
public class Shape extends Material
{
    private int q, x, y, layer, scale, shape;
    protected GreenfootImage grey, red, blue, yellow, orange, green, purple;
    
    /**
     * Creates a shape
     * @param   shape: the shape of the corner
     * @param   q: the quadrant that this shape is in
     * @param   guide: the FollowPoint that this shape is following
     * @param   scale: the size of the shape
     */
    public Shape(int shape, int q, FollowPoint guide, int colour, int scale)
    {
        super(guide);
        this.q = q;
        this.colour = colour;
        this.scale = scale;
        this.shape = shape;
        
        setColour();
        getImage().scale(scale, scale);
        setRotation();
    }
    
    /**
     * Correctly setting the rotation of the corners.
     */
    public void setRotation () {
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
    
    /**
     * Based on quadrant, moves the shape to the correct corner and have it follow the guide point.
     */
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
    
    /**
     * Changes the image based on the colour of the shape
     */
    public void setColour(){
        if(shape == 1){
            grey = new GreenfootImage("Shapes/Circle/grey.png");
            red = new GreenfootImage("Shapes/Circle/red.png");
            blue = new GreenfootImage("Shapes/Circle/blue.png");
            yellow = new GreenfootImage("Shapes/Circle/yellow.png");
            orange = new GreenfootImage("Shapes/Circle/orange.png");
            purple = new GreenfootImage("Shapes/Circle/purple.png");
            green = new GreenfootImage("Shapes/Circle/green.png");
        } else if (shape == 2){
            grey = new GreenfootImage("Shapes/Square/grey.png");
            red = new GreenfootImage("Shapes/Square/red.png");
            blue = new GreenfootImage("Shapes/Square/blue.png");
            yellow = new GreenfootImage("Shapes/Square/yellow.png");
            orange = new GreenfootImage("Shapes/Square/orange.png");
            purple = new GreenfootImage("Shapes/Square/purple.png");
            green = new GreenfootImage("Shapes/Square/green.png");
        } else if (shape == 3){
            grey = new GreenfootImage("Shapes/Star/grey.png");
            red = new GreenfootImage("Shapes/Star/red.png");
            blue = new GreenfootImage("Shapes/Star/blue.png");
            yellow = new GreenfootImage("Shapes/Star/yellow.png");
            orange = new GreenfootImage("Shapes/Star/orange.png");
            purple = new GreenfootImage("Shapes/Star/purple.png");
            green = new GreenfootImage("Shapes/Star/green.png");
        }
        
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
