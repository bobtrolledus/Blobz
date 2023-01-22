import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FollowPoint here.
 * 
 * @author Andy/Anson
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
    private Machines nearbyMachine;
    private boolean occupied = false;
    private boolean isLabel;
    
    public FollowPoint(int dir, boolean isLabel) {
        getImage().scale(1, 1);
        this.dir = dir;
        this.isLabel = isLabel;
    }
    
    public void act() {
        belt = (Belts) getOneIntersectingObject(Belts.class);
        
        setRotation();
        
        y = this.getY();
        x = this.getX();
        
        switch (dir){
            case 0:
                y++;
                nearbyShape = (Shapes) getOneObjectAtOffset(0, 22, Shapes.class);
                nearbyMachine = (Machines) getOneObjectAtOffset(0, 22, Machines.class);
                break;
            case 1:
                x--;
                if(shapeID[2] == -1 && shapeID[3] == -1 && shapeID[6] == -1 && shapeID[7] == -1){
                    nearbyShape = (Shapes) getOneObjectAtOffset(-3, 0, Shapes.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(-3, 0, Machines.class);
                } else {
                    nearbyShape = (Shapes) getOneObjectAtOffset(-22, 0, Shapes.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(-22, 0, Machines.class);
                }
                break;
            case 2:
                y--;
                nearbyShape = (Shapes) getOneObjectAtOffset(0, -22, Shapes.class);
                nearbyMachine = (Machines) getOneObjectAtOffset(0, -22, Machines.class);
                break;
            case 3:
                x++;
                if(shapeID[0] == -1 && shapeID[1] == -1 && shapeID[4] == -1 && shapeID[5] == -1){
                    nearbyShape = (Shapes) getOneObjectAtOffset(3, 0, Shapes.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(3, 0, Machines.class);
                } else {
                    nearbyShape = (Shapes) getOneObjectAtOffset(22, 0, Shapes.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(22, 0, Machines.class);
                }
                break;
        }
        
        if(nearbyMachine != null && nearbyMachine.isOccupied()){
            occupied = true;
        } else {
            occupied = false;
        }
        
        if(belt != null && belt.getReal() && nearbyShape == null && !isLabel){
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
    
    public boolean checkIfLabel(){
        return isLabel;
    }
}
