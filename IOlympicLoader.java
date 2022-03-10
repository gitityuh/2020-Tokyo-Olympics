import java.util.List;
import java.io.FileNotFoundException;

/**
 * Instances of classes that implement this interface can be used to load a 
 * list of data points from a specified xml source file.
 * The following csv columns are used to load these olympic attributes:
 *   - Country -- the country the athlete is in
 *   - Year: the year of the olympics
 *   - Medal: the medals won in that year
 *   - Sex: the gender of the athlete
 *   - summer/winter: whether it was a summer or winter olympic
 *   
 */
public interface IOlympicLoader {

    /**
     * This method loads the data from the xml file to the country object.
     * @param filepath teh type of data we will sort through to return a list of
     * @return a list of country objects that have the specified param requirments
     */
    List<IOlympic> loadData(String filepath) throws FileNotFoundException;

}

