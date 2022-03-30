import java.util.ArrayList;
import java.util.List;

public class CountrySearcherBackend implements ICountrySearcherBackend{



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

  }
}
