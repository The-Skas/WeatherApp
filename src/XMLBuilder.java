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
        
        //getLocation("London");
        for(int i =0; i < 48; i++)
        {
            System.out.println(i + "= " + codeTranslator(Integer.toString(i)));
        }
    }
    public static Forecast getForecastOfSearch(String location)
    {
        ArrayList<String[]> locs = getLocation(location);
        if(locs.isEmpty())
            return null;
        int idLoc = Integer.parseInt(locs.get(0)[0]);
        return getForecast(idLoc, false);
    }
    //Translates Yahoo weather code into a smaller set
    private static String codeTranslator(String inputCode)
    {
        int code = Integer.parseInt(inputCode);
        int outputCode;
        if(code >= 0 && code <= 4)
        {
            outputCode = 4;
        }
        else if(code >= 5 && code <= 7)
        {
            outputCode = 5;
        }
        else if(code >= 8 && code <= 12)
        {
            outputCode = 3;
        }
        else if(code >= 13 && code <= 16 || code == 18)
        {
            outputCode = 5;
        }
        else if(code >= 19 && code <= 22)
        {
            outputCode = 6;
        }
        else if(code >= 23 && code <=25)
        {
            outputCode = 0;
        }
        else if(code >= 26 && code <= 28)
        {
            outputCode = 2;
        }
        else if(code == 29 || code == 30)
        {
            outputCode = 1;
        }
        else if(code >= 31 && code <= 34)
        {
            outputCode = 0;
        }
        else if(code >= 37 && code <= 39)
        {
            outputCode = 4;
        }
        else if(code >= 41 && code <= 43)
        {
            outputCode = 5;
        }
        else
        {
            switch (code) 
            {
                case 17: outputCode = 4;
                         break;
                case 35: outputCode = 4;
                         break;
                case 36: outputCode = 0;
                         break;
                case 40: outputCode = 3;
                         break;
                case 44: outputCode = 1;
                         break;
                case 45: outputCode = 4;
                         break;
                case 46: outputCode = 5;
                         break;
                case 47: outputCode = 4;
                         break;
                case 3200: outputCode = 7;
                           break;
                default: outputCode = 7;
                         break;
            }
        }
        
        return Integer.toString(outputCode);
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
            NodeList nodi = doc.getElementsByTagName("yweather:forecbast");

            for(int i = 0; i < nodi.getLength(); i++)
            {
                Element node = (Element) nodi.item(i);
                fiveDays[i][0] = node.getAttribute("day");
                fiveDays[i][1] = node.getAttribute("date");
                fiveDays[i][2] = node.getAttribute("low");
                fiveDays[i][3] = node.getAttribute("high");
                fiveDays[i][4] = codeTranslator(node.getAttribute("code"));
            }
            
            //Wind speed
            nodi = doc.getElementsByTagName("yweather:wind");
            Element node = (Element) nodi.item(0);
            windspeed = Double.parseDouble(node.getAttribute("speed"));
            
            //Visibility
            nodi = doc.getElementsByTagName("yweather:atmosphere");
            node = (Element) nodi.item(0);
            
            String visibilityTemp = node.getAttribute("visibility");
            if(visibilityTemp.isEmpty())    
            {
                visibility = -1;
            }
            else
            {
                visibility = Double.parseDouble(visibilityTemp);
            }
            
            //weather Today code
            nodi = doc.getElementsByTagName("yweather:condition");
            node = (Element) nodi.item(0);
            weatherToday = Integer.parseInt(codeTranslator(node.getAttribute("code")));
            
            //units of measurement
            nodi = doc.getElementsByTagName("yweather:units");
            node = (Element) nodi.item(0);
            units = (node.getAttribute("temperature")).charAt(0);
            
            
            //Build Forecast Object
            f.setValues(windspeed, visibility, fiveDays, weatherToday, units);
  
        }
        catch (Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Connection Error! in getForecast");

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
        name = name.replaceAll(" ", "+");
        url += "'" + name + "'";
        System.out.println(url);
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
           System.out.println(ex);
           JOptionPane.showMessageDialog(null, "Connection Error! in getLocation");
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
