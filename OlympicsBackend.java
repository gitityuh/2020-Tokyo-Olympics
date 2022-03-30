import java.util.ArrayList;
import java.util.List;

public class OlympicsBackend implements ICountrySearcherBackend{
    private RedBlackTree<ICountry> goldMedalTree;
    private RedBlackTree<ICountry> silverMedalTree;
    private RedBlackTree<ICountry> bronzeMedalTree;
    private RedBlackTree<ICountry> countryNameTree;

    @Override public void addCountry(ICountry country) {
        goldMedalTree.insert(country,country.getGoldMedals());
        silverMedalTree.insert(country,country.getSilverMedals());
        bronzeMedalTree.insert(country,country.getBronzeMedals());
        countryNameTree.insert(country,country.getName());
    }

    @Override public int getNumberOfCountries() {
        return 0;
    }

    @Override public void setMedalType(String medal, boolean filter) {

    }

    @Override public boolean getMedalFilter(String medal) {
        return false;
    }

    @Override public void toggleMedalFilter(String medal) {

    }

    @Override public ICountry searchByName(String name) {
        return null;
    }

    @Override public List<ICountry> outputByTotalMedals()
    {

        return null;
    }

    @Override public List<ICountry> outputByTypeOfMedals(String medalType) {
        return null;
    }


    //gold
    //silver
    //bronze
    //total medals
    //alphabetical name




}
