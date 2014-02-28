/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import src.MouseW;


/**
 *
 * @author skas
 */
public class TextField extends Render implements Updateable{
    private boolean isHit;
    private String text;
    
    public TextField()
    {
        
    }

    public void render(Graphics g)
    {
         g.drawString(text, 30, 30);
    }
    
    @Override
    public void update(int delta) {
        if(this.isHit(MouseW.getX(), MouseW.getY()))
        {
            isHit = true;
        }
        
        if(isHit == true)
        {
           
        }
    }
    
}
