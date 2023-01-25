import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class ShapeGenerator here.
 * 
 * @author Andy/Anson
 * @version (a version number or a date)
 */
public class ShapeGenerator extends Actor
{
    private int x = 0, y = 0, quadrant = 1, spawnX, spawnY, dir, scale, label;
    private Integer[] corners = new Integer[8];
    private Integer[] colours = new Integer[8];
    private int rawColour;
    private FollowPoint point;
    private boolean spawnColour;
    private double value;
    
    public ShapeGenerator (ArrayList<Integer> values, ArrayList<Integer> colour, int dir, int label, double value){
        corners = new Integer[values.size()];
        corners = values.toArray(corners);
        colours = new Integer[colour.size()];
        colours = colour.toArray(colours);
        this.dir = dir;
        this.label = label;
        spawnColour = false;
        this.value = value;
    }
    
    public ShapeGenerator (int colour, int dir, int label){
        rawColour = colour;
        this.dir = dir;
        this.label = label;
        spawnColour = true;
    }
    
    public void act(){
        spawnPoint();
        
        if(!spawnColour)
        {
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
                        getWorld().addObject(new Circle(quadrant, point, colours[i], scale), x, y);
                        point.setID(i, 1);
                        break;
                    case 2:
                        getWorld().addObject(new Square(quadrant, point, colours[i], scale), x, y);
                        point.setID(i, 2);
                        break;
                    case 3:
                        getWorld().addObject(new Star(quadrant, point, colours[i], scale), x, y);
                        point.setID(i, 3);
                        break;
                }
                point.setColour(i, colours[i]);
                quadrant++;
            }
        }
        
        if(spawnColour)
        {
            switch (rawColour){
                case (1):
                    getWorld().addObject(new Red(label, point), x, y);
                    point.setRawColour(1);
                    break;
                case (2):
                    getWorld().addObject(new Yellow(label, point), x, y);
                    point.setRawColour(2);
                    break;
                case (4):
                    getWorld().addObject(new Blue(label, point), x, y);
                    point.setRawColour(4);
                    break;
            }
        }
        
        getWorld().removeObject(this);
    }
    
    public void spawnPoint(){
        if(label == -1){
            point = new FollowPoint(dir, false, value);
        } else {
            point = new FollowPoint(dir, true, value);
        }

        getWorld().addObject(point, this.getX(), this.getY());
    }
}
