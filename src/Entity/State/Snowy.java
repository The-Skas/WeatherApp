/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity.State;

import src.Entity.CloudEmitter;
import src.Entity.SnowEmitter;
import src.Entity.WeatherState;

/**
 *
 * @author skas
 */
public class Snowy extends State<WeatherState>{

    private int rate = 1500;
    private float color = 1.0f;
    private float min_alpha = 0.0f;
    public Snowy(int rate, float color, float min_alpha)
    {
        this.rate = rate;
        this.color = color;
        this.min_alpha = min_alpha;
    }

    public Snowy() {
    }
    @Override
    public void enter(WeatherState obj) {
        //obj.weatherEntities should be empty
        obj.weatherEntities.add(new CloudEmitter(color, rate, min_alpha));
        obj.weatherEntities.add(new SnowEmitter());
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
