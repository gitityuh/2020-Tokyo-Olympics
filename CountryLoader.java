import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *This class loads the countries from the xml file into the
 * country class
 *
 *
*/
public class CountryLoader implements ICountryLoader{
    String countryName;
    int gold;
    int silver;
    int bronze;
    int rank;
    String continent;
    
    
    /*
    This method loads the data from the olympic xml file and imports
    it to the Country class
     */
    @Override
    public List<ICountry> loadData(String filepath) throws FileNotFoundException {
        List<ICountry> retList = new ArrayList<>();
        //loads xml to a file
        File xml = new File(filepath);
        if(!xml.exists()){
            throw new FileNotFoundException("filepath is invalid");
        }
        //This is thexml file parser I got from a TA piazza link
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);
            NodeList list = doc.getElementsByTagName("row");
            for(int y = 0; y<list.getLength();y++){
                Node node = list.item(y);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    countryName = element.getElementsByTagName("Country").item(0).getTextContent();
                    
                    gold = Integer.parseInt(element.getElementsByTagName("Gold_Medal").item(0).getTextContent());
                    silver = Integer.parseInt(element.getElementsByTagName("Silver_Medal").item(0).getTextContent());
                    bronze = Integer.parseInt(element.getElementsByTagName("Bronze_Medal").item(0).getTextContent());
                    rank = Integer.parseInt(element.getElementsByTagName("Rank_By_Total").item(0).getTextContent());
                    continent = element.getElementsByTagName("Continent").item(0).getTextContent();
                    Country newCountry = new Country(countryName,gold,silver,bronze,rank,continent);
                    retList.add(newCountry);
                }
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (ParserConfigurationException  | SAXException | IOException  e) {
            e.printStackTrace();
        }
        return retList;
    }
}

