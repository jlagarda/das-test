1. Pull Down Repository to your local computer

Proceed with either of options below:
Option 1: Assumes you have either Java 8 or OpenJdk 11 installed
  1. Go to project directory
  2. Execute command java -jar build/libs/gs-spring-boot-docker-0.1.0.jar
  
 Option 2: Assumes you have Docker CLi Installed
  1. Go to project directory
  2. docker build -t test .
  3. docker run -it --rm test
