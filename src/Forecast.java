package src;

import java.util.ArrayList;


/*
 * Skas says: I am going to need to use an instance of this class for each of
 * the 7 ButtonTabs, each of them will contain a Forecast object.
*/



public class Forecast 
{
    public static String currentLocation = "London";
    public static Forecast current;
    public static boolean isCelsius = true;
    private double windspeed;
    private double visibility;
    private String[][] fiveDays = new String[5][5];
    private int weatherToday;
    private char units;
    
    //We would need a function to retrieve 7 days of forecast depending on
    //location. This is for both the search, and the UI.
    public static ArrayList<Forecast> fiveDaysForecast;
    public static ArrayList<Forecast> getWeeksForecast(String loc)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Forecast()
    {
        
    }
    
    public static int getWeatherTypeOfDay(int day)
    {
        String typeStr=Forecast.current.getFiveDays()[day][WeatherInfo.CODE.ordinal()];
        int type = Integer.parseInt(typeStr);
        return type;
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
    
    //Used for debugging purposes.
    public void setTempratureToday(int newWeather)
    {
        this.weatherToday = newWeather;
    }
    
    public String getUnits()
    {
        return isCelsius()? "C" : "F";
    }
    
    public boolean isCelsius()
    {
        if(units == 'C' || units == 'c') return true;
        else return false;
    }
    
    public void setUnits(char unit)
    {
        units = unit;
    }
    public String toString()
    {
        String s = "TEMPRATURE: "+getWeatherToday() + "\n";
        s +=       "WIND SPEED: "+getWindSpeed()    + "\n";
        s +=     "ALL OTHER DAYS \n";
        
        for(int i = 0; i < this.fiveDays.length; i++)
        {
            for(int j = 0; j< this.fiveDays[i].length; j++)
            {
                s+=" "+this.fiveDays[i][j];
            }
            s+="\n";
        }
        return s;
    }
    
    
    public enum WeatherType {
        SUNNY, PARTLYCLOUDY, CLOUDY, RAINY, THUNDERSTORM, SNOW, FOG, NOTAVAILABLE
    }
    public enum WeatherInfo {
        DAY,DATE,LOW, HI, CODE
    }
}
