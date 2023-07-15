# MedicationApi <br> <span style="font-size: 12px">*Current version: v1*</span>




Demo project for Resilia's revision course, for Quero Ser Dev program. This application is a Rest API that serves the list of medicines of a pharmacy and the orders made by clients.


### Project Specification
* **Language**: Java 11
* **Project Manager**: Maven 3.8.7
* **Framework**: Spring Boot 2.7.13
* **Database**: MySQL


<div style="display: flex; justify-content: end">
<span>


*Other dependencies: [Check the pom.xml file](pom.xml)*


</span>
</div>


## How to Execute


To execute the project, you will need an *integrated development environment (IDE)* and a compiler. The IDE I used for this project was **IntelliJ Community Edition**. For best compatibility, use the compiler **Maven**, as specified on the [Project Specification](#project-specification).
<br>
The *main class* of this project has the name **"MedicationApiApplication"**.


## What this project has


The project is a *Rest API* that uses Java along with SpringBoot. It is structured on modules, using mainly the layers: *Controller, Service* and *Repository*. The data is being kept by a MySQL database.
<br>
As requirements for this project, it's also used *DTO*'s (Data Transfer Object) between the layers; and there is some exception handling.


### Requirements of the project


* For medications, it was required that:
    - There should be an endpoint to Create, Update and Delete a medication.
    - There must be an injectable type of medication, with different administration types. For this, I also modeled an *under prescription* type of medication.
    - There should be an endpoint to list medications, offering filtering and sorting options.


* For orders, it was required that:
    - There should be endpoints to add and remove medications from a shopping cart.
    - There should be an endpoint to complete the order on the shopping cart.


    I also implemented endpoints to get a list of orders with different criterias and to update quantities of medications.


Other requirements: it must have documentation for the database, the endpoints and how to execute the project.


## Database Schema


Following the requirement, I have made available the diagram with the database schema that was used for this project. It was modeled using **dbDiagram.io**, an online tool to make Diagrams of Entity and Relationship.


<img src="./doc/Java Proj - QSD Revision.svg"/></img>


The link for **read-only** of the diagram is also available [here](https://dbdiagram.io/d/63eeb3c0296d97641d8199a6).


## Endpoints Documentation


This project uses **springdocs-openapi** with **Swagger** to generate the documentation of the API. When running the project, you can access it using the path: http://{server:port}/swagger-ui/index.html#/ to access the documentation. Remember to change the {server:port} to the server and port you are using while running the project. The server is usually *localhost*, and the port will be defined by *TomCat*.


I also will leave the [documentation in Json format](./doc/DocumentationApi.json). So you can copy the content and paste into [Swagger Editor](https://editor.swagger.io/) to see the documentation without running the application.


## Special Thanks
To RaiaDrogasil for the opportunity on the program, to Resilia Educação for all the content teached, to Hugo for all the help along the project and to all my fellows on Quero Ser Dev program ^-^

