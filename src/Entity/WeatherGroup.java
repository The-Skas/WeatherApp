/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import src.Forecast;
import src.MouseW;

/**
 *
 * @author skas
 */
public class WeatherGroup extends Render implements Updateable 
{
    //Suns 
    private Sun sun;
    //Earth
    private Earth earth;
    //Car
    //WeatherState
    public WeatherGroup()
    {
        //to add to list
        super();
        try {
            this.sun =new Sun(500, 200, 1.0f);
            this.earth=new Earth(500, 350);
            new Car(0,350);
            new WeatherState();
        } catch (SlickException ex) {
            Logger.getLogger(WeatherGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTemprature()
    {
        
    }
    
    @Override
    public void update(int delta) {
        
        //I know, the sun doesnt orbit the earth. No need to call copernicus. 
        sun.orbit(earth);
        int i = ButtonTab.SELECTED_IND;
        sun.setTemprature(Forecast.current.getFiveDays()[i][Forecast.WeatherInfo.HI.ordinal()]);
     
    }
    
    /**
     * This serves as an adapter method to have WeatherGroup as Render.
     * @param g
     */
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
        //render background color
    }
    
}
