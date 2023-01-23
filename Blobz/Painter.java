import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Painter here.
 * 
 * @author Anson
 * @version (a version number or a date)
 */
public class Painter extends WideMachines
{
    private int spawnX1Coord, spawnY1Coord, inputX1Coord, inputY1Coord, inputX2Coord, inputY2Coord;
    private int colour;
    private boolean grabbedShape, grabbedColour;
    public Painter()
    {
        getImage().scale(Utils.gridSize * 2, Utils.gridSize);
        real = true;
    }
    
    public Painter(boolean spawner)
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
                Painter mousePainter = new Painter(false);
                getWorld().addObject(mousePainter, Utils.getMouseX(), Utils.getMouseY());
            }
            
            if(!spawner)
            {
                followMouse(1);
                gridSnap(this.getImage(), 2);
                updateRotation();
                place(Painter.class, 2);
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
            if(!occupied)
            {
                if(!grabbedShape)
                {
                    getShape();
                    getColourShape();
                }
                if(!grabbedColour)
                {
                    getColourPigment();
                }
            }

            if(outputShape != null && grabbedShape && grabbedColour)
            {
                spawnShape();
            }
        }
    }
    
    public void getShape()
    {
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            outputShape = tempPoint.getShape();
            direction = tempPoint.getRotation();
            grabbedShape = true;
        }
    }
    
    public void getColourShape()
    {
        if(getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).size() > 0)
        {
            FollowPoint tempPoint = getWorld().getObjectsAt(inputX1Coord, inputY1Coord, FollowPoint.class).get(0);
            outputColour = tempPoint.getColour();
            getWorld().removeObject(tempPoint);
        }
    }
    
    public void getColourPigment()
    {
        if(getWorld().getObjectsAt(inputX2Coord, inputY2Coord, Colours.class).size() > 0)
        {
            Colours temp = (Colours) getWorld().getObjectsAt(inputX2Coord, inputY2Coord, Colours.class).get(0);
            if(temp.getX() == inputX2Coord && temp.getY() == inputY2Coord && outputColour != null)
            {
                FollowPoint tempPoint = getWorld().getObjectsAt(inputX2Coord, inputY2Coord, FollowPoint.class).get(0);
                colour = tempPoint.getRawColour();
                for(int i = 0; i < outputColour.length; i++)
                {
                    if(outputColour[i] == -1)
                    {
                        outputColour[i] = colour;
                    }
                    else if(outputColour[i] != colour && (outputColour[i] != 3 || outputColour[i] != 5 || outputColour[i] != 6))
                    {
                        outputColour[i] += colour;
                    }
                }
                grabbedColour = true;
                getWorld().removeObject(tempPoint);
            }
        }
    }
    
    public void spawnShape()
    {
        if(timer.millisElapsed() > Utils.getPainterDelay() && getWorld().getObjectsAt(spawnX1Coord, spawnY1Coord, Shapes.class).size() < 1)
        {
            getWorld().addObject(new ShapeGenerator(outputShape, outputColour, direction, false), spawnX1Coord, spawnY1Coord);
            outputShape = null; 
            outputColour = null;
            grabbedShape = false;
            grabbedColour = false;
            timer.mark();
            occupied = false;
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
                    spawnX1Coord = getX() - (Utils.gridSize / 2);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() - (Utils.gridSize / 2);
                    inputX2Coord = getX() + (Utils.gridSize / 2);
                    inputY2Coord = getY() - (Utils.gridSize / 2);
                    setDirection(0);
                    setRotation(180);
                    break;
                case 1:
                    spawnX1Coord = getX() - (Utils.gridSize / 2) - 1;
                    spawnY1Coord = getY() - (Utils.gridSize / 2);
                    inputX1Coord = getX() + (Utils.gridSize / 2) - 1;
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord;
                    inputY2Coord = getY() + (Utils.gridSize / 2);
                    setDirection(1);
                    setRotation(-90);
                    break;
                case 2:
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() - (Utils.gridSize / 2) - 1;
                    inputX1Coord = spawnX1Coord;
                    inputY1Coord = getY() + (Utils.gridSize / 2) - 1;
                    inputX2Coord = getX() - (Utils.gridSize / 2);
                    inputY2Coord = getY() + (Utils.gridSize / 2) - 1;
                    setDirection(2);
                    setRotation(0);
                    break;
                case 3:
                    spawnX1Coord = getX() + (Utils.gridSize / 2);
                    spawnY1Coord = getY() + (Utils.gridSize / 2);
                    inputX1Coord = getX() - (Utils.gridSize / 2);
                    inputY1Coord = spawnY1Coord;
                    inputX2Coord = inputX1Coord;
                    inputY2Coord = getY() - (Utils.gridSize / 2);
                    setDirection(3);
                    setRotation(90);
                    break;
            }
        }
    }
}
