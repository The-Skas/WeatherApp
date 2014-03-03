/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;
import org.newdawn.slick.gui.TextField;

/**
 *
 * @author skas
 */
public class ThreadSearch implements Runnable {
    private TextField search;
    private String searchQ;
    public ThreadSearch(TextField search)
    {
        this.search = search;
        this.searchQ = search.getText();
    }
    @Override
    public void run() {
        search.setText("Searching ...");
        XMLBuilder.getForecastOfSearch(searchQ);
        search.setText("");
    }
    
}
