import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Deposits here.
 * 
 * @author Andy
 * @version (a version number or a date)
 */
public class Deposits extends Resources
{
    private String deposit;
    private int[] circle = {1, 1, 1, 1, -1, -1, -1, -1};
    private int[] square = {2, 2, 2, 2, -1, -1, -1, -1};
    private int[] star = {3, 3, 3, 3, -1, -1, -1, -1};
    private int[] grey = {-1, -1, -1, -1, -1, -1, -1, -1};
    
    private int[] shapeID;
    private int[] colourID;
    private int rawColourID;
    
    private boolean isLabeled = false, colourDeposit;
    private FollowPoint point = new FollowPoint(1, false);
    
    public Deposits(String deposit){
        this.deposit = deposit;
        getImage().scale(40, 40);
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
    
    public int[] getDepositShape()
    {
        return shapeID;
    }
    
    public int[] getDepositShapeColour()
    {
        return colourID;
    }
    
    public int getRawColour()
    {
        return rawColourID;
    }
}
