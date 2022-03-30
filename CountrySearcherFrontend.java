import java.util.InputMismatchException;
import java.util.List;
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
    System.out.println("Choose a command from the menu above: ");
    int commandLine = 0;
    try {
      commandLine = scanner.nextInt();
    } catch (InputMismatchException ime) {
      System.out.println("Input must be a number. Please try again");
      runCommandLoop();
    }

    // chooses method to call by command
    switch (commandLine) {
      case 1:
        countriesByMedal();
        break;
      case 2:
        displayCountries(backend.outputByAlphabeticalName());
        break;
      case 3:
        medalsByCountry();
      case 4:
        filterByContinent();
      case 5:
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
    String medalType = "";
    try {
      medalType = scanner.nextLine().trim().toLowerCase();
    } catch (InputMismatchException ime) {
      System.out.println("Input must be either gold, silver, or bronze. Please try again.");
      countriesByMedal();
    }
    List<ICountry> countries = backend.outputByTypeOfMedals(medalType);
    int medalCount = 0;
    for (ICountry country : countries) {
      switch (medalType) {
        case "gold":
          medalCount = country.getGoldMedals();
        case "silver":
          medalCount = country.getSilverMedals();
        case "bronze":
          medalCount = country.getBronzeMedals();
        default:
          System.out.println("Invalid medal type!");
          countriesByMedal();
          break;
      }
      System.out.println(country.getName() + ": " + medalType + " " + medalCount);
    }
    displayCommandMenu();
  }

  /**
   * reads year from System.in, displays results
   */
  @Override public void medalsByCountry() {
    System.out.println("Choose a country you would like to search for: ");
    String countryName = scanner.nextLine().trim().toLowerCase();
    ICountry countryObject = backend.searchByName(countryName);
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
        "   1) " + continentStrings[1] + " Africa" +
        "   2) " + continentStrings[2] + " Asia" +
        "   3) " + continentStrings[3] + " Oceania" +
        "   4) " + continentStrings[4] + " Europe" +
        "   5) " + continentStrings[5] + " North America" +
        "   6) " + continentStrings[6] + " South America"
    );

    System.out.println("Choose the continent that you would like to toggle the filter for: ");
    int toggleNum = 0;
    try {
       toggleNum = scanner.nextInt();
    } catch (InputMismatchException ime) {
      System.out.println("Filter must be a number. Please try again.");
      filterByContinent();
    }
    switch (toggleNum) {
      case 1:
        backend.toggleContinentFilter(1);
        if (continentStrings[1].equals("_x_")) {
          continentStrings[1] = "___";
        } else {
          continentStrings[1] = "_x_";
        }
      case 2:
        backend.toggleContinentFilter(2);
        if (continentStrings[2].equals("_x_")) {
          continentStrings[2] = "___";
        } else {
          continentStrings[2] = "_x_";
        }
      case 3:
        backend.toggleContinentFilter(3);
        if (continentStrings[3].equals("_x_")) {
          continentStrings[3] = "___";
        } else {
          continentStrings[3] = "_x_";
        }
      case 4:
        backend.toggleContinentFilter(4);
        if (continentStrings[4].equals("_x_")) {
          continentStrings[4] = "___";
        } else {
          continentStrings[4] = "_x_";
        }
      case 5:
        backend.toggleContinentFilter(5);
        if (continentStrings[5].equals("_x_")) {
          continentStrings[5] = "___";
        } else {
          continentStrings[5] = "_x_";
        }
      case 6:
        backend.toggleContinentFilter(6);
        if (continentStrings[6].equals("_x_")) {
          continentStrings[6] = "___";
        } else {
          continentStrings[6] = "_x_";
        }
      default:
        System.out.println("Please try again.");
        filterByContinent();
    }
    displayCommandMenu();
  }
}
