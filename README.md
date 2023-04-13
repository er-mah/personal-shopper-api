# personal-shopper-api

This web application is developed in Spring Boot framework. 

### Requirements
- Java SDK - Amazon Corretto - v18.0.1
- Maven v3.6.3

## AWS
- `./mvnw clean package`
- Upload package
- Kill build currently running:

        lsof -i tcp:6060
        kill -9 434852
        
        
- `wget https://github.com/er-mah/personal-shopper-api/releases/download/v0.5/personal-shopper-0.5.jar`
- `nohup java -jar personal-shopper-0.5.jar`