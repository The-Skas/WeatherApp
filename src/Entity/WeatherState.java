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

    public ArrayList<Render> weatherEntities;
    private State<WeatherState> state = new Sunny();
    
    private int currType =0;
    public WeatherState()
    {
        super();
        weatherEntities = new ArrayList<>();
    }
    
    @Override
    public void update(int delta) {
        int type = Forecast.getWeatherTypeOfDay(ButtonTab.SELECTED_IND);
        if(type != currType)
        {
            this.state.exit(this);
            if(WeatherType.CLOUDY.ordinal() == type)
            {
                this.state = new Cloudy(1500, 1.0f, 0.75f);
            }
            else if(WeatherType.PARTLYCLOUDY.ordinal() == type)
            {
                //Clouds with less alpha
                this.state = new Cloudy(3000, 1.0f, 0.0f);
            }
            else if(WeatherType.RAINY.ordinal() == type)
            {
                this.state = new Rainy();
            }
            else if(WeatherType.THUNDERSTORM.ordinal() == type)
            {
                this.state = new Stormy();
            }
            else if(WeatherType.SNOW.ordinal() == type)
            {
                this.state = new Snowy();
            }
            else if(WeatherType.SUNNY.ordinal() == type)
            {
                this.state = new Sunny();
            }
            else
            {
                this.state = new Sunny();
            }
            this.state.enter(this);
            this.currType = type;
        }
        //Updating Cloud Emitter;
    }
    
    public void render(org.newdawn.slick.Graphics g)
    {
        //Render Clouds/Rain/
    }
    
}
