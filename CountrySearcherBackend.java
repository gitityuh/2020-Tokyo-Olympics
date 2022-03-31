import java.util.ArrayList;
import java.util.List;

public class CountrySearcherBackend implements ICountrySearcherBackend{
  private boolean africaToggle;
  private boolean asiaToggle;
  private boolean ausToggle;
  private boolean europeToggle;
  private boolean naToggle;
  private boolean saToggle;


  @Override public void addCountry(ICountry country) {

  }

  @Override public ICountry searchByName(String name) {
    ArrayList<ICountry> list = new ArrayList<>();
    Country usa = new Country("United States of America", 1, 1, 1, 3, 1, "North America");
    Country china = new Country("China", 0, 1, 1, 2, 2,"Asia");
    Country australia = new Country("Australia", 0, 0, 1, 1, 3, "Oceania");

    list.add(australia);
    list.add(china);
    list.add(usa);

    for (ICountry country : list) {
      if (country.getName().equals(name)) {
        return country;
      }
    }
    return null;
  }

  @Override public List<ICountry> outputByTypeOfMedals(String medalType) {

    ArrayList<ICountry> list = new ArrayList<>();
    Country usa = new Country("United States of America", 1, 1, 1, 3, 1, "North America");
    Country china = new Country("China", 0, 1, 1, 2, 2,"Asia");
    Country australia = new Country("Australia", 0, 0, 1, 1, 3, "Oceania");

    ArrayList<ICountry> output = new ArrayList<>();

    list.add(australia);
    list.add(china);
    list.add(usa);

    for (ICountry country : list) {
      switch (medalType.toLowerCase()) {
        case "gold":
          if (country.getGoldMedals() > 0)
            output.add(country);
          break;
        case "silver":
          if (country.getSilverMedals() > 0)
            output.add(country);
          break;
        case "bronze":
          if (country.getBronzeMedals() > 0)
            output.add(country);
          break;
      }
    }

    return output;
  }

  @Override public List<ICountry> outputByAlphabeticalName() {
    ArrayList<ICountry> list = new ArrayList<>();
    Country usa = new Country("United States of America", 1, 1, 1, 3, 1, "North America");
    Country china = new Country("China", 0, 1, 1, 2, 2,"Asia");
    Country australia = new Country("Australia", 0, 0, 1, 1, 3, "Oceania");
    list.add(australia);
    list.add(china);
    list.add(usa);
    return list;
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
}
