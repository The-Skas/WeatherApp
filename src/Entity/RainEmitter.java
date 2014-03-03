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

public class RainEmitter extends Render implements Updateable {
    private static final int MAX_RAIN = 20;
    private static final String PATH = "rain.png";
    private ArrayList<RainParticle> rain;
    
    public RainEmitter()
    {
        super(400,200, PATH);
        //Initialises shared img
        RainParticle.rainImg = this.img;
        rain = new ArrayList<>();
        for(int i = 0; i < MAX_RAIN; i++)
            rain.add(new RainParticle());
        
        
    }
    
    @Override
    public void update(int delta) 
    {
        for(int i = 0; i < rain.size(); i++)
        {
            rain.get(i).update(delta);
        }
    }
    
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
        for(int i = 0; i < rain.size(); i++)
        {
            RainParticle rainP = rain.get(i);
            this.img.draw(rainP.getX(), rainP.getY(), 
                        rainP.getWidth()*2.0f ,rainP.vY*rainP.getHeight()*0.5f);
        }
    }
    
}
