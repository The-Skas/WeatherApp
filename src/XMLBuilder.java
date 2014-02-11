package myweatherapp;

import org.w3c.dom.Document;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XMLBuilder {
    
    
    
    public static void main(String []args)
    {
        Forecast weatherData  = getForecast(getLocation("London"), true);
        System.out.println(weatherData.getWeatherToday());
    }
    
    public static Forecast getForecast(int location, boolean celsius)
    {
        Forecast f = new Forecast();

        double windspeed;
        double visibility;
        String[][] fiveDays = new String[5][5];
        int weatherToday;
        char units;
        
        InputStream inputXml = null;
        try
        {
            String url = "http://weather.yahooapis.com/forecastrss?w=" + location;

            if(celsius)
            {
                url += "&u=c";
            }

            inputXml  = new URL(url).openConnection().getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputXml);
            NodeList nodi = doc.getElementsByTagName("yweather:forecast");

            for(int i = 0; i < nodi.getLength(); i++)
            {
                Element node = (Element) nodi.item(i);
                fiveDays[i][0] = node.getAttribute("day");
                fiveDays[i][1] = node.getAttribute("date");
                fiveDays[i][2] = node.getAttribute("low");
                fiveDays[i][3] = node.getAttribute("high");
                fiveDays[i][4] = node.getAttribute("code");
            }
            
            //Wind speed
            nodi = doc.getElementsByTagName("yweather:wind");
            Element node = (Element) nodi.item(0);
            windspeed = Double.parseDouble(node.getAttribute("speed"));
            
            //Visibility
            nodi = doc.getElementsByTagName("yweather:atmosphere");
            node = (Element) nodi.item(0);
            visibility = Double.parseDouble(node.getAttribute("visibility"));
            
            //weather Today code
            nodi = doc.getElementsByTagName("yweather:condition");
            node = (Element) nodi.item(0);
            weatherToday = Integer.parseInt(node.getAttribute("code"));
            
            //units of measurement
            nodi = doc.getElementsByTagName("yweather:units");
            node = (Element) nodi.item(0);
            units = (node.getAttribute("temperature")).charAt(0);
            
            
            //Build Forecast Object
            f.setValues(windspeed, visibility, fiveDays, weatherToday, units);
  
        }
        catch (Exception ex)
        {
           System.out.println("Error in makeForecast: " + ex.getMessage());
        }
        finally
        {
            try
            {
               if (inputXml != null)
               inputXml.close();
            }
            catch (IOException ex)
            {
               JOptionPane.showMessageDialog(null, "Error in retrieveData: " + ex.getMessage());
            }
        }
        
        return f;
    }
    
    public static int getLocation(String name)
    {
        String url = "http://query.yahooapis.com/v1/public/yql?q=select+*+from+geo.places+where+text+=+";
        url += "'" + name + "'";
        
        InputStream inputXml = null;
        
        try
        {
            inputXml  = new URL(url).openConnection().getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputXml);
            NodeList nList = doc.getElementsByTagName("place");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;
            String woeidString = (eElement.getElementsByTagName("woeid").item(0).getTextContent());
            
            return Integer.parseInt(woeidString);
        }
        catch (Exception ex)
        {
           System.out.println("Error in getLocation: " + ex.getMessage());
        }
        finally
        {
            try
            {
               if (inputXml != null)
               inputXml.close();
            }
            catch (IOException ex)
            {
               JOptionPane.showMessageDialog(null, "Error in getLocation: " + ex.getMessage());
            }
        }
        
        return -1;
    }
    
}
