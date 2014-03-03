/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.ArrayList;

/**
 *
 * @author skas
 */

public class SnowEmitter extends Render implements Updateable {
    private static final int MAX_RAIN = 20;
    private static final String PATH = "snowflake.png";
    private ArrayList<SnowParticle> snow;
    
    public SnowEmitter()
    {
        super(400,200, PATH);
        //Initialises shared img
        SnowParticle.snowImg = this.img;
        snow = new ArrayList<>();
        for(int i = 0; i < MAX_RAIN; i++)
            snow.add(new SnowParticle());
        
        
    }
    
    @Override
    public void update(int delta) 
    {
        for(int i = 0; i < snow.size(); i++)
        {
            snow.get(i).update(delta);
        }
    }
    
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
        for(int i = 0; i < snow.size(); i++)
        {
            SnowParticle snowP = snow.get(i);
            this.img.draw(snowP.getX(), snowP.getY(),snowP.getScale());
        }
    }
    
}
