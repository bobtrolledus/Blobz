import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private int[] colours = {5, -1, 3, -1, 4, 6, 2, 6};
    
    private int colour;
    private int[] shapeID;
    private int[] colourID;
    private boolean isColour;
    private FollowPoint point;
    
    public Extractor()
    {
        getImage().scale(Utils.gridSize, Utils.gridSize);
        real = true;
    }
    
    public Extractor(boolean spawner)
    {
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
            if(spawner && Greenfoot.mouseClicked(this))
            {
                Extractor mouseExtractor = new Extractor(false);
                getWorld().addObject(mouseExtractor, Utils.getMouseX(), Utils.getMouseY());
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
                if(Greenfoot.isKeyDown("escape"))
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
            
            if(shapeID != null || colour != -1)
            {
                spawnShape();
            }
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
                shapeID = temp.getDepositShape();
                colourID = temp.getDepositShapeColour();
            }  
        }
        occupied = true;
    }
    
    public void spawnShape()
    {
        point = (FollowPoint)getOneIntersectingObject(FollowPoint.class);
        
        if(timer.millisElapsed() > Utils.getExtractorDelay() && getObjectsInRange(21, FollowPoint.class).size() < 2)
        {
            if(isColour)
            {
                getWorld().addObject(new ShapeGenerator(colour, direction, false), spawnXCoord, spawnYCoord);
                timer.mark();
            }
            if(!isColour)
            {
                getWorld().addObject(new ShapeGenerator(shapeID, colourID, direction, false), spawnXCoord, spawnYCoord);
                timer.mark();
            }
            shapeID = null;
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
                    spawnYCoord = getY() + 21;
                    direction = 0;
                    setRotation(180);
                    break;
                case 1:
                    spawnXCoord = getX() - 20;
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
