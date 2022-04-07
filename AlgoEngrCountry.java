public class AlgoEngrCountry implements ICountry {

    public int goldData;
    public String countryName;
    AlgoEngrCountry(String name, int numGolds){
        goldData = numGolds;
        countryName = name;
    }
    @Override
    public String getName() {
        return countryName;
    }

    @Override
    public int getGoldMedals() {
        return goldData;
    }

    @Override
    public int getSilverMedals() {
        return 0;
    }

    @Override
    public int getBronzeMedals() {
        return 0;
    }

    @Override
    public int getTotalMedals() {
        return 0;
    }

    @Override
    public int getRank() {
        return 0;
    }

    @Override
    public String getContinent() {
        return null;
    }

    @Override
    public int compareTo(ICountry o) {
        return 0;
    }
}
