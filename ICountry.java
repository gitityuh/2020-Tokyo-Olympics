/**
 * Instances of classes that implement this interface represents a single
 * Country object that can be stored, sorted, and searched based on
 * these accessors below.
 */
public interface ICountry extends Comparable<ICountry> {

    // constructor args (String name, int goldMedal, int silverMedal, int bronzeMedal,
    // int totalMedals, int rank, String continent)

    String getName(); //
    int getGoldMedals();
    int getSilverMedals();
    int getBronzeMedals();
    int getTotalMedals();
    int getRank();
    String getContinent();

    // compareTo() method supports sorting these objects by name and number of total medals

}
