/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity.State;

import src.Entity.ButtonWarning;
import src.Entity.Fog;
import src.Entity.WeatherState;

/**
 *
 * @author skas
 */
public class Foggy extends State<WeatherState> {
    //Do nothing when sunny.
    @Override
    public void enter(WeatherState obj) 
    {
        obj.weatherEntities.add(new Fog());
        obj.weatherEntities.add(new ButtonWarning());
    }

    @Override
    public void execute(WeatherState obj) {
    }

    @Override
    public void exit(WeatherState obj) 
    {
        for(int i = 0; i < obj.weatherEntities.size();i++)
        {
            obj.weatherEntities.get(i).destroy();
        }
    }
    
}
