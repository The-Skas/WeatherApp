/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;


import java.util.HashSet;
import java.util.Set;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import src.MouseW;

/**
 *
 * @author skas
 */
public class Sun extends Render implements Updateable {
    //The color will change according to the temprature;
    private final float HIGHEST_COLOR = 30.0f;
    
    private Color color = new Color(255,255,255);
    
    private float temprature;
    
    private int sleepms = 0;
    
    private static int ONE_SECOND_MS = 1000;
    
    public Sun(int x,int y,float scale) throws SlickException
    {
        super();
        try {
            this.img = new Image("res/sunWhite.png");
            this.img.setFilter(Image.FILTER_LINEAR);
            this.rot = img.getRotation();
            this.y = 100;
            this.scale = 0.35f;
//            img.setCenterOfRotation(((float)img.getWidth())*scale, ((float)img.getHeight())*scale);
            img.setCenterOfRotation(50,50);
            this.x = Render.screenWidth/2.0f - this.getWidth()/2.0f;
            
        }catch(SlickException e)
        {
            System.out.println(e);
        }
    }
    public void orbit(Earth earth)
    {
        float centerOfSun = this.getY() + this.getHeight()/2.0f;
        float centerOfEarth = earth.getY() + earth.getHeight()/2.0f;
        
        float distanceOfCenters = centerOfEarth-centerOfSun;
        this.img.setCenterOfRotation(this.getWidth()/2.0f, this.getY() + distanceOfCenters );
    }
    
    public void setTemprature(String temp)
    {
        this.temprature = Float.parseFloat(temp);
    }
    
    public float getTemprature()
    {
        return this.temprature / this.HIGHEST_COLOR;
    }
    
    public void render(Graphics g)
    {
        super.render(g);
         //This centerOfRotation Has to be set everyupdate;
//        img.setCenterOfRotation(this.getWidth()/2,this.getHeight()/2);
        this.img.draw(getX(),getY(), this.getScale(), this.color);
        
        //This is to check if the mouse is over the sun.
       
       
        
      
    }

    public void update(int delta) 
    {
        this.color.b = 1.0f-this.getTemprature();

        //replace this with the Camera Render.
        
        this.rot =(190.0f * MouseW.getX()/(float)Render.screenWidth) - 95.0f;

            //Limits the rotation.
            

            this.img.setRotation(this.rot);
            if(this.sleepms <= 0)
            {
                if(this.rot >= 90.0f)
                {
                    this.rot = 90.0f;
                    ButtonTab.IncrementDay();
                    this.sleepms = 1000;
                }
                else if(this.rot <= -90.0f)
                {
                    this.rot = -90.0f;
                    ButtonTab.decrementDay();
                    this.sleepms = 1000;
                }
            }
            else
            {
                this.sleepms -= delta;
            }
    }
        
}
    
    

