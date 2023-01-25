import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * An abstract class for all machines. Contains methods for spawning, deletion, and ghost block.
 * 
 * @author Anson  
 * @version Jan 24, 2023
 */
public abstract class Machines extends Actor
{
    ghostBlock block;
    public boolean spawner = false, real = false, updatedImage = false, occupied = false, spawned = false, lastMirror = false;
    public int gridPositionX, gridPositionY;  
    public int direction, lastRotation; 
    public SimpleTimer timer = new SimpleTimer();
    public ArrayList<Integer> outputShape = new ArrayList<Integer>(8);
    public ArrayList<Integer> outputColour = new ArrayList<Integer>(8);
    protected GreenfootSound[] effects = new GreenfootSound[20];
    protected GreenfootSound[] place = new GreenfootSound[20];
    public GreenfootSound placeSound;
    protected int effectsIndex = 0, placeIndex = 0;
    protected int value;
    
    /**
     * Allows an image of the seleted machine to follow mouse cursor
     * @param xSize Size of machine image
     */
    public void followMouse(int xSize)
    {
        if(Utils.getMouse() != null)
        {
            if(xSize > 1 && (Utils.getDirection() == 1 || Utils.getDirection() == 3))
            {
                setLocation(Utils.getMouseX() + getImage().getWidth() / 2 - Utils.gridSize, Utils.getMouseY() + getImage().getHeight() / 2 - Utils.gridSize);
            } else {
                setLocation(Utils.getMouseX() + getImage().getWidth() / 2 - Utils.gridSize / 2, Utils.getMouseY() + getImage().getHeight() / 2 - Utils.gridSize / 2);
            }
        } 
    }
    
    /**
     * Creates a GhostBlock class of machine that snaps to grid
     * @param image Image of machine
     * @param xSize Size of machine
     */
    public void gridSnap(GreenfootImage image, int xSize)
    {
        if(Utils.getMouse() != null)
        {
            if(Utils.getMouseX() > 200 && Utils.getMouseX() < 1000)
            {
                gridPositionX = (int) (Utils.getMouseX() - 200) / Utils.gridSize;
                gridPositionY = (int) (Utils.getMouseY()) / Utils.gridSize;
                if(xSize > 1 && (Utils.getDirection() == 1 || Utils.getDirection() == 3))
                {
                    if(getWorld().getObjects(ghostBlock.class).isEmpty() == true)
                    {
                        block = new ghostBlock(image, xSize);
                        getWorld().addObject(block, (gridPositionX * Utils.gridSize) + 220, (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2) - 20);
                    }
                    if(block != null)
                    {
                        if(gridPositionX != block.getXCoord() || gridPositionY != block.getYCoord())
                        {
                            for(Arrows arrow : getWorld().getObjects(Arrows.class))
                            {
                                getWorld().removeObject(arrow);
                            }
                            getWorld().removeObject(block);
                            getWorld().addObject(block, (gridPositionX * Utils.gridSize) + 220, (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2) - 20);
                        }
                    }
                } else {
                    if(getWorld().getObjects(ghostBlock.class).isEmpty() == true)
                    {
                        block = new ghostBlock(image, xSize);
                        getWorld().addObject(block, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                    }
                    if(block != null)
                    {
                        if(gridPositionX != block.getXCoord() || gridPositionY != block.getYCoord())
                        {
                            for(Arrows arrow : getWorld().getObjects(Arrows.class))
                            {
                                getWorld().removeObject(arrow);
                            }
                            getWorld().removeObject(block);
                            getWorld().addObject(block, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                        }
                        block.setXGridCoord(gridPositionX);
                        block.setYGridCoord(gridPositionY);
                        
                    }
                }
            }
            if(Utils.getMirrored() != lastMirror && xSize > 1 && block != null)
            {
                block.getImage().mirrorHorizontally();
                lastMirror = Utils.getMirrored();
            }
            if(Utils.getMouseX() < 200 || Utils.getMouseX() > 1000)
            {
                getWorld().removeObject(block);
            }
        }
    }

    /**
     * Places an instance onto the map and updates the global 2D array of machines
     * @param cls Class of machine to instantiate
     * @param xSize Size of machine
     */
    public void place(Class cls, int xSize)
    {
        if(Utils.getMouse() != null)
        {
            if(Utils.getMouseX() > 200 && Utils.getMouseX() < 1000 && Utils.getMouseY() > 0 && Utils.getMouseY() < 800)
            {
                int buttonNumber = Utils.getMouseButton();
                if(buttonNumber == 1 && Utils.getSpaceMachine(gridPositionY, gridPositionX) == null)
                {
                    if(xSize > 1)
                    {
                        if((Utils.getDirection() == 0 && Utils.getSpaceMachine(gridPositionY, gridPositionX + 1) == null) || (Utils.getDirection() == 1 && Utils.getSpaceMachine(gridPositionY - 1, gridPositionX) == null) || (Utils.getDirection() == 2 && Utils.getSpaceMachine(gridPositionY, gridPositionX + 1) == null) || (Utils.getDirection() == 3 && Utils.getSpaceMachine(gridPositionY - 1, gridPositionX) == null))
                        {
                            try{ 
                                Machines temp = (Machines) cls.newInstance();
                                Utils.fillSpaceMachine(gridPositionY, gridPositionX, temp);
                                playPlace();
                                switch(Utils.getDirection())
                                {
                                    case 0:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                                        Utils.fillSpaceMachine(gridPositionY, gridPositionX + 1, temp);
                                        break;
                                    case 1:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (180 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2 - 20));
                                        Utils.fillSpaceMachine(gridPositionY - 1, gridPositionX, temp);
                                        break;
                                    case 2:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                                        Utils.fillSpaceMachine(gridPositionY, gridPositionX + 1, temp);
                                        break;
                                    case 3:
                                        getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (180 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2 - 20));
                                        Utils.fillSpaceMachine(gridPositionY - 1, gridPositionX, temp);
                                        break;
                                }
                                if(Utils.getMirrored())
                                {
                                    temp.getImage().mirrorHorizontally();
                                }
                            }
                                catch(Exception e){
                            }
                        }
                    } else if(cls == Extractor.class){
                        if(Utils.getSpaceDeposit(gridPositionY, gridPositionX) != null)
                        {
                            try{
                                Machines temp = (Machines) cls.newInstance();
                                getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                                Utils.fillSpaceMachine(gridPositionY, gridPositionX, temp);
                                playPlace();
                            }
                                catch(Exception e){        
                            }
                        }
                    } else {
                        try{ 
                            Machines temp = (Machines) cls.newInstance();
                            getWorld().addObject(temp, (gridPositionX * Utils.gridSize) + (200 + getImage().getWidth() / 2), (gridPositionY * Utils.gridSize) + (getImage().getHeight() / 2));
                            Utils.fillSpaceMachine(gridPositionY, gridPositionX, temp);
                            playPlace();
                        }
                            catch(Exception e){
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Deletes all shapes in surronding area when machine is deleted
     */
    public void deleteShapes()
    {
        for(FollowPoint point : getObjectsInRange(25, FollowPoint.class))
        {
            if(!point.checkIfLabel())
            {
                getWorld().removeObject(point);
            }
        }
    }
    
    /**
     * Updates image of machine when rotated
     */
    public void updateRotation()
    {
        if(Utils.getDirection() != lastRotation)
        {
            updatedImage = false;
        }
    }
    
    /**
     * Sets spawn direction variable based on direction of machine placed
     * @param direction
     */
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    /**
     * Returns a boolean to check if machine is currently making a shape
     * @return occupied Boolean
     */
    public boolean isOccupied()
    {
        return occupied;
    }
    
    /**
     * Updates image rotation of machine <br>
     * - 0 = Down <br>
     * - 1 = Left <br>
     * - 2 = Up <br>
     * - 3 = Right <br>
     * @param direction Direction that machine should face
     */
    public void updateImage(int direction)
    {
        switch (direction)
        {
            case 0:
                setRotation(180);
                break;
            case 1:
                setRotation(-90);
                break;
            case 2:
                setRotation(0);
                break;
            case 3:
                setRotation(90);
                break;
        }
    }
    
    public void setEffect(GreenfootSound effect){
        for(int i = 0; i < 20; i++){
            effects[i] = effect;
            GreenfootSound placeSound = new GreenfootSound("place.wav");
            placeSound.setVolume(90);
            place[i] = placeSound;
        }
    }
    
    public void playEffect(){
        effects[effectsIndex].play();
        effectsIndex++;
        if(effectsIndex > effects.length - 1){
            effectsIndex = 0;
        }
    }
    
    public void playPlace(){
        place[placeIndex].play();
        placeIndex++;
        if(placeIndex > place.length - 1){
            placeIndex = 0;
        }
    }
}
