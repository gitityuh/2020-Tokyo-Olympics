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

    if (commandLine.charAt(0) == '1') {
      countriesByMedal();
    }
    else if (commandLine.charAt(0) == '2') {
      displayCountries();
    }
  }

  // to help make it easier to test the functionality of this program,
  // the following helper methods will help support runCommandLoop():

  /**
   * prints command options to System.out
   */
  @Override public void displayCommandMenu() {

  }

  /**
   * displays a list of countries
   * @param countries
   */
  @Override public void displayCountries(List<ICountry> countries) {

  }

  /**
   * reads word from System.in, displays results
   */
  @Override public void countriesByMedal() {

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
