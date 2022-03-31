import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the CountrySearcher App.
 */
public class CountrySearcherFrontend implements ICountrySearcherFrontend{

  Scanner scanner;
  ICountrySearcherBackend backend;
  String[] continentStrings = {"___", "_x_", "_x_", "_x_", "_x_", "_x_", "_x_"};

  /**
   * Reads input from System.in
   * @param backend
   */
  public CountrySearcherFrontend(ICountrySearcherBackend backend) {
    scanner = new Scanner(System.in);
    this.backend = backend;
    System.out.println("Welcome to the Country Searcher App!\n" +
        "=================================");
    displayCommandMenu();
//    runCommandLoop();
  }

  /**
   * Reads input from String input
   * @param backend
   * @param input
   */
  public CountrySearcherFrontend(ICountrySearcherBackend backend, String input) {
    scanner = new Scanner(input);
    this.backend = backend;
    System.out.println("Welcome to the Country Searcher App!\n" +
        "=================================");
    displayCommandMenu();
  }

  /**
   * This method drives the entire read, eval, print loop (repl) for the
   * Country Search App.  This loop will continue to run until the user
   * explicitly enters the quit command.
   */
  @Override public void runCommandLoop() {
    System.out.println("Choose a command from the menu above: ");
    String commandLine = "";
    try {
      commandLine = scanner.nextLine();
    } catch (InputMismatchException ime) {
      System.out.println("Input must be a number. Please try again");
      runCommandLoop();
    }

    // chooses method to call by command
    switch (commandLine.charAt(0)) {
      case '1':
        countriesByMedal();
        break;
      case '2':
        displayCountries(backend.outputByAlphabeticalName());
        break;
      case '3':
        medalsByCountry();
        break;
      case '4':
        filterByContinent();
      case '5':
        return;
      // if all cases fail, repeat command loop
      default:
        System.out.println("Invalid Command!");
        displayCommandMenu();
        runCommandLoop();
        break;
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
        "   5) Quit\n"
    );
    runCommandLoop();
  }

  /**
   * displays a list of countries sorted alphabetically
   * @param countries list of countries in Olympic
   */
  @Override public void displayCountries(List<ICountry> countries) {
    for (ICountry country : countries) {
      System.out.println(country.getName());
    }
    displayCommandMenu();
  }

  /**
   * reads word from System.in, displays results
   */
  @Override public void countriesByMedal() {
    System.out.println("Choose a medal type you would like to search for: ");
    String medalType = scanner.nextLine().trim().toLowerCase();

//    if (!(medalType.equals("gold") || medalType.equals("silver") || medalType.equals("bronze"))) {
//      System.out.println("The medal type is invalid. Please try again.");
//      countriesByMedal();
//    }
    if (!(medalType.equals("gold") || medalType.equals("silver") || medalType.equals("bronze"))) {
      System.out.println("Invalid medal type. Please try again.");
      countriesByMedal();
    }

    List<ICountry> countries = backend.outputByTypeOfMedals(medalType);
//    int medalCount = 0;
    for (ICountry country : countries) {

      switch (medalType) {
        case ("gold") :
          System.out.println(country.getName() + ": " + country.getGoldMedals());
          break;
        case ("silver") :
          System.out.println(country.getName() + ": " + country.getSilverMedals());
          break;
        case ("bronze") :
          System.out.println(country.getName() + ": " + country.getBronzeMedals());
          break;
      }

      }
//      System.out.println(country.getName() + ": " + medalType + " " + medalCount);
//    }
    displayCommandMenu();
  }

  /**
   * reads year from System.in, displays results
   */
  @Override public void medalsByCountry() {
    System.out.println("Choose a country you would like to search for (case sensitive!): ");
    String countryName = scanner.nextLine().trim();
    ICountry countryObject = backend.searchByName(countryName);
    if (countryObject == null) {
      System.out.println("Country does not exist. Please try again.");
      medalsByCountry();
    }
    System.out.println("Gold: " + countryObject.getGoldMedals());
    System.out.println("Silver: " + countryObject.getSilverMedals());
    System.out.println("Bronze: " + countryObject.getBronzeMedals());
    displayCommandMenu();
  }

  /**
   * filter toggles by continent
   */
  @Override public void filterByContinent() {
//    boolean[] continentToggles = {false, true, true, true, true, true, true};

    System.out.println("Continents:\n" +
        "   1) " + continentStrings[1] + " Africa\n" +
        "   2) " + continentStrings[2] + " Asia\n" +
        "   3) " + continentStrings[3] + " Oceania\n" +
        "   4) " + continentStrings[4] + " Europe\n" +
        "   5) " + continentStrings[5] + " North America\n" +
        "   6) " + continentStrings[6] + " South America\n" +
        "   7) quit"
    );

    System.out.println("Choose the continent that you would like to toggle the filter for: ");
    String toggleNum = "";
    try {
       toggleNum = scanner.nextLine().trim();
    } catch (InputMismatchException ime) {
      System.out.println("Filter must be a number. Please try again.");
      filterByContinent();
    }
    switch (toggleNum.charAt(0)) {
      case '1':
        backend.toggleContinentFilter(1);
        if (continentStrings[1].equals("_x_")) {
          continentStrings[1] = "___";
        } else {
          continentStrings[1] = "_x_";
        }
        break;
      case '2':
        backend.toggleContinentFilter(2);
        if (continentStrings[2].equals("_x_")) {
          continentStrings[2] = "___";
        } else {
          continentStrings[2] = "_x_";
        }
        break;
      case '3':
        backend.toggleContinentFilter(3);
        if (continentStrings[3].equals("_x_")) {
          continentStrings[3] = "___";
        } else {
          continentStrings[3] = "_x_";
        }
        break;
      case '4':
        backend.toggleContinentFilter(4);
        if (continentStrings[4].equals("_x_")) {
          continentStrings[4] = "___";
        } else {
          continentStrings[4] = "_x_";
        }
        break;
      case '5':
        backend.toggleContinentFilter(5);
        if (continentStrings[5].equals("_x_")) {
          continentStrings[5] = "___";
        } else {
          continentStrings[5] = "_x_";
        }
        break;
      case '6':
        backend.toggleContinentFilter(6);
        if (continentStrings[6].equals("_x_")) {
          continentStrings[6] = "___";
        } else {
          continentStrings[6] = "_x_";
        }
        break;
      case '7':
        displayCommandMenu();
        return;
      default:
        System.out.println("Please try again.");
        filterByContinent();
        break;
    }
    filterByContinent();
  }
}
