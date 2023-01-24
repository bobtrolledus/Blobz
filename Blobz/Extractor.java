import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of class Extractor here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Extractor extends NarrowMachines
{
    private int spawnXCoord, spawnYCoord;
    private int[] corners = {3, -1, 2, -1, 2, 1, 3, 1};
    private int[] colours = {1, -1, 2, -1, 2, 1, 4, 2};
    private int colour;
    private boolean isColour;
    private FollowPoint point;
    
    public Extractor()
    {
        setImage("images/Machines/extractor.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public Extractor(boolean spawner)
    {
        setImage("images/Machines/extractor.png");
        getImage().scale(Utils.gridSize, Utils.gridSize);
        this.spawner = spawner;
    }
    
    /**
     * Act - do whatever the Extractor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(!real)
        {
            if(!spawned && spawner && Utils.getMouse() != null && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("3")))
            {
                Extractor mouseExtractor = new Extractor(false);
                getWorld().addObject(mouseExtractor, Utils.getMouseX(), Utils.getMouseY());
                spawned = true;
            }
            if(spawner && spawned)
            {
                if(checkDeselectKey())
                {
                    spawned = false;
                }
            }
            
            if(!spawner)
            {
                followMouse(1);
                gridSnap(this.getImage(), 1);
                updateRotation();
                place(Extractor.class, 1);
                if(!updatedImage)
                {
                    lastRotation = Utils.getDirection();
                    updateImage(lastRotation);
                    updatedImage = true;
                }
                if(checkDeselectKey())
                {
                    if(Utils.getMouseX() > 200 && Utils.getMouseY() < 1000)
                    {
                        for(Arrows arrow : getWorld().getObjects(Arrows.class))
                        {
                            getWorld().removeObject(arrow);
                        }
                    }
                    getWorld().removeObject(block);
                    getWorld().removeObject(this);
                }
            }
        }
        
        if(real) {
            if(!occupied)
            {
                getShape();
            }
            
            if(outputShape != null || colour != -1)
            {
                spawnShape();
            }
            
            if(isDeletedNarrow())
            {
                getWorld().removeObject(this);
            }
            
        }
    }
    
    public boolean checkDeselectKey()
    {
        if(Utils.getMouse() != null)
        {
            if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8") || Greenfoot.isKeyDown("9"))
            {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void getShape()
    {
        if(getWorld().getObjectsAt(getX(), getY(), Deposits.class).size() > 0)
        {
            Deposits temp = (Deposits) getWorld().getObjectsAt(getX(), getY(), Deposits.class).get(0);
            if(temp.isColourDeposit())
            {
                colour = temp.getRawColour();
                isColour = true;
            }
            else if (!temp.isColourDeposit())
            {
                isColour = false;
                outputShape = temp.getDepositShape();
                outputColour = temp.getDepositShapeColour();
            }  
        }
        occupied = true;
    }
    
    public void spawnShape()
    {
        point = (FollowPoint)getOneIntersectingObject(FollowPoint.class);
        
        if(timer.millisElapsed() > Utils.getExtractorDelay() && getObjectsInRange(25, FollowPoint.class).size() < 2)
        {
            if(isColour)
            {
                getWorld().addObject(new ShapeGenerator(colour, direction, -1), spawnXCoord, spawnYCoord);
                timer.mark();
            }
            if(!isColour)
            {
                getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, -1), spawnXCoord, spawnYCoord);
                timer.mark();
            }
            outputShape = null;
            colour = -1;
            occupied = false;
        }
    }
    
    protected void addedToWorld(World world)
    {
        timer.mark();
        if(real)
        {
            switch (Utils.getDirection())
            {
                case 0:
                    spawnXCoord = getX();
                    spawnYCoord = getY() + 20;
                    direction = 0;
                    setRotation(180);
                    break;
                case 1:
                    spawnXCoord = getX() - 21;
                    spawnYCoord = getY();
                    direction = 1;
                    setRotation(-90);
                    break;
                case 2:
                    spawnXCoord = getX();
                    spawnYCoord = getY() - 21;
                    direction = 2;
                    setRotation(0);
                    break;
                case 3:
                    spawnXCoord = getX() + 20;
                    spawnYCoord = getY();
                    direction = 3;
                    setRotation(90);
                    break;
            }
        }
    }
}
