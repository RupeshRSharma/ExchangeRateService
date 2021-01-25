# Scalable Capital - Exchange Rate Service

1. Exchange rate service is a Spring boot based micro service built using **Java 8**, **Spring boot 2.4.2** and **Gradle**
2. Build project on local using command (for MAC only) - **./gradlew clean build** from the respective Micro service parent directory, for eg. `cd ~\rupeshsharma\exchangerateservice`
3. Run using command **./gradlew bootRun** (alternatively, use docker build and docker run commands to run with docker)
4. Swagger UI to verify task - http://localhost:8080/swagger-ui.html#
5. Check status of application using - http://localhost:8080/actuator/health

###API

1. Calculate Reference rate - <br><br>
Sample Request - <br>
   `http://localhost:8080/1.0/exchange-rates/reference-rate?fromCurrency=USD&toCurrency=HUF` <br><br>
   Sample Response - <br>
   ``{
   "status": "SUCCESS",
   "errors": [],
   "data": {
   "currencyPair": "USD/HUF",
   "referenceRate": 0.0034,
   "link": "https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/eurofxref-graph-usd.en.html"
   }
   }``<br><br>

2. Calculate Conversion rate - <br><br>
   Sample Request - <br>
   `http://localhost:8080/1.0/exchange-rates/conversion-rate?amount=1&fromCurrency=USD&toCurrency=EUR` <br><br>
   Sample Response - <br>
   ``{
   "status": "SUCCESS",
   "errors": [],
   "data": {
   "fromCurrency": "USD",
   "toCurrency": "EUR",
   "fromAmount": 1,
   "convertedAmount": 0.8264
   }
   }``<br><br>

3. Get all Currency - <br><br>
   Sample Request - <br>
   `http://localhost:8080/1.0/exchange-rates/currencies` <br><br>
   Sample Response - <br>
   ``{
   "status": "SUCCESS",
   "errors": [],
   "data": [
   {
   "code": "HUF",
   "name": "Hungarian Forint",
   "value": 357.61,
   "timesRequested": 1
   },
   {
   "code": "EUR",
   "name": "Europe",
   "value": 1,
   "timesRequested": 1
   },
   {
   "code": "GBP",
   "name": "Pound Sterling",
   "value": 0.89,
   "timesRequested": 0
   },
   {
   "code": "USD",
   "name": "United States Dollar",
   "value": 1.21,
   "timesRequested": 2
   }
   ]
   }
   ``<br><br>

###Task:
Spring Microservice Test Assignment
The goal of this test assignment is to demonstrate an "Exchange Rate Service". The service
shall expose an API, which can be consumed by a frontend application.
Overview of your tasks
1. Create a Spring project using https://start.spring.io/.
   a. Choose only the Spring modules needed for the task and do not add
   unnecessary dependencies.
   b. Choose Java or Kotlin, Gradle or Maven, according to your preferences.
   The purpose of Spring here is to make it easy for you to create an HTTP API. If you
   are not familiar with Spring, you can use any other framework or take some time to
   learn the basics.
2. Ensure that the project builds as a standalone application.
3. Implement an API for the functionality described below.
4. (Optional) Show how to build, test, and run with docker.
   User Stories
   As a user, who accesses this service through a user interface, ...
 

1. I want to retrieve the ECB reference rate for a currency pair, e.g. USD/EUR or
   HUF/EUR.
2. I want to retrieve an exchange rate for other pairs, e.g. HUF/USD.
3. I want to retrieve a list of supported currencies and see how many times they were
   requested.
4. I want to convert an amount in a given currency to another, e.g. 15 EUR = ??? GBP
5. I want to retrieve a link to a public website showing an interactive chart for a given
   currency pair.
   The user interface is not part of this assignment.
   
Implementation hints<br>
   ● The European Central Bank publishes reference exchange rates on a daily basis.
   You can find them here:
   https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_excha
   nge_rates/html/index.en.html<br>
   ● The ECB uses EUR as the base currency. You can calculate non-EUR rates from the
   published data set.<br>
   ● You won't need a database or other external storage for the task. You can keep the
   data in memory.<br>
   ● We suggest you spend 3 to 4 hours on the task. It's OK to submit an incomplete
   solution.<br>
   ● Decide how to spend your time working towards a solution. When time is running out,
   wrap up and explain possible next steps.<br>
   ● Please email your solutions as a zip file.
<br><br>
#####Notes:
1. No security framework is present
2. Only integration tests are written
3. No of times a currency was requested, this logic has been implemented using AtomicInteger to support concurrent requests, the same can be achieved using Synchronization or Locks or Semaphores
