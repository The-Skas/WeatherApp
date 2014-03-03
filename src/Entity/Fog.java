/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import org.newdawn.slick.Color;

/**
 *
 * @author skas
 */
public class Fog extends Render implements Updateable
{
    private static final String PATH = "cloud.png";
    private Color color = new Color(255,255,255,0);
    public Fog()
    {
        super(325,225,PATH);
        this.color.a = 0.5f;
        this.scale = 3;
    }

    @Override
    public void update(int delta) 
    {
        
    }
    
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
       this.img.draw(this.getX(), this.getY(), this.getScale(), this.color);
    }
}
