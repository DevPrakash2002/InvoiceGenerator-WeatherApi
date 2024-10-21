# Freight Fox Project

## Invoice PDF Generator

	A Spring Boot application that generates PDF invoices from JSON data using Thymeleaf templates and Flying Saucer PDF library.

## Weather Service API

	A Spring Boot application that provides weather information based on pincode and date. The service integrates with Geoapify for geocoding and OpenWeatherMap for weather data.

### Table of Contents
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Troubleshooting](#troubleshooting)
- [Dependencies](#dependencies)

### Prerequisites

	- Java JDK 17 or later
	- Maven 3.6 or later
	- Your favorite IDE (Spring Tool Suite, IntelliJ IDEA, or Eclipse)
	- Postman (for testing)

### Project Structure
	freightfox_project/
	├── src/
	│   ├── main/
	│   │   ├── java/
	|   │   │   └── com/freight/fox/
	│   │   │       ├── controller/
	│   │   │       │   └── InvoicePdfController.java
	|   |   |       |   └── WeatherController.java
	│   │   │       ├── model/
	│   │   │       │   ├── Invoice.java
	│   │   │       │   └── InvoiceItem.java
	│   │   │       │   └── GeocodeResponse.java
	│   │   │       │   └── GeocodeResult.java
	│   │   │       │   └── WeatherRequest.java
	│   │   │       │   └── WeatherResponse.java
	│   │   │       │   └── OpenWeatherResponse.java
	│   │   │       ├── service/
	│   │   │       │   └── PdfGeneratorService.java
	│   │   │       │   └── WeatherService.java
	│   │   │       ├── config/
	│   │   │       │   └── ThymeleafConfig.java
	│   │   │       └── InvoiceApplication.java
	│   │   └── resources/
	│   │       ├── templates/
	│   │       │   └── invoice-template.html
	│   │       └── application.properties
	└── pom.xml


### Installation

1. Clone the repository:

	git clone <repository-url>
	cd freightfox_project

2. Update pom.xml with the following dependencies:

	<dependencies>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-thymeleaf</artifactId>
	    </dependency>
	    <dependency>
	        <groupId>org.xhtmlrenderer</groupId>
	        <artifactId>flying-saucer-pdf</artifactId>
	        <version>9.1.22</version>
	    </dependency>
	    <dependency>
	        <groupId>org.projectlombok</groupId>
	        <artifactId>lombok</artifactId>
	        <optional>true</optional>
	    </dependency>
	</dependencies>

3. Configure API keys:

	Open src/main/resources/application.properties
	
	Update the following properties with your API keys:

	* geoapify.api.key=your_geoapify_api_key
	* openweathermap.api.key=your_openweathermap_api_key

4. Build the project:

    mvn clean install

### Configuration

1. Configure application.properties:

	propertiesCopyspring.thymeleaf.mode=HTML5
	spring.thymeleaf.encoding=UTF-8
	spring.thymeleaf.prefix=classpath:/templates/
	spring.thymeleaf.suffix=.html
	spring.thymeleaf.cache=false
	server.port=8081
	
	

2. Ensure the template file invoice-template.html is in the correct location:
	src/main/resources/templates/invoice-template.html

### Usage

1. Start the application:
	
		mvn spring-boot:run
	
2. Create a POST request in Postman:
	
#### For Invoice Generation 
	
			URL: http://localhost:8081/api/invoice/generate-pdf
	
Headers:

			CopyContent-Type: application/json
			Accept: application/pdf
	
Body (JSON):

			jsonCopy{
			    "seller": "ABC Company Ltd",
			    "sellerAddress": "123 Business Street, City, State 12345",
			    "sellerGstin": "29ABCDE1234F1Z5",
			    "buyer": "XYZ Corporation",
			    "buyerAddress": "456 Commerce Avenue, Town, State 67890",
			    "buyerGstin": "29XYZAB5678C1D4",
			    "items": [
			        {
			            "name": "Product A",
			            "quantity": 2,
			            "rate": 100.00,
			            "amount": 200.00
			        }
			    ]
			}
			
3. Send the request and save the generated PDF.
	
#### For Weather API:
	
		Body (JSON):
				{
				    "pincode": "28012",
				    "date": "2024-10-21"
				}
				

### API Documentation

#### Generate PDF Invoice

	Endpoint: POST /api/invoice/generate-pdf

Headers:

		* Content-Type: application/json
		* Accept: application/pdf

Request Body:

	json:
		{
			"seller": "string",
			"sellerAddress": "string",
			"sellerGstin": "string",
			"buyer": "string",
			"buyerAddress": "string",
			"buyerGstin": "string",
			"items": [
				{
					"name": "string",
					"quantity": number,
					"rate": number,
					"amount": number
				}
			]
		}

Response:

		* Content-Type: application/pdf
		* Status: 200 OK
		* Body: PDF file

#### For Weather API:

Request Body:

	json:
			{
			"pincode": "28012",
			"date": "2024-10-21"
			}

Response:

		* Status: 200 OK
		* Body: 
			Json :
					{
			"temperature": 292.15,
			"humidity": 65,
			"description": "scattered clouds",
			"date": "2024-10-21",
			"pincode": "28012"
			}

### Troubleshooting

1. If you get "No acceptable representation":

	* Check if the Accept header is set to "application/pdf"
	* Verify the Content-Type header is set to "application/json"


2. If you get template processing errors:

	* Verify the template file is in the correct location


3. If the PDF is not generated:

	* Check the application logs for detailed error messages
	* Verify the JSON structure matches the Invoice class

### Testing API using cURL
	
	curl -X POST http://localhost:8080/api/weather \-H "Content-Type: application/json" \-d '{"pincode": "28012", "date": "2024-10-21"}'

### Dependencies

* Spring Boot 3.x
* Thymeleaf
* Flying Saucer PDF
* Lombok
* SLF4J

### Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request


### This README provides:

1. Clear instructions for setup and installation
2. Project structure overview
3. Configuration details
4. Usage examples with Postman
5. API documentation
6. Troubleshooting guide
7. List of dependencies

