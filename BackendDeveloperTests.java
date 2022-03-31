import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendDeveloperTests {
    /**
     * test to see if the tree can retrieve countries based on gold medals the insert method relies on the AlgoEngineer
     * code so for now it is hardcoded
     */
    @Test private static void test1() {
        OlympicsBackend backend = new OlympicsBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 =
            new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.goldMedalTree.setRoot(country1);
        backend.goldMedalTree.setRootRight(country2);
        List<ICountry> countriesGold = backend.outputByTypeOfMedals("gold");
        assertEquals(country2, countriesGold.get(1));
    }//end test 1

    /**
     * test to see if the backend can retrieve silver medal countries in correct order
     * the insert is hard coded
     */
    @Test private static void test2() {
        OlympicsBackend backend = new OlympicsBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "asia", "Asian Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.silverMedalTree.setRoot(country1);
        backend.silverMedalTree.setRootleft(country2);
        List<ICountry> countriesSilver = backend.outputByTypeOfMedals("silver");
        assertEquals(country1, countriesSilver.get(1));
    }//end test 2

    /**
     * test bronze medal case, want to see if the tree can retrieve bronze in sorted order
     * in this case they countries have the same medal, the algo engineer should make it so when these
     * items are compared it will switch to alphabetical order for countries with the same medal
     * therefore in this case African country should be the root
     */
    @Test private static void test3() {
        OlympicsBackend backend = new OlympicsBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.bronzeMedalTree.setRoot(country1);
        backend.bronzeMedalTree.setRootleft(country2);
        List<ICountry> countriesBronze = backend.outputByTypeOfMedals("bronze");
        assertEquals(country1, countriesBronze.get(1));
    }

    /**
     * test the continent filter should only return North american continent
     *
     */
    @Test private static void test4() {
        OlympicsBackend backend = new OlympicsBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.bronzeMedalTree.setRoot(country1);
        backend.bronzeMedalTree.setRootleft(country2);
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
    @Test private static void test5() {
        OlympicsBackend backend = new OlympicsBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.countryNameTree.setRoot(country1);
        backend.countryNameTree.setRootleft(country2);
        List<ICountry> countriesAlphabetical = backend.outputByAlphabeticalName();
        ICountry country = backend.searchByName("north american country");
        assertEquals(country1, countriesAlphabetical.get(0));
        assertEquals(country,countriesAlphabetical.get(1));
    }//end test4





    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public void insert(Object t, Object b){

    }

}
