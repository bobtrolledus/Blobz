import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stacker here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Stacker extends WideMachines
{
    private int spawnX1Coord, spawnY1Coord, inputX1Coord, inputX2Coord, inputY1Coord, inputY2Coord;
    private int[] shapeID1, shapeID2, colourID1, colourID2;
    private int[] outputShape = new int[8];
    private int[] outputColour = new int[8];
    private boolean grabbedShape1, grabbedShape2;
    public Stacker()
    {
        setImage("images/Machines/stacker.png");
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
    }
    
    public Stacker(boolean spawner)
    {
        setImage("images/Machines/stacker.png");
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
            if(!spawned && spawner && (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("8")))
            {
                Stacker mouseStacker = new Stacker(false);
                getWorld().addObject(mouseStacker, Utils.getMouseX(), Utils.getMouseY());
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
                place(Stacker.class, 2);
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
                if(!grabbedShape1 || !grabbedShape2)
                {
                    getShape();   
                }
            }

            if(outputShape != null && grabbedShape1 && grabbedShape2)
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
        if(Greenfoot.isKeyDown("escape") || Greenfoot.isKeyDown("1") || Greenfoot.isKeyDown("2") || Greenfoot.isKeyDown("3") || Greenfoot.isKeyDown("4") || Greenfoot.isKeyDown("5") || Greenfoot.isKeyDown("6") || Greenfoot.isKeyDown("7"))
        {
            return true;
        } else {
            return false;
        }
    }
    
    public void getShape()
    {
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            shapeID1 = tempPoint.getShape();
            if((shapeID1[4] != -1 || shapeID1[5] != -1 || shapeID1[6] != -1 || shapeID1[7] != -1))
            {
                shapeID1 = null;
            } else {
                if(!grabbedShape1)
                {
                    colourID1 = tempPoint.getColour();
                    grabbedShape1 = true;
                    getWorld().removeObject(tempPoint);
                }
            }
        }
        if(getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).get(0);
            shapeID2 = tempPoint.getShape();
            if((shapeID2[4] != -1 || shapeID2[5] != -1 || shapeID2[6] != -1 || shapeID2[7] != -1))
            {
                shapeID2 = null;
            } else {
                if(!grabbedShape2)
                {
                    colourID2 = tempPoint.getColour();
                    grabbedShape2 = true;
                    getWorld().removeObject(tempPoint);
                }
            }
        }
        if(grabbedShape1 && grabbedShape2)
        {
            for(int i = 0; i < 4; i++)
            {
                outputShape[i] = shapeID1[i];
                outputColour[i] = colourID1[i];
            }
            for(int i = 4; i < 8;  i++)
            {
                outputShape[i] = shapeID2[i - 4];
                outputColour[i] = colourID2[i -4];
            }
            occupied = true;
        }
    }
    
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getStackingDelay() && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shapes.class).size() < 1)
        {
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, false), spawnX1Coord, spawnY1Coord);
            shapeID1 = null;
            shapeID2 = null;
            colourID1 = null;
            colourID2 = null;
            outputShape = new int[8]; 
            outputColour = new int[8];
            grabbedShape1 = false;
            grabbedShape2 = false;
            timer.mark();
            occupied = false;
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
                    spawnX1Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() - (Utils.gridSize / 2);
                    inputX2Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    inputY2Coord = getY() - (Utils.gridSize / 2);
                    setDirection(0);
                    setRotation(180);
                    break;
                case 1:
                    spawnX1Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY1Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    inputX1Coord = getX() + (Utils.gridSize / 2) - 1;
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord;
                    inputY2Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    setDirection(1);
                    setRotation(-90);
                    break;
                case 2:
                    spawnX1Coord = getX() - ((Utils.gridSize / 2) * mirrored);
                    spawnY1Coord = getY() - (Utils.gridSize / 2) - 1;
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() + (Utils.gridSize / 2) - 1;
                    inputX2Coord = getX() + ((Utils.gridSize / 2) * mirrored);
                    inputY2Coord = getY() + (Utils.gridSize / 2) - 1;
                    setDirection(2);
                    setRotation(0);
                    break;
                case 3:
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() - ((Utils.gridSize / 2) * mirrored);
                    inputX1Coord = getX() - (Utils.gridSize / 2);
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord;
                    inputY2Coord = getY() + ((Utils.gridSize / 2) * mirrored);
                    setDirection(3);
                    setRotation(90);
                    break;
            }
        }
    }
}
