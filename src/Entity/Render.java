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
public abstract class Render {
    //base class
    
     protected float x, y;
     
     public void setX(float x)
     {
        this.x=x;
     }
     
     public void setY(float y)
     {
        this.y=y;
     }
     
     public float getX()
     {
         return this.x;
     }
     
     public float getY()
     {
         return this.y;
     }
     
     abstract void render();
}
