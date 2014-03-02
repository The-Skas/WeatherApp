/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/**
 *
 * @author skas
 */
public class WeatherGroup extends Render implements Updateable 
{
    //Suns 
    private Sun sun;
    //Earth
    //Car
    //WeatherState
    public WeatherGroup()
    {
        //to add to list
        super();
        try {
            new Sun(500, 200, 1.0f);
            new Earth(500, 350);
            new Car(0,350);
        } catch (SlickException ex) {
            Logger.getLogger(WeatherGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void update(int delta) {
        //Do relevant state update.
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
