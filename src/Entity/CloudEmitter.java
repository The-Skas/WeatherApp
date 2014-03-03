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
    private final int emiteRate = 1500;
    private int timeToEmit = 0;
    private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    private float cloudColor;
    public CloudEmitter(float colorf)
    {
        //adds to render/updateable list
        super();
        this.cloudColor = colorf;
    }
    @Override
    public void update(int delta) 
    {
        if(timeToEmit >= emiteRate)
        {
            timeToEmit = 0;
            this.clouds.add(new Cloud(cloudColor, this));
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
    
    public void remove(Cloud c)
    {
        this.clouds.remove(c);
    }
}
