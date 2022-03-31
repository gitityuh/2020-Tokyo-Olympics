runTests: FrontendDeveloperTestsCompile
	java -jar junit5.jar --class-path . --select-class FrontendTests

FrontendDeveloperTestsCompile: FrontendDeveloperTests.java
	javac -cp junit5.jar:. FrontendDeveloperTests.java

clean:
	rm *.class
