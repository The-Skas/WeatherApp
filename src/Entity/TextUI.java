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
    private java.awt.Font font;
    private TrueTypeFont fontw;
    private FontMetrics fm;
    Render container;
    public TextUI(Render container, String text)
    {
        this.container = container;
        this.text = text;
        this.font = new Font(Font.SERIF, Font.BOLD, 35);

        this.fontw = new TrueTypeFont(font, true);
        this.scale = 1.0f;
    }
    public TextUI(Render container, String text, Font font)
    {
        this.container = container;
        this.text = text;
        this.font = font;
        this.scale = 1.0f;
    }
     public TextUI( String text)
    {
        this.container = null;
        this.text = text;
        this.scale = 1.0f;
        this.font = new Font(Font.SERIF, Font.BOLD, 35);
        this.fontw = new TrueTypeFont(font, true);
    }
    private final static int SIZE = 256;
    private BufferedImage createImage(String label) 
    {
      FontRenderContext frc = new FontRenderContext(null, true, true);
      TextLayout layout = new TextLayout(label, font, frc);
      Rectangle r = layout.getPixelBounds(null, 0, 0);
      System.out.println(r);
      BufferedImage bi = new BufferedImage(
          r.width + 1, r.height + 1, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = (Graphics2D) bi.getGraphics();
      g2d.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setColor(new Color(0,0,0, 0));
      g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());
    //        g2d.setColor(getForeground());
      layout.draw(g2d, 0, -r.y);
      g2d.dispose();
      return bi;
    }
    public void render(org.newdawn.slick.Graphics g)
    {
//       BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
//       FontMetrics fm = bi.getGraphics().getFontMetrics(font);
//       int width = fm.stringWidth(this.text);
//       int height = fm.getHeight();
       int textWidth = fontw.getWidth(this.text);
       int textHeight = fontw.getHeight(this.text);
        
       float xmid =container.getWidth()/2.0f;
       float ymid =container.getHeight()/2.0f;
       g.setColor(org.newdawn.slick.Color.white);
//       g.setFont(fontw);
       
       float xText = (container.getX()+xmid)- textWidth/2;
       float yText = (container.getY()+ymid)- textHeight/2;

       g.setColor(org.newdawn.slick.Color.red);
       fontw.drawString(xText, yText, this.text);
       g.drawRect(container.getX()+xmid, container.getY()+ymid, 
                    textWidth, textHeight);
    }
    
    
    
    @Override
    public void reInit()
    {
       System.out.println("size"+font.getSize());
       float fontsizeF = (float) font.getSize();
       int newfontSize =(int) (this.getScale()*fontsizeF*goldenRatio);
    
       this.font = new Font(font.getName(),font.getStyle(), newfontSize);
       this.fontw = new TrueTypeFont(font, false);
    }
}
