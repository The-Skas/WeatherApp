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
import src.Entity.Render;
import src.Entity.Sun;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class WeatherApp extends BasicGame {
        private Image img;
        private Sun sun;
        private Font font;
	public WeatherApp(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
            System.out.println("Initialising");
            img = new Image("res/sun.png");
            sun = new Sun();
//            font = new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 16), false);
            
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
            sun.update(delta);
            System.out.println(""+delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
        {
//            g.setFont(font);
            g.drawString("Howdy!", 30, 30);
            if(sun instanceof Render)
            {
                sun.render();
            }
                
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new WeatherApp("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
                        appgc.setVSync(true);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(WeatherApp.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
