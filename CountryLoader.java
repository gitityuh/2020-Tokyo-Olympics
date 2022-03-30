import java.io.FileNotFoundException;
import java.util.List;
//place holder class
public class CountryLoader implements ICountryLoader{
    /**
     * This method loads the data from the xml file to the country object.
     *
     * @param filepath the path to xml file
     * @return a list of country objects that have the specified param requirements
     */
    @Override public List<ICountry> loadData(String filepath) throws FileNotFoundException {
        return null;
    }
}
