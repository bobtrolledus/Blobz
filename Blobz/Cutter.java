import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cutter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cutter extends WideMachines
{
    private int inputXCoord, inputYCoord, spawnX1Coord, spawnY1Coord, spawnX2Coord, spawnY2Coord;
    private int[] cut1 = new int[8];
    private int[] cut2 = new int[8];
    private int[] cutColour1 = new int[8];
    private int[] cutColour2 = new int[8];
    public Cutter()
    {
        setImage("images/Machines/cutter.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
    }
    
    public Cutter(boolean spawner)
    {
        setImage("images/Machines/cutter.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
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
            if(!spawned && spawner && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("2")))
            {
                Cutter mouseCutter = new Cutter(false);
                getWorld().addObject(mouseCutter, Utils.getMouseX(), Utils.getMouseY());
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
                followMouse(2);
                gridSnap(this.getImage(), 2);
                updateRotation();
                place(Cutter.class, 2);
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
        if(real)
        {   
            if(!occupied)
            {
                if(getWorld().getObjectsAt(inputXCoord, inputYCoord, Shapes.class).size() > 0)
                {
                    getShape();
                    getColour();
                }
            }
            
            if(outputShape != null)
            {
                spawnShape();
            }
            
            if(isDeletedWide())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    public boolean checkDeselectKey()
    {
        if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7") || Greenfoot.isKeyDown("8"))
        {
            return true;
        } else {
            return false;
        }
    }
    
    public void getShape()
    {       
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
            for(int i = 0; i < cut1.length; i++)
            {
                cut1[i] = -1;
                cut2[i] = -1;
            }
        }
    }
    
    public void getColour()
    {
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputColour = tempPoint.getColour();
            getWorld().removeObject(tempPoint);
            for(int i = 0; i < cutColour1.length; i++)
            {
                cutColour1[i] = -1;
                cutColour2[i] = -1;
            }
            occupied = true;
        }
    }
    
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getCutterDelay() && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shapes.class).size() < 1 && getWorld().getObjectsAt(spawnX2Coord, spawnY2Coord, Shapes.class).size() < 1)
        {
            cut1[2] = outputShape[2];
            cut1[3] = outputShape[3];
            cut1[6] = outputShape[6];
            cut1[7] = outputShape[7];
            cut2[0] = outputShape[0];
            cut2[1] = outputShape[1];
            cut2[4] = outputShape[4];
            cut2[5] = outputShape[5];
            cutColour1[2] = outputColour[2];
            cutColour1[3] = outputColour[3];
            cutColour1[6] = outputColour[6];
            cutColour1[7] = outputColour[7];
            cutColour2[0] = outputColour[0];
            cutColour2[1] = outputColour[1];
            cutColour2[4] = outputColour[4];
            cutColour2[5] = outputColour[5];
            getWorld().addObject(new ShapeGenerator(cut1, cutColour1, direction, false), spawnX1Coord, spawnY1Coord);
            getWorld().addObject(new ShapeGenerator(cut2, cutColour2, direction, false), spawnX2Coord, spawnY2Coord);
            outputShape = null;
            occupied = false;
            timer.mark();
        }
    }
    
    protected void addedToWorld(World world)
    {
        checkMirrored();
        timer.mark();
        if(real)
        {
            switch (Utils.getDirection())
            {
                case 0:
                    inputXCoord = getX() + ((Utils.gridSize / 2) * mirrored);
                    inputYCoord = getY() - (Utils.gridSize / 2);
                    spawnX1Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    spawnX2Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    spawnY2Coord = getY() + (Utils.gridSize / 2);
                    setDirection(0);
                    setRotation(180);
                    break;
                case 1:
                    inputXCoord = getX() + (Utils.gridSize / 2);
                    inputYCoord = getY() + ((Utils.gridSize / 2) * mirrored);
                    spawnX1Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY1Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    spawnX2Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY2Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    setDirection(1);
                    setRotation(-90);
                    break;
                case 2:
                    inputXCoord = getX() - ((Utils.gridSize / 2) * mirrored);
                    inputYCoord = getY() + (Utils.gridSize / 2) - 1;
                    spawnX1Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() - (Utils.gridSize / 2) - 1;
                    spawnX2Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    spawnY2Coord = getY() - (Utils.gridSize / 2) - 1;
                    setDirection(2);
                    setRotation(0);
                    break;
                case 3:
                    inputXCoord = getX() - (Utils.gridSize / 2);
                    inputYCoord = getY() - ((Utils.gridSize / 2) * mirrored);
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    setDirection(3);
                    setRotation(90);
                    break;
            }
        }
    }
}
