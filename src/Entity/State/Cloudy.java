/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity.State;

import src.Entity.CloudEmitter;
import src.Entity.WeatherState;

/**
 *
 * @author skas
 */
public class Cloudy extends State<WeatherState>{

    @Override
    public void enter(WeatherState obj) {
        obj.weatherEntities.add(new CloudEmitter(1.0f));
    }

    @Override
    public void execute(WeatherState obj) {
    }

    @Override
    public void exit(WeatherState obj) {
    }
    
}
