import java.util.List;

/**
 * An instance of a class that implements the following interface can be used to
 * sort and search the database of countries's 2020 Tokyo FrontendCountry medals within
 * the Countrys app.
 */
public interface ICountrySearcherBackend {
    public void addCountry(ICountry country); // adds country to backend database

    //public int getNumberOfCountries();

    // These methods set the medal filters before calling the search and output methods
    // (all medals are included in the search results by default)
    //public void setMedalType(String medal, boolean filter);
    //public boolean getMedalFilter(String medal);
    //public void toggleMedalFilter(String medal);

    // These methods return either a list of ICountrys or an ICountry
    ICountry searchByName(String name);
    //public List<ICountry> outputByTotalMedals();
    List<ICountry> outputByTypeOfMedals(String medalType);
    List<ICountry> outputByAlphabeticalName();
    void toggleContinentFilter(Integer number);
}

