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
        backend.addCountryBackendTests(country1);
        backend.addCountryBackendTests(country2);
        backend.goldMedalTreeBackend.setRoot(country1);
        backend.goldMedalTreeBackend.setRootRight(country2);
        List<ICountry> countriesGold = backend.outputByTypeOfMedalsBackendTest("gold");
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
        backend.addCountryBackendTests(country1);
        backend.addCountryBackendTests(country2);
        backend.silverMedalTreeBackend.setRoot(country1);
        backend.silverMedalTreeBackend.setRootleft(country2);
        List<ICountry> countriesSilver = backend.outputByTypeOfMedalsBackendTest("silver");
        assertEquals(country1, countriesSilver.get(1));
    }//end test 2

    /**
     * test bronze medal case, want to see if the tree can retrieve bronze in sorted order
     * in this case they countries have the same medal, the algo engineer should make it so when these
     * items are compared it will switch to alphabetical order for countries with the same medal
     * therefore in this case African country should be the root
     */
    @Test public void test3() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 =
            new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountryBackendTests(country1);
        backend.addCountryBackendTests(country2);
        backend.bronzeMedalTreeBackend.setRoot(country1);
        backend.bronzeMedalTreeBackend.setRootleft(country2);
        List<ICountry> countriesBronze = backend.outputByTypeOfMedalsBackendTest("bronze");
        assertEquals(country1, countriesBronze.get(1));
    }

    /**
     * test the continent filter should only return North american continent
     */
    @Test public void test4() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 =
            new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountryBackendTests(country1);
        backend.addCountryBackendTests(country2);
        backend.bronzeMedalTreeBackend.setRoot(country1);
        backend.bronzeMedalTreeBackend.setRootleft(country2);
        backend.toggleContinentFilter(1); //this will remove africa from the search results
        //therefore only country two should be in the list
        List<ICountry> countriesBronze = backend.outputByTypeOfMedalsBackendTest("bronze");

        assertEquals(country2, countriesBronze.get(0));
        assertEquals(countriesBronze.size(), 1);
    }//end test4


    /**
     * test to see if get individual country works
     * and if the output by Aplhabetical works
     */
    @Test public void test5() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 =
            new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountryBackendTests(country1);
        backend.addCountryBackendTests(country2);
        backend.countryNameTreeBackend.setRoot(country1);
        backend.countryNameTreeBackend.setRootRight(country2);
        List<ICountry> countriesAlphabetical = backend.outputByAlphabeticalNameBackendTest();
        // System.out.println(countriesAlphabetical.get(1).getName());
        ICountry country = backend.searchByNameBackendTest("North American Country");
        assertEquals(country1, countriesAlphabetical.get(0));
        assertEquals(country, countriesAlphabetical.get(1));
    }//end test5


    //new tests after merge week 4

    //new test no longer using place holder classes for the results
    //the following tests are using the integrated code form the RedBalckTree class and the AlgoEng's code

    /**
     * testing to see if a simple insert into the red black tree returns the correct in order of countries
     * sorted by gold medals
     */
    @Test public void test6() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 =
            new CountryBackend(10, 1, 2, "north america", "North American Country");
        backend.addCountry(country1);
        backend.addCountry(country2);
        List<ICountry> countriesGoldmedal = backend.outputByTypeOfMedals("gold");
        assertEquals(countriesGoldmedal.get(1), country1);
        assertEquals(countriesGoldmedal.get(0), country2);
    }

    /**
     * test with more nodes to see if after a
     * rotation the countries are still returned correctly when sorting by gold medals
     */
    @Test public void test7() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "African Country");
        CountryBackend country2 =
            new CountryBackend(10, 1, 2, "north america", "North American Country");
        CountryBackend country3 =
            new CountryBackend(3, 1, 2, "north america", "North American Country Two");
        CountryBackend country4 =
            new CountryBackend(7, 1, 2, "south america", "South American Country");
        CountryBackend country5 = new CountryBackend(6, 1, 2, "asia", "Asian Country");

        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.addCountry(country3);
        backend.addCountry(country4);
        backend.addCountry(country5);
        List<ICountry> countriesGoldmedal = backend.outputByTypeOfMedals("gold");
        assertEquals(countriesGoldmedal.get(1), country1);
        assertEquals(countriesGoldmedal.get(0), country3);
    }//end test 7

    /**
     * testing to see if the countries can be sorted alphabetically and the continent filter works
     */
    @Test public void test8() {
        CountrySearcherBackend backend = new CountrySearcherBackend();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "A");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "B");
        CountryBackend country3 = new CountryBackend(3, 1, 2, "north america", "C");
        CountryBackend country4 = new CountryBackend(7, 1, 2, "south america", "D");
        CountryBackend country5 = new CountryBackend(6, 1, 2, "asia", "E");

        backend.addCountry(country1);
        backend.addCountry(country2);
        backend.addCountry(country3);
        backend.addCountry(country4);
        backend.addCountry(country5);
        List<ICountry> countriesNames = backend.outputByAlphabeticalName();
        //toggling off north american continent
        backend.toggleContinentFilter(5);
        assertEquals(countriesNames.get(1), country4);
        assertEquals(countriesNames.get(0), country1);
    }//end test 8

    //testing the Red Black tree and Algorithm Engineer Code
    /*
    test to see if the RBT can sort countries by gold medal count
     */
    @Test public void test9() {
        RedBlackTree RBT = new RedBlackTree();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "A");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "B");
        CountryBackend country3 = new CountryBackend(3, 1, 2, "north america", "C");
        CountryBackend country4 = new CountryBackend(7, 1, 2, "south america", "D");
        CountryBackend country5 = new CountryBackend(6, 1, 2, "asia", "E");
        //RBT should be sorting by gold medal numbers
        RBT.insert(country1,country1.getGoldMedals());
        RBT.insert(country2,country2.getGoldMedals());
        RBT.insert(country3,country3.getGoldMedals());
        RBT.insert(country4,country4.getGoldMedals());
        RBT.insert(country5,country5.getGoldMedals());
        //testing to see if the RBT has the correct nodes in the correct location ideally
        //sorting integers
        assertEquals(RBT.root,country1);
        assertEquals(RBT.root.rightChild,country4);
        assertEquals(RBT.root.leftChild,country3);
    }//end test 9

    /**
     * test to see if the RBT can sort by alphabetical names
     */
    @Test public void test10() {
        RedBlackTree RBT = new RedBlackTree();
        CountryBackend country1 = new CountryBackend(5, 4, 2, "africa", "A");
        CountryBackend country2 = new CountryBackend(10, 1, 2, "north america", "B");
        CountryBackend country3 = new CountryBackend(3, 1, 2, "north america", "C");
        CountryBackend country4 = new CountryBackend(7, 1, 2, "south america", "D");
        CountryBackend country5 = new CountryBackend(6, 1, 2, "asia", "E");
        RBT.insert(country1,country1.getName());
        RBT.insert(country2,country2.getName());
        RBT.insert(country3,country3.getName());
        RBT.insert(country4,country4.getName());
        RBT.insert(country5,country5.getName());
        //testing to see if the RBT has the correct nodes in the correct location ideally
        //sorting by Name by should be storing country objects
        assertEquals(RBT.root,country2);
        assertEquals(RBT.root.rightChild,country4);
        assertEquals(RBT.root.leftChild,country1);
    }//end test 10

    public static void main(String[] args) {

    }

    //public void insert(Object t, Object b){

    //}

}
