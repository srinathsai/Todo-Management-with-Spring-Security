# Todo-Management-with-Spring-Security
A TODO management developed with additional Spring security which is an extension of my previously built application called User management . 

## Motivation
In today's world as there are many works to be completed keeping track of each work's progress is a hectic and tedius job. No matter how much rememberance any individual has there is a possibility that we might forget to keep track of any work. Therefore, this project is built to address this issue and help people to have a good track of works by just performing some operations in REST APIS. 
As there will be a different todos lists for different people. There is therefore a necessity to built such that one user must not involve other user's todo list. Thus, due to this fact **Spring Security** has been included to secure the acess of todos for user and admin based on their roles.

## Requirements :-
  - Postman API
  - IntelliJ Idea
  - MySQL

## Features of Spring Security utilized :-

  - Basic Form based auntentication provided by SpringBoot.
  - Implemented role based acess feature provided by SpringBoot.
  - Changed default username and randomly generated password for the db acess.
  - Db authentication feature has been implemented.

#Initial process :-
  After extracting my codefiles and uploading them in IntelliJ Idea. You need to configure your MySQL datasorce link, you need to change the username and password to your mySQL username's and password. 

## Ways of DB authentication implemented :-
  - Based on requested api (If you want to work your todo on this style you need to include role based tags in application.properties file and need to comment out the lines in "securityFilterChain" named function in config file and comment the lines that are used by customer details object).
  - Based on Spring Data JPA (No need to change anything you can run as it is because already user ,roles and their join tables with a @Entity tag are writtten in their respective class files).

## How DB authentication works in Spring :-

