package src;

import org.w3c.dom.Document;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XMLBuilder {
    
    
    
    public static void main(String []args)
    {
        //Forecast weatherData  = getForecast(getLocation("London"), true);
        //System.out.println(weatherData.getWeatherToday());
        ArrayList<String[]> locs = getLocation("London");
        int idLoc = Integer.parseInt(locs.get(0)[0]);
        Forecast fc = getForecast(idLoc, true);
        System.out.println(fc.getWeatherToday());
        
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
    
    public static ArrayList getLocation(String name)
    {
        String url = "http://query.yahooapis.com/v1/public/yql?q=select+*+from+geo.places+where+text+=+";
        url += "'" + name + "'";
        
        ArrayList locations = new ArrayList<String[]>();
        
        InputStream inputXml = null;
        
        try
        {
            inputXml  = new URL(url).openConnection().getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputXml);
            NodeList nList = doc.getElementsByTagName("place");
            
            for(int i = 0; i < nList.getLength(); i++)
            {
                String[] locationEntry = new String[4];
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                locationEntry[0] = (eElement.getElementsByTagName("woeid").item(0).getTextContent());
                locationEntry[1] = (eElement.getElementsByTagName("name").item(0).getTextContent());
                locationEntry[2] = (eElement.getElementsByTagName("country").item(0).getTextContent());
                locationEntry[3] = (eElement.getElementsByTagName("admin1").item(0).getTextContent());
                locations.add(locationEntry);
                System.out.println(locationEntry[0] + " " + locationEntry[1] + " " + locationEntry[2] + " " + locationEntry[3]);
            }
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
        
        return locations;
    }
    
}
