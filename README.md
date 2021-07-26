# stocks-trade
Stock Trade Project

This is an application that provides the means of managing simple stocks and trades.
This is offered as a simple web service that provides the following operations:
•	Get watchlist
•	Add a stock to watchlist
•	Remove a stock from watchlist
•	Calculate stock average for a given stock and a given interval

Requirements
Java 8
Maven 3
Design
The application is structured in layers, namely:
A controller is responsible for handling the requests and communicating with the layer service for providing a response. Here the internal domain is converted to DTOs to eliminate the coupling between the API and the internal domain.
A service is responsible for executing the business logic, here we know how to calculate stock averages.
A domain object represents the model (Stock)
A repository abstracts the underlying datastore. For the sake of the example, the underlying datastore is a local concurrent hash map.
Code structure
The application contains the main application source code and unit tests.

Build
project$ mvn clean install
Running
project$ cd stocks-trade
project$ mvn spring-boot:run (or) java -jar stocks-trade-0.0.1-SNAPSHOT

For validation purposes you can now access localhost:8080/swagger-ui.html in the corresponding endpoints in order to test the service. The following endpoints are available:

GET /watched - Get stock watchlist
POST /watched/AAPL - Add a stock to the watchlist
DELETE /watched/AAPL - Remove a stock from the watchlist

GET /stock/AAPL/d - Calculate the average stock price for stock for an interval - d(daily),w(weekly),m(monthly)
