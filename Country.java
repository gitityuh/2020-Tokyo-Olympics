public class Country implements ICountry{
    String countryName;
    int gold;
    int silver;
    int bronze;
    int rank;
    String continent;


    public Country(String countryName1, int gold1,int silver1,int bronze1,int rank1,String continent1){
        countryName = countryName1;
        gold = gold1;
        silver =silver1;
        bronze = bronze1;
        rank = rank1;
        continent = continent1;
    }

    @Override
    public String getName() {
        return countryName;
    }

    @Override
    public int getGoldMedals() {
        return gold;
    }

    @Override
    public int getSilverMedals() {
        return silver;
    }

    @Override
    public int getBronzeMedals() {
        return bronze;
    }

    @Override
    public int getTotalMedals() {
        return gold + silver + bronze;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public String getContinent() {
        return continent;
    }

    @Override
    public int compareTo(ICountry o) {

        return 0;
    }
}

