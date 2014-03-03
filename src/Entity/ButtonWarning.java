/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src.Entity;

/**
 *
 * @author skas
 */
public class ButtonWarning extends Button  {
    private static final String PATH = "warning.png";
    
    public ButtonWarning()
    {
        super(PATH, 100, 100, 1.0f, "");
    }

    @Override
    public void update(int delta) {
        super.update(delta);
    }

    @Override
    public void action() {
        System.out.println("Stop poking me!");
    }
}
