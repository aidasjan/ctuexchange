# University Exchange Application
 
## Installation
In order to run the application, the database needs to be set up.

Steps to set up the database using Docker:
1. Run `docker-compose up` to initialize the MySQL container.
2. Connect to the database and execute `/sql/init.sql` script.
3. Edit database credentials in `/src/main/resources/application.properties` if necessary.


