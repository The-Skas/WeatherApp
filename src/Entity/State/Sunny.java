/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity.State;

import src.Entity.WeatherState;

/**
 *
 * @author skas
 */
public class Sunny extends State<WeatherState> {
    //Do nothing when sunny.
    @Override
    public void enter(WeatherState obj) {
    }

    @Override
    public void execute(WeatherState obj) {
    }

    @Override
    public void exit(WeatherState obj) {
    }
    
}
