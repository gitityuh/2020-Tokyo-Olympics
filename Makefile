FrontendDeveloperTests: FrontendDeveloperTestsCompile
	java -jar junit5.jar --class-path . --select-class FrontendDeveloperTests

FrontendDeveloperTestsCompile: FrontendDeveloperTests.java
	javac -cp junit5.jar:. FrontendDeveloperTests.java


runBackendDeveloperTests: BackendDeveloperTestCompile
	java -jar junit5.jar -- class-path . --select-class BackendDeveloperTests



BackendDeveloperTestCompile: BackendDeveloperTests.java
	javac -cp junit5.jar:. BackendDeveloperTests.java

clean:
	rm *.class




runDatatWranglerTests: DataWranglerTests 
	javac  -cp .:junit5.jar CountryLoader.java
	
	javac  -cp .:junit5.jar Country.java

DataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint


