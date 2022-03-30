import java.util.List;
import java.util.Scanner;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the CountrySearcher App.
 */
public class CountrySearcherFrontend implements ICountrySearcherFrontend{

  Scanner scanner;
  ICountrySearcherBackend backend;

  /**
   * Reads input from System.in
   * @param backend
   */
  public CountrySearcherFrontend(ICountrySearcherBackend backend) {
    scanner = new Scanner(System.in);
    this.backend = backend;
    System.out.println("Welcome to the Show Searcher App!\n" +
        "=================================");
    displayCommandMenu();
    runCommandLoop();
  }

  /**
   * Reads input from String input
   * @param backend
   * @param input
   */
  public CountrySearcherFrontend(ICountrySearcherBackend backend, String input) {
    scanner = new Scanner(input);
    this.backend = backend;
    System.out.println("Welcome to the Show Searcher App!\n" +
        "=================================");
    displayCommandMenu();
  }

  /**
   * This method drives the entire read, eval, print loop (repl) for the
   * Country Search App.  This loop will continue to run until the user
   * explicitly enters the quit command.
   */
  @Override public void runCommandLoop() {
    String commandLine = scanner.nextLine().trim();
    char command = commandLine.charAt(0);
    // chooses method to call by command
    switch (command) {
      case '1':
        countriesByMedal();
        break;
      case '2':
        displayCountries();
        break;
      case '3':
        medalsByCountry();
      case '4':
        filterByContinent();
      case '5':
        return;
      // if all cases fail, repeat command loop
      default:
        System.out.println("Invalid Command!");
        displayCommandMenu();
        runCommandLoop();
    }
  }

  // to help make it easier to test the functionality of this program,
  // the following helper methods will help support runCommandLoop():

  /**
   * prints command options to System.out
   */
  @Override public void displayCommandMenu() {
    System.out.println(
        "Command Menu:\n" +
        "   1) Countries by Medals\n" +
        "   2) Countries in Alphabetical Order\n" +
        "   3) Medals by Country\n" +
        "   4) Filter by Continent\n" +
        "   3) Quit\n"
    );
  }

  /**
   * displays a list of countries sorted alphabetically
   * @param countries list of countries in Olympic
   */
  @Override public void displayCountries(List<ICountry> countries) {
    for (ICountry country : countries) {
      System.out.println(country.getName());
    }
  }

  /**
   * reads word from System.in, displays results
   */
  @Override public void countriesByMedal() {
    ICountry country = backend.searchByName("Canada");
  }

  /**
   * reads year from System.in, displays results
   */
  @Override public void medalsByCountry() {

  }

  /**
   * filter toggles by continent
   */
  @Override public void filterByContinent() {

  }
}
