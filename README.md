Paybook
==========

For SWEN343 - Enterprise Software
This is a enterprise project that mimics functionality of peer to peer payment services.
It uses Sprint.io as a backend framework to build a REST API.

*This project is no longer maintained.*

## Setup and Usage

### Installation Prerequisites

**System Packages:**

* Java JDK & JRE 7

* gradle

* NodeJS

* npm

**Install the following node packages globally:**

* bower

* http-server

### Building and running the API

Locate the gradlew file in the `api/` directory. Make sure that it is executable.

    cd enterprise/api
    chmod +x gradlew

Build the gradle project

    cd enterprise/api
    ./gradlew clean build

A build directory should be created on successful gradle build. Execute the built jar file to launch the application.

    cd enterprise/api
    java -jar build/libs/paybook-0.1.0.jar

Test the enpoints for the server using http://localhost:8080


### Building and running the Angular App

Run bower install from inside the view directory:

    cd enterprise/view
    bower install

Serve the app using http-server:

    cd enterprise/view
    http-server -p 8000

### Running the Angular App and the Api

Follow the preivous steps for setting up each component.

Install `nginx`

Copy the nginx config file from the *deploy* directory

    cd enterprise
    cp deploy/paybook-nginx.conf /etc/nginx/conf.d/paybook-nginx.conf

Restart nginx

    sudo service nginx restart
