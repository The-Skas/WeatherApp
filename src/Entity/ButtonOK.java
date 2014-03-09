/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author skas
 */
public class ButtonOK extends Button {
    private static final String PATH= "curveButton.png";
    
    private Render parent;
    public ButtonOK(float x, float y, Render parent)
    {
        super(PATH,x, y, 1.0f, "OK");
        this.parent = parent;
        
        //since it always belongs to a container, we dont want it in the main
        //list, we want the parent checking the logic.
        //Middle of Screen
    }
    @Override
    public void update(int delta) {
        super.update(delta);
       
        if(this.isButtonDown())
        {
            this.color = new Color(50,50,50);
        }
        else if(this.isButtonUp())
        {
            this.color = new Color(255,255,255);
        }
        else if(this.isButtonRollOver())
        {
            this.color = new Color(100,100,100);
        }
        
    }

    @Override
    public void action() {
        //destroy Parent
        this.parent.destroy();
    }
    
    @Override
    public void destroy()
    {
        super.destroy();
        this.text.destroy();
    }
    
    @Override
    public void render(Graphics g)
    {
        super.render(g);
        this.text.render(g);
    }
    
    
}
