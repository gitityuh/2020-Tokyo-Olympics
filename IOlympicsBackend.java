import java.util.List;

/**
 * An instance of a class that implements the following interface can be used to
 * sort and search the database of countries's 2020 Tokyo Olympic medals within
 * the Olympics app.
 */
public interface IOlympicsBackend {
    public void addCountry(IOlympic country); // adds country to backend database

    public int getNumberOfCountries();
    
    // These methods set the medal filters before calling the search and output methods
    // (all medals are included in the search results by default)
    public void setMedalType(String medal, boolean filter);
    public boolean getMedalFilter(String medal);
    public void toggleMedalFilter(String medal);

    // These methods return either a list of IOlympics or an IOlympic
    public IOlympic searchByName(String name);
    public List<IOlympic> outputByTotalMedals();
    public List<IOlympic> outputByTypeOfMedals(String medalType);
}

