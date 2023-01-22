import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Deposits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deposits extends Resources
{
    private String deposit;
    private int[] circle = {1, 1, 1, 1, -1, -1, -1, -1};
    private int[] square = {2, 2, 2, 2, -1, -1, -1, -1};
    private int[] star = {3, 3, 3, 3, -1, -1, -1, -1};
    private int[] grey = {-1, -1, -1, -1, -1, -1, -1, -1};
    private boolean isLabeled = false;
    private FollowPoint point;
    
    public Deposits(String deposit){
        this.deposit = deposit;
        getImage().scale(40, 40);
    }
    
    public void act(){
        if (!isLabeled){
            if (deposit.equals("circle")){
                getWorld().addObject(new ShapeGenerator(circle, grey, 0, true), getX(), getY());
            } else if (deposit.equals("square")){
                getWorld().addObject(new ShapeGenerator(square, grey, 0, true), getX(), getY());
            } else if (deposit.equals("star")){
                getWorld().addObject(new ShapeGenerator(star, grey, 0, true), getX(), getY());
            } else if (deposit.equals("red")){
                getWorld().addObject(new Red(true), getX(), getY());
            } else if (deposit.equals("blue")){
                getWorld().addObject(new Blue(true), getX(), getY());
            } else if (deposit.equals("yellow")){
                getWorld().addObject(new Yellow(true), getX(), getY());
            }
            
            isLabeled = true;
        }
    }
}
