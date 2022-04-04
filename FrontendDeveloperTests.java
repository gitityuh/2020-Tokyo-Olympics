import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the functionality of CountrySearcherFrontend
 */
public class FrontendDeveloperTests {

  /**
   * Tests the Quit functionality
   */
  @Test public void test1() {
    FrontendCountrySearcherBackend backend = new FrontendCountrySearcherBackend();
    ConsoleOutputCapturer outputCapturer = new ConsoleOutputCapturer();
    // captures console output
    outputCapturer.start();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend, "5\n");
    // if frontend terminated, it should run code below
    String output = outputCapturer.stop();
    assertTrue(output.startsWith("Welcome to the FrontendCountry Searcher App!"));
  }

  /**
   * Tests the Countries by Medals functionality
   */
  @Test public void test2() {
    FrontendCountrySearcherBackend backend = new FrontendCountrySearcherBackend();
    ConsoleOutputCapturer outputCapturer = new ConsoleOutputCapturer();
    // captures console output
    outputCapturer.start();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend, "1\ngold\n5\n");
    String output = outputCapturer.stop();
    assertTrue(output.startsWith("Welcome to the FrontendCountry Searcher App!"));
    assertTrue(output.contains("United States of America: 1"));
  }

  /**
   * Tests the Countries in Alphabetical Order functionality
   */
  @Test public void test3() {
    FrontendCountrySearcherBackend backend = new FrontendCountrySearcherBackend();
    ConsoleOutputCapturer outputCapturer = new ConsoleOutputCapturer();
    // captures console output
    outputCapturer.start();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend, "2\n5\n");
    String output = outputCapturer.stop();
    assertTrue(output.startsWith("Welcome to the FrontendCountry Searcher App!"));
    // this will depend on the implementation of compareTo, but for now the following tests all
    // countries I added manually:
    assertTrue(output.contains("Australia"));
    assertTrue(output.contains("China"));
    assertTrue(output.contains("United States of America"));
  }

  /**
   * Tests the Medals by FrontendCountry functionality
   */
  @Test public void test4() {
    FrontendCountrySearcherBackend backend = new FrontendCountrySearcherBackend();
    ConsoleOutputCapturer outputCapturer = new ConsoleOutputCapturer();
    // captures console output
    outputCapturer.start();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend, "3\nChina\n5\n");
    String output = outputCapturer.stop();
    assertTrue(output.startsWith("Welcome to the FrontendCountry Searcher App!"));
    // output that I expect from manual list created in Backend
    assertTrue(output.contains("Gold: 0"));
    assertTrue(output.contains("Silver: 1"));
    assertTrue(output.contains("Bronze: 1"));
  }

  /**
   * Tests the Filter by Continent functionality
   */
  @Test public void test5() {
    FrontendCountrySearcherBackend backend = new FrontendCountrySearcherBackend();
    ConsoleOutputCapturer outputCapturer = new ConsoleOutputCapturer();
    // captures console output
    outputCapturer.start();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend, "4\n1\n2\n7\n5\n");
    String output = outputCapturer.stop();
    assertTrue(output.startsWith("Welcome to the FrontendCountry Searcher App!"));
    // checks if the two continents have been toggled off
    assertTrue(output.contains("___ Africa"));
    assertTrue(output.contains("___ Asia"));
  }

  /**
   * Main method to run the frontend UI
   * @param args
   */
  public static void main(String[] args) {
    FrontendCountrySearcherBackend backend = new FrontendCountrySearcherBackend();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend);
  }

}
