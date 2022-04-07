import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the functionality of CountrySearcherFrontend
 */
public class FrontendDeveloperTests {

  /**
   * Tests the Quit functionality
   */
  @Test public void test1() {
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
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
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
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
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
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
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
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
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
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
   * Integration test with CountrySearcherBackend
   */
  @Test public void test6() {
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
    ConsoleOutputCapturer outputCapturer = new ConsoleOutputCapturer();
    // captures console output
    outputCapturer.start();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend, "1\ngold\n5\n");
    String output = outputCapturer.stop();
    assertTrue(output.startsWith("Welcome to the FrontendCountry Searcher App!"));
    assertTrue(output.contains("United States of America: 1"));
  }

  /**
   * Integration test with RedBlackTree
   */
  @Test public void test7() {
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
    ConsoleOutputCapturer outputCapturer = new ConsoleOutputCapturer();
    // captures console output
    outputCapturer.start();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend, "2\n5\n");
    String output = outputCapturer.stop();
    assertTrue(output.startsWith("Welcome to the FrontendCountry Searcher App!"));
    assertTrue(output.contains("Australia"));
    assertTrue(output.contains("China"));
    assertTrue(output.contains("United States of America"));
  }

  /**
   * Tests DataWrangler's Country class
   */
  @Test public void test8() {
    CountryLoader loader = new CountryLoader();
    try {
      loader.loadData("no_file_here.xml");
    } catch (FileNotFoundException ignored) {

    } catch (Exception e) {
      // returns false if any other Exception other than FileNotFoundException is thrown
      fail();
    }

    try {
      loader.loadData("Tokyo_Medals_2021.xml");
    } catch (Exception e) {
      // should not throw an exception
      fail();
    }
  }

  /**
   * Tests if first Country loaded with CountryLoader is correct
   */
  @Test public void test9() {
    CountryLoader loader = new CountryLoader();
    List<ICountry> testList = null;
    try {
      testList = loader.loadData("Tokyo_Medals_2021.xml");
    } catch (FileNotFoundException ignored) {

    }

    assert testList != null;
    assertEquals(testList.get(0).getName(), "United States of America");
    assertEquals(testList.get(0).getContinent(), "North America");
    assertEquals(testList.get(0).getGoldMedals(), 39);
    assertEquals(testList.get(0).getSilverMedals(), 41);
    assertEquals(testList.get(0).getBronzeMedals(), 33);
  }

  /**
   * Main method to run the frontend UI
   */
  public static void main(String[] args) {
    CountrySearcherBackendFrontend backend = new CountrySearcherBackendFrontend();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend);
  }

}
