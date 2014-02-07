/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rometest;


import org.w3c.dom.Document;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 *
 * @author skas
 */
public class XMLBuilder {
    public static void main(String []args){
    InputStream inputXml = null;
    try
    {



       inputXml  = new URL("http://weather.yahooapis.com/forecastrss?w=2502265").openConnection().getInputStream();



       DocumentBuilderFactory factory = DocumentBuilderFactory.
                                        newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();
       Document doc = builder.parse(inputXml);
       NodeList nodi = doc.getElementsByTagName("yweather:atmosphere");
     
       System.out.println(nodi.item(1));
//       if (nodi.getLength() > 0)
//       {
//          Element nodo = (Element)nodi.item(0);
//          System.out.println(nodo.getAttribute("code"));
//          String strLow;
//                 strLow = nodo.getAttribute("low");
//          Element nodo1 = (Element)nodi.item(0);
//          String strHigh = nodo1.getAttribute("high");
//          System.out.println("Temperature low: " + strLow);
//          System.out.println("Temperature high: " + strHigh);
          
          for(int i = 0; i < nodi.getLength(); i++)
          {
              Element node = (Element) nodi.item(i);
              System.out.println(node.getAttribute("visibility"));
          }
        
    }
    catch (Exception ex)
    {
       System.out.println(ex.getMessage());
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
          System.out.println(ex.getMessage());
       }
    }
    }
    
}
