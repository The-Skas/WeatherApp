/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package src;

/**
 *
 * @author skas
 */
public class ThreadSearch implements Runnable {
    private String search;
    public ThreadSearch(String searchQ)
    {
        this.search = searchQ;
    }
    @Override
    public void run() {
        XMLBuilder.getForecastOfSearch(search);
    }
    
}
