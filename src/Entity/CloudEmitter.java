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
public class CloudEmitter extends Render implements Updateable
{
    private final static int DEFAULT_EMIT = 1500;
    private int emitRate = DEFAULT_EMIT;
    private int timeToEmit = emitRate;
    private float min_alpha = 0.0f;
    private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    private float cloudColor;
    public CloudEmitter(float colorf)
    {
        //adds to render/updateable list
        super();
        this.cloudColor = colorf;
    }
    public CloudEmitter(float colorf, int rate, float min_alpha)
    {
        this(colorf);
        this.emitRate = rate;
        this.timeToEmit = rate;
        this.min_alpha = min_alpha;
    }
    @Override
    public void update(int delta) 
    {
        if(timeToEmit >= emitRate)
        {
            timeToEmit = 0;
            this.clouds.add(new Cloud(cloudColor,this.min_alpha, this));
        }
        else
        {
            timeToEmit += delta;
        }
    }
    
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
      
    }
    
    public void destroyCloud(Cloud c)
    {
        this.clouds.remove(c);
    }
    
    /**
     * Over rides the destroy from base class Render.
     * Otherwise, the object cant remove all refrences to its variable arraylist.
     */
    @Override
    public void destroy()
    {
        for(int i = 0; i<clouds.size(); i++)
        {
            clouds.get(i).destroy();
        }
        //destroy itself
        super.destroy();
    }
}
