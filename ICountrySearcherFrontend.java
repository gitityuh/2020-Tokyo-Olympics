import java.util.List;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the CountrySearcher App.
 */
public interface ICountrySearcherFrontend {

    // constructor args (ICountrySearcherBackend) reads input from System.in
    // constructor args (String, ICountrySearcherBackend) reads input from String

    /**
     * This method drives the entire read, eval, print loop (repl) for the
     * Country Search App.  This loop will continue to run until the user
     * explicitly enters the quit command.
     */
    void runCommandLoop();

    // to help make it easier to test the functionality of this program,
    // the following helper methods will help support runCommandLoop():

    public void displayCommandMenu(); // prints command options to System.out

    public void displayCountries(List<ICountry> countries); // displays a list of countries

    public void countriesByMedal(); // reads word from System.in, displays results

    public void medalsByCountry(); // reads year from System.in, displays results

    public void filterByContinent(); // filter toggles by continent
}

