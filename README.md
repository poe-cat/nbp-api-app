# Console application in Java (feat. NBP API)

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Running the application locally](#running-the-application-locally)

## General info
The main functions of the application: 
* searching and sorting the database, 
* adding data and saving them in the database and in the XML file, 
* downloading the dollar exchange rate using the NBP API, 
* converting the price of purchased products, 
* displaying the results for the user on the console.

## Technologies
Project is created with:
* Java 17
* SQLite 3.39.3.0
* Jakarta XML Binding 3.0.0 
* Apache Maven 3.8.6

## Setup
To use SQLite with Java programs, you must have SQLite JDBC Driver and Java set up on the system. Make sure that corresponding dependencies from pom.xml file are correctly downloaded.
To working correctly, you must be successfully connected to the Internet, due to the fact that the program uses an external API.

### Database and XML file
For database file (inventory.db) go to src folder. XML file is located in src/main/resources/faktura.xml.

There are 3 items already stored in database.

## Running the application locally

There are several ways to run application on your local machine. One way is to execute the main method in the src/main/java/nbpapi/NbpApiAppMain.java class from your IDE.

Alternatively, you can use jar file from out/artifacts/nbp_api_app_jar/nbp_api_app.jar directory. Note, that you'll need Java version 17 or higher:

In Command Line go to JAR directory and type:

```
java -jar nbp_api_app.jar

```

