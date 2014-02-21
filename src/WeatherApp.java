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
import java.util.ArrayList;
import src.Entity.Render;
import src.Entity.Sun;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import src.Entity.Button;
import src.Entity.Updateable;
import src.MouseW;

public class WeatherApp extends BasicGame {
        private Image img;
        private Sun sun;
        private Font font;
        private ArrayList<src.Entity.Render> entities;
	public WeatherApp(String gamename) {
		super(gamename);
                
	}
	public void init(GameContainer gc) throws SlickException {
            entities = new ArrayList<>();
            Render.entities = entities;
            Render.screenWidth = 1024;
            Render.screenHeight = 768;
            float midX = 1024.0f;
            float midY = 768.0f;
            new Sun(500, 200, 1.0f);
            new Button("buttonframe.png",
                    midX/2.0f,midY/2.0f, 
                     1.0f);
//            font = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 16), false);
            //         g.setFont(font);

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException 
        {
           for(int i = 0; i < entities.size();i++)
           {
               if(entities.get(i) instanceof Updateable)
               {
                   ((Updateable)entities.get(i)).update(delta);
               }
           }
           
           if(container.getInput().isKeyDown(Input.KEY_ENTER))
           {
            AppGameContainer gc = (AppGameContainer) container;
            System.out.println("screen width value b4!!: "+gc.getScreenWidth());
            gc.setDisplayMode(480, 320, false);
            
            System.out.println("screen width value!!: "+gc.getScreenWidth());
            Render.screenHeight = 320;
            Render.screenWidth =  480;
            container.setPaused(true);
           }
          
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException 
        {
           g.setBackground(Color.gray);
           for(int i = 0; i < entities.size();i++)
           {
               
                entities.get(i).render(g);
               
           }
           

           
                
	}
        
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new WeatherApp("Simple Slick Game"));
			appgc.setDisplayMode(1024, 768, false);
                        appgc.setVSync(false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(WeatherApp.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
