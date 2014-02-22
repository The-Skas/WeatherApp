/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import src.MouseW;

/**s
 *
 * @author skas
 */

/*
 * This class cant be instantiated.
*/
public abstract class Button extends Render implements Updateable {
    protected enum ButtonState {UP, DOWN, ROLLOVER};
    private final String prefix = "res/";
    private Color color;
    private ButtonState state;
    public Font font;
    public TextUI text;
    
    /*
     * Creates a Button object with the following params
     * @param path: the name of the image in the res folder
     * @params x, y: set x and y coordinates
     * @params text: set the text on the button.
     */
    
    public Button(String path,float x, float y, float scale, String text){
        super();
        try {
            this.img = new Image(prefix+path);
            this.x = x;
            this.y = y;
            this.scale = scale;
            
            if(text.isEmpty())
                this.text = new TextUI(this,"");
            else
                this.text = new TextUI(this,text);
            
            color = new Color(255,255,255,255);
            
            this.font = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 16), false);

        } catch (SlickException ex) {
            Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isButtonDown()
    {
        return (state == ButtonState.DOWN);
    }
    
    public boolean isButtonRollOver()
    {
        return (state == ButtonState.ROLLOVER);
    }
    
    public boolean isButtonUp()
    {
        return (state == ButtonState.UP);
    }
    public void render(Graphics g)
    {
//        for(int i = 0; i < 1000; i++)
//        g.fillRoundRect(0, 0, 100, 150, 20,20);
//        g.setLineWidth(2.0f);
//        g.setColor(Color.gray);
//        g.drawRoundRect(0, 0, 100, 150, 20);
        if(isButtonUp())
        {
            this.img.draw(this.getX(), this.getY(), this.getScale(),color);
        }
        else
        {    
            this.img.draw(this.getX(), this.getY(), this.getScale(),new Color(100,100,100, 255)); 
        }
        
        //Then draw Text
         g.setFont(this.font);
         
         text.render(g);      
    }

    @Override
    public void update(int delta) {
        if(this.isHit(MouseW.getX(), MouseW.getY()))
        {
            if(MouseW.isButtonDown(0))
            {
                state = ButtonState.DOWN;
            }
            else if(state == ButtonState.DOWN)
            {
                this.action();
                state = ButtonState.ROLLOVER;
            }
            else
            {
                state = ButtonState.ROLLOVER;
            }
        }
        else
        {
            state = ButtonState.UP;
        }
    }
    
    @Override
    public void reInit()
    {
        this.text.reInit();
    }
    //This inforces an action must be implemented for a button.
    //This is called when a button click and release occurs over the button.
    public abstract void action();
    
    
}
