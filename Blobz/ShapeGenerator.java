import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class ShapeGenerator here.
 * 
 * @author Andy/Anson
 * @version (a version number or a date)
 */
public class ShapeGenerator extends Resources
{
    private int x = 0, y = 0, quadrant = 1, spawnX, spawnY, dir, layer = 1;
    private Integer[] corners = new Integer[8];
    private Integer[] colours = new Integer[8];
    private int rawColour;
    private FollowPoint point;
    private boolean isLabel, spawnColour;
    
    public ShapeGenerator (ArrayList<Integer> values, ArrayList<Integer> colour, int dir, boolean label){
        corners = new Integer[values.size()];
        corners = values.toArray(corners);
        colours = new Integer[colour.size()];
        colours = colour.toArray(colours);
        this.dir = dir;
        isLabel = label;
        spawnColour = false;
    }
    
    public ShapeGenerator (int colour, int dir, boolean label){
        rawColour = colour;
        this.dir = dir;
        isLabel = label;
        spawnColour = true;
    }
    
    public void act(){
        spawnPoint();
        if(!spawnColour)
        {
            for(int i = 0; i < corners.length; i++){
                if(isLabel && quadrant > 4){
                    quadrant = 1;
                    layer = 3;
                } else if(quadrant > 4){
                    quadrant = 1;
                    layer = 2;
                }
                
                if(i == 0 || i == 1 || i == 4 || i == 5){
                    x = getX() + (this.getImage().getWidth() / 2) - 1;
                } else {
                    x = getX() - (this.getImage().getWidth() / 2) + 1;
                }
                
                if(i == 0 || i == 3 || i == 4 || i == 7){
                    y = getY() - (this.getImage().getHeight() / 2) + 1;
                } else {
                    y = getY() + (this.getImage().getHeight() / 2) - 1;
                }
                
                switch(corners[i]){
                    case -1:
                        point.setID(i, -1);
                        break;
                    case 1:
                        getWorld().addObject(new Circle(quadrant, point, layer, colours[i]), x, y);
                        point.setID(i, 1);
                        break;
                    case 2:
                        getWorld().addObject(new Square(quadrant, point, layer, colours[i]), x, y);
                        point.setID(i, 2);
                        break;
                    case 3:
                        getWorld().addObject(new Star(quadrant, point, layer, colours[i]), x, y);
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
                    getWorld().addObject(new Red(isLabel, point), x, y);
                    point.setRawColour(1);
                    break;
                case (2):
                    getWorld().addObject(new Yellow(isLabel, point), x, y);
                    point.setRawColour(2);
                    break;
                case (4):
                    getWorld().addObject(new Blue(isLabel, point), x, y);
                    point.setRawColour(4);
                    break;
            }
        }
        
        getWorld().removeObject(this);
    }
    
    public void spawnPoint(){
        if(isLabel){
            point = new FollowPoint(dir, true);
        } else {
            point = new FollowPoint(dir, false);
        }

        getWorld().addObject(point, this.getX(), this.getY());
    }
}
