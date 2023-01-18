import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cutter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cutter extends Machines
{
    private boolean spawner = false, real = false, updatedImage = false;
    private int lastRotation, direction;
    private int inputXCoord, inputYCoord, spawnX1Coord, spawnY1Coord, spawnX2Coord, spawnY2Coord;
    private SimpleTimer timer = new SimpleTimer();
    private int[] outputShape;
    private int[] cut1 = new int[8];
    private int[] cut2 = new int[8];
    private Shapes shape;
    public Cutter()
    {
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
    }
    
    public Cutter(boolean spawner)
    {
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
            if(spawner && Greenfoot.mouseClicked(this))
            {
                Cutter mouseCutter = new Cutter(false);
                getWorld().addObject(mouseCutter, Utils.getMouseX(), Utils.getMouseY());
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
        if(real)
        {
            getShape();
            if(outputShape != null)
            {
                spawnShape();
            }
        }
    }
    
    public void updateRotation()
    {
        if(Utils.getDirection() != lastRotation)
        {
            updatedImage = false;
        }
    }
    
    public void getShape()
    {       
        if(getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputXCoord, inputYCoord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
            getWorld().removeObject(tempPoint);
            for(int i = 0; i < cut1.length; i++)
            {
                cut1[i] = -1;
                cut2[i] = -1;
            }
        }
    }
    
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getBalancerDelay())
        {
            cut1[2] = outputShape[2];
            cut1[3] = outputShape[3];
            cut1[6] = outputShape[6];
            cut1[7] = outputShape[7];
            cut2[0] = outputShape[0];
            cut2[1] = outputShape[1];
            cut2[4] = outputShape[4];
            cut2[5] = outputShape[5];
            getWorld().addObject(new ShapeGenerator(cut1, direction), spawnX1Coord, spawnY1Coord);
            getWorld().addObject(new ShapeGenerator(cut2, direction), spawnX2Coord, spawnY2Coord);
            outputShape = null;
            timer.mark();
        }
    }
    
    protected void addedToWorld(World world)
    {
        getShape();
        timer.mark();
        if(real)
        {
            switch (Utils.getDirection())
            {
                case 0:
                    inputXCoord = getX() + (Utils.gridSize / 2);
                    inputYCoord = getY() - (Utils.gridSize / 2);
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() + (Utils.gridSize / 2);
                    direction = 180;
                    setRotation(180);
                    break;
                case 1:
                    inputXCoord = getX() + (Utils.gridSize / 2);
                    inputYCoord = getY() + (Utils.gridSize / 2);
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() - (Utils.gridSize / 2);
                    spawnX2Coord = getX() - (Utils.gridSize / 2);
                    spawnY2Coord = getY() + (Utils.gridSize / 2);
                    direction = 270;
                    setRotation(-90);
                    break;
                case 2:
                    inputXCoord = getX() - (Utils.gridSize / 2);
                    inputYCoord = getY() + (Utils.gridSize / 2);
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() - (Utils.gridSize / 2);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() - (Utils.gridSize / 2);
                    direction = 0;
                    setRotation(0);
                    break;
                case 3:
                    inputXCoord = getX() - (Utils.gridSize / 2);
                    inputYCoord = getY() - (Utils.gridSize / 2);
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() - (Utils.gridSize / 2);
                    spawnX2Coord = getX() + (Utils.gridSize / 2);
                    spawnY2Coord = getY() + (Utils.gridSize / 2);
                    direction = 90;
                    setRotation(90);
                    break;
            }
        }
    }
}
