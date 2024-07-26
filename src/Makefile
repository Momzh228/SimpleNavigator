PACKAGE_PATH = SimpleNavigator/src/main/java/edu/school21
MVN_PATH = mvn -f SimpleNavigator
LIBS_PATH = SimpleNavigator/libs

all: app

app:
	@$(MVN_PATH) clean install
	@java -jar SimpleNavigator/target/SimpleNavigator.jar

test:
	@$(MVN_PATH) clean test

clean:
	@

