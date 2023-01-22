import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FollowPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FollowPoint extends UtilsBlocks
{
    private int dir = 0, x, y;
    private RotationPoint rotation;
    private int[] shapeID = new int[8];
    private int[] shapeColour = new int[8];
    private Belts belt;
    private Boolean stopped = false;
    private Shapes nearbyShape;
    
    public FollowPoint(int dir) {
        getImage().scale(1, 1);
        this.dir = dir;
    }
    
    public void act() {
        belt = (Belts) getOneIntersectingObject(Belts.class);
        
        setRotation();
        
        y = this.getY();
        x = this.getX();
        
        switch (dir){
            case 0:
                y++;
                nearbyShape = (Shapes) getOneObjectAtOffset(0, 20, Shapes.class);
                break;
            case 1:
                x--;
                nearbyShape = (Shapes) getOneObjectAtOffset(-20, 0, Shapes.class);
                break;
            case 2:
                y--;
                nearbyShape = (Shapes) getOneObjectAtOffset(0, -20, Shapes.class);
                break;
            case 3:
                x++;
                nearbyShape = (Shapes) getOneObjectAtOffset(20, 0, Shapes.class);
                break;
        }
        
        if(belt != null && belt.getReal() && nearbyShape == null){
            setLocation(x, y);
        }
    }
    
    public void setID(int index, int shapeNum)
    {
        shapeID[index] = shapeNum;
    }
    
    public void setColour(int index, int colourNum)
    {
        shapeColour[index] = colourNum;
    }
    
    public int[] getColour()
    {
        return shapeColour;
    }
    
    public int[] getShape()
    {
        return shapeID;
    }
    
    public int getRotation()
    {
        return dir;
    }
    
    public boolean touchingMachine(Machines machine)
    {
        return intersects(machine);
    }
    
    public void setRotation(){
        rotation = (RotationPoint) getOneIntersectingObject(RotationPoint.class);
        if(rotation != null){
            dir = rotation.getRotation();   
        }
    }
}
