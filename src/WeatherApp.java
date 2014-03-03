/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

/**
 *
 * @author skas
 */
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import src.Entity.Render;
import src.Entity.Sun;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;

import src.*;
import src.Entity.*;

public class WeatherApp extends BasicGame {
        private Image img;
        private Sun sun;
        private boolean toggleResolution = false;
        private ArrayList<src.Entity.Render> entities;
        private TextField searchField;
	public WeatherApp(String gamename) {
		super(gamename);
                
	}
	public void init(GameContainer gc) throws SlickException {
            //hide FPS
            gc.setShowFPS(false);
            entities = new ArrayList<>();
            Render.entities = entities;
            Render.screenWidth = 1024;
            Render.screenHeight = 768;
            float midX = 1024.0f;
            float midY = 768.0f;
            Font tFont =new Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 35);
            TrueTypeFont uFont = new TrueTypeFont(tFont, true);
            
            this.searchField = new TextField(gc , uFont, 0 , 0 , 200 , 35, new ComponentListener() {
                public void componentActivated(AbstractComponent ac) {
                    System.out.println("Hey TextField here!");
                    String s = searchField.getText();
                    ThreadSearch threadSearch = new ThreadSearch(s);
                    Thread t = new Thread(threadSearch);
                    t.start();
                  
                }
            });
            this.searchField.setBackgroundColor(Color.gray);
            new WeatherGroup();
            
            //DrawBottomUI
            new BottomUI(-2, 520);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException 
        {
//            Sound.update(delta);
           for(int i = 0; i < entities.size();i++)
           {
               if(entities.get(i) instanceof Updateable)
               {
                   ((Updateable)entities.get(i)).update(delta);
               }
           }
           
           if(container.getInput().isKeyDown(Input.KEY_BACKSLASH))
           {
            int newWidthRes; int newHeightRes;
            if(this.toggleResolution)
            {
                newWidthRes = 1024;
                newHeightRes = 768;
            }
            else
            {
                newWidthRes = 480;
                newHeightRes = 320;
            }
            
            AppGameContainer gc = (AppGameContainer) container;
            System.out.println("screen width value b4!!: "+gc.getScreenWidth());
            gc.setDisplayMode(newWidthRes, newHeightRes, false);
            
            System.out.println("screen width value!!: "+gc.getScreenWidth());
            Render.screenHeight = newHeightRes;
            Render.screenWidth =  newWidthRes;
            
            for(int i = 0; i < entities.size();i++)
            {
                if(entities.get(i) instanceof Render)
                {
                    entities.get(i).reInit();
                }
            }
            this.toggleResolution = !this.toggleResolution;
           }
          
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException 
        {
           g.setBackground(Color.black);
           for(int i = 0; i < entities.size();i++)
           {
               
                entities.get(i).render(g);
               
           }
           if(this.searchField != null)
           {
             this.searchField.render(container, g);
            g.drawString("Location: "+Forecast.currentLocation, 0, 30);

           }
           else
           {
               System.out.println("Search field is NULL!");
           }
	}
        
	public static void main(String[] args) {
                   
                    
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new WeatherApp("Weather App"));
			appgc.setDisplayMode(1024, 768, false);
                        appgc.setVSync(false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(WeatherApp.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
