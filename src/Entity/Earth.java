/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

/**
 *
 * @author skas
 */
public class Earth extends Render implements Updateable
{
    private final static String path = "earth.png";    
    
    public Earth(float x, float y)
    {
        super(x,y, path);
        this.rot = 360;
        this.x = Render.screenWidth/2.0f - this.getWidth()/2.0f;
    }
    @Override
    public void update(int delta) {
        this.rot += 0.01*delta;
        this.img.setRotation(this.rot);
    }
    
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
        img.setCenterOfRotation(this.getWidth()/2,this.getHeight()/2);
        super.render(g);
    }
    
}
