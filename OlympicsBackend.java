import java.util.List;

public class OlympicsBackend implements ICountrySearcherBackend{

    @Override public void addCountry(ICountry country) {

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

    @Override public List<ICountry> outputByTotalMedals() {
        return null;
    }

    @Override public List<ICountry> outputByTypeOfMedals(String medalType) {
        return null;
    }
}
