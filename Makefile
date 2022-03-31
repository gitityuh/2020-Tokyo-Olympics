runTests: BackendDeveloperTestCompile
	java -jar junit5.jar -- class-path . --select-class BackendDeveloperTests



BackendDeveloperTestCompile: BackendDeveloperTests.java
	javac -cp junit5.jar:. BackendDeveloperTests.java

clean:
	rm *.class


