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
public class ButtonSetting extends Button {
    private static final String PATH = "setting-icon.png";

    public ButtonSetting(int x,int y) {
        super(PATH, x, y, 1.0f, "");
    }
    public void action() 
    {
        this.addToEntities(SettingsWindow.getInstance());
    }
    
    
    
}
