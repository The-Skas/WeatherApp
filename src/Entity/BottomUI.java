/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.awt.Font;
import java.util.ArrayList;
import src.Forecast;
import static src.XMLBuilder.getForecast;
import static src.XMLBuilder.getLocation;
import org.newdawn.slick.Graphics;
import src.Forecast.WeatherInfo;
import src.Forecast.WeatherType;
import src.XMLBuilder;

/**
 *
 * @author skas
 */
public class BottomUI extends Render implements Updateable
{
    private final static String path = "UI.png";
    private final int MAX_BUTTONS = 5;
    
    private TextUI tempratureText;
    private TextUI windSpeedText;
    private TextUI percipText;
    private TextUI humidText;
    //HelpIcon
     //-pops up
    //SettingIcon
     //-pops up
    public BottomUI(float x, float y)
    {
        //Adds to entity list.
        super(x,y, path);
        new WeatherTypeUI(x,y);
        
        //cant be threaded since application needs a first location.
        XMLBuilder.getForecastOfSearch("London");
        //Construct lower tabs.
        float quarterScreen = Render.screenWidth/4;
        float spanByMAX = (Render.screenWidth-quarterScreen)/MAX_BUTTONS;
        for(int i = 0; i < MAX_BUTTONS; i++)
        {
            //Additionally, each button should store the state of the weather.
            new ButtonTab(
                5+quarterScreen + spanByMAX*i,170+this.getY(),"Feb 27"
            );
        }
        
        //Create Text
        float yTemprature = 220/3.0f + this.getY();
        tempratureText = new TextUI("33",(int)quarterScreen,(int) yTemprature,
                            new Font(Font.SERIF, Font.BOLD, 70));
        tempratureText.x = Render.screenWidth*1.0f/2.0f;
        
        int infoX = Render.screenWidth*3/3;
        int infoY = (int)this.y+15;
        
        new ButtonSetting(infoX-70, infoY);
        int infoDiffY = 185/3;
//        windSpeedText = new TextUI("wind",infoX, infoY);
//        //add icon
//        percipText = new TextUI("Percip",infoX, infoY+infoDiffY*1);
//        //add icon
//        humidText  = new TextUI("Humid", infoX, infoY+infoDiffY*2);
    }
    
    @Override
    public void update(int delta)
    {
        WeatherInfo inf = null;
        int i = ButtonTab.SELECTED_IND;
        
        float tempHigh = Float.parseFloat
                        (
                            Forecast.current.getFiveDays()[i][WeatherInfo.HI.ordinal()]
                        );
        
        float tempLow = Float.parseFloat
                        (
                            Forecast.current.getFiveDays()[i][WeatherInfo.LOW.ordinal()]
                        );
        //is farenheit
        if(!(Forecast.current.isCelsius()))
        {
            tempHigh = tempHigh * 1.8f + 32;
            tempLow  = tempLow  * 1.8f + 32;
        }
        tempratureText.setText((int)tempHigh
                              +""+Forecast.current.getUnits()+" / "
                              +(int)tempLow
                              +""+Forecast.current.getUnits());
        
        String code = Forecast.current.getFiveDays()[i][WeatherInfo.CODE.ordinal()];
        int weatherType = Integer.parseInt(code);
        
    }
    
    /**
     * This render serves to stretch the container due to the scaling issues
     * in the different resolutions. This is because of the different ratios.
     * @param g Graphics object
     */
    @Override
    public void render(Graphics g)
    {
        this.img.draw(this.getX(), this.getY(), this.getWidthTrue(), this.getHeightTrue());
    }
}
