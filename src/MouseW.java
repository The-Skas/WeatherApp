

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
    
    public static boolean isButtonDown(int button)
    {
        return Mouse.isButtonDown(button);
    }
    
}
