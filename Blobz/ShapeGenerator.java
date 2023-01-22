import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShapeGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShapeGenerator extends Resources
{
    private int x = 0, y = 0, quadrant = 1, spawnX, spawnY, dir, layer = 1;
    private int[] corners = new int[8];
    private int[] colours = new int[8];
    private int rawColour;
    private FollowPoint point;
    private boolean isLabel, spawnColour;
    
    public ShapeGenerator (int[] values, int[] colours, int dir, boolean label){
        for(int i = 0; i < corners.length; i++){
            corners[i] = values[i];
            this.colours[i] = colours[i];
        }
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
                if(isLabel){
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
