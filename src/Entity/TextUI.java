/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.BufferedImageUtil;


/**
 *
 * @author skas
 */
public class TextUI extends Render 
{
    private static float goldenRatio = 1.525f;
    private String text;
    private java.awt.Font font; //=new Font(Font.SERIF, Font.BOLD, 35);
    protected TrueTypeFont fontw;
    private FontMetrics fm;
    private final float fontSizeRef;
    private Render container;
    
    public TextUI( String text, int x, int y)
    {
        super(x,y);
        this.text = text;
        this.scale = 1.0f;
        this.font = new Font(Font.SERIF, Font.BOLD, 35);
        this.fontw = new TrueTypeFont(font, true);
        this.fontSizeRef = this.font.getSize();
    }
    
    public TextUI( String text, int x, int y, Font font)
    {
        super(x,y);
        this.text = text;
        this.scale = 1.0f;
        this.font = font;
        this.fontw = new TrueTypeFont(font, true);
        this.fontSizeRef = this.font.getSize();
    }
    public TextUI(Render container, String text)
    {
        this(text, 0 ,0, new Font(Font.SERIF, Font.BOLD, 35));
        this.container = container;
    }
    public TextUI(Render container, String text, Font font)
    {
        this(text,0 ,0, new Font(Font.SERIF, Font.BOLD, 35));
        this.container = container;
        this.font = font;
    }
    public void render(org.newdawn.slick.Graphics g)
    {
        //If it has a container, it draws it relative to its center.
        if(this.container != null)
        {
            int textWidth = fontw.getWidth(this.text);
            int textHeight = fontw.getHeight(this.text);

            float xmid =container.getWidth()/2.0f;
            float ymid =container.getHeight()/2.0f;
            g.setColor(org.newdawn.slick.Color.white);

            float xText = (container.getX()+xmid)- textWidth/2;
            float yText = (container.getY()+ymid)- textHeight/2;

            g.setColor(org.newdawn.slick.Color.red);
            fontw.drawString(xText, yText, this.text);
            
            if(DEBUG)
            {
                g.drawRect(xText, yText, 
                      textWidth, textHeight);
            }
        }
        else
        {
            fontw.drawString(this.getX(), this.getY(),this.text);
            if(DEBUG)
            {
                g.drawRect(this.getX(), this.getY(), 
                      this.fontw.getWidth(text), this.fontw.getHeight(text));
            }
            
        }
    }
    
    public void setText(String s)
    {
        this.text = s;
    }
    
    @Override
    public void reInit()
    {
       System.out.println(this.text);
       System.out.println("size"+font.getSize());
       int newfontSize =(int) Math.ceil(this.getScale()* fontSizeRef);
    
       this.font = new Font(font.getName(),font.getStyle(), newfontSize);
       this.fontw = new TrueTypeFont(font, false);
       
       
    }
}
