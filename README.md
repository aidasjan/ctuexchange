# University Exchange Application

## Overview
Application is intended to make university exchange course selection more convenient by providing course recommendations from other universities and adding them to student's course list.
It supports CRUD operations for students, universities, courses, tags, as well as many-to-many relationship for students-courses and courses-tags.
More complex query is getting total amount of credits selected by students per university.

## Installation
In order to run the application, the database needs to be set up.

Steps to set up the database using Docker:
1. Run `docker-compose up` to initialize the MySQL container.
2. Connect to the database on port 3306 using crededntials provided in `/docker-compose.yml` and execute `/sql/init.sql` script.

## Running the application

### Server Application
Server application can be started using `docker-compose up`. Port 8080 is used by server, port 3306 by database.

### Client application
Client application can be started by switching to the `/client` directory and running `npm start`. 
It requires server to be running on `http://localhost:8080`.
