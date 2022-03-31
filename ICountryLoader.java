import java.util.List;
import java.io.FileNotFoundException;

/**
 * Instances of classes that implement this interface can be used to load a 
 * list of data points from a specified xml source file.
 * The following csv columns are used to load these Country attributes:
 *   name: name of the country
 *   goldMedal: number of gold medals
 *   silverMedal: number of silver medals
 *   bronzeMedal: number of bronze medals
 *   totalMedals: number of all medals
 *   rank: olympic rank
 *   continent: continent of the Country
 *   
 */
public interface ICountryLoader {

    /**
     * This method loads the data from the xml file to the country object.
     * @param filepath the path to xml file
     * @return a list of country objects that have the specified param requirements
     */
    List<ICountry> loadData(String filepath) throws FileNotFoundException;

}

