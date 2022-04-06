import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendDeveloperTests {
    /**
     * test to see if the tree can retrieve countries based on gold medals the insert method relies on the AlgoEngineer
     * code so for now it is hardcoded
     */
    @Test public void test1() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 =
            new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.goldMedalTreeBackend.setRoot(country1);
        backend.goldMedalTreeBackend.setRootRight(country2);
        List<ICountry> countriesGold = backend.outputByTypeOfMedals("gold");
        assertEquals(country2, countriesGold.get(1));
    }//end test 1

    /**
     * test to see if the backend can retrieve silver medal countries in correct order
     * the insert is hard coded
     */
    @Test public void test2() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "asia", "Asian Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.silverMedalTreeBackend.setRoot(country1);
        backend.silverMedalTreeBackend.setRootleft(country2);
        List<ICountry> countriesSilver = backend.outputByTypeOfMedals("silver");
        assertEquals(country1, countriesSilver.get(1));
    }//end test 2

    /**
     * test bronze medal case, want to see if the tree can retrieve bronze in sorted order
     * in this case they countries have the same medal, the algo engineer should make it so when these
     * items are compared it will switch to alphabetical order for countries with the same medal
     * therefore in this case African country should be the root
     */
    @Test public  void test3() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.bronzeMedalTreeBackend.setRoot(country1);
        backend.bronzeMedalTreeBackend.setRootleft(country2);
        List<ICountry> countriesBronze = backend.outputByTypeOfMedals("bronze");
        assertEquals(country1, countriesBronze.get(1));
    }

    /**
     * test the continent filter should only return North american continent
     *
     */
    @Test public void test4() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.bronzeMedalTreeBackend.setRoot(country1);
        backend.bronzeMedalTreeBackend.setRootleft(country2);
        backend.toggleContinentFilter(1); //this will remove africa from the search results
        //therefore only country two should be in the list
        List<ICountry> countriesBronze = backend.outputByTypeOfMedals("bronze");

        assertEquals(country2, countriesBronze.get(0));
        assertEquals(countriesBronze.size(),1);
    }//end test4


    /**
     * test to see if get individual country works
     * and if the output by Aplhabetical works
     *
     */
    @Test public void test5() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.countryNameTreeBackend.setRoot(country1);
        backend.countryNameTreeBackend.setRootRight(country2);
        List<ICountry> countriesAlphabetical = backend.outputByAlphabeticalName();
       // System.out.println(countriesAlphabetical.get(1).getName());
        ICountry country = backend.searchByName("North American Country");
        assertEquals(country1, countriesAlphabetical.get(0));
        assertEquals(country,countriesAlphabetical.get(1));
    }//end test5





    public static void main(String[] args) {

    }

    //public void insert(Object t, Object b){

    //}

}
