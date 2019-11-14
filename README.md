

## spring-boot-fat-jar-jsp-sample modification


* Give a gradle sample and add "/BOOT-INF/classes/META-INF/resource" into resoureSet to make it work.
* Remove ```// when run as exploded directory``` code block, since it is seldom used and looks confused.
* Add applicationClass argument to avoid error if StaticResourceConfigurer is put in a stable lib.

## Run

* Run as fat jar:

	```bash
	gradlew clean build
	java -jar jsp-start/build/libs/jsp-start.jar
	```

	open http://localhost:8080/
