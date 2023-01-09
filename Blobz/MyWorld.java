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
        Color lightGray = new Color(229, 228, 226);
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
    }
}
