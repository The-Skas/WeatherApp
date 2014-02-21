/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
/**
 *
 * @author skas
 * 
 * This Serves as a base class for all Renderable Entities.
 * 
 * The methods implemented serve to scale to the set Resolution. An example 
 * class that extends and implements/uses all the functionality is Sun.
 * 
 */
public abstract class Render {
     public final boolean DEBUG = true;
    //This serves to refer to the arrayList of Entities on the screen.
     public static ArrayList<src.Entity.Render> entities;
     public static final float D_SCREENWIDTH  = 1024;
     public static final float D_SCREENHEIGHT =  768;
     //Should store the width/height of screen
     public static int screenWidth;
     public static int screenHeight;
     
     protected Image img;
     protected float x, y;
     protected float rot;
     protected float scale;
     
     public static float getWidthScreenScale()
     {
         return ((float)screenWidth)/D_SCREENWIDTH;
     }
     
     public static float getHeightScreenScale()
     {
         return ((float)screenHeight)/D_SCREENHEIGHT;
     }
     
     public static float getScreenScale()
     {
         return (getWidthScreenScale()+getHeightScreenScale())/2.0f;
     }
     public Render()
     {
         if(this.scale <= 0)
             this.scale = 1;
         if(entities !=null)
         {
            Render.entities.add(this);
         }
         else 
         {
             System.out.println("***ENITIES LIST IS NULL!");
             entities = new ArrayList<>();
         }
     }
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
         return this.x * getScreenScale();
     }
    
     public float getY()
     {
         return this.y * getScreenScale();
     }
     
     public float getScale()
     {
         return this.scale * getScreenScale();
     }
     public float getWidth()
     {
         return this.img.getWidth() * getScale();
     }
     
     public float getHeight()
     {
         return this.img.getHeight() * getScale();
     }
     
     
     public boolean isHit(float x, float y)
     {
         boolean ishit = x >= this.getX() && x <= this.getX()+this.getWidth() &&
                         y >= this.getY() && y <= this.getY()+this.getHeight();
         if(DEBUG) {
            System.out.println("Mouse X: "+x +" --- Mouse Y: "+y);
            System.out.println("Sun   X: "+this.getX() +" --- Sun   Y: "+this.getY());
            
            if(ishit)
            {
                this.img.draw(this.getX(), this.getY(), this.getScale(), Color.red);
            }
                
            
         }
         
         return x >= this.getX() && x <= this.getX()+this.getWidth() &&
                 y >= this.getY() && y <= this.getY()+this.getHeight();
     }
     
     public boolean isHitRadius(float x, float y)
     {
         float radius = getWidth()/2;
         float xcenter = this.getX()+radius;
         float ycenter = this.getY()+radius;
         float xdiff = x-xcenter;
         float ydiff = y-ycenter;
         
         if(Math.sqrt(xdiff*xdiff + ydiff*ydiff) <= radius)
         {
             this.img.draw(this.getX(), this.getY(), this.getScale(), Color.red);
             return true;
         }
         return false;
     }
     
     public void render(Graphics g)
     {
         g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
     }
     
     public void destroy()
     {
         entities.remove(this);
     }
}
