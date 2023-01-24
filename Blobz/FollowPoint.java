import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class FollowPoint here.
 * 
 * @author Andy/Anson
 * @version (a version number or a date)
 */
public class FollowPoint extends UtilsBlocks
{
    private int dir = 0, x, y, colour;
    private RotationPoint rotation;
    private ArrayList<Integer> shapeID = new ArrayList<Integer>(8);
    private ArrayList<Integer> shapeColour = new ArrayList<Integer>(8);
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
        for(int i = 0; i < 8; i++)
        {
            shapeID.add(0);
        }
        for(int i = 0; i < 8; i++)
        {
            shapeColour.add(0);
        }
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
                if(shapeID.get(2) == -1 && shapeID.get(3) == -1 && shapeID.get(6) == -1 && shapeID.get(7) == -1){
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
                if(shapeID.get(0) == -1 && shapeID.get(1) == -1 && shapeID.get(4) == -1 && shapeID.get(5) == -1){
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
        shapeID.set(index, shapeNum);
    }
    
    public void setColour(int index, int colourNum)
    {
        shapeColour.set(index, colourNum);
    }
    
    public void setRawColour(int colour)
    {
        this.colour = colour;
    }
    
    public ArrayList<Integer> getColour()
    {
        return shapeColour;
    }
    
    public ArrayList<Integer> getShape()
    {
        return shapeID;
    }
    
    public int getRawColour()
    {
        return colour;
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
