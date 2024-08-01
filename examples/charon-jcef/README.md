# app-jcef-example

Example application for using Java Chrome Embedded Framework

## Prerequisites

### JCEF

A build of JCEF to be available via your PATH environment variable. \n

https://bitbucket.org/chromiumembedded/java-cef/wiki/BranchesAndBuilding

Add the `some_directory/jcef_build/native/Release` directory to your PATH environment variable.

### Java JDK

Java 1.8 x64

### Env
Extract java-cef-49.87.win64.2.jar 
Copy java-cef-49.87.win64.2 below files exclusive org,META-INF to jdk\jre\bin

## Maven Build

1. mvn clean package
cd target
java -jar jcef-example-SNAPSHOT-0.1.0.jar

## screenshot
![Image text](https://raw.githubusercontent.com/harvies/app-jcef-example/master/screenshot/20171103113019.png)
