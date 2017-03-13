# Fanaticaltest Test factory Demo

## Docker

### Rebuild
```
sh rebuild.sh
```

### Build
```
docker-compose build
```

### Start
```
docker-compose up -d
```

### Stop
```
docker-compose down
```

### Chrome + VNC
If you are not using docker-compose
```
docker run -d -p 8080:4444 -p 5900:5900 selenium/standalone-chrome-debug:3.0.1-aluminum
```
### Firefox + VNC
If you are not using docker-compose
```
docker run -d -p 4444:4444 -p 5901:5900 selenium/standalone-firefox-debug:3.0.1-aluminum
```
### VNC access
To access to the VNC
No username
Password : secret

### More info
```
https://github.com/SeleniumHQ/docker-selenium
```

## Start Selenium stand alone server locally
* go to the folder where you have downloaded selenium-server-standalone
* run the follwing command
* assuming the selenium version you use is 3.0.1
```
java -jar selenium-server-standalone-3.0.1.jar
```

## Marionette
If you are not using Docker and you use windows to run selenium, you may need to run Marionnette when using Firefox.
You need to download the driver : https://github.com/mozilla/geckodriver/releases/download/v0.10.0/geckodriver-v0.10.0-win64.zip
Unzip and put in a safe place like C:\dev\marionette
Then add in local env in path as follow C:\dev\marionette

## Start test
* Ensure you have copied or renamed src/test/resources/config.sample.properties to src/test/resources/config.properties
* Use command line
* (If not using Docker) Ensure to have started selenium-server-standalone
```
cd c:\dev\selenium
java -jar selenium-server-standalone-3.0.1.jar -port 8080
```
* Go to the root of the project
* Type the following command
```
mvn clean verify -Dtest=CukesRunner > log.txt
mvn clean verify -Dtest=CukesRunner -Dcucumber.options="-t @Project=DemoWebsite" > log.txt
mvn clean verify -Dtest=CukesRunner -Dcucumber.options="-t @Feature=TermsAndConditions" > log.txt
mvn clean verify -Dtest=CukesRunner -Dcucumber.options="-t @Feature=Shopping" > log.txt
mvn clean verify -Dtest=CukesRunner -Dcucumber.options="-t @Feature=Authentication" > log.txt
```

## Cucumber version
Use cucumber api 1.1.6 in order to have `scenario.getName()`

## Clean decencies
```
mvn dependency:analyze
```

While cleaning start with adding missing refences then removed unused.