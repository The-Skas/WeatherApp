/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.ArrayList;
import org.newdawn.slick.Color;

/**
 *
 * @author skas
 */
public class Cloud extends Render implements Updateable
{
    private enum State
    {
        ENTER,EXECUTE,EXIT
    };
    private static final float DEFAULT_CHANGE =  0.0001f;
    private static final String PATH = "cloud.png";
    
    
    private Color color = new Color(255,255,255);
    
    private State state;
    
    //Float color between 1.0->0.0f
    private float max_alpha;
    //Float color between 1.0->0.0f
    private float changeRate;
    
    //
    private float vX = 0.01f;
    
    //TTL
    private float TTL = 3000;
    
    private CloudEmitter parent;
    public Cloud(float color)
    {
       super(400,200,PATH);
       
       this.color.r = color;
       this.color.g = color;
       this.color.b = color;
       this.color.a = 0.0f;
       this.state = State.values()[0];
       this.changeRate = DEFAULT_CHANGE;
       this.max_alpha = 1.0f;
    }
    
    public Cloud(float color, float alpha)
    {
       this(color);
       this.max_alpha = alpha;
    }
    
    //random                                /emitter
    public Cloud(float color, CloudEmitter parent)
    {
        this(color);
        //Sets to 0.5-1.0f max
        this.max_alpha *= Math.random();
        this.changeRate*= Math.random()*3.0f +1.0f;
        this.y += Math.random()*50.0f - 25.0f;
        this.x += Math.random()*100.0f - 50f;
        this.vX*= Math.random()*0.25f + 1.0f;
        this.parent = parent;
    }
    public void render(org.newdawn.slick.Graphics g)
    {
       this.img.draw(this.getX(), this.getY(), this.getScale(), this.color);
    }
    
    @Override
    public void update(int delta) 
    {
        if(this.state == State.ENTER)
        {
//            System.out.println("CLOUD ENTERING");
            this.color.a += ((float) delta) * changeRate;
            if(this.color.a >= this.max_alpha)
            {
                this.state = State.EXECUTE;
            }
        }
        else if(this.state == State.EXIT)
        {
            if(this.color.a <= 0)
            {
                this.destroy();
                this.parent.remove(this);
                //remove from parent
            }
            this.color.a -= ((float) delta) * changeRate;
        }
        else if(this.state == State.EXECUTE)
        {
            this.TTL -= delta;
            if(this.TTL <= 0) {
                this.state = State.EXIT;
            }
        }
        //Movement
        this.x += this.vX * delta;
            
        //Do relevant movement, etc.
    }
    
}
