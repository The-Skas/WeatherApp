/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.awt.Font;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Forecast;
import src.WeatherApp;

/**
 *
 * @author skas
 */
public class SettingsWindow extends Render implements Updateable
{
    private final static String PATH = "buttonframe.png";
    private static SettingsWindow instance;
    private ArrayList<Render> children = new ArrayList<>();
    private ButtonOK okBtn;
    private ButtonToggle farenheit;
    private ButtonToggle celsius;
    
    private ButtonToggle resSmall;
    private ButtonToggle resLarge;
    
    private TextUI unitTxt;
    private TextUI farenTxt;
    private TextUI celsTxt;
    
    private TextUI resTxt;
    private TextUI resLargeTxt;
    private TextUI resSmallTxt;
    
    public static SettingsWindow getInstance()
    {
        if (SettingsWindow.instance == null)
        {
            instance = new SettingsWindow();
            return instance;
        }
        else
        {
            instance.okBtn.text.reInit();
            for(int i = 0; i <instance.children.size(); i++)
            {
                if(instance.children.get(i) instanceof TextUI)
                {
                    TextUI txtUI =(TextUI) instance.children.get(i);
                    txtUI.reInit();
                }
                else if(instance.children.get(i) instanceof Button)
                {
                    Button buttonUI = (Button) instance.children.get(i);
                    Button.topButtons.add(buttonUI);
                }
            }
            return instance;
        }
    }
    
    private SettingsWindow()
    {
        super(0,0,PATH);
        //Is celcius
        boolean isCelsius = Forecast.current.isCelsius();
        boolean isLargeRes = (Render.screenWidth == 1024);
        
        int yOff = -50;
        this.x = Render.screenWidth/2.0f - this.img.getWidth()/2.0f;
        this.y = 100;
        float yButton = this.img.getHeight()/2.0f;
        this.okBtn = new ButtonOK(this.x + 160,this.y + 340, this );
        Button.topButtons.add(okBtn);
        okBtn.destroy();
        children.add(okBtn);
        //okBtn already is destroyed
        float quarterScreen = Render.screenWidth/4;
        
        //Add text
        this.unitTxt = new TextUI("Units:",(int)quarterScreen + 30,(int) (200+yOff));
        this.unitTxt.destroy();
        children.add(unitTxt);
        
        this.farenTxt = new TextUI("Farenheit",(int)quarterScreen + 110, 250 + yOff,
                                    new Font("Verdana", Font.BOLD, 25));
        this.farenTxt.destroy();
        children.add(farenTxt);
        
        this.celsTxt = new TextUI("Celsius",(int)quarterScreen + 310, 250 + yOff,
                                        new Font("Verdana", Font.BOLD, 25));
        celsTxt.destroy();
        children.add(celsTxt);
        //Add buttons
        farenheit=new ButtonToggle(quarterScreen+150, 200 + yOff, !isCelsius);
        farenheit.destroy();
        children.add(farenheit);
        Button.topButtons.add(farenheit);
        
        celsius = new ButtonToggle(farenheit.x + 200, farenheit.y, isCelsius);
        children.add(celsius);
        celsius.destroy();
        Button.topButtons.add(celsius);
        
        this.resTxt = new TextUI("Resolution:",(int)quarterScreen + 30,(int) (350+yOff));
        resTxt.destroy();
        children.add(resTxt);
        
        this.resSmallTxt = new TextUI("480X320",(int)quarterScreen + 110, 450 + yOff,
                                        new Font("Verdana", Font.BOLD, 25));
        children.add(this.resSmallTxt);
        resSmallTxt.destroy();
        
        this.resSmall = new ButtonToggle(quarterScreen+150, 400+yOff, !isLargeRes);
        children.add(this.resSmall);
        Button.topButtons.add(resSmall);
        resSmall.destroy();
        
        this.resLargeTxt = new TextUI("1024X768", (int) (this.resSmall.x + 140), 450 + yOff,
                                        new Font("Verdana", Font.BOLD, 25));
        children.add(this.resLargeTxt);
        resLargeTxt.destroy();

        
        this.resLarge =    new ButtonToggle(this.resSmall.x+200, resSmall.y, isLargeRes);
        children.add(this.resLarge);
        Button.topButtons.add(resLarge);
        resLarge.destroy();
        


    }
    
    @Override
    public void update(int delta) 
    {
        //Allow parent to control
        
        if(farenheit.isHit())
        {
           farenheit.on();

           celsius.off();

        }
        if(celsius.isHit())
        {
           celsius.on();
           farenheit.off();
        }
        
        if(resSmall.isHit())
        {
           resSmall.on();

           resLarge.off();

        }
        if(resLarge.isHit())
        {
           resLarge.on();
           resSmall.off();
        }
        
        for(int i = 0; i < children.size();i++)
        {
            if(children.get(i) instanceof Updateable)
            {
                Updateable temp = (Updateable)children.get(i);
                temp.update(delta);
            }
        }
    }
    
    public void render(org.newdawn.slick.Graphics g)
    {
        //render frame first
        super.render(g);

        for(int i = 0; i < children.size();i++)
        {
            children.get(i).render(g);
        }
    }
    
    public void printAll() 
    {
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                //field.setAccessible(true); // if you want to modify private fields
                System.out.println(field.getName()
                        + " - " + field.getType()
                        + " - " + field.get(this));
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SettingsWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void destroy()
    {
        
        super.destroy();
        
        //weather
        if(celsius.isSelected())
        {
            Forecast.current.setUnits('C');
        }
        else
        {
            Forecast.current.setUnits('F');
        }
        
        if(resSmall.isSelected())
        {
            if(Render.screenWidth != 480)
            {
                Render.screenWidth = 480;
                Render.screenHeight = 320;
                WeatherApp.resolutionChanged = true;
            }
        }
        else
        {
            if(Render.screenWidth != 1024)
            {
                Render.screenWidth = 1024;
                Render.screenHeight = 768;
                WeatherApp.resolutionChanged = true;
            }
        }
        
        Button.topButtons.clear();
        //Do relevant logic here.
    }
    
            
    
}
