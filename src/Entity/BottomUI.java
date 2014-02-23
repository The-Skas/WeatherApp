/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import org.newdawn.slick.Graphics;

/**
 *
 * @author skas
 */
public class BottomUI extends Render
{
    private final static String path = "UI.png";
    private final int MAX_BUTTONS = 7;
    public BottomUI(float x, float y)
    {
        //Adds to entity list.
        super(x,y, path);
        
        //Construct lower tabs.
        float quarterScreen = Render.screenWidth/4;
        float spanByMAX = (Render.screenWidth-quarterScreen)/MAX_BUTTONS;
        for(int i = 0; i < 7; i++)
        {
            new ButtonTab(
            quarterScreen-10 + spanByMAX*i,185+this.getY(), "Feb 27"
            );
        }

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
