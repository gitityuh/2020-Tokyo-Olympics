run: CountrySearcherApp.java
	javac CountrySearcherApp.java
	java CountrySearcherApp

runTests: runDataWranglerTests runFrontendDeveloperTests runBackendDeveloperTests runAlgorithmEngineerTests

clean:
	rm *.class

runDataWranglerTests: DataWranglerTestsCompile
	java -jar junit5.jar --class-path . --select-class DataWranglerTests

DataWranglerTestsCompile: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

runFrontendDeveloperTests: FrontendDeveloperTestsCompile
	java -jar junit5.jar --class-path . --select-class FrontendDeveloperTests

FrontendDeveloperTestsCompile: FrontendDeveloperTests.java
	javac -cp junit5.jar:. FrontendDeveloperTests.java

runBackendDeveloperTests: BackendDeveloperTestsCompile
	java -jar junit5.jar --class-path . --select-class BackendDeveloperTests

BackendDeveloperTestsCompile: BackendDeveloperTests.java
	javac -cp junit5.jar:. BackendDeveloperTests.java

runAlgorithmEngineerTests: AlgorithmEngineerTestsCompile
	java -jar junit5.jar --class-path . --select-class AlgorithmEngineerTests

AlgorithmEngineerTestsCompile: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java


