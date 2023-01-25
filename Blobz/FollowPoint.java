import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A point that shapes and colours follow. Also contains information about shapes or colour that are following the point.
 * 
 * @author Andy/Anson
 * @version Jan 24, 2023
 */
public class FollowPoint extends UtilsBlocks
{
    private int dir = 0, x, y, colour;
    private RotationPoint rotation;
    private ArrayList<Integer> shapeID = new ArrayList<Integer>(8);
    private ArrayList<Integer> oldShape = new ArrayList<Integer>(8);
    private ArrayList<Integer> shapeColour = new ArrayList<Integer>(8);
    private Belts belt;
    private Boolean stopped = false;
    private Material nearbyMaterial;
    private Machines nearbyMachine;
    private boolean occupied = false;
    private boolean isLabel;
    private double value;
    
    public FollowPoint(int dir, boolean isLabel, double value) {
        getImage().scale(1, 1);
        getImage().setTransparency(0);
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
        
        this.value = value;
        if(value > 50){
            value = 50;
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
                if(shapeID.get(1) == -1 && shapeID.get(2) == -1 && shapeID.get(5) == -1 && shapeID.get(6) == -1){
                    nearbyMaterial = (Material) getOneObjectAtOffset(0, 3, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(0, 3, Machines.class);
                } else {
                    nearbyMaterial = (Material) getOneObjectAtOffset(0, 21, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(0, 21, Machines.class);
                }
                break;
            case 1:
                x--;
                if(shapeID.get(2) == -1 && shapeID.get(3) == -1 && shapeID.get(6) == -1 && shapeID.get(7) == -1){
                    nearbyMaterial = (Material) getOneObjectAtOffset(-3, 0, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(-3, 0, Machines.class);
                } else {
                    nearbyMaterial = (Material) getOneObjectAtOffset(-21, 0, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(-21, 0, Machines.class);
                }
                break;
            case 2:
                y--;
                if(shapeID.get(0) == -1 && shapeID.get(3) == -1 && shapeID.get(4) == -1 && shapeID.get(7) == -1){
                    nearbyMaterial = (Material) getOneObjectAtOffset(0, -3, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(0, -3, Machines.class);
                } else {
                    nearbyMaterial = (Material) getOneObjectAtOffset(0, -21, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(0, -21, Machines.class);
                }
                break;
            case 3:
                x++;
                if(shapeID.get(0) == -1 && shapeID.get(1) == -1 && shapeID.get(4) == -1 && shapeID.get(5) == -1){
                    nearbyMaterial = (Material) getOneObjectAtOffset(3, 0, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(3, 0, Machines.class);
                } else {
                    nearbyMaterial = (Material) getOneObjectAtOffset(21, 0, Material.class);
                    nearbyMachine = (Machines) getOneObjectAtOffset(21, 0, Machines.class);
                }
                break;
        }
        
        if(nearbyMachine != null && nearbyMachine.isOccupied()){
            occupied = true;
        } else {
            occupied = false;
        }
        
        if((belt != null && belt.getReal()) && nearbyMaterial == null && !occupied && !isLabel){            
            if(getOneObjectAtOffset(x, y, WideMachines.class) == null || getOneObjectAtOffset(x, y, Material.class) == null)
            {
                setLocation(x, y);
            }
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
    
    public double getValue(){
        return value;
    }
}
