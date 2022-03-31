import java.util.List;

public class OlympicCountryApp {



        public static void main(String[] args) throws Exception {
            ICountryLoader loader = new CountryLoaderBackEnd(); //new ShowLoader();
            List<ICountry> countries = loader.loadData("Tokyo_Medals_2021.xml");
            ICountrySearcherBackend backend = new OlympicsBackend(); //new ShowSearcherBackend();
            for(ICountry country : countries) backend.addCountry(country);
            ICountrySearcherFrontend frontend = new CountrySearcherFrontendBackEnd(); //new ShowSearcherFrontend(backend);
            frontend.runCommandLoop();
        }


}
