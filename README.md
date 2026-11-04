spring-boot-gwt
===============

A quickstart spring boot gwt application

## Versions

- Spring Boot 4.1.2 (embedded Tomcat 11)
- GWT 2.13.1 (org.gwtproject), gwt-maven-plugin 2.10.0
- Google Gin 2.1.2 (with Guice 6.0.0)
- Java 17 or later (tested with JDK 25), Maven 3

## Running the application

### GWT Dev Mode

    mvn gwt:run

This opens the GWT Development Mode window. Click **Launch Default Browser**
to open the app at http://localhost:8888/GwtWebApp.html. Reload the page to
recompile client changes.

Use `mvn gwt:debug` instead to wait for a remote debugger before starting.

### Packaged Spring Boot app

    mvn package
    ./run.sh

`run.sh` starts the executable war (`java -jar target/*.war`) on
http://localhost:8080/GwtWebApp.html. `test.sh` opens that URL in a browser.

The REST endpoint is at http://localhost:8080/rest/todos.

## What's in the sources

Quick dive in the code :

- fr.ekito.gwt.server for server side code
- fr.ekito.gwt.client for GWT client side code
- fr.ekito.gwt.client.controller contains Client Http Rest controller

The application is build uppon Google Gin 

## More details

More details about the project on Ekito's blog :  http://www.ekito.fr/people/?p=4816
