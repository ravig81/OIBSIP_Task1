# oibsip_task1

# Online Reservation System - Java Project
## Overview
The Online Reservation System is a Java-based application that allows users to manage train reservations. It provides functionalities for users to add, delete, and view reservation records. The system interacts with a MySQL database to store and retrieve user data.

## Features
* User Authentication: Users can log in using their credentials (username and password).
* Insert Record: Users can create new reservations by entering passenger details and journey information.
* Delete Record: Users can remove existing reservations using the PNR number.
* View Records: Users can view all existing reservation records in a structured format.
## Technologies Used
* Java: Core programming language for the application.
* MySQL: Database management system for storing reservation records.
* JDBC: Java Database Connectivity for database interactions.
## Database Schema
The database, named ravig, contains a table called OnlineReservations with the following columns:

* PNR_Number: Unique identifier for each reservation.
* Passenger_Name: Name of the passenger.
* Train_Number: Identifier for the train.
* Class_Type: Class type of the reservation (e.g., Sleeper, AC).
* Journey_Date: Date of the journey.
* From_Location: Starting location of the journey.
* To_Location: Destination location of the journey.
  
## How to Run
Set Up Database:

1. Create a MySQL database named ravig.
2. Create a table named OnlineReservations with appropriate columns as per the schema above.
    * JDBC Driver: Ensure that the MySQL JDBC driver is included in your project dependencies.

3. Configuration:
   * Update the database connection URL, username, and password in the code.
4. Compile and Run:
   * Compile the Java files and run the OnlineReserSystem class.
