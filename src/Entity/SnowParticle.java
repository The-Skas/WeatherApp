/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import org.newdawn.slick.Image;

/**
 *
 * @author skas
 */
public class SnowParticle extends Render implements Updateable {
    public static Image snowImg;
    public float vY = 0.1f;
    public SnowParticle()
    {
        //to not add to arraylist
        super(false);
        this.x =(float) Math.random()*200.0f + 400;
        this.y = 200f + (float)Math.random() * 150f;
        this.scale =(float)( 0.5 * Math.random());
        this.img = snowImg;
    }
    public void update(int  delta)
    {
        this.y += vY * (float)delta;
        
        if(this.y > 350)
        {
            this.reset();
        }
    }
    public void reset()
    {
        this.x =(float) Math.random()*200.0f + 400;
        this.y = 200;
        this.scale =(float)( 0.5 * Math.random());

    }
    
}
