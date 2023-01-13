import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShapeGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShapeGenerator extends Resources
{
    private int x = 0, y = 0, quadrant = 1;
    private int[] corners = new int[8];
    private Extractor extractor;
    private FollowPoint point, guider;
    
    public ShapeGenerator (int[] values, Extractor extractor){
        for(int i = 0; i < corners.length; i++){
            corners[i] = values[i];
        }
        this.extractor = extractor;
    }
    
    public void act(){
        spawnPoint();
        
        for(int i = 0; i < corners.length; i++){
            if(i == 0 || i == 1 || i == 4 || i == 5){
                x = extractor.getSpawnXCoord() + (this.getImage().getWidth() / 2) - 1;
            } else {
                x = extractor.getSpawnXCoord() - (this.getImage().getWidth() / 2) + 1;
            }
            
            if(i == 0 || i == 3 || i == 4 || i == 7){
                y = extractor.getSpawnYCoord() - (this.getImage().getHeight() / 2) + 1;
            } else {
                y = extractor.getSpawnYCoord() + (this.getImage().getHeight() / 2) - 1;
            }
            
            switch(corners[i]){
                case -1:
                    break;
                case 1:
                    getWorld().addObject(new Circle(false, quadrant, point), x, y);
                    break;
                case 2:
                    getWorld().addObject(new Square(false, quadrant, point), x, y);
                    break;
                case 3:
                    getWorld().addObject(new Star(false, quadrant, point), x, y);
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
        point = new FollowPoint();
        getWorld().addObject(point, this.getX(), this.getY());
    }
}
