/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;
import org.lwjgl.input.Mouse;
import src.Entity.Render;

/**
 *
 * @author skas
 */
public class MouseW {
    
    public static int getX()
    {
        return Mouse.getX();
    }
    public static int getY()
    {
        return Render.screenHeight - Mouse.getY();
    }
    
    
}
