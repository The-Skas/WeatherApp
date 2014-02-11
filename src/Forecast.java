package myweatherapp;

public class Forecast 
{
    private double windspeed;
    private double visibility;
    private String[][] fiveDays = new String[5][5];
    private int weatherToday;
    private char units;
        
    public Forecast()
    {
        
    }
    
    public void setValues(double ws, double v, String[][] fd, int wt, char u)
    {
        windspeed = ws;
        visibility = v;
        fiveDays = fd;
        weatherToday = wt;
        units = u;
    }
    
    public double getWindSpeed()
    {
        return windspeed;
    }
    
    public double getVisibility()
    {
        return visibility;
    }
    
    public String[][] getFiveDays()
    {
        return fiveDays;
    }
    
    public int getWeatherToday()
    {
        return weatherToday;
    }
    
    public boolean isCelsius()
    {
        if(units == 'C' || units == 'c') return true;
        else return false;
    }
}
