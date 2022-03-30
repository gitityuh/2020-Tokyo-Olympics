import java.util.List;

public class OlympicsBackend implements IOlympicsBackend{
    @Override public void addCountry(IOlympic country) {

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

    @Override public IOlympic searchByName(String name) {
        return null;
    }

    @Override public List<IOlympic> outputByTotalMedals() {
        return null;
    }

    @Override public List<IOlympic> outputByTypeOfMedals(String medalType) {
        return null;
    }
}
