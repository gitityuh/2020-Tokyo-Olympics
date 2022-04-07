import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DataWranglerTests {


    /*
    makes sure xml data is read by data loader class and returns a list
     */
    @Test
    void testXMLFile(){
        File testfile = new File("./Tokyo_Medals_2021.xml");
       assertTrue ( testfile.canRead());
    }
    /*
    makes sure the getName of the country works
     */
    @Test
            void testGetName() throws FileNotFoundException {
        CountryLoader test = new CountryLoader();
        List<ICountry> retList = new ArrayList<>();


            retList = test.loadData("./Tokyo_Medals_2021.xml");


       assertEquals( retList.get(0).getName(),("United States of America"));


    }

    /*
    tests to make sure the medals are added correctly
     */
   @Test
            void testMedalCount() throws FileNotFoundException {
        CountryLoader test = new CountryLoader();
        List<ICountry> retList = new ArrayList<>();


       retList = test.loadData("./Tokyo_Medals_2021.xml");

        assertEquals(retList.get(5).getGoldMedals() + retList.get(5).getBronzeMedals() + retList.get(5).getSilverMedals(), 46);

    }
@Test
    void testGetRank() throws FileNotFoundException{
    CountryLoader test = new CountryLoader();
    List<ICountry> retList = new ArrayList<>();
    retList = test.loadData("./Tokyo_Medals_2021.xml");

    assertEquals(retList.get(0).getRank(),1);

}
@Test
    void testGetContinent() throws FileNotFoundException {
    CountryLoader test = new CountryLoader();
    List<ICountry> retList = new ArrayList<>();
    retList = test.loadData("./Tokyo_Medals_2021.xml");

    assertEquals(retList.get(10).getContinent(),"North America");

}





/**
 * CODE REVIEW TESTS
 * FRONTEND DEVELOPER
 * Tests the Countries by Medals functionality
 *
 */


 @Test public void FDtest1() {
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
  @Test public void FDtest2() {
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
























}

