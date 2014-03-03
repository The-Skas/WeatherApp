/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import org.newdawn.slick.Color;
import src.Sound;

/**
 *
 * @author skas
 */
public class Lightning extends Render implements Updateable {
    public static final String PATH = "lightning.png";
    public static final int ONE_SECOND = 1000;
    private int current_ms = 5000;
    private Color color = new Color(255,255,255,0);
    public Lightning()
    {
        super(450,150,PATH);
        this.scale = 0.5f;
        this.color.a =0;
    }
    public void update(int delta) {
        if(current_ms > ONE_SECOND * 5)
        {
            this.x =(float)(450+ Math.random() * 100 - 50);
            this.current_ms = 0;
            this.color.a = 1.0f;
            Sound.play(0, 4);
        }
        else
        {
            this.color.a -= 0.001 * delta;
            this.current_ms += delta;
        }
    }
    
    public void render(org.newdawn.slick.Graphics g)
    {
       this.img.draw(this.getX(), this.getY(), this.getScale(), this.color);
    }
    
}
