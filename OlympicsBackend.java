import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OlympicsBackend implements ICountrySearcherBackend {
    private RedBlackTree<ICountry> goldMedalTree;
    private RedBlackTree<ICountry> silverMedalTree;
    private RedBlackTree<ICountry> bronzeMedalTree;
    //private RedBlackTree<ICountry> totalMedalTree;
    private RedBlackTree<ICountry> countryNameTree;
    private boolean africaToggle = true;
    private boolean asiaToggle = true;
    private boolean ausToggle = true;
    private boolean europeToggle = true;
    private boolean naToggle = true;
    private boolean saToggle = true;



    @Override public void addCountry(ICountry country) {
        goldMedalTree.insert(country, country.getGoldMedals());
        silverMedalTree.insert(country, country.getSilverMedals());
        bronzeMedalTree.insert(country, country.getBronzeMedals());
        //totalMedalTree.insert(country, country.getTotalMedals());
        countryNameTree.insert(country, country.getName());
    }

    //I don't think this method is ever used
    //@Override public int getNumberOfCountries() {

      //  return 0;
   // }

    //this is method is also never used
   // @Override public void setMedalType(String medal, boolean filter) {

    //}

    //@Override public boolean getMedalFilter(String medal) {
    //    return false;
    //}

    //@Override public void toggleMedalFilter(String medal) {

    //}

    @Override public ICountry searchByName(String name) {
        ArrayList<ICountry> countries = countryNameTree.storeKeyValues(countryNameTree.root);
        countries = removeFiltered(countries);
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getName().toLowerCase() == name.toLowerCase()) {
                return countries.get(i);
            }
        }
        System.out.println(
            "this country is not within the current content filters?/ something went wrong");
        return null;
    }//end search by name

    //this is not used
   // @Override public List<ICountry> outputByTotalMedals() {
    //    ArrayList<ICountry> countriesByTotalMedals =
     //       totalMedalTree.storeKeyValues(totalMedalTree.root);

      //  return null;
    //}

    @Override public List<ICountry> outputByTypeOfMedals(String medalType) {

        if(medalType.toLowerCase().equals("gold")){
            ArrayList<ICountry> gold = goldMedalTree.storeKeyValues(goldMedalTree.root);
            gold = removeFiltered(gold);
            return gold;
        }else if(medalType.toLowerCase().equals("silver")){
            ArrayList<ICountry> silver = silverMedalTree.storeKeyValues(silverMedalTree.root);
            silver = removeFiltered(silver);
            return silver;
        }else if(medalType.toLowerCase().equals("bronze")){
            ArrayList<ICountry> bronze = bronzeMedalTree.storeKeyValues(bronzeMedalTree.root);
            bronze = removeFiltered(bronze);
            return bronze;
        }else{
            System.out.println("invalid medal type returning null");
            return null;
        }

    }

    @Override public List<ICountry> outputByAlphabeticalName() {
        ArrayList<ICountry> alphabeticalName = countryNameTree.storeKeyValues(countryNameTree.root);
        alphabeticalName = removeFiltered(alphabeticalName);
        return alphabeticalName;
    }

    @Override public void toggleContinentFilter(Integer number) {
        if(number == 1){
            africaToggle = !africaToggle;
        }else if(number == 2){
            asiaToggle = !asiaToggle;
        }else if(number == 3){
            ausToggle = !ausToggle;
        }else if(number == 4){
            europeToggle = !europeToggle;
        }else if(number == 5){
            naToggle = !naToggle;
        }else if(number == 6){
            saToggle = !saToggle;
        }else{
            System.out.println("invalid number");
        }
    }//end toggle content filters

    public ArrayList<ICountry> removeFiltered(ArrayList<ICountry> countries){
        if(africaToggle == false){
            for(int i = 0; i < countries.size(); i ++){
                if(countries.get(i).getContinent().toLowerCase().equals("africa")){
                    countries.remove(i);
                }
            }
        }else if(asiaToggle == false){
            for(int i = 0; i < countries.size(); i ++){
                if(countries.get(i).getContinent().toLowerCase().equals("asia")){
                    countries.remove(i);
                }
            }
        }else if(ausToggle == false){
            for(int i = 0; i < countries.size(); i ++){
                if(countries.get(i).getContinent().toLowerCase().equals("oceania")){
                    countries.remove(i);
                }
            }
        }else if(europeToggle == false){
            for(int i = 0; i < countries.size(); i ++){
                if(countries.get(i).getContinent().toLowerCase().equals("europe")){
                    countries.remove(i);
                }
            }
        }else if(naToggle == false){
            for(int i = 0; i < countries.size(); i ++){
                if(countries.get(i).getContinent().toLowerCase().equals("north america")){
                    countries.remove(i);
                }
            }
        }else if(saToggle == false){
            for(int i = 0; i < countries.size(); i ++){
                if(countries.get(i).getContinent().toLowerCase().equals("south america")){
                    countries.remove(i);
                }
            }
        }
        return countries;
    }//removeFiltered


    //gold
    //silver
    //bronze
    //total medals
    //alphabetical name



}
