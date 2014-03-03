/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.ArrayList;
import src.Entity.State.*;
import src.Forecast;
import src.Forecast.WeatherType;

/**
 *
 * @author skas
 */
public class WeatherState extends Render implements Updateable 
{

    public ArrayList<Updateable> weatherEntities;
    private State<WeatherState> state;
    
    private int currType =0;
    public WeatherState()
    {
        super();
        weatherEntities = new ArrayList<Updateable>();
        weatherEntities.add(new CloudEmitter(0.5f));
    }
    
    @Override
    public void update(int delta) {
        int type = Forecast.getWeatherTypeOfDay(ButtonTab.SELECTED_IND);
        if(WeatherType.CLOUDY.ordinal() == type &&
         type != currType )
        {
            this.state.exit(this);
            this.state = new Cloudy();
            //exit state, enter new state.
        }
        //Updating Cloud Emitter;
    }
    
    public void render(org.newdawn.slick.Graphics g)
    {
        //Render Clouds/Rain/
    }
    
}
