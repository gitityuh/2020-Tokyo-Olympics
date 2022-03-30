public class FrontendTester {
  public static void main(String[] args) {
    CountrySearcherBackend backend = new CountrySearcherBackend();
    CountrySearcherFrontend frontend = new CountrySearcherFrontend(backend);

  }
}
