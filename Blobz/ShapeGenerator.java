import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShapeGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShapeGenerator extends Resources
{
    private int x = 0, y = 0, quadrant = 1, spawnX, spawnY, dir;
    private int[] corners = new int[8];
    private FollowPoint point;
    
    public ShapeGenerator (int[] values, int dir){
        for(int i = 0; i < corners.length; i++){
            corners[i] = values[i];
        }
        this.dir = dir;
    }
    
    public void act(){
        spawnPoint();
        
        for(int i = 0; i < corners.length; i++){
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
                    getWorld().addObject(new Circle(false, quadrant, point), x, y);
                    point.setID(i, 1);
                    break;
                case 2:
                    getWorld().addObject(new Square(false, quadrant, point), x, y);
                    point.setID(i, 2);
                    break;
                case 3:
                    getWorld().addObject(new Star(false, quadrant, point), x, y);
                    point.setID(i, 3);
                    break;
            }
            
            quadrant++;
            
            if(quadrant > 4){
                quadrant = 1;
            }
        }
        
        getWorld().removeObject(this);
    }
    
    public void spawnPoint(){
        point = new FollowPoint(dir);
        getWorld().addObject(point, this.getX(), this.getY());
    }
}
