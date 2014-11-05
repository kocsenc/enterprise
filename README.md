Paybook
==========

For SWEN343 - Enterprise Software
## Installation

Pre-requisites
* Java JDK and JRE
* gradle installed

Steps to follow
1. Locate the gradlew file in the `api/` directory

2. Make sure its executable, run `chmod +x gradlew` if necessary

3. Run `./gradlew clean install` and make sure the build passes

4. A build directory should be created on successful gradle build

5. Run `java -jar build/libs/paybook-0.1.0.jar`

6. On your browser, navigate to the index.html file located in /views/index.html


## API 

### Endpoints

#### /user/all : GET
  
Returns a collection of all users.

#### /user/{id}/requests : GET

Returns a collection of all requests for the user associated with the given id.

#### /user/{id}/payments : GET

Returns a collection of all payments for the user associated with the given id.

#### /user/{id}/pay_types : GET

Returns a collection of types of payments for the user associated with the given id.


