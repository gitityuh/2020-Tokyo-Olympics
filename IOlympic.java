/**
 * Instances of classes that implement this interface represent a single
 * Olmpic object that can be stored, sorted, and searched for based on
 * these accessors below.
 */
public interface IOlympic extends Comparable<IOlympic> {

	// constructor args (String Country, int year, String sex, String Winter/summer, String Medal Won)
	// where the providers string includes the names of every streaming source

	String getCountry(); // retrieve the Country of this olympic object
	int getYear(); // retrieve the year that this olympic was
	String getSex(); //gets the sex of the olympic athletes
	String getSeason(); // gets whether this was a summer or winter olympica
	String medalsWon(); // gets the gold,silver and bronze medals won
	// compareTo() method supports sorting these objects by year

}
