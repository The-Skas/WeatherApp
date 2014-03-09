/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import src.Forecast;
import src.Forecast.WeatherInfo;
import static src.XMLBuilder.getLocation;


/**
 *
 * @author skas
 * This Button should communicate to all other tabButtons, to the UI where info
 * will be displayed. That same info will be used for rendering.
 * 
 * As well, implementing the search location will require all the info within
 * the buttons to be updated. (each tab should store weather info.)
 */
public class ButtonTab extends Button {
    private static final String imgPATH = "tab.png";
    private static ArrayList<ButtonTab> tabList = new ArrayList<>();
    public static ButtonTab activeButton;
    
    public static int SELECTED_IND = 0;
    private static int DAY = 0;
    //determines if a button is Selected;
    private boolean isSelected = false;
    private int day_i;
    public ButtonTab(float x, float y, String text) {
        super(imgPATH, x, y, 1.0f, text);
        this.scale = 1.25f;
        tabList.add(this);
        
        if(ButtonTab.activeButton == null)
        {
            ButtonTab.activeButton = this;
            this.isSelected = true;
        }
        
        this.day_i = DAY;
        DAY++;
        
    }
    
    
    @Override
    public void update(int delta)
    {
        super.update(delta);
        if(this.isSelected == false)
        {
            if(this.isButtonDown())
            {
                this.color = new Color(0,0,0,255);
            }
            else if(this.isButtonUp())
            {
                this.color = new Color(255,255,255,255);
            }
            else if(this.isButtonRollOver())
            {
                this.color = new Color(100,100,100,255);
            }
        }
        else
        {
            this.color = new Color(0,0,0,255);
        }
        
        //Set Text for button
        String [][] fiveDays = Forecast.current.getFiveDays();
        String weather = fiveDays[day_i][WeatherInfo.DATE.ordinal()];
        String [] format = weather.split(" ");
        this.text.setText(format[0]+" "+format[1]);
    }
    
   
    public void action() 
    {
        this.color = new Color(Color.black);
        this.isSelected = true;
        
        ButtonTab.activeButton.isSelected = false; 
        ButtonTab.activeButton = this;
        
        ButtonTab.SELECTED_IND = this.day_i;
        //This play
    }
}
