/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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
     public final boolean DEBUG = false;
    //This serves to refer to the arrayList of Entities on the screen.
     public static ArrayList<src.Entity.Render> entities;
     public static final float D_SCREENWIDTH  = 1024;
     public static final float D_SCREENHEIGHT =  768;
     public static final String prefix = "res/";
     //Should store the width/height of screen
     public static int screenWidth;
     public static int screenHeight;
     
     protected Image img;
     protected float x, y;
     protected float rot;
     protected float scale = 1.0f;
     
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
     public Render(boolean t)
     {
         //do nothing
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
     public Render(float x, float y)
     {
         this();
         this.x = x;
         this.y = y;
         this.scale = 1.0f;
     }
     public Render(float x, float y, String imgPATH)
     {
         this(x,y);
         LoadImg(imgPATH);
     }
     
     public void LoadImg(String imgPATH)
     {
         try {
             this.img = new Image(prefix+imgPATH);
         } catch (SlickException ex) {
             Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public void reInit()
     {
         //perform scaling to resolution for objects, specifically text.
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
         return this.x * getWidthScreenScale();
     }
    
     public float getY()
     {
         return this.y * getHeightScreenScale();
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
     
     //this returns the width of the image scaled to a resolution of ONLY
     //the width
     public float getWidthTrue()
     {
         return this.img.getWidth() * getWidthScreenScale();
     }
     //this returns the height of the image scaled to a resolution of ONLY
     //the height
     public float getHeightTrue()
     {
         return this.img.getHeight() * getHeightScreenScale();
     }
     public boolean isHit(float x, float y)
     {
         
         boolean ishit = x >= this.getX() && x <= this.getX()+this.getWidth() &&
                         y >= this.getY() && y <= this.getY()+this.getHeight();
         if(DEBUG) {
//            System.out.println("Mouse X: "+x +" --- Mouse Y: "+y);
//            System.out.println("Sun   X: "+this.getX() +" --- Sun   Y: "+this.getY());
            
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
     
     public void render(org.newdawn.slick.Graphics g)
     {
         if(DEBUG)
            g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
         this.img.draw(this.getX(), this.getY(), this.getScale());
     }
     
     public void destroy()
     {
         entities.remove(this);
     }
}
