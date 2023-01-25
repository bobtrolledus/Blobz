import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * The actor called to generate a Material object
 * 
 * @author Andy/Anson
 * @version Jan 23 2023
 */
public class ShapeGenerator extends Actor
{
    private int x = 0, y = 0, quadrant = 1, spawnX, spawnY, dir, scale, label;
    private Integer[] corners = new Integer[8];
    private Integer[] colours = new Integer[8];
    private int rawColour;
    private FollowPoint point;
    private boolean isColour;
    private double value;

    /**
     * Constructor for shapes
     */
    public ShapeGenerator (ArrayList<Integer> values, ArrayList<Integer> colour, int dir, int label, double value){
        corners = new Integer[values.size()];
        corners = values.toArray(corners);
        colours = new Integer[colour.size()];
        colours = colour.toArray(colours);
        this.dir = dir;
        this.label = label;
        isColour = false;
        this.value = value;
    }
    
    /**
     * Constructor for colours
     */
    public ShapeGenerator (int colour, int dir, int label){
        rawColour = colour;
        this.dir = dir;
        this.label = label;
        isColour = true;
    }
    
    public void act(){
        spawnPoint();
        
        if(!isColour)
        {
            spawnShape();
        }
        
        if(isColour)
        {
            spawnColour();
        }
        
        getWorld().removeObject(this);
    }
    
    /**
     * Spawns the follow point that the object follows
     */
    public void spawnPoint(){
        if(label == -1){
            point = new FollowPoint(dir, false, value);
        } else {
            point = new FollowPoint(dir, true, value);
        }

        getWorld().addObject(point, this.getX(), this.getY());
    }
    
    /**
     * Spawns the shape
     */
    public void spawnShape(){
        for(int i = 0; i < corners.length; i++){
            if (label == -1){
                if (quadrant < 5){
                    scale = 19;
                } else {
                    scale = 15;
                }
            } else if (label == 1){
                if (quadrant < 5){
                    scale = 17;
                } else {
                    scale = 13;
                }
            } else if (label == 2){
                if (quadrant < 5){
                    scale = 21;
                } else {
                    scale = 17;
                }
            }
            
            if(quadrant == 1 || quadrant == 2 || quadrant == 5 || quadrant == 6){
                x = point.getX() + (this.getImage().getWidth() / 2) - 1;
            } else {
                x = point.getX() - (this.getImage().getWidth() / 2) + 1;
            }
                
            if(quadrant == 1 || quadrant == 4 || quadrant == 5 || i == 8){
                y = point.getY() - (this.getImage().getHeight() / 2) + 1;
            } else {
                y = point.getY() + (this.getImage().getHeight() / 2) - 1;
            }
                
            switch(corners[i]){
                case -1:
                    point.setID(i, -1);
                    break;
                case 1:
                    getWorld().addObject(new Shape(1, quadrant, point, colours[i], scale), x, y);
                    point.setID(i, 1);
                    break;
                case 2:
                    getWorld().addObject(new Shape(2, quadrant, point, colours[i], scale), x, y);
                    point.setID(i, 2);
                    break;
                case 3:
                    getWorld().addObject(new Shape(3, quadrant, point, colours[i], scale), x, y);
                    point.setID(i, 3);
                    break;
            }
            point.setColour(i, colours[i]);
            quadrant++;
        }
    }
    
    /**
     * Spawns the colour blob
     */
    public void spawnColour(){
        switch (rawColour){
            case (1):
                getWorld().addObject(new Colours(1, point), x, y);
                point.setRawColour(1);
                break;
            case (2):
                getWorld().addObject(new Colours(2, point), x, y);
                point.setRawColour(2);
                break;
            case (4):
                getWorld().addObject(new Colours(4, point), x, y);
                point.setRawColour(4);
                break;
        }
    }
}
