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
        File testfile = new File("/home/ggervasio/Project2_DN_red/Tokyo_Medals_2021.xml");
       assertTrue ( testfile.canRead());
    }
    /*
    makes sure the getName of the country works
     */
    @Test
            void testGetName() throws FileNotFoundException {
        CountryLoader test = new CountryLoader();
        List<ICountry> retList = new ArrayList<>();


            retList = test.loadData("/home/ggervasio/Project2_DN_red/Tokyo_Medals_2021.xml");


       assertEquals( retList.get(0).getName(),("United States of America"));


    }

    /*
    tests to make sure the medals are added correctly
     */
   @Test
            void testMedalCount() throws FileNotFoundException {
        CountryLoader test = new CountryLoader();
        List<ICountry> retList = new ArrayList<>();


       retList = test.loadData("/home/ggervasio/Project2_DN_red/Tokyo_Medals_2021.xml");

        assertEquals(retList.get(5).getGoldMedals() + retList.get(5).getBronzeMedals() + retList.get(5).getSilverMedals(), 46);

    }
@Test
    void testGetRank() throws FileNotFoundException{
    CountryLoader test = new CountryLoader();
    List<ICountry> retList = new ArrayList<>();
    retList = test.loadData("/home/ggervasio/Project2_DN_red/Tokyo_Medals_2021.xml");

    assertEquals(retList.get(0).getRank(),1);

}
@Test
    void testGetContinent() throws FileNotFoundException {
    CountryLoader test = new CountryLoader();
    List<ICountry> retList = new ArrayList<>();
    retList = test.loadData("/home/ggervasio/Project2_DN_red/Tokyo_Medals_2021.xml");

    assertEquals(retList.get(10).getContinent(),"North America");

}
}

