/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import quicktime.sound.Sound;
import src.Forecast;

/**
 *
 * @author skas
 */
public class WeatherTypeUI extends Render implements Updateable 
{
    private static final String PATH = "UIType.png";
    private Image [] weatherTypeImgs = new Image[5];
    private int lastInd = 0;
    
    public static int CURR_TYPE = 0;
    
    private float imgwScale = 1.0f;
    private float yOffset;
    private float xOffset;
   
    public WeatherTypeUI(float x, float y)
    {
        super(x,y,PATH);
        this.imgwScale = 0.3f;
        this.xOffset = 60;
        this.yOffset = 50;
        
        for(int i = 0; i < weatherTypeImgs.length; i++)
        {
            try {
                weatherTypeImgs[i] = new Image("res/weatherIcons/"+i+".png");
            } catch (SlickException ex) {
                Logger.getLogger(WeatherTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public float getYOffset()
    {
        return this.yOffset * this.getHeightScreenScale() + this.getY();
    }
    
    public float getXOffset()
    {
        return this.xOffset * this.getWidthScreenScale()  + this.getX();
    }
            
    
    @Override
    public void render(org.newdawn.slick.Graphics g)
    {
        super.render(g);
        
        this.weatherTypeImgs[CURR_TYPE].draw(getXOffset(), getYOffset(), this.getScale()*this.imgwScale);
    }
    @Override
    public void update(int delta) {
        //Render current buttons img
        CURR_TYPE = Forecast.getWeatherTypeOfDay(ButtonTab.SELECTED_IND);
        
        if(ButtonTab.SELECTED_IND != this.lastInd)
        {
            src.Sound.play(0, CURR_TYPE);
            this.lastInd=ButtonTab.SELECTED_IND;
        }
    }
    
}
