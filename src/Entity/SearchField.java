/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import src.Forecast;
import src.ThreadSearch;

/**
 *
 * @author skas
 */
public class SearchField extends Render 
{
    private TextField searchField;
    private Font tFont =new Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 35);
    private TrueTypeFont uFont = new TrueTypeFont(tFont, true);
    private GameContainer gc;
    
    private final static String path = "search-icon.png";
    public SearchField(GameContainer gc)
    {
        super(0,0, path);
        this.searchField = new TextField(gc , uFont, 0 , 0 , 200 , 35, new ComponentListener() {
                public void componentActivated(AbstractComponent ac) {
                    System.out.println("Hey TextField here!");
                    String s = searchField.getText();
                    ThreadSearch threadSearch = new ThreadSearch(searchField);
                    Thread t = new Thread(threadSearch);
                    t.start();
                  
                }
            });
        this.searchField.setBorderColor(Color.black);
        this.gc = gc;
        //remove from Entities array list since this object is refrenced in
        //the main render loop. Refer to the WeatherApp Class, render function.
    }
    
    public void render(org.newdawn.slick.Graphics g)
    {
        g.setColor(Color.black);
        g.setLineWidth(5.0f);
        g.drawRoundRect((float)this.searchField.getX(),
                        (float)this.searchField.getY(), 
                        (float)this.searchField.getWidth(), 
                        (float)this.searchField.getHeight(), 
                        10);
        this.img.draw(170, 7, 0.5f);
        
        g.setColor(Color.red);
        g.drawString("Location: "+Forecast.currentLocation, 3, 40);
        this.searchField.render(gc, g);

       
    }
}
