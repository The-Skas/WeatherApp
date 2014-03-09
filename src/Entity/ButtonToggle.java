/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author skas
 */
public class ButtonToggle extends Button {
    private static final String PATH = "unselcircle.png";
    
    private Image img2;
    private Image tempImg;
    private boolean selected = false;
    public ButtonToggle( float x, float y, boolean select) {
        super(PATH, x, y, 1.0f, "");
        try {
            img2 = new Image("res/selcircle.png");
        } catch (SlickException ex) {
            Logger.getLogger(ButtonToggle.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(select)
        {
            toggle();
        }
    }
    
    
    public boolean isSelected()
    {
        return selected;
    }
    
    public void toggle()
    {
        this.tempImg = img;
        this.img = img2;
        this.img2 = tempImg;
        
        this.selected = !selected;
    }
    
    public void on()
    {
        if(!selected)
        {
            System.out.println("Turning on!");
            toggle();
        }
    }
    
    public void off()
    {
        if(selected)
        {
            toggle();
            System.out.println("Turning Off!");
        }
    }
    @Override
    public void action() 
    {
    }
}
