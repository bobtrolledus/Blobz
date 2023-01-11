import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends UI
{
    /**
     * Act - do whatever the Buttons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    
    // Mr.Cohen's button methods
    /**
     * <h3>Finally, draw centered text in Greenfoot!</h3>
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Use this instead of Greenfoot.drawString to center your text, or just call getStringWidth
     *    directly and draw it yourself if you prefer the control over the ease of use.</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param middleX   the x Coordinate that the text should be centered on
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText(GreenfootImage canvas, String text, int middleX, int bottomY) {
        canvas.drawString (text, middleX - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }
    
    /**
     * <p>
     * <b>IMPORTANT:</b> Set your Font in your GreenfootImage before you send it here.
     * </p>
     * <p>Similar to the method above, except it always centers the text on the whole image
     *    instead of a specified x position. UNTESTED!</p>
     * 
     * @param canvas    The GreenfootImage that you want to draw onto, often the background of a World, but
     *                  could also be an Actor's image or any other image.
     * @param text      The text to be drawn.
     * @param bottomY   the y Coordinate at the baseline of the text (similar to GreenfootImage.drawString)
     * 
     * @since June 2021
     */
    public static void drawCenteredText(GreenfootImage canvas, String text, int bottomY) {
        canvas.drawString (text, canvas.getWidth()/2 - (getStringWidth(canvas.getFont(), text)/2), bottomY);
    }

    /**
     * Get the Width of a String, if it was printed out using the drawString command in a particular
     * Font. Works by making an image, drawing the String onto it, and then searching for the last
     * non-transparent pixels in the image. 
     * 
     * This is not a cheap method, and should not be called from an act method. It is appropriate
     * to call this in the constructor.
     * 
     * In advanced cases, you may want to cache the results during a loading method. You could also
     * call it manually while coding, note the results, and use literal values to avoid having this
     * code called at all.
     * 
     * @param font the GreenFoot.Font which is being used to draw text
     * @param text the actual text to be drawn
     * 
     * @since June 2021
     */
    public static int getStringWidth(Font font, String text) {

        // how far past the last sighted text to keep looking
        final int END_MARGIN = 100; 

        // largest font size, used to set height of temp image
        final int MAX_FONT_SIZE = 300;

        // you can make this higher if your world is bigger
        final int MAX_WIDTH = 1000; 

        int fontSize = font.getSize();
        GreenfootImage temp = new GreenfootImage (MAX_WIDTH, MAX_FONT_SIZE);
        temp.setFont(font);
        temp.drawString (text, 0, fontSize);

        int checkX = 0;
        int lastFound = 0;

        //int testValue = 1000;
        boolean running = true;

        int marginOfError = 3; // how many pixels can be skipped scanning vertically for pixels?
        if (fontSize < 18){
            marginOfError = 2;
        }
        while (running){ 
            // new row
            boolean found = false; 
            if (temp != null){
                for (int i = 0; i < fontSize && !found; i+=marginOfError){
                    
                    // This method has a high cost on Greenfoot Gallery... Maybe use this and then precache values 
                    // and never call this method live. 
                    Color c = temp.getColorAt(checkX, i);

                     if (!( c.getAlpha() == 0 && c.getRed() == 0 && c.getBlue() == 0 && c.getGreen() == 0 )){
                        found = true;
                        lastFound = checkX;
                    }
                }
            }
            checkX++;
            if (checkX - lastFound > END_MARGIN){ // if I have run for a certain amount and not found any new               
                running = false; // pixels, I'm done.
            }
        }
        return lastFound;
    }
}