# Spring Boot Web Application

A Car Rental Web Application made using Spring Boot + ThymeLeaf. The purpose of this repository is to implement Software Design and Architectural Patterns. As of now, the implemented patterns are:
1. Builder [Needs reworking]
2. Command
3. Factory
4. Mediator
5. Observer

# How to Run

1. Configure Application.properties

Create an application.properties file and put it in the directory:
```/target/classes/``` 

Input the following fields:
```
spring.application.name=CarRentalApp
spring.datasource.url= "MySQL Connection String"
spring.datasource.username= "Your Username"
spring.datasource.password= "Your Password"
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= "Your Email" (Sends email confirmations)
spring.mail.password= "Your Email Password"
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
mail.from= "Your Email"
```
2. Navigate to Project Directory

Open the project in your terminal and run the command:
``` .\mvnw.cmd spring-boot:run```

This was a University project, and earned 2nd place in Semester Exibition.
