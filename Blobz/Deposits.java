import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Deposits here.
 * 
 * @author Andy/Anson
 * @version (a version number or a date)
 */
public class Deposits extends Resources
{
    private String deposit;
    private ArrayList<Integer> circle = new ArrayList<Integer>();
    private ArrayList<Integer> square = new ArrayList<Integer>();
    private ArrayList<Integer> star = new ArrayList<Integer>();
    private ArrayList<Integer> grey = new ArrayList<Integer>();
    
    private ArrayList<Integer> shapeID;
    private ArrayList<Integer> colourID;
    private int rawColourID;
    
    private boolean isLabeled = false, colourDeposit;
    private FollowPoint point = new FollowPoint(1, false);
    
    public Deposits(String deposit){
        this.deposit = deposit;
        getImage().scale(40, 40);
        
        setTarget(circle, 1);
        setTarget(circle, -1);
        setTarget(square, 1);
        setTarget(square, -1);
        setTarget(star, 3);
        setTarget(star, -1);
        setTarget(grey, -1);
        setTarget(grey, -1);
    }
    
    public void act(){
        if (!isLabeled){
            if (deposit.equals("circle")){
                getWorld().addObject(new ShapeGenerator(circle, grey, 0, true), getX(), getY());
                shapeID = circle;
                colourID = grey;
                colourDeposit = false;
            } else if (deposit.equals("square")){
                getWorld().addObject(new ShapeGenerator(square, grey, 0, true), getX(), getY());
                shapeID = square;
                colourID = grey;
                colourDeposit = false;
            } else if (deposit.equals("star")){
                getWorld().addObject(new ShapeGenerator(star, grey, 0, true), getX(), getY());
                shapeID = star;
                colourID = grey;
                colourDeposit = false;
            } else if (deposit.equals("red")){
                getWorld().addObject(new Red(true, point), getX(), getY());
                rawColourID = 1;
                colourDeposit = true;
            } else if (deposit.equals("blue")){
                getWorld().addObject(new Blue(true, point), getX(), getY());
                rawColourID = 4;
                colourDeposit = true;
            } else if (deposit.equals("yellow")){
                getWorld().addObject(new Yellow(true, point), getX(), getY());
                rawColourID = 2;
                colourDeposit = true;
            }
            updateGrid();
            isLabeled = true;
        }
    }
    
    public void setTarget(ArrayList<Integer> list, int value)
    {
        for(int i = 0; i < 4; i++)
        {
            list.add(value);
        }
    }
    
    public void updateGrid()
    {
        int gridPositionX = (int) (getX() - 200) / Utils.gridSize;
        int gridPositionY = (int) (getY() / Utils.gridSize);
        Utils.fillSpaceDeposit(gridPositionY, gridPositionX, this);
    }
    
    public boolean isColourDeposit()
    {
        return colourDeposit;
    }
    
    public ArrayList<Integer> getDepositShape()
    {
        return shapeID;
    }
    
    public ArrayList<Integer> getDepositShapeColour()
    {
        return colourID;
    }
    
    public int getRawColour()
    {
        return rawColourID;
    }
}
