/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.util.Set;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author skas
 */

public class Sun implements Renderable, Updateable {
    Image img;
    private float x;
    private float y;
    private float rot;
    private float scale;
    public Sun() throws SlickException
    {
        try {
            img = new Image("res/sun.png");
            img.setFilter(Image.FILTER_LINEAR);
            this.rot = img.getRotation();
            this.x = 5;
            this.y = 5;
            this.scale = 0.5f;
            img.setCenterOfRotation(10,10);
            
            
            
        }catch(SlickException e)
        {
            System.out.println(e);
        }
    }
    
    public void render()
    {
//        img.setCenterOfRotation(255*scale,255*scale);
        
        this.img.draw(x, y, scale);
        System.out.println(img.getCenterOfRotationX()+" - " + img.getCenterOfRotationY());
    }

    public void update(int delta) 
    {
        System.out.println(this.x/delta);
        this.rot += 0.1;
        this.img.setRotation(this.rot);
    }
    
    
}
