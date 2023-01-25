import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Deposits here.
 * 
 * @author Andy/Anson
 * @version Jan 24, 2023
 */
public class Deposits extends Actor
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
    private FollowPoint point = new FollowPoint(1, false, 0);
    
    public Deposits(String deposit){
        this.deposit = deposit;
        getImage().scale(40, 40);
        
        setTarget(circle, 1);
        setTarget(circle, -1);
        setTarget(square, 2);
        setTarget(square, -1);
        setTarget(star, 3);
        setTarget(star, -1);
        setTarget(grey, -1);
        setTarget(grey, -1);
    }
    
    public void act(){
        if(!isLabeled){
            label();
            isLabeled = true;
        }
    }
    
    /**
     * Labels the deposit with the correct material image
     */
    public void label(){
        if (deposit.equals("circle")){
            getWorld().addObject(new ShapeGenerator(circle, grey, 0, 1, 0), getX(), getY());
            shapeID = circle;
            colourID = grey;
            colourDeposit = false;
        } else if (deposit.equals("square")){
            getWorld().addObject(new ShapeGenerator(square, grey, 0, 1, 0), getX(), getY());
            shapeID = square;
            colourID = grey;
            colourDeposit = false;
        } else if (deposit.equals("star")){
            getWorld().addObject(new ShapeGenerator(star, grey, 0, 1, 0), getX(), getY());
            shapeID = star;
            colourID = grey;
            colourDeposit = false;
         } else if (deposit.equals("red")){
            getWorld().addObject(new ShapeGenerator(1, 0, 1), getX(), getY());
            rawColourID = 1;
            colourDeposit = true;
        } else if (deposit.equals("blue")){
            getWorld().addObject(new ShapeGenerator(4, 0, 1), getX(), getY());
            rawColourID = 4;
            colourDeposit = true;
        } else if (deposit.equals("yellow")){
            getWorld().addObject(new ShapeGenerator(2, 0, 1), getX(), getY());
            rawColourID = 2;
            colourDeposit = true;
        }
        updateGrid();
        
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
