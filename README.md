# Creditcard-Fullstack-App
This repo contains fullstack manage credit card application

## Creditcardapi = Rest API build using Spring boot.
	Required Technology specs :
		1)JDK 11
		2)Spring boot version : 2.6.0
		3)Maven 3.5 or above

## How to build & Run Creditcard Rest API Application
	1)Install JDK 11
	2)Install maven 3.5 or above
	3)Go to project directory ex : C:\Creditcard-Fullstack-App\creditcardapi
	4)Execute command 
		mvn clean install
		
	5)After maven builds success
	6)Go to the target directory inside the project.
		Ex : C:\Creditcard-Fullstack-App\creditcardapi\target 
	7)Execute below command to run the api
		java -jar com.publicis.sapient.creditcard.app-0.0.1-SNAPSHOT.jar


### Get All Card API
		URL : http://localhost:8080/creditcard/api/v1/getAllCards
		Content-Type : application/json

### Add Card API
		URL : http://localhost:8080/creditcard/api/v1/addCard
		Content-Type : application/json
		Json payload :
		{
			"name": "ssssss", 
			"cardNumber": "1232321",
		    "limit": "2132312"
		}

## Creditcard-ui = UI build using React.
## Required Technology specs :
		1)Node 8.11.0
		2)React : 18.2.0

## How to build & Run Creditcard-ui Application
	1)Install Node 8.11.0
	2)Go to project directory ex : C:\Creditcard-Fullstack-App\creditcard-ui
	3)Execute command 
		npm install
		
	4)After npm install done
	5)Execute below command to run the api
		npm start
	6) Application will start on URL : http://localhost:3000/creditcard-ui
