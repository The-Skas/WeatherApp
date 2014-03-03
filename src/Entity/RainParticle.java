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
public class RainParticle extends Render implements Updateable {
    public static Image rainImg;
    private final float acY = 0.0015f;
    public float vY = 0.01f;
    public float heightScale = 0.5f;
    public RainParticle()
    {
        super(false);
        this.x =(float) Math.random()*100.0f + 400;
        this.y = 200f + (float)Math.random() * 150f;
        this.scale =(float)( 0.5 * Math.random());
        this.heightScale =(float) Math.random();
        this.img = rainImg;
    }
    public void update(int  delta)
    {
        this.y += vY * (float)delta;
        this.vY += acY * (float) delta;
        
        if(this.y > 350)
        {
            this.reset();
        }
    }
    public void reset()
    {
        this.x =(float) Math.random()*100.0f + 400;
        this.y = 200;
        this.vY = 0.01f;
        this.scale =(float)( 0.5 * Math.random());

    }
    
}
