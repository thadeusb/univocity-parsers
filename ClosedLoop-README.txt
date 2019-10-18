Testing:
mvn clean test

Package a jar:
JDK8_HOME=/usr/lib/jvm/java-8-openjdk-amd64/ mvn clean package

Deploy to artifactory:
./target/closedloop-univocity-parsers-2.7.3.jar

Use deploy button to libs-release-local
Click generate default pom / deploy jars internal pom
