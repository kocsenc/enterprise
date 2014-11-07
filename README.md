Paybook
==========

For SWEN343 - Enterprise Software

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

### API Endpoints

#### /api/user/all : `GET`
  
Returns a collection of all users.

#### /api/user/{id}/requests : `GET`

Returns a collection of all requests for the user associated with the given id.

#### /api/user/{id}/payments : `GET`

Returns a collection of all payments for the user associated with the given id.

#### /api/user/{id}/pay_types : `GET`

Returns a collection of types of payments for the user associated with the given id.


