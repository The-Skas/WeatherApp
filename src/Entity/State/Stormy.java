/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity.State;

import src.Entity.ButtonWarning;
import src.Entity.CloudEmitter;
import src.Entity.Lightning;
import src.Entity.RainEmitter;
import src.Entity.WeatherState;

/**
 *
 * @author skas
 */
public class Stormy extends State<WeatherState>{

    private int rate = 1500;
    private float color = 0.5f;
    private float min_alpha = 0.7f;
    public Stormy(int rate, float color, float min_alpha)
    {
        this.rate = rate;
        this.color = color;
        this.min_alpha = min_alpha;
    }

    public Stormy() {
    }
    @Override
    public void enter(WeatherState obj) {
        //obj.weatherEntities should be empty
        obj.weatherEntities.add(new CloudEmitter(color, rate, min_alpha));
        obj.weatherEntities.add(new RainEmitter());
        obj.weatherEntities.add(new Lightning());
        obj.weatherEntities.add(new ButtonWarning());

        //add rain effects.
    }

    @Override
    public void execute(WeatherState obj) {
    }

    @Override
    public void exit(WeatherState obj) {
        for(int i = 0; i < obj.weatherEntities.size();i++)
        {
            obj.weatherEntities.get(i).destroy();
        }
    }
    
}
