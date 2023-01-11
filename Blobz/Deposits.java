import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Deposits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deposits extends Resources
{
    private int xPos, yPos;
    private Boolean firstSpawn = true;
    
    private Material deposit;
    
    public Deposits(Material deposit, int xPos, int yPos){
        this.deposit = deposit;
        this.xPos = xPos;
        this.yPos = yPos;
        this.getImage().scale(45, 45);
        deposit.getImage().scale(20, 20);
    }
    
    public void act(){
        if (firstSpawn){
            this.setLocation(xPos * 45 + 200 - (this.getImage().getWidth() / 2), yPos * 45 - (this.getImage().getHeight() / 2));
            getWorld().addObject(deposit, this.getX(), this.getY());
            firstSpawn = false;
        }
    }
}
