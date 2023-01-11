import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1300, 900, 1); 
        setPaintOrder(Belts.class, ghostBlock.class);
        Color lightGray = new Color(228, 228, 226);
        Color gray = new Color(171, 171, 171);
        getBackground().setColor(lightGray);
        getBackground().fill();
        getBackground().setColor(gray);        
        for(int x = 0; x <= 900; x++){
            if(x % 45 == 0){
                    getBackground().drawLine(x + 200, 0, x + 200, 1100);
            }
        }
        for(int x = 0; x < 1100; x++){
            if(x % 45 == 0){
                    getBackground().drawLine(200, x, 1100, x);
            }
        }
        prepare();
        addDeposits();
    }
    
    public void prepare()
    {
        addObject(new Belts(true), 100, 100);
    }
        
    public void addDeposits()
    {
        addObject(new Deposits(new shape1(true), 2, 2), 0, 0);
        addObject(new Deposits(new shape1(true), 3, 2), 0, 0);
        addObject(new Deposits(new shape1(true), 4, 2), 0, 0);
        addObject(new Deposits(new shape1(true), 3, 3), 0, 0);
        addObject(new Deposits(new shape1(true), 4, 3), 0, 0);
        addObject(new Deposits(new shape1(true), 5, 3), 0, 0);
        addObject(new Deposits(new shape1(true), 3, 4), 0, 0);
        addObject(new Deposits(new shape1(true), 4, 4), 0, 0);
        addObject(new Deposits(new shape1(true), 5, 4), 0, 0);
        addObject(new Deposits(new shape1(true), 6, 4), 0, 0);
    }
}
