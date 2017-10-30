# Project description
This is an information system for a public library. The system should keep track of book collections in library departments, library members as well as individual loaned items of every member. The system should be capable of providing information about all the members and books, what a member borrowed and when, who borrowed a certain book and what condition they returned the book in. Take into account that a person can borrow multiple books in a single loan.

# Modules
-----
The project contains the following modules:
* <b>library-persistance</b> - contains entities with service and dao implementations.

# How to run the project
----
Make sure your database is running, you can set the login data in the file
```
library-persistance/src/main/resources/META-INF/persistence.xml
```
Then you can build the project simply by executing these commands from the directory where you cloned the project
```
cd the-library
mvn clean
mvn install
```
