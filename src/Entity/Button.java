/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**s
 *
 * @author skas
 */
public class Button extends Render implements Updateable {
    private final String prefix = "res/";
    private Color color;
    public Button(String path,float x, float y, float scale){
        super();
        try {
            this.img = new Image(prefix+path);
            this.x = x;
            this.y = y;
            color = new Color(0,0,0,255);
        } catch (SlickException ex) {
            Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void render(Graphics g)
    {
//        for(int i = 0; i < 1000; i++)
//        g.fillRoundRect(0, 0, 100, 150, 20,20);
//        g.setLineWidth(2.0f);
//        g.setColor(Color.gray);
//        g.drawRoundRect(0, 0, 100, 150, 20);
        super.render(g);
        this.img.draw(this.getX(), this.getY(), this.getScale(),color);
    }

    @Override
    public void update(int delta) {
    }
    
    
}
