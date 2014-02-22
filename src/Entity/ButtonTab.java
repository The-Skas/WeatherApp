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
public class ButtonTab extends Button {
            //         g.setFont(font);
    private static final String imgPATH = "tab.png";
    public ButtonTab(float x, float y, String text) {
        super(imgPATH, x, y, 1.0f, text);
    }
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
